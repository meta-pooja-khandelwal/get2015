<!DOCTYPE html>
<%@page import="com.vehicle.user.UserType"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="styleSheet" href="./view/LoginStyle.css" type="text/css">
</head>
<body>
	<%@ page errorPage="error.jsp"%>
	<div id="wrapper">
		<h1>Login</h1>
		<form id="loginForm" action="LoginValidation" method="post">
			<table>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" class="input"
						required="required" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" class="input"
						required="required" /></td>
				<tr id="error">
				</tr>
			</table>
			<%
				String person = request.getParameter("person");
			%>
			<input type="hidden" value=<%=person%> name="person"> <input
				type="submit" value="Login" id='login' />

		</form>
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
	</div>

</body>
<script type="text/javascript" src="Script.js"></script>
</html>