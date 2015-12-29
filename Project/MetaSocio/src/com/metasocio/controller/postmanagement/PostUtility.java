package com.metasocio.controller.postmanagement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.metasocio.model.commentmanagement.Comment;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;

/**************************************************************************
 * Description : class LikeUtility , Helper or utility class for PostLoader
 **************************************************************************/
public class PostUtility {

	/*****************************************************************************
	 * This method will create DIV to be shown on load more post on home or
	 * group view
	 * 
	 * @param postMap
	 *            : contains post as key and comment list as value
	 * @param user
	 *            : currently logged in user
	 * @param likeMap
	 *            : contains boolean value.Here key is post and value is true if
	 *            logged in user has already liked on that post otherwise value
	 *            will be false
	 * @param pageTitle
	 *            : either group page or null
	 * @return
	 ******************************************************************************/
	public String createDiv(Map<Post, List<Comment>> postMap, User user,
			Map<Post, Boolean> likeMap, String pageTitle) {
		String result = "";

		if (!postMap.isEmpty()) {
			@SuppressWarnings("rawtypes")
			Iterator iterator = postMap.keySet().iterator();

			while (iterator.hasNext()) {
				Post post = (Post) iterator.next();
				List<Comment> postCommentList = null;
				postCommentList = postMap.get(post);
				result += "<div class='row  padding-small rounded-corner'"
						+ "style='background-color: ghostwhite; margin-top: 10px;'>"
						+ "<div class='col-md-2'>"
						+ "<img src='"
						+ post.getUser().getPicture()
						+ "' height='100px'"
						+ "width='100px'>"
						+ "</div>"
						+ "<div class='col-md-10'>"
						+ "<p><strong>"
						+ post.getCreatedBy()
						+ "</strong> <span style='color: orange'>Posted  &nbsp;</span><span style='font-size: small;'>" 
						+"<span data-livestamp='"+post.getDatePosted()+"'></span></span></p>" + "<h3>"
						+ "<p style='word-break: break-word;' id='post"
						+ post.getPostId() + "'>" + post.getPostDetails()
						+ "</p>";
				if (user.getUserId() == post.getUser().getUserId()) {
					result += "<input type='hidden' id='"
							+ post.getPostId()
							+ "'"
							+ "value='"
							+ post.getPostDetails()
							+ "'>"
							+ "<a  onclick='buttonPress("
							+ post.getPostId()
							+ ")' style='font-size:0.5em ;cursor: pointer'><i class='fa fa-pencil'"
							+ "id='editPostButton"
							+ post.getPostId()
							+ "'>"
							+ "</i>&nbsp;Edit</a>"
							+ "<span class='editPost"
							+ post.getPostId()
							+ "' id='editPost"
							+ post.getPostId()
							+ "' style='display: none'>"
							+ "<a class='btn-sm btn-outline-dark' href='javascript:editPost("
							+ post.getPostId()
							+ ")'>Edit Post</a> <a class='btn-sm btn-outline-dark'";

					if (pageTitle.equalsIgnoreCase("groupPage")) {
						result += "href='PostHelper?pageTitle=groupPage&action=deletePost&postId="
								+ post.getPostId() + "'>";
					} else {
						result += "href='PostHelper?action=deletePost&postId="
								+ post.getPostId() + "'>";
					}
					result += "href='PostHelper?pageTitle=groupPage&action=deletePost&postId="
							+ post.getPostId() + "'>Delete Post</a></span>";
				}

				result += "</h3>"
						+ "<div id='editDiv"
						+ post.getPostId()
						+ "' style='display: none'>"
						+ "<textarea id='editContent"
						+ post.getPostId()
						+ "' class='form-control' value='hi' style='resize: none'></textarea>"
						+ "<input type='hidden' id='postId" + post.getPostId()
						+ "' value=''>"
						+ "<button class='btn-sm btn-outline-dark'"
						+ "id='updateButton" + post.getPostId() + "'"
						+ "onclick='updatePost(" + post.getPostId()
						+ ")'>Update</button>" + "</div>";

				if (likeMap.get(post)) {

					result += "<span id='demo" + post.getPostId()
							+ "'> <a  style='color:orange;cursor: pointer;'>"
							+ " <i class='fa fa-thumbs-up' id='like"
							+ post.getPostId() + "'" + "onClick='loadInfo("
							+ post.getPostId() + ")'" + "value='"
							+ post.getPostId() + "'></i>" + "</a>"
							+ post.getLikes() + "</span>";

				} else {

					result += "<span id='demo" + post.getPostId()
							+ "'> <a style='color: grey ;cursor: pointer'> "
							+ "<i class='fa fa-thumbs-up' id='like"
							+ post.getPostId() + "'" + "onClick='loadInfo("
							+ post.getPostId() + ")'" + "value='"
							+ post.getPostId() + "' ></i></a>"
							+ post.getLikes() + "</span>";

				}

				for (Comment comment : postCommentList) {

					result += "<div class='col-md-12'>"
							+ "<div class='row rounded-corner padding-smallComment'"
							+ "id='comment' background-color:'#F6F7F8;'>"
							+ "<div class='col-md-2'>" + "<img src='"
							+ comment.getUser().getPicture()
							+ "' height='50px'" + "width='50px'></div>"
							+ "<div class='col-md-10'>" + "<p><b> <i>"
							+ comment.getCreatedBy() + "&nbsp;</b>"
							+ "<span Style='color: orange;'>Commented</span>"
							+ " </i><input type='hidden' id='commentId"
							+ comment.getCommentId() + "'" + "value='"
							+ comment.getComments() + "'>";

					if (user.getUserId() == comment.getUser().getUserId()) {

						result += "<a  onclick='commentButtonPress("
								+ comment.getCommentId()
								+ ")' style='font-size:0.75em ;cursor: pointer'>"
								+ "<i class='fa fa-pencil editCommentButton"
								+ comment.getCommentId()
								+ "'></i>&nbsp;Edit</a>"
								+ "<span class='editComment"
								+ comment.getCommentId()
								+ "' id='editComment"
								+ comment.getCommentId()
								+ "' style='display: none'> "
								+ "<a class='btn-sm btn-outline-dark' href='javascript:editComment("
								+ comment.getCommentId()
								+ ")'>Edit Comment</a>";
						if (pageTitle.equalsIgnoreCase("groupPage")) {
							result += "<a class='btn-sm btn-outline-dark' href='CommentHelper?pageTitle=groupPage&action=deleteComment&commentId="
									+ comment.getCommentId()
									+ "'>Delete Comment</a>";
						} else {
							result += "<a class='btn-sm btn-outline-dark' href='CommentHelper?action=deleteComment&commentId="
									+ comment.getCommentId()
									+ "'>Delete Comment</a>";

						}
						result += "</span>";

					}

					result += "</p><pre style='word-break: break-word;' id='comment"
							+ comment.getCommentId()
							+ "'>"
							+ comment.getComments()
							+ "</pre>"
							+ "<div id='editCommentDiv"
							+ comment.getCommentId()
							+ "' style='display: none'>"
							+ "<textarea id='editCommentContent"
							+ comment.getCommentId()
							+ "' class='form-control' value='hi' style='resize: none' required='required'></textarea>"
							+ "<input type='hidden' id='commentId"
							+ comment.getCommentId()
							+ "' value=''>"
							+ "<button class='btn-sm btn-outline-dark' id='updateCommentButton"
							+ comment.getCommentId()
							+ "'"
							+ "onclick='updateComment("
							+ comment.getCommentId()
							+ ")'>Update</button>"
							+ "</div>" + "</div>" + "</div>" + "</div>";

				}
				if (pageTitle.equalsIgnoreCase("groupPage")) {
					result += "<form action='CommentHelper?pageTitle=groupPage&action=addComment&postID="
							+ post.getPostId() + "'" + "method='post'>";
				} else {
					result += "<form action='CommentHelper?action=addComment&postID="
							+ post.getPostId() + "'" + "method='post'>";
				}
				result += "<textarea name='comment' placeholder='Add your comments here' "
						+ "class='form-control' required='required' style='resize: none'></textarea>"
						+ "<button type='submit' style='margin-top:5px;' class='btn-sm btn-outline-dark'>COMMENT</button>"
						+ "</form>" + "</div>" + "</div>";

			}
		} else {

			result = "";

		}

		return result;
	}

}
