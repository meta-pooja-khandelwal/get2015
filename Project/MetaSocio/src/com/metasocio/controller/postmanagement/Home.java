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
import com.metasocio.service.followermanagement.FollowerService;
import com.metasocio.service.likemanagement.LikeService;
import com.metasocio.service.postmanagement.PostService;
import com.metasocio.service.usermanagement.UserService;

/***************************************************************************
 * Description : Servlet implementation class Home extending HttpServlet, has
 * control of home page
 ***************************************************************************/
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*************************************************************************
	 * This method will redirect to home page with required attributes and
	 * parameters
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *************************************************************************/

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			// Getting the value from the session
			HttpSession session = request.getSession(false);
			user = (User) session.getAttribute("userObject");
			// Calling the service Layer
			UserService userService = new UserService();
			boolean isProfileComplete = userService.isEmailExists(user
					.getEmail());
			if (!isProfileComplete) {
				String message = "Please complete your profile with mandatory fields first.";
				request.setAttribute("message", message);
				request.getRequestDispatcher(
						"./view/usermanagement/profile.jsp").forward(request,
						response);
			} else {

				PostService postService = new PostService();
				// Calling the Comment service layer
				CommentService commentService = new CommentService();
				List<User> usersOfSamedepartment;
				// getting the users from the same department
				usersOfSamedepartment = userService
						.getUsersHavingSameDepartment(user);
				// Putting the post map with the comment list and post
				Map<Post, List<Comment>> postMap = new LinkedHashMap<Post, List<Comment>>();
				Map<Post, Boolean> likeMap = new LinkedHashMap<Post, Boolean>();

				int startIndex = 0;
				int maximumResult = 2;
				int groupId = 0;
				// Getting the posts with image
				List<Post> posts = postService.retrievePosts(startIndex,
						maximumResult, groupId);

				LikeService likeService = new LikeService();
				FollowerService followerService = new FollowerService();
				List<User> followersList = followerService.getFollowers(user);
				// Iterating over post
				for (Post post : posts) {
					boolean isLikedByUser = likeService.hasUSerAlreadyLiked(
							user.getUserId(), post.getPostId());
					List<Comment> comments = commentService
							.retrieveComments(post.getPostId());
					postMap.put(post, comments);
					likeMap.put(post, isLikedByUser);
				}
				request.setAttribute("followerList", followersList);
				request.setAttribute("usersList", usersOfSamedepartment);
				request.setAttribute("postMap", postMap);
				request.setAttribute("likeMap", likeMap);
				request.getRequestDispatcher("./view/postmanagement/home.jsp")
						.forward(request, response);
			}
		}

		catch (MetaSocioSystemException e) {
			request.setAttribute("message",
					"Cant show your timeline. Please try After some time.");
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
