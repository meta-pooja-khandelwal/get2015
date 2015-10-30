
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./view/SearchCarStyle.css" rel="stylesheet" />
<title>CarDekho.com</title>
</head>
<body>
	<%@ page errorPage="error.jsp"%>

	<div id="wrapper">

		<header>
		<div id="head">
			<nav> <%String person=request.getParameter("person");
				if(UserType.admin.equals(UserType.valueOf(person))){
					out.write("<input type='button' onclick=location.href='./CreateCarController'; value='Create' id='create'/>"); 
				}
				%> <input type="button"
				onclick="location.href='./VehicleHomeController?person=<%=person %>';"
				value="Home" id="home" /> <%
				if(UserType.admin.equals(UserType.valueOf(person))){
					out.write("<input type='button' onclick=location.href='VehicleController'; value='Log Out' id='logOut'/>");
				}
				%> </nav>

		</div>
		<img src="./view/download9.jpg" id="image" /> </header>
		<section>
		<h1 align="center">Search</h1>
		<hr>
		<form action="CarDekhoController">
			<h3 align="center">Company Name</h3>
			<h3 align="center">
				<select name="make">
					<option value="selectCompany" selected disabled>-Select-</option>

					<%
					   List<String> makes=null;
					   //makes=(List<String>)request.getAttribute("makes");
					   if(request.getAttribute("makes")!=null){
					         makes=(List<String>)request.getAttribute("makes");
					    	}
					   for(int i=0;i<makes.size();i++){
						   String make=makes.get(i);
						   out.write("<option value="+make+">"+make+"</option>");
					   }
					   
					    session=request.getSession(true);
					    session.setAttribute("companyList",makes);
					   %>
				</select>
			</h3>
			<h3 align="center">Enter Budget</h3>
			<h3 align="center">
				<%
				int minBudget=0;
				if(request.getAttribute("minBudget")!=null){
					 minBudget=(int)request.getAttribute("minBudget");
				}
				
				int maxBudget=0;
				if(request.getAttribute("maxBudget")!=null){
					maxBudget=(int)request.getAttribute("maxBudget");
					
				}
				//System.out.println(minBudget);
				//int minBudget=Integer.parseInt(minBudgetString);
				
				//int maxBudget=Integer.parseInt(minBudgetString);
				%>
				<input type="number" style="width: 40%; text-align: center;"
					name="budget" placeholder="Enter Budget" min="<%=minBudget%>"
					max="<%=maxBudget%>" required="required"> <input
					type="hidden" value="<%=person %>" name="person"> <input
					type="hidden" value="<%=minBudget %>" name="minBudget"> <input
					type="hidden" value="<%=maxBudget %>" name="maxBudget">
			</h3>
			<br>
			<h3 align="center">
				<input type="submit" value="Search" />
			</h3>
		</form>
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
		</section>
		<aside> <img src="./view/download13.png" id="asideImage">
		</aside>
		<footer>

		<h4 align="center" style="color: white;">All Rights Reserved
			&copy 2015</h4>

		</footer>

	</div>
</body>
</html>