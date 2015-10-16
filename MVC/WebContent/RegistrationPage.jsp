<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@page import="com.models.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<!-- HTML page title -->
<title>Registration</title>
<!-- link of RegistrationStyle.css file -->
<link rel="styleSheet" type="text/css" href="RegistrationPageStyle.css ">
</head>

<body>

	<%
		Object obj = request.getAttribute("employee");

		int employeeId = 0;
		String name = "";
		String email = "";
		int age = 0;
		String message = "";
		java.util.Date date = new Date();
		if (obj instanceof Employee) {
			Employee employee = (Employee) obj;
			employeeId = employee.getEmployeeID();
			name = employee.getName();
			email = employee.getEmail();
			age = employee.getAge();
			date = employee.getDateOfRegistration();
			System.out.println(date);
		} else {
			//System.out.println(request.getAttribute("newUserId"));

			employeeId = (Integer) request.getAttribute("newUserId");
			//request.setAttribute("employeeId", employeeId);
			//System.out.println(employeeId);

		}
	%>

	<!-- HTML semantic tag -->
	<section>

		<!-- HTML header -->
		<h1>Registration Form</h1>

		<form action="/MVC_Session-1/Validation?employeeId=<%=employeeId%>"
			method="Post">
			<!-- Table For Data To Symmetric -->
			<table>
				<!-- ID-->
				<tr>
					<td><span class="span">*</span>Employee ID</td>
					<td><input type="number" name="id" value="<%=employeeId%>"
						required="required" readonly="readonly"></td>
				</tr>

				<!--  Name -->
				<tr>
					<td><span class="span">*</span>Name</td>
					<td><input type="text" name="name" value="<%=name%>"
						required="required"></td>
				</tr>

				<!--  Email -->
				<tr>
					<td><span class="span">*</span>Email ID</td>
					<td><input type="email" name="email" value="<%=email%>"
						required="required"></td>
				</tr>
				<!--  Age Field -->
				<tr>
					<td><span class="span">*</span>Age</td>
					<td><input type="number" name="age" value="<%=age%>"
						required="required"></td>
				</tr>
				<!-- Date -->
				<tr>
					<td><span class="span">*</span>Registration Date</td>
					<td colspan="2" align="right"><input type="date" name="date"
						value="<%=date%>" required="required"></td>
				</tr>

				<!-- check box -->
				<tr>
					<td colspan="2"><input type="checkbox" id="checkbox"
						onchange="activateSubmit()">I accept all terms and
						conditions.</td>
				</tr>

				<!-- Submission Button -->
				<tr>
					<td colspan="2" align="right"><input type="submit"
						disabled="disabled" id="submit"></td>
				</tr>
			</table>
		</form>
		<p align="center"
			style="font-size: 40px; font-weight: bold; color: red;">${message}</p>
	</section>
	<script type="text/javascript">
		function activateSubmit() {
			var checkbox = document.getElementById("checkbox");
			var submit = document.getElementById("submit");
			if (checkbox.checked == true) {
				submit.disabled = false;
			} else {
				submit.disabled = true;
			}
		}
	</script>
</body>

</html>