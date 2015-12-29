package com.metasocio.controller.groupmanagement;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.groupmanagement.GroupService;
import com.metasocio.service.usermanagement.UserService;

/******************************************************************
 * Class that has control of group related functions such as delete group,browse
 * group,join group,leave group,create group etc. Servlet implementation class
 * GroupHelper
 *****************************************************************/

@WebServlet("/GroupHelper")
public class GroupHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**************************************************************************
	 * This method has functionality to delete,browse,join and leave the group
	 * and then redirect to either Home or GroupHome Controller based on page
	 * title
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		// getting attribute from current HTTP session
		User userObject = (User) session.getAttribute("userObject");
		// creating object of UserService
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("delete")) {
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			GroupService groupService = new GroupService();
			try {
				Group group = groupService.getGroupById(groupId);
				if (group.getUser().getUserId() == userObject.getUserId()) {
					group.setIsdelete(1);
					Date date=new Date();
					Timestamp updatedAt=new Timestamp(date.getTime());
					group.setUpdatedAt(updatedAt);
					group.getUsersSet().clear();
					groupService.updateGroup(group);
				}
				response.sendRedirect("Home");
			} catch (MetaSocioSystemException e) {

				request.setAttribute("message","Group can't be deleted now. Please try after some time.");
				request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
			}
		}  else if (action.equalsIgnoreCase("getRemainingGroups")) {

			User user = (User) session.getAttribute("userObject");
			UserService iUserService = new UserService(); // create group of UserService class
			GroupService iGroupService=new GroupService();// create group of GroupService class

			try {	
				User member = iUserService.getUserByEmail(user.getEmail());		
				List<Group> groupList = iGroupService.getAllGroups();
				List<Group> groupListInWhichUserIsNotMember = iGroupService.getGroupsInWhichUserIsNotMember(member, groupList);
				request.setAttribute("listTitle", "Groups List");
				request.setAttribute("groupList", groupListInWhichUserIsNotMember);
				request.getRequestDispatcher("./view/followermanagement/followers.jsp").forward(request, response);				
			} catch (MetaSocioSystemException e) {
				request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
			}
			
		} else if (action.equalsIgnoreCase("joinGroup")) {

			Group group = new Group();
			User newMember = new User();
			UserService userService = new UserService();

			GroupService groupService = new GroupService();

			try {

				if (request.getParameter("pageTitle") != null
						&& request.getParameter("pageTitle").equalsIgnoreCase(
								"groupPage")) {
					group = (Group) session.getAttribute("groupObject");
					int userId = Integer.parseInt(request.getParameter("userId"));
					newMember = userService.getUserById(userId);
				} else {
					newMember = (User) session.getAttribute("userObject");
					int groupId = Integer.parseInt(request.getParameter("groupId"));
					group = groupService.getGroupById(groupId);
				}

				group.getUsersSet().add(newMember);
				groupService.updateGroup(group);

				response.sendRedirect("GroupHome?groupId=" + group.getGroupId());

			} catch (MetaSocioSystemException e) {

				request.setAttribute("message",
						"You can't join the group!! Please try after sometime");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		} else if (action.equalsIgnoreCase("leaveGroup")) {

			int groupId = Integer.parseInt(request.getParameter("groupId"));
			GroupService groupService = new GroupService();
			try {
				Group group = groupService.getGroupById(groupId);
				Set<User> membersSet = group.getUsersSet();
				group.getUsersSet().clear();
				groupService.updateGroup(group);
				group.setUsersSet(membersSet);
				groupService.updateGroup(group);
				response.sendRedirect("Home");

			} catch (MetaSocioSystemException e) {
				request.setAttribute("message",
						"Can't leave the group now ! Please try after sometime");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}

		}

		else {
			request.setAttribute("message",
					"The page you are looking for cannot be accessed now.");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);
		}

	}

	/**************************************************************************
	 * This method has functionality to create the group and then redirect to
	 *  GroupHome Controller 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User userObject = (User) session.getAttribute("userObject"); // Getting user from session attribute
		String action = request.getParameter("action");
		String groupTitle = (String) request.getParameter("groupName"); // Getting group title of new group
		GroupService groupService = new GroupService();
		if (action.equalsIgnoreCase("create")) {
			try {
				Group group = new Group(); // creating group instance
				// Setting group info
				group.setGroupName(groupTitle);
				group.setImageUrl("assets/img/groupPhoto.png");
				Date date = new Date();
				group.setUser(userObject);
				group.setCreatedBy(userObject.getName());
				group.setCreatedAt(date);
				group.setUpdatedAt(new Timestamp(date.getTime()));
				Set<User> groupMembersSet = new HashSet<User>();
				groupMembersSet.add(userObject);
				group.setUsersSet(groupMembersSet);
				
				int groupId = groupService.createGroup(group); // Creating new group
				Group myGroup = groupService.getGroupById(groupId); // getting group info by group id
				session.setAttribute("groupObject", myGroup); // setting attribute (group) in session
				response.sendRedirect("GroupHome?groupId=" + groupId); // redirecting to group page

			} catch (MetaSocioSystemException e) {
				request.setAttribute("message",
						"Can't create group now. Please try after some time......");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		}

	}

}
