<%@page import="java.util.Map"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="com.models.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registered Employees List</title>
</head>

<body>

	<%
		out.write("list");
		String id = request.getParameter("id");
		Map<Integer, Employee> employeesList = (Map<Integer, Employee>) request
				.getAttribute("employeesList");

		for (int i = 1; i <= employeesList.size(); i++) {
			Employee employee = employeesList.get(i);

			out.println(employee.getRegisteredEmployees());
			out.write("<br><a  href='/MVC_Session-1/ViewDetailsController?id="
					+ employee.getEmployeeID()
					+ "'> View Employee details </a>");

			out.write("<br><a  href='/MVC_Session-1/EditEmployeeDetailsController?id="
					+ employee.getEmployeeID()
					+ "'>Edit Employee details </a><br>");
			out.write("<br>");
		}
	%>

</body>

</html>