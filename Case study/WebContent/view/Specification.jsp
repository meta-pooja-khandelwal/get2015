<%@page import="com.sun.org.apache.bcel.internal.generic.NEW"%>
<%@page import="com.vehicle.model.Car"%>
<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Specifications</title>
<link rel="styleSheet" type="text/css"
	href="./view/SpecificationStyle.css">
</head>
<body>
	<%@ page errorPage="error.jsp"%>
	<div class="wrapper">
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
		<header> <%
		String person=request.getParameter("person");
		Car car=null;
		if(request.getAttribute("car")!=null){
			car=(Car)request.getAttribute("car");
		}
		%> <img src="./view/logo.png" alt="car dekho logo" width="400px"
			height="100px" align="left"> <input type="button"
			onclick="location.href='VehicleHomeController?person=<%=person %>';"
			value="Home" id="home" /> <%
			if(UserType.admin.equals(UserType.valueOf(person))){
				out.write("<input type='button' onclick=location.href='./VehicleController' value='Log out' id='logOut' />");
				out.write("<input type='button' onclick=location.href='./CreateCarController' value='Create Car' id='createCar' />");
			}
			%> <input type="button"
			onclick="location.href='./SearchCarController?person=<%=person%>'"
			value="Search Car" id="searchCar" /> </header>
		<section>
		<div id="heading">
			<h3>Specifications</h3>
		</div>
		<table>
			<tr>
				<td>ID :</td>
				<td><%=car.getCarId() %></td>
			</tr>

			<tr>
				<td>Make :</td>
				<td id="make"><%=car.getMake() %></td>
			</tr>

			<tr>
				<td>Model :</td>
				<td id="model"><%=car.getModel() %></td>
			</tr>

			<tr>
				<td>Engine In Cc :</td>
				<td id="engineInCc"><%=car.getEngineInCc() %></td>
			</tr>

			<tr>
				<td>Fuel Capacity :</td>
				<td id="fuelCapacity"><%=car.getFuelCapacity() %></td>
			</tr>

			<tr>
				<td>Mileage :</td>
				<td id="mileage"><%=car.getMileage() %></td>
			</tr>

			<tr>
				<td>AC :</td>
				<td id="ac"><%=car.getAc()%></td>
			</tr>

			<tr>
				<td>Accessory Kit :</td>
				<td id="accessorykit"><%=car.getAccessorykit() %></td>
			</tr>

			<tr>
				<td>Power Steering :</td>
				<td id="powerSteering"><%=car.getPowerSteering() %></td>
			</tr>

			<tr>
				<td>Show Room Price :</td>
				<td id="showRoomPrice"><%=car.getShowRoomPrice() %></td>
			</tr>

			<tr>
				<td>roadTax :</td>
				<td id="roadTax"><%=car.getRoadTax() %></td>
			</tr>

			<tr>
				<td>onRoadPrice :</td>
				<td id="onRoadPrice"><%=car.getOnRoadPrice()%></td>
			</tr>

			<tr>
				<td>Created time:</td>
				<td id="createdTime"><%=car.getCreatedTime() %></td>
			</tr>

			<tr>
				<td>Created By:</td>
				<td id="createdBy"><%=car.getCreatedBy() %></td>
			</tr>
		</table>
		</section>
		<footer> <label>Copyright &copy 2015.&nbsp All rights
			reserved.</label> </footer>
	</div>
</body>
</html>