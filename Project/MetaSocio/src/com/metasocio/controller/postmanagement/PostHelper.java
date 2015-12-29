package com.metasocio.controller.postmanagement;

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
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.postmanagement.PostService;

/***************************************************************************
 * Description : Servlet implementation class PostHelper extending HttpServlet,
 * it has a control of all the post related functions such as share post,edit
 * post,delete post
 ***************************************************************************/

@WebServlet("/PostHelper")
public class PostHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**************************************************************************
	 * This method has functionality to delete the post and then redirect to
	 * either Home or GroupHome Controller based on page title
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PostService postService = new PostService();
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("userObject");
		if (action.equalsIgnoreCase("deletePost")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			try {
				Date date = new Date();
				Timestamp updatedAt = new Timestamp(date.getTime());
				postService.deletePostByPostId(postId, user.getUserId(),updatedAt);
				if (request.getParameter("pageTitle") != null
						&& request.getParameter("pageTitle").equalsIgnoreCase("groupPage")) {
					Group group = (Group) session.getAttribute("groupObject");
					response.sendRedirect("GroupHome?groupId="+ group.getGroupId());
				} else {
					response.sendRedirect("Home");
				}
			} catch (MetaSocioSystemException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", "Can't delete post");
				request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message","The page you are trying to access is not available");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);
		}
	}

	/****************************************************************************
	 * This method has functionality to share the post,edit post based on action
	 * value retrieved from request and then to either Home or GroupHome
	 * Controller based on page title
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *****************************************************************************/

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PostService postService = new PostService();
		String action = request.getParameter("action");

		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User) session.getAttribute("userObject");
		if (action.equalsIgnoreCase("addPost")) {
			String post = request.getParameter("post");
			try {
				int groupId;
				if ((request.getParameter("groupId") != null)) {
					groupId = Integer.parseInt(request.getParameter("groupId"));
				} else {
					groupId = 0;
				}
				Post newPost = new Post();
				Date date = new java.util.Date();
				newPost.setDatePosted(date);
				newPost.setCreatedBy(user.getName());
				newPost.setUpdatedAt(new Timestamp(date.getTime()));
				newPost.setGroupId(groupId);
				newPost.setUser(user);
				String postDetails="";
				for (int i = 0; i < (post.length()); i++) {
					if (post.charAt(i) == '<') {
						postDetails += "&lt;";
					} else if (post.charAt(i) == '>') {
						postDetails += "&gt;";
					} else if (post.charAt(i) == '/') {
						postDetails += "///";
					//}// else if (post.charAt(i) == 's') {
						//postDetails += "s&#8232";
					} else {
						postDetails += String.valueOf(post.charAt(i));
					}
				}

				newPost.setPostDetails(postDetails);

				postService.savePost(newPost);

				if (request.getParameter("pageTitle") != null
						&& request.getParameter("pageTitle").equalsIgnoreCase("groupPage")) {
					response.sendRedirect("GroupHome?groupId=" + groupId);
				} else {
					response.sendRedirect("Home");
				}
			} catch (MetaSocioSystemException e) {
				request.setAttribute("message", "Can't share the post");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		} else if (action.equalsIgnoreCase("editPost")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			String postDetails = request.getParameter("postContent");
			try {
				Date date = new Date();
				Timestamp updatedAt = new Timestamp(date.getTime());
				postService.editPostByPostId(postId, user.getUserId(),updatedAt, postDetails);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(postDetails);
			} catch (MetaSocioSystemException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", "Can't update the post");
				request.getRequestDispatcher("./exception/error.jsp").forward(
						request, response);
			}
		}

		else {
			request.setAttribute("message",
					"The page you are trying to access is not available");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);

		}

	}

}
