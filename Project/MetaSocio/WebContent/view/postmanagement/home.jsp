<!DOCTYPE html>
<%@page import="com.metasocio.model.groupmanagement.Group"%>
<html lang="en">
<%@ include file="../header.html"%>
<%@ include file="../scripts.html"%>
<%@page import="com.metasocio.model.postmanagement.Post"%>
<%@page import="com.metasocio.model.commentmanagement.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<body id="page-top">
	<%@ page session="false"%>
	<%@ page errorPage="../../exception/error.jsp"%>
	<%@include file="../navbar.jsp"%>
	<%
				User userObject = new User();
				userObject = (User) session.getAttribute("userObject");
			%>
	<section id="about"
		style="background-color: #E9EAED; margin-top: 20px;">
		<div class="container-fluid">
			<div class="row"
				style="background-color: ghostwhite; border-bottom: 1px solid orange">
				
				<!-- image section -->
				
				<div class="col-lg-2 col-sm-12 text-center">
					<img align="middle" src="<%=userObject.getPicture()%>"
						class="img-responsive rounded-corners" id="profilePhoto"
						width="200px;" style="border-radius: 10px;"> <br> <label><%=userObject.getName()%>&nbsp;&nbsp;&nbsp;</label>
					<br> <a href="UserProfile" data-toggle="tooltip"
						title="Edit Profile" style="text-align: center;"><span
						class="fa fa-pencil-square ">&nbsp;Edit Profile</span></a> <a
						href="PhotoUploader" data-toggle="tooltip" title="Edit Image"
						style="text-align: center;"><span class="fa fa-pencil-square ">&nbsp;Edit
							Image</span></a>
				</div>
				
				
				
				<!-- post section -->
				<form action="PostHelper?action=addPost" method="post">
					<div class="col-lg-7 wow fadeIn">
						<div
							class="form-group col-xs-12 col-lg-12 floating-label-form-group controls"
							style="border-color: orange;">
							<label>What's on your mind ? </label>
							<textarea class="form-control"
								placeholder="What's on your mind ? " name="post"
								style="background-color: transparent; resize: none"
								required="required"></textarea>
						</div>
						<div class="col-md-12 form-group text-center"
							style="margin-top: 10px;">
							<button type="submit" class="btn btn-outline-dark ">
								<i class="fa fa-share"></i>&nbsp;Share Your Status
							</button>
						</div>
					</div>
				</form>
				
				
				
				<!-- suggestions -->
				<div class="col-md-2 col-sm-6 col-lg-3 wow fadeIn"
					data-wow-delay=".8s" id="result"
					style="background-color: whitesmoke; border-radius: 10px;">
					<div class="about-content text-center">
						<i class="fa fa-user-plus fa-2x
						"></i>
						<h3>Suggested People</h3>
						<%
							// String email =(String) request.getAttribute("email");
						%>
						<%
							List<User> usersOfSameDepartment = new ArrayList<User>();
							usersOfSameDepartment = (List<User>) request
									.getAttribute("usersList");
							if (usersOfSameDepartment.size() == 1) {
						%>
						<div class="col-md-12 col-lg-2 col-sm-12 col-xs-12">No
							Suggestions</div>
						<%
							} else {
								int number = 1;
								for (User userOfSameDepartment : usersOfSameDepartment) {
									if (!userOfSameDepartment.getEmail().equalsIgnoreCase(
											userObject.getEmail())) {
										if (number > 2) {
											break;
										} else {
											number++;
						%>
						<div class="row" style="margin-top: 20px;">
							<div class="col-md-6"><%=userOfSameDepartment.getName()%></div>
							<div class="col-md-6">
								<a class="btn-sm btn-outline-dark"
									href="FollowPeople?followingId=<%=userOfSameDepartment.getUserId()%>">FOLLOW</a>
							</div>
						</div>
						<%
							}
									}
								}
							}
						%>
					</div>
				</div>
			</div>
			
			<!-- second section -->
			<div class="row">
				<div class="col-md-6 col-sm-6 col-lg-2 wow fadeIn"
					data-wow-delay=".2s"
					style="margin: 10px 0px; border-radius: 10px; padding-bottom: 20px">
					<div class="about-content">
						<h3>Groups</h3>
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12"
							style="margin-bottom: 20px;">
							<button class="btn btn-outline-dark " style="width: 100%;"
								data-toggle="modal" data-target="#createGroup">
								Create<br> Group
							</button>
						</div>
						<div class="col-md-12">
							<a href="GroupHelper?action=getRemainingGroups"
								class="btn btn-outline-dark" style="width: 100%">Browse<br>
								Group
							</a>
						</div>
					</div>
				</div>
				
				
				<!-- post section -->
				<div class="col-md-8 col-sm-8 col-lg-7 wow fadeIn"
					data-wow-delay=".4s">
					<div id="postLoader">
						<%
							Map<Post, List<Comment>> postMap = (Map) request
									.getAttribute("postMap");
							Map<Post, Boolean> likeMap = (Map) request.getAttribute("likeMap");
							String postAvailabilityIndicator = null;
							if (!postMap.isEmpty()) {
								Iterator iterator = postMap.keySet().iterator();
								while (iterator.hasNext()) {
									Post post = (Post) iterator.next();
									List<Comment> commentList = null;
									commentList = postMap.get(post);
						%>
						<div class="row  padding-small rounded-corner"
							style="background-color: ghostwhite; margin-top: 10px;">
							
							<!-- image in post -->
							<div class="col-md-2">
								<img src="<%=post.getUser().getPicture()%>" height="100px"
									width="100px">
							</div>
							<div class="col-md-10">
								<p>
									<strong> <%=post.getCreatedBy()%></strong> <span
										style="color: orange">Posted &nbsp;</span><span
										style="font-size: small;"><span
										data-livestamp="<%=post.getDatePosted()%>"></span></span>
								</p>
								<h3>
									
									
									<!-- post is getting printed here -->
									<p style="word-break: break-word;"
										id="post<%=post.getPostId()%>"><%=post.getPostDetails()%></p>
									<%
										if (userObject.getUserId() == post.getUser().getUserId()) {
									%>
									<input type="hidden" id="<%=post.getPostId()%>"
										value="<%=post.getPostDetails()%>"> <a
										onclick="buttonPress(<%=post.getPostId()%>)"
										style="font-size: 0.5em; cursor: pointer"><i
										class="fa fa-pencil" id="editPostButton<%=post.getPostId()%>">
									</i>&nbsp;Edit</a> <span class="editPost<%=post.getPostId()%>"
										id="editPost<%=post.getPostId()%>" style="display: none">
										<a class="btn-sm btn-outline-dark"
										href='javascript:editPost(<%=post.getPostId()%>)'>Edit
											Post</a> <a class="btn-sm btn-outline-dark"
										href='PostHelper?action=deletePost&postId=<%=post.getPostId()%>'>Delete
											Post</a>
									</span>
									<%
										}
									%>
								</h3>
								<div id="editDiv<%=post.getPostId()%>" style="display: none">
									<textarea id="editContent<%=post.getPostId()%>"
										class="form-control" value="hi" style="resize: none"></textarea>
									<input type="hidden" id="postId<%=post.getPostId()%>" value="">
									<button class="btn-sm btn-outline-dark"
										id="updateButton<%=post.getPostId()%>"
										onclick="updatePost(<%=post.getPostId()%>)">Update</button>
								</div>
								<%
									if (likeMap.get(post)) {
								%>
								<span id="demo<%=post.getPostId()%>"> <a
									style="color: orange; cursor: pointer"> <i
										class="fa fa-thumbs-up" id="like<%=post.getPostId()%>"
										onClick="loadInfo(<%=post.getPostId()%>)"
										value="<%=post.getPostId()%>"></i></a> <%=post.getLikes()%>
								</span>
								<%
									} else {
								%>
								<span id="demo<%=post.getPostId()%>"> <a
									style="color: grey; cursor: pointer"> <i
										class="fa fa-thumbs-up" id="like<%=post.getPostId()%>"
										onClick="loadInfo(<%=post.getPostId()%>)"
										value="<%=post.getPostId()%>"></i></a> <%=post.getLikes()%>
								</span>
								<%
									}
								%>
								<%
									for (Comment comment : commentList) {
								%>
								<div class="col-md-12">
									<div class="row rounded-corner" id="comment"background-color: #F6F7F8;">
										<div class="col-md-2">
											<img src="<%=comment.getUser().getPicture()%>" height="50px"
												width="50px">
										</div>
										<div class="col-md-10">
											<p>
												<b> <i><%=comment.getCreatedBy()%>&nbsp;</b><span
													Style="color: orange;">Commented</span> </i><input
													type="hidden" id="commentId<%=comment.getCommentId()%>"
													value="<%=comment.getComments()%>">
												<%
													if (userObject.getUserId() == comment.getUser()
																		.getUserId()) {
												%>
												<a onclick="commentButtonPress(<%=comment.getCommentId()%>)"
													style="font-size: 0.75em; cursor: pointer;"> <i
													class="fa fa-pencil editCommentButton<%=comment.getCommentId()%>"></i>&nbsp;Edit
												</a> <span id="editComment<%=comment.getCommentId()%>"
													class="editComment<%=comment.getCommentId()%>"
													style="display: none"> <a
													class="btn-sm btn-outline-dark"
													href='javascript:editComment(<%=comment.getCommentId()%>)'>Edit
														Comment</a> <a class="btn-sm btn-outline-dark"
													href='CommentHelper?action=deleteComment&commentId=<%=comment.getCommentId()%>'>Delete
														Comment</a>
												</span>
												<%
													}
												%>
											</p>
											
											<!-- Comment printing section -->
											<pre style="word-break: break-word;"
												id="comment<%=comment.getCommentId()%>"><%=comment.getComments()%></pre>
											<div id="editCommentDiv<%=comment.getCommentId()%>"
												style="display: none">
												<textarea id="editCommentContent<%=comment.getCommentId()%>"
													class="form-control" style="resize: none"
													required="required"></textarea>
												<input type="hidden"
													id="commentId<%=comment.getCommentId()%>" value="">
												<button class="btn-sm btn-outline-dark"
													id="updateCommentButton<%=comment.getCommentId()%>"
													onclick="updateComment(<%=comment.getCommentId()%>)">Update</button>
											</div>
										</div>
									</div>
								</div>
								<%
									}
								%>
								<form
									action="CommentHelper?action=addComment&postID=<%out.println(post.getPostId());%>"
									method="post">
									<textarea name="comment" placeholder="Add your comments here"
										class="form-control" required="required" style="resize: none"></textarea>
									<button style="margin-top: 5px;" type="submit"
										class=" btn-sm btn-outline-dark">COMMENT</button>
								</form>
							</div>
						</div>
						<%
							}
						%>
						<%
							postAvailabilityIndicator = "Posts available";
							} else {
						%>
						<div class="row text-center">
							<div class="col-md-12">
								<h3>There are no post to show.</h3>
							</div>
						</div>
						<%
							}
						%>
					</div>
					<%
						if (postAvailabilityIndicator != null) {
					%>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-12 text-center">
							<a href="javascript:loadPosts(<%=0%>,'homePage')"
								class="btn-sm btn-outline-dark text-center" id="loadMore">LOAD
								MORE</a>
						</div>
					</div>
					<%
						}
					%>
				</div>
				<div class="col-md-3 col-sm-6 col-lg-3 wow fadeIn"
					data-wow-delay=".8s" id="result">
					<div class="about-content text-center">
						<i class="fa fa-users fa-2x"></i>
						<h3>People Following You</h3>
						<%
							List<User> followerList = (List<User>) request
									.getAttribute("followerList");
							for (User followerListIteration : followerList) {
						%>
						<div class="col-md-12">
							<p><%=followerListIteration.getName()%></p>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div id="createGroup" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Create Group</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6">
							<form action="GroupHelper?action=create" method="post"
								class="form" role="form">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="name" class="control-label">Group Name</label> <input
												type="text" name="groupName" id="groupName"
												class="form-control" placeholder="Enter Group Name" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<input type="Submit"
											class="btn btn-success col-lg-offset-2 col-lg-8"
											Value="Create Group">
									</div>
								</div>
							</form>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<img class="img-responsive" src="assets/img/groupPhoto.png">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
