package com.metasocio.controller.followermanagement;

import java.io.IOException;
import java.util.List;

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

/*************************************************************************
 * Servlet implementation class Following, has control of following
 ************************************************************************/
@WebServlet("/Following")
public class Following extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Following() {
		super();
	}

	/****************************************************************************************
	 * To get following list (list of users followed by present user ) on view
	 * this servlet will be called to get following list
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *****************************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User userObject = (User) session.getAttribute("userObject"); // Getting user object from session
		FollowerService followerService = new FollowerService(); // Creating instance of follower Service
		UserService userService = new UserService();
		try {
			/*
			 * Checks user in the database ,if already exists then redirect to
			 * following  view otherwise redirect to profile view to complete his
			 * profile
			 */
			boolean isProfileComplete = userService.isEmailExists(userObject
					.getEmail());
			if (!isProfileComplete) {

				String message = "Please complete your profile with mandatory fields first.";
				request.setAttribute("message", message);
				request.getRequestDispatcher(
						"./view/usermanagement/profile.jsp").forward(request,
						response);
			} else {
				int userId = userObject.getUserId(); // getting id of present user
				List<User> followingList = followerService.getFollowing(userId); // Getting list of followings of present user
				request.setAttribute("listTitle", "Following");
				request.setAttribute("usersList", followingList);
				request.getRequestDispatcher("./view/followermanagement/followers.jsp").forward(
						request, response);
			}

		} catch (MetaSocioSystemException e) {
			request.setAttribute("message", "Can't find following");
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
