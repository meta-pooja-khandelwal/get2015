package com.metasocio.controller.followermanagement;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.followermanagement.FollowerService;
import com.metasocio.service.usermanagement.UserService;

/*************************************************************************************
 * This class provides control to following person , this Servlet implementation
 * class FollowPeople
 *************************************************************************************/
@WebServlet("/FollowPeople")
public class FollowPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowPeople() {
		super();
	}

	/********************************************************************************************
	 * This method is adding a person to user's following list
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *******************************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			HttpSession session = request.getSession(false);
			// getting attribute from current HTTP session
			User follower = (User) session.getAttribute("userObject");
			// creating object of UserService class
			UserService userService = new UserService();
			int followingId = (Integer.parseInt(request.getParameter("followingId")));
			// calling getUserById method existing in UserService class
			User following = userService.getUserById(followingId);
			FollowerService followerService = new FollowerService();
			// getting all users in following set
			Set<User> followingsSet = follower.getUsers();
			followingsSet.add(following);
			follower.setUsers(followingsSet);
			followerService.addFollowing(follower);
			String title = (String) request.getParameter("title");
			// if title is null then redirects to Home controller otherwise redirects to Followers controller
			if (title != null) {
				if (title.equalsIgnoreCase("Followers")) {
					response.sendRedirect("Followers");
				}
			} else {
				response.sendRedirect("Home");
			}
		} catch (MetaSocioSystemException e) {

			request.setAttribute("message",
					"You can't follow the person now! Please try after sometime");
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
		doGet(request, response);
	}

}
