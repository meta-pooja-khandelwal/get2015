package com.metasocio.controller.commentmanagement;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.commentmanagement.Comment;
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.commentmanagement.CommentService;

/**************************************************************************
 * Description : Servlet implementation class CommentHelper extending
 * HttpServlet, it has a control of all the comment related functions 
 * such as share comment,edit comment,delete comment
 ***************************************************************************/

@WebServlet("/CommentHelper")
public class CommentHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**************************************************************************
	 * This method has functionality to delete the comment and then redirect to
	 * either Home or GroupHome Controller based on page title
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("deleteComment")) {
			try {
				HttpSession session = request.getSession(false);// getting HTTP session
				User user = (User) session.getAttribute("userObject");
				// getting comment id of comment to be deleted from home or group page
				int commentId = Integer.parseInt(request.getParameter("commentId"));
				CommentService commentService = new CommentService();// creating instance of class
				Date date = new Date();
				Timestamp updatedAt = new Timestamp(date.getTime());
				// deleting comment by calling deleteCommentByCommentId() method exists in CommentService class 
				commentService.deleteCommentByCommentId(commentId,user.getUserId(), updatedAt);
				/*
				 * if pageTitle retrieved from request is group page then redirect 
				 * to GroupHome controller else redirects to Home controller
				 */
				if (request.getParameter("pageTitle") != null && request.getParameter("pageTitle").equalsIgnoreCase("groupPage")) {
					Group group = (Group) session.getAttribute("groupObject");// getting group object from current HTTP session
					response.sendRedirect("GroupHome?groupId="+ group.getGroupId());// redirecting user to GroupHome controller
				} else {
					response.sendRedirect("Home");// redirecting user to Home controller
				}
			} catch (MetaSocioSystemException e) {
				String errorMessage = "Can't delete comment";
				request.setAttribute("message", errorMessage);
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("message",
					"The page you are trying to access is not available");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);
		}

	}

	/******************************************************************************************
	 * This method has functionality to share the comment,edit comment based on action value 
	 * retrieved from request and then to either Home or GroupHome Controller based on page title
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *******************************************************************************************/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);// getting HTTP session
		User user = new User();
		user = (User) session.getAttribute("userObject");
		CommentService commentService = new CommentService(); // getting object of CommentService class
		if (action.equalsIgnoreCase("addComment")) {
			String comment = request.getParameter("comment");
			// getting postId, to get on which post commented
			String postID = request.getParameter("postID");

			try {

				session.setAttribute("userObject", user);
				Comment newComment = new Comment();// creating object of Comment class
				Post post = new Post();// creating object of Post class
				post.setPostId(Integer.parseInt(postID));// setting postId to object of Post class named post
				// setting information of comment to object of Comment class named newComment
				newComment.setPost(post);
				Date date = new java.util.Date();
				newComment.setDateCommented(date);
				newComment.setUpdatedAt(new Timestamp(date.getTime()));
				newComment.setCreatedBy(user.getName());
				newComment.setUser(user);
				String commentDetails="";
				for (int i = 0; i < (comment.length()); i++) {
					if (comment.charAt(i) == '<') {
						commentDetails += "<&#8232";
					} else if (comment.charAt(i) == '>') {
						commentDetails += "&#8232>";
					} else if (comment.charAt(i) == '/') {
						commentDetails += "/&#8232";
					} else if (comment.charAt(i) == 's') {
						commentDetails += "s&#8232";
					} else {
						commentDetails += String.valueOf(comment.charAt(i));
					}
				}
	
				newComment.setComments(commentDetails);
				// calling share method existing in CommentService
				commentService.shareComment(newComment);
				if (request.getParameter("pageTitle") != null
						&& request.getParameter("pageTitle").equalsIgnoreCase("groupPage")) {

					Group group = (Group) session.getAttribute("groupObject");

					response.sendRedirect("GroupHome?groupId="
							+ group.getGroupId());
				} else {
					response.sendRedirect("Home");// redirect to Home
				}
			} catch (MetaSocioSystemException e) {
				request.setAttribute("message",
						"Could not share post,Please try After some time.");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		} else if (action.equalsIgnoreCase("editComment")) {
			try {
				int commentId = Integer.parseInt(request.getParameter("commentId")); // Getting comment id which is to edit
				String commentDetails = request.getParameter("commentContent"); // edited comment details
				Date date = new Date();
				Timestamp updatedAt = new Timestamp(date.getTime());
				// calling comment service class to perform edit comment operation
				commentService.editCommentByCommentId(commentId,commentDetails, user.getUserId(), updatedAt); 
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(commentDetails); // setting comment  details to response
			} catch (MetaSocioSystemException e) {
				request.setAttribute("message", "Can't update the comment");
				request.getRequestDispatcher("./exception/error.jsp").forward(request, response); // Forwarding to error page
			}
		} else {
			request.setAttribute("message","The page you are trying to access is not available");
			request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
		}

	}

}
