<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@page import="com.metasocio.model.groupmanagement.Group"%>
<html lang="en">
<%@ include file="../header.html"%>
<%@ include file="../scripts.html"%>
<body id="page-top">
	<%@ page session="false"%>
	<%@ page errorPage="../../exception/error.jsp"%>
	<%@ include file="../navbar.jsp"%>
	<section class="bg-gray">
		<div class="container-fluid">
			<div class="row text-center">
				<div class="col-lg-12 wow fadeIn">
					<hr class="colored">
					<h1>${listTitle}</h1>
				</div>
			</div>
			<div class="row text-center">
				<!-- title of the page i.e follower/following/group. -->
				<% String title = (String) request.getAttribute("listTitle");
           if(title.equalsIgnoreCase("Groups")){
				List<Group> groupsList = new ArrayList<Group>();
				groupsList = (List<Group>) request.getAttribute("groupList");
				 if(groupsList.isEmpty()){
	            	  %>
					<h3>
						My group list is empty
						</h3>
				<%   }else{
				for (Group group : groupsList) {

		%>
				<div class="col-md-2 col-sm-3 wow fadeIn" data-wow-delay=".2s">
					<div class="about-content">
						<img src="assets/img/groupPhoto.png" class="img-responsive">
						<h3><%=group.getGroupName()%></h3>
						<a href="GroupHome?groupId=<%=group.getGroupId()%>"
							class="btn btn-outline-dark">Open</a>
					</div>
				</div>
				<% 		}
					}}
           else	if (title.equalsIgnoreCase("Groups List")) {
				List<Group> remainingGroupList = new ArrayList<Group>();
				remainingGroupList = (List<Group>) request.getAttribute("groupList");
				 if(remainingGroupList.isEmpty()){
	            	  %>
					<h3>
						No more group available
						</h3>
				<%   }
				 else{
				for (Group group : remainingGroupList) {
		%>
				<div class="col-md-2 col-sm-3 wow fadeIn" data-wow-delay=".2s">
					<div class="about-content">
						<img class="img-responsive" src="assets/img/groupPhoto.png">
						<h3><%=group.getGroupName()%></h3>
						<a
							href="GroupHelper?action=joinGroup&groupId=<%=group.getGroupId()%>&title=<%=title%>"
							class="btn btn-outline-dark">Join</a>
					</div>
				</div>
				<%
					}
				 }	
					}else {
            List<User> userList = new ArrayList<User>();
            	userList = (List<User>) request.getAttribute("usersList");
            List<User> usersWhomYoAreNotFollowing = (List<User>) request.getAttribute("followersWhomYouAreNotFollowing");
            if(userList.isEmpty()){
            	  %>
					<h3>
						No
						<%=title %></h3>
				<%   }
            for(User user : userList){
            	%>
				<div class="col-md-2 col-sm-3 wow fadeIn" data-wow-delay=".2s">
					<div class="about-content">
						<img class="img-responsive" src=<%=user.getPicture() %> width="200px">
						<h3><%=user.getName() %></h3>
						<%
                      if(title.equalsIgnoreCase("followers")){
                      for(User userWhoIsNotFollowing : usersWhomYoAreNotFollowing){
                    	  if(userWhoIsNotFollowing.getUserId() == user.getUserId()){                  	                    	
                      		%>
						<a
							href="FollowPeople?followingId=<%=user.getUserId()%>&title=<%=title %>"><button
								class="btn btn-outline-dark">Follow</button></a>
						<%-- <a href="AddFriend?friendId=<%=user.getUserId()%>" class="btn btn-outline-dark" >Follow</a> --%>
						<%
                      	}
                      }}
                      	else if(title.equalsIgnoreCase("following")){
                      		%>
						<a href="Unfollow?followingId=<%=user.getUserId() %>"
							class="btn btn-outline-dark">Unfollow</a>
						<%
                      		}
                      %>
					</div>
				</div>
				<%          	
            } }          
            %>
			</div>
		</div>
	</section>
</body>
</html>
