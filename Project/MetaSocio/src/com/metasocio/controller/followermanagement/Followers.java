package com.metasocio.controller.followermanagement;

import java.io.IOException;
import java.io.PrintWriter;
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

/****************************************************************
 * Servlet implementation class Followers,has control of followers
 ***************************************************************/
@WebServlet("/Followers")
public class Followers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**********************************************************************************************
	 * To get followers list (list of users who are following the present user) on view this
	 * 							 servlet will be called  to get followers list
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *********************************************************************************************/
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false); //getting current HTTP session
		User userObject = (User)session.getAttribute("userObject");
		UserService userService = new UserService();
		try {
			
			/*Checks user in the database, if already exists then redirect to followers 
			 * view otherwise redirect to profile view to complete his profile*/
		boolean isProfileComplete = userService.isEmailExists(userObject.getEmail()); 
		if(!isProfileComplete){
			String message = "Please complete your profile with mandatory fields first.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/usermanagement/profile.jsp").forward(request, response);	
		}
		else{
				FollowerService followerService = new FollowerService();	//Creating instance of follower Service
				List<User> followersList = followerService.getFollowers(userObject);	//Getting followers list
				List<User> followersWhomYouAreNotFollowing = followerService	//Getting list of followers whom present user does not follows
						.getUsersWhomYouAreNotFollowing(userObject.getUserId(),followersList);
				
				//Setting attribute to request
				request.setAttribute("followersWhomYouAreNotFollowing",	followersWhomYouAreNotFollowing);		
				request.setAttribute("usersList", followersList);
				request.setAttribute("listTitle", "Followers");
				PrintWriter out= response.getWriter();
				out.write("Successfully retrieved followers.");
				//Forwarding to followers list page
				request.getRequestDispatcher("./view/followermanagement/followers.jsp").forward(request,response);	
			
		}
		} catch (MetaSocioSystemException e) {
			request.setAttribute("message", "Can't find followers");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);		//Forwarding to error page if any exception occurred
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
