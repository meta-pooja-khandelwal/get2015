<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="styleSheet" href="./view/VehicleHomeStyle.css"
	type="text/css">
</head>

<body>
	<%@ page errorPage="error.jsp"%>
	<div id="wrapper">
		<header> <img class="firstImage" src="./view/download9.jpg" />
		<img class="firstImage secondImage" src="./view/download14.jpg" /> </header>
		<div id="container">
			<nav>
			<ul>

				<% 
				   String person=request.getParameter("person");
				   if(UserType.admin==UserType.valueOf(person)){
					
					 out.write("<li id='createCarLink'><a href='./CreateCarController'>Create Car</a></li>");  
				   }
				   %>

				<li><a href="./SearchCarController?person=<%=person%>">Search
						Car</a></li>
			</ul>

			<%
				   if(UserType.admin==UserType.valueOf(person)){
					 out.write("<input type='button' onclick=location.href='./VehicleController' value='LogOut' id='logOut' />");  
				   }
				 %> </nav>
			<article> <%@include file="article.jsp"%>
			</article>
			<aside> <img id="asideImage" src="./view/download13.png">
			</aside>
		</div>
	</div>
</body>
</html>