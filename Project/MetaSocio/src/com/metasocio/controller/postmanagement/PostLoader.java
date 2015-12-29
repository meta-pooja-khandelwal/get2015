package com.metasocio.controller.postmanagement;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.commentmanagement.Comment;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.commentmanagement.CommentService;
import com.metasocio.service.likemanagement.LikeService;
import com.metasocio.service.postmanagement.PostService;

/***************************************************************************
 * Description : Servlet implementation class PostLoader extending HttpServlet,
 * it has a control of pagination on home and group view to show posts
 ***************************************************************************/

@WebServlet("/PostLoader")
public class PostLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**************************************************************************
	 * This method has functionality to get the posts with comments list for
	 * each post from database on AJAX call
	 * 
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int startIndex = Integer.parseInt(request.getParameter("startIndex"));

		int groupId = Integer.parseInt(request.getParameter("groupId"));
		String pageTitle = request.getParameter("pageTitle");
		int maxResult = 2;
		try {

			PostService postService = new PostService();
			CommentService commentService = new CommentService();
			Map<Post, List<Comment>> postMap = new LinkedHashMap<Post, List<Comment>>();
			List<Post> postList = null;
			List<Comment> commentList = null;
			postList = postService.retrievePosts(startIndex, maxResult,groupId);
			Map<Post, Boolean> likeMap = new LinkedHashMap<Post, Boolean>();
			LikeService likeService = new LikeService();
			User user = new User();
			HttpSession session = request.getSession(false);
			user = (User) session.getAttribute("userObject");
			for (Post post : postList) {
				boolean isLikedByUser = likeService.hasUSerAlreadyLiked(user.getUserId(), post.getPostId());
				likeMap.put(post, isLikedByUser);
				commentList = commentService.retrieveComments(post.getPostId());
				postMap.put(post, commentList);
			}

			String result = "";
			PostUtility postUtility = new PostUtility();
			result = postUtility.createDiv(postMap, user, likeMap, pageTitle);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);

		} catch (MetaSocioSystemException e) {
			request.setAttribute("message", "[" + e.getMessage() + "]");
			request.getRequestDispatcher("./exception/error.jsp").forward(
					request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServ }
	 * 
	 *      /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
