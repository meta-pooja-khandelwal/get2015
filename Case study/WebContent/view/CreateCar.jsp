<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Car</title>
<link rel="styleSheet" type="text/css" href="./view/CreateCarStyle.css">
</head>
<body>
	<%@ page errorPage="error.jsp"%>

	<div class="wrapper">
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
		<header> <img src="./view/logo.png" alt="car dekho logo"
			width="400px" height="100px" align="left"> <input type="button"
			onclick="location.href='./VehicleController'" value="Log Out"
			id="logOut" /> <!-- ------------------------------------------------- -->
		<input type="button"
			onclick="location.href='./SearchCarController?person=<%=UserType.admin%>'"
			value="Search Car" id="searchCar" /> <input type="button"
			onclick="location.href='./VehicleHomeController?person=<%=UserType.admin%>'"
			value="Home" id="goHome" /> </header>
		<form action="CreateCar">
			<div id="heading">
				<h2>CREATE CAR</h2>
			</div>

			<section> <label class="left"><span id="span">*</span>Make</label>
			<input class="input" type="text" placeholder="Company Name"
				pattern="[a-zA-Z ]+" name="make" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Model</label> <input
				class="input" type="text" name="model" placeholder="Model Name"
				pattern="[a-zA-Z_]+" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>EngineInCC</label> <input
				class="input" type="number" name="engineInCc"
				placeholder="engineInCc" min="500" max="4000" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>FuelCapacity</label> <input
				class="input" type="number" name="fuelCapacity"
				placeholder="fuelCapacity" min="25" max="200" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Mileage</label> <input
				class="input" type="number" name="mileage" placeholder="Mileage"
				min="1" max="100" required="required">
			<br>
			<br>
			<br>

			<label class="left">AC</label>
			<div class="input">
				<input type="radio" name="ac" value="true">Available <input
					type="radio" name="ac" value="false">Not Available
			</div>
			<br>
			<br>
			<br>

			<label class="left">Accessory Kit</label> <input class="input"
				type="text" name="accessorykit" placeholder="Accessory Kit"
				pattern="[a-zA-Z_ ,]+"> <br>
			<br>
			<br>

			<label class="left">Power Steering</label>
			<div class="input">
				<input type="radio" name="powerSteering" value="true">Available
				<input type="radio" name="powerSteering" value="false">Not
				Available
			</div>
			<br>
			<br>
			<br>
			<label class="left"><span id="span">*</span>Show Room Price</label> <input
				class="input" type="number" name="showRoomPrice"
				placeholder="Show Room Price" min="100000" max="100000000"
				required="required">
			<br>
			<br>
			<br>

			<br>
			<label class="left"><span id="span">*</span>Created Time</label> <input
				class="input" type="date" name="createdTime" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Created By</label> <input
				class="input" type="text" name="createdBy" placeholder="Created By"
				pattern="[a-zA-Z ]+" required="required">
			<br>
			<br>
			<br>



			<label class="left"><span id="span">*</span>Upload Image</label> <input
				class="input" type="file" name="imagePath"
				placeholder="Upload Image" required="required">
			<br>
			<br>
			<br>

			<input class="button" type="submit" value="Create"> </section>

		</form>
		<footer> <label>Copyright &copy 2015.&nbsp All rights
			reserved.</label> </footer>
</body>
</html>