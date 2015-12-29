package com.metasocio.controller.likemanagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.likemanagement.LikeService;

/**************************************************************************
 * Description : Servlet implementation class LikeManager extending HttpServlet,
 * Managing likes regarding operations
 **************************************************************************/
@WebServlet("/LikeManager")
public class LikeManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**********************************************************************
	 * constructor for this class invoking super class also
	 * 
	 * @see HttpServlet#HttpServlet()
	 *********************************************************************/
	public LikeManager() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/*****************************************************************************************
	 * This function is called when user makes a post request
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ****************************************************************************************/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String postID = request.getParameter("postID"); // getting parameter from request

		// int groupId = Integer.parseInt(request.getParameter("groupId"));
		User user = new User(); // Creating instance of 'User' POJO
		try {
			String likesUpdated = null;
			HttpSession session = request.getSession(false); // Checking for session
			user = (User) session.getAttribute("userObject"); // Getting session attribute (user info)
			LikeService likeService = new LikeService(); // Creating instance of 'LikeService' class
			likeService.manageLikes(user, Integer.parseInt(postID));
			// calling function of LikeService class to manage whole like operations
			int noOfLikes = likeService.LikesOnPostByPostId(Integer
					.parseInt(postID));
			String likes = Integer.toString(noOfLikes);

			boolean isLikedByUser = likeService.hasUSerAlreadyLiked(
					user.getUserId(), Integer.parseInt(postID));
			
			LikeUtility likeUtility=new LikeUtility();
			
			likesUpdated=likeUtility.createTag(isLikedByUser,postID,likes);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(likesUpdated);// redirecting to caller
			/* } */} catch (MetaSocioSystemException e) {
			request.setAttribute("message",
					"Error in Getting likes, Please try After some time."); // setting an attribute  in  request
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response); // forwarding to error page if any exception occurs
		}
	}
}
