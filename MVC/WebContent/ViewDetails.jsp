<%@page import="java.util.Map"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="com.models.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
</head>

<body>

	<%
		Employee employee = (Employee) request.getAttribute("employee");
		out.println("<p>Name :" + employee.getName() + "<br></p>");
		out.println("<p>Email :" + employee.getEmail() + "<br></p>");
		out.println("<p>Employee Id :" + employee.getEmployeeID()
				+ "<br></p>");
		out.println("<p>Age :" + employee.getAge() + "<br></p>");
		out.println("<p>Date of registration :"
				+ employee.getDateOfRegistration() + "<br></p>");
		out.write("<a style='float: left;margin-left:20px;' href='/MVC_Session-1/LandingPage.html'>Home</a>");
		out.write("<a style='float: left;margin-left:20px;' href='/MVC_Session-1/RegistrationPageController'>Registration</a>");
	%>

</body>

</html>