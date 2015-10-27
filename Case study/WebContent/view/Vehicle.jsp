<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="styleSheet" href="VehicleHomeStyle.css" type="text/css">
<title>Vehicle</title>
</head>
<body>
	<%@ page errorPage="error.jsp"%>
	<p align="center"
		style="color: red; font-size: 20px; font-weight: bold;">${param.message}</p>
	<div id="wrapper">
		<header> <%@include file="header.jsp"%>
		</header>
		<div id="container">
			<nav>
			<ul>


				<li><a
					href="/Case_Study_Session-4/VehicleHomeController?person=<%=UserType.user%>">User</a>
				</li>



				<li><a
					href="/Case_Study_Session-4/LoginController?person=<%=UserType.admin%>">Admin</a>

				</li>
			</ul>
			</nav>
			<article> <%@include file="article.jsp"%>
			</article>

			<aside> <%@include file="aside.jsp"%>
			</aside>
		</div>
	</div>

</body>
</html>