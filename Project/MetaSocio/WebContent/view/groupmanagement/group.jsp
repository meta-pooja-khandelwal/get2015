<!DOCTYPE html>
<%-- <%@page import="sun.misc.FpUtils"%> --%>
<%@page import="com.metasocio.model.postmanagement.Post"%>
<%@page import="com.metasocio.model.commentmanagement.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@page import="com.metasocio.model.groupmanagement.Group"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<html lang="en">
<%@include file="../header.html"%>
<%@include file="../scripts.html"%>
<body id="page-top">
	<%@ page session="false"%>
	<%@ page errorPage="../../exception/error.jsp"%>
	<%@include file="../navbar.jsp"%>
	<%User userObject=new User();
        userObject=(User)session.getAttribute("userObject");
        Group groupObject = (Group)request.getAttribute("groupObject");
        session.setAttribute("groupObject", groupObject); %>
	<section id="about"
		style="background-color: #E9EAED; margin-top: 20px;">
		<div class="container-fluid">
			<div class="row rounded-corner"
				style="background-color: ghostwhite; border-bottom: 1px solid orange">
				<div class="col-lg-2">
					<img src="assets/img/groupPhoto.png"
						class="img-responsive rounded-corners" id="profilePhoto"
						width="200px;"> <br> <label><%=groupObject.getGroupName() %>&nbsp;&nbsp;&nbsp;</label>
					<%if(groupObject.getUser().getUserId() != userObject.getUserId()){ %>
					<br><a class="page-scroll btn-sm btn-outline-dark"
						href="GroupHelper?action=leaveGroup&groupId=<%=groupObject.getGroupId()%>">Leave
						Group</a>
					<%}else{ %>
					<br><a class="page-scroll btn-sm btn-outline-dark"
						href="GroupHelper?action=delete&groupId=<%=groupObject.getGroupId()%>">Delete
						Group</a>
					<%} %>
				</div>
				<form
					action="PostHelper?action=addPost&groupId=<%=groupObject.getGroupId()%>&pageTitle=groupPage"
					method="post">
					<div class="col-lg-7 wow fadeIn">
						<h1 class="text-center"><%=groupObject.getGroupName() %>
							&nbsp;&nbsp;&nbsp;
							<!-- <span class="fa fa-pencil"></span></a> -->
						</h1>
						<div
							class="form-group col-xs-12 col-lg-12 floating-label-form-group controls"
							style="border: 1px solid orange;">
							<label>What's on your mind ? </label>
							<textarea class="form-control"
								placeholder="What's on your mind ? " name="post"
								style="background-color: transparent; resize: none"
								required="required"></textarea>
						</div>
						<div class="col-md-12 form-group text-center"
							style="margin-top: 10px;">
							<button type="submit" class="btn btn-outline-dark ">
								<i class="fa fa-share"></i>&nbsp;Share It On the Group
							</button>
						</div>
					</div>
				</form>
				<div class="col-md-2 col-sm-6 col-lg-3 wow fadeIn"
					data-wow-delay=".8s" id="result"
					style="background-color: whitesmoke; border-radius: 10px;">
					<div class="about-content text-center">
						<i class="fa fa-user-plus fa-2x"></i>
						<h3>Suggested People</h3>
						<%
                        User suggestedUser = (User) request.getAttribute("suggestedUser");
                        if(suggestedUser!=null){
                        %>
						<div class="row">
							<div class="col-md-3">
								<img src="<%=suggestedUser.getPicture()%>"
									class="img-responsive">
							</div>
							<div class="col-md-9"><%=suggestedUser.getName()%></div>
							<a class="btn btn-sm btn-outline-dark"
								href="GroupHelper?action=joinGroup&userId=<%=suggestedUser.getUserId()%>&pageTitle=groupPage">Add</a>
						</div>
						<% }else{
                        	%>
						<div class="row">
							<div class="col-md-12">No Suggestions Available</div>
						</div>
						<% 
                        } %>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6 col-lg-2 wow fadeIn"
					data-wow-delay=".2s">
					<div class="about-content"></div>
				</div>
				<div class="col-md-8 col-sm-8 col-lg-7 wow fadeIn"
					data-wow-delay=".4s">
					<div id="postLoader">
						<% 	
					Map<Post, List<Comment>> postMap  = (Map)request.getAttribute("postMap"); 
				 Map<Post, Boolean> likeMap  = (Map)request.getAttribute("likeMap");  
				 String postAvailabilityIndicator = null;
					//Map<Integer,String> imageMapForPostedUsers = (Map)request.getAttribute("imageMapForPostedUsers"); 
					//Map<Integer,String> imageMapForCommentedUsers = (Map)request.getAttribute("imageMapForCommentedUsers"); 
					if(!postMap.isEmpty())
					{
						 Iterator iterator = postMap.keySet().iterator();
					   while (iterator.hasNext()) {
					      Post post = (Post) iterator.next();
					      Boolean isLikedByUser = likeMap.get(post);
					      List<Comment> commentList = null;
					      commentList = postMap.get(post);
					     %>
						<form id="editForm" method="post" hidden="hidden">
							<div class="row text-center">
								<input type="hidden" id="postId" value=""> <input
									type="hidden" id="commentId" value="">
								<div class="col-lg-8 wow fadeIn">
									<div
										class="form-group col-xs-12 col-lg-12 floating-label-form-group controls"
										style="border-color: orange;">
										<label>Edit</label>
										<textarea class="form-control" placeholder="Edit"
											id="editContent" style="background-color: transparent"
											required="required"></textarea>
									</div>
								</div>
								<div class="col-lg-7 wow fadeIn">
									<div class="form-group col-xs-12">
										<input type="submit" class="btn btn-outline-dark "
											value="Edit" />
									</div>
								</div>
							</div>
						</form>
						<div class="row  padding-small rounded-corner"
							style="background-color: ghostwhite; margin-top: 10px;">
							<div class="col-md-2">
								<img src="<%=post.getUser().getPicture() %>" height="100px"
									width="100px">
							</div>
							<div class="col-md-10">
								<p>
									<strong> <%=post.getCreatedBy()%></strong> <span
										style="color: orange">Posted </span><span
										style="font-size: 0.75em"><span
										data-livestamp="<%=post.getDatePosted() %>"></span></span>
								</p>
								<h3>
									<p style="word-break: break-word;"
										id="post<%=post.getPostId()%>"><%=post.getPostDetails() %></p>
									<%if(userObject.getUserId()==post.getUser().getUserId()){ %>
									<input type="hidden" id="<%=post.getPostId()%>"
										value="<%=post.getPostDetails()%>"> <a
										style="font-size: 0.5em; cursor: pointer"><i
										class="fa fa-pencil " id="editPostButton<%=post.getPostId()%>"
										onclick="buttonPress(<%=post.getPostId()%>)">&nbsp;EDIT</i></a> <span
										class="editPost<%=post.getPostId() %>" style="display: none">
										<a class="btn-sm btn-outline-dark"
										href='javascript:editPost(<%=post.getPostId()%>)'>Edit
											Post</a> <a class="btn-sm btn-outline-dark"
										href='PostHelper?action=deletePost&pageTitle=groupPage&postId=<%=post.getPostId()%>'>Delete
											Post</a>
									</span>
									<%
									}
								%>
								</h3>
								<div id="editDiv<%=post.getPostId()%>" style="display: none">
									<textarea id="editContent<%=post.getPostId()%>"
										class="form-control" required="required" style="resize: none"></textarea>
									<input type="hidden" id="postId<%=post.getPostId()%>" value="">
									<button class="btn-sm btn-outline-dark"
										id="updateButton<%=post.getPostId()%>"
										onclick="updatePost(<%=post.getPostId()%>)">Update</button>
								</div>
								<%
								if(isLikedByUser){
								%>
								<div id="demo<%=post.getPostId()%>">
									<a> <i class="fa fa-thumbs-up"
										id="like<%=post.getPostId()%>"
										onClick="loadInfo(<%=post.getPostId()%>)"
										value="<%=post.getPostId()%>"
										style="color: orange; cursor: pointer"></i></a>
									<%=post.getLikes()%>
								</div>
								<%}else{
								%>
								<div id="demo<%=post.getPostId()%>">
									<a> <i class="fa fa-thumbs-up"
										id="like<%=post.getPostId()%>"
										onClick="loadInfo(<%=post.getPostId()%>)"
										value="<%=post.getPostId()%>"
										style="color: grey; cursor: pointer"></i></a>
									<%=post.getLikes()%>
								</div>
								<%-- <a href="LikeController?postID=<%out.println(post.getPostId()); %>" class="button"> --%>
								<% } 
							for(Comment comment : commentList){  %>
								<div class="col-md-12">
									<div class="row rounded-corner padding-smallComment"
										id="comment">
										<div class="col-md-2">
											<img src="<%=comment.getUser().getPicture()%>" height="50px"
												width="50px">
										</div>
										<div class="col-md-10">
											<p>
												<b> <i><%=comment.getCreatedBy()%><span
														style="color: orange;"> &nbsp;Commented</span></i></b>
												<%if(userObject.getUserId()==comment.getUser().getUserId()){ %>
												<a style="font-size: 0.75em; cursor: pointer"><i
													class="fa fa-pencil editCommentButton<%=comment.getCommentId()%>"
													onclick="commentButtonPress(<%=comment.getCommentId()%>)">&nbsp;EDIT</i></a>
												<span class="editComment<%=comment.getCommentId() %>"
													style="display: none"> <a
													class="btn-sm btn-outline-dark"
													href='javascript:editComment(<%=comment.getCommentId()%>)'>Edit
														Comment</a> <a class="btn-sm btn-outline-dark"
													href='CommentHelper?action=deleteComment&pageTitle=groupPage&commentId=<%=comment.getCommentId()%>'>Delete
														Comment</a>
												</span>
												<%} %>
											
											<div id="editCommentDiv<%=comment.getCommentId() %>"
												style="display: none">
												<textarea
													id="editCommentContent<%=comment.getCommentId() %>"
													class="form-control" style="resize: none"
													required="required"></textarea>
												<input type="hidden"
													id="commentId<%=comment.getCommentId() %>" value="">
												<button class="btn-sm btn-outline-dark"
													id="updateCommentButton<%=comment.getCommentId()%>"
													onclick="updateComment(<%=comment.getCommentId()%>)">Update</button>
											</div>
											<input type="hidden"
												id="commentId<%=comment.getCommentId()%>"
												value="<%=comment.getComments()%>">
											<pre style="word-break: break-word;"
												id="comment<%=comment.getCommentId() %>"><%=comment.getComments() %></pre>
										</div>
									</div>
								</div>
								<%} %>
								<form
									action="CommentHelper?action=addComment&postID=<%out.println(post.getPostId());%>&pageTitle=groupPage"
									method="post">
									<textarea name="comment" placeholder="Add your comments here"
										class="form-control" required="required" style="resize: none"></textarea>
									<button type="submit" style="margin-top: 5px;"
										class="btn-sm btn-outline-dark">COMMENT</button>
								</form>
							</div>
							<div class="col-md-12"></div>
						</div>
						<%} %>
						<%postAvailabilityIndicator = "Posts available";
						}
					else{
						%>
						<div class="row text-center">
							<div class="col-md-12">
								<p>There are no post to show.</p>
							</div>
						</div>
						<%
					}
					%>
					</div>
					<%if(postAvailabilityIndicator!=null){ %>
					<div class="row">
						<div class="col-md-12 text-center">
							<a
								href="javascript:loadPosts(<%=groupObject.getGroupId() %>,'groupPage')"
								style="margin-top: 10px;"
								class="btn-sm btn-outline-dark text-center" id="loadMore">LOAD
								MORE</a>
						</div>
					</div>
					<%} %>
				</div>
				<div class="col-md-3 col-sm-6 col-lg-3 wow fadeIn"
					data-wow-delay=".8s" id="result">
					<div class="about-content text-center">
						<i class="fa fa-users fa-2x"></i>
						<h3>group members</h3>
						<%
                        			    Set<User> groupMemberList = groupObject.getUsersSet();
                        			    if(!groupMemberList.isEmpty()){
                        			    for(User groupMember : groupMemberList){
                                    			   %>
						<div class="col-md-12">
							<div class="col-md-3">
								<img src="<%=groupMember.getPicture()%>" class="img-responsive">
							</div>
							<div class="col-md-5">
								<%if(groupMember.getUserId() == userObject.getUserId())
                        	{%>
								You
								<%}
                        	else
                        	{ %>
								<small><%=groupMember.getName()%></small>
								<%}
                        			    %>
							</div>
							<div class="col-md-4">
								<%
                        			    if(groupMember.getUserId() == groupObject.getUser().getUserId()){
                        			    	%>
								<img src="assets/img/Admin.png" class="img-responsive hidden-sm"
									alt="Admin">
								<% 
                        			    }
                        			    %>
							</div>
						</div>
						<br>
						<br>
						<br>
						<%}} %>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
