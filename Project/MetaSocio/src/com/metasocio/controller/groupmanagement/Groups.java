package com.metasocio.controller.groupmanagement;

import java.io.IOException;
import java.util.List;

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

/**************************************************************************
 * class that has control to show group list, Servlet implementation class
 * Groups
 *************************************************************************/
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Groups() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*************************************************************************
	 * It will redirect to profile page if logged in user has not created his
	 * profile otherwise redirect to groups list view
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// getting existing session
		HttpSession session = request.getSession(false);
		// getting object from session
		User userObject = (User) session.getAttribute("userObject");
		// creating object of UserService
		UserService userService = new UserService();
		// creating object of GroupService
		GroupService groupService = new GroupService();
		try {
			
			/*Checks user in the database, if already exists then redirect to group  
			 * list view otherwise redirect to profile view to complete his profile*/
			boolean isProfileComplete = userService.isEmailExists(userObject
					.getEmail());
			if (!isProfileComplete) {

				String message = "Please complete your profile with mandatory fields first.";
				request.setAttribute("message", message);
				request.getRequestDispatcher(
						"./view/usermanagement/profile.jsp").forward(request,
						response);
			} else {
				List<Group> myGroupList = groupService.getMyGroups(userObject
						.getUserId());

				request.setAttribute("listTitle", "Groups");
				request.setAttribute("groupList", myGroupList);
				request.getRequestDispatcher(
						"./view/followermanagement/followers.jsp").forward(
						request, response);
			}

		}

		catch (MetaSocioSystemException e) {
			request.setAttribute("message",
					"Can't show your groups list! Please try after sometime");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
