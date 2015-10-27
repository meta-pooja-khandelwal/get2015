<%@page import="com.vehicle.user.UserType"%>
<%@page import="com.vehicle.model.Car"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Car</title>
<link rel="styleSheet" type="text/css" href="./view/EditCarStyle.css">
</head>

<body>
	<%@ page errorPage="error.jsp"%>
	<div class="wrapper">
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
		<header> <%
 	String person = request.getParameter("person");
 	Car car = new Car();
 	if (request.getAttribute("car") != null) {
 		car = (Car) request.getAttribute("car");
 	}
 %> <img src="./view/logo.png" alt="car dekho logo" width="400px"
			height="100px" align="left"> <input type="button"
			onclick="location.href='VehicleHomeController?person=<%=person%>';"
			value="Home" id="home" /> <%
 	if (UserType.admin.equals(UserType.valueOf(person))) {
 		out.write("<input type='button' onclick=location.href='./VehicleController' value='Log out' id='logOut' />");
 		out.write("<input type='button' onclick=location.href='./CreateCarController' value='Create Car' id='createCar' />");
 	}
 %> <input type="button"
			onclick="location.href='./SearchCarController?person=<%=person%>'"
			value="Search Car" id="searchCar" /> </header>

		<form action="./EditCar">
			<div id="heading">
				<h2>EDIT CAR SPECIFICATIONS</h2>
			</div>
			<input type="hidden" value="<%=person%>" name="person" />
			<section> <label class="left"><span id="span">*</span>Car
				Id</label> <input class="input" type="number" placeholder="Car Id"
				name="carId" value="<%=car.getCarId()%>" required="required"
				readonly="readonly">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Make</label> <input
				class="input" type="text" placeholder="Company Name"
				pattern="[a-zA-Z ]+" name="make" value="<%=car.getMake()%>"
				required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Model</label> <input
				class="input" type="text" name="model" placeholder="Model Name"
				pattern="[a-zA-Z_]+" value="<%=car.getModel()%>"
				required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>EngineInCC</label> <input
				class="input" type="number" name="engineInCc"
				placeholder="engineInCc" min="500" max="4000"
				value="<%=car.getEngineInCc()%>" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>FuelCapacity</label> <input
				class="input" type="number" name="fuelCapacity"
				placeholder="fuelCapacity" min="25" max="200"
				value="<%=car.getFuelCapacity()%>" required="required">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Mileage</label> <input
				class="input" type="number" name="mileage" placeholder="Mileage"
				min="1" max="100" value="<%=car.getMileage()%>" required="required">
			<br>
			<br>
			<br>

			<label class="left">AC</label>
			<div class="input">
				<%
					String ac = car.getAc();
				%>

				<input type="radio" name="ac" value="true"
					<%if (ac.equalsIgnoreCase("true")) {%> checked <%}%>>Available
				<input type="radio" name="ac" value="false"
					<%if (ac.equalsIgnoreCase("false")) {%> checked <%}%>>Not
				Available

			</div>
			<br>
			<br>
			<br>

			<label class="left">Accessory Kit</label> <input class="input"
				type="text" name="accessorykit" placeholder="Accessory Kit"
				value="<%=car.getAccessorykit()%>" pattern="[a-zA-Z_ ,]+"
				required="required"> <br>
			<br>
			<br>

			<label class="left">Power Steering</label>
			<div class="input">
				<%
					String powerSteering = car.getPowerSteering();
				%>
				<input type="radio" name="powerSteering" value="true"
					<%if (powerSteering.equalsIgnoreCase("true")) {%> checked <%}%>>Available
				<input type="radio" name="powerSteering" value="false"
					<%if (powerSteering.equalsIgnoreCase("false")) {%> checked <%}%>>Not
				Available

			</div>
			<br>
			<br>
			<br>
			<label class="left"><span id="span">*</span>Show Room Price</label> <input
				class="input" type="number" name="showRoomPrice"
				placeholder="Show Room Price" min="100000" max="100000000"
				value="<%=car.getShowRoomPrice()%>" required="required">
			<br>
			<br>
			<br>

			<br>
			<label class="left"><span id="span">*</span>Road Tax</label> <input
				class="input" type="number" name="roadTax" placeholder="Road Tax"
				min="100" max="100000" value="<%=car.getRoadTax()%>"
				required="required">
			<br>
			<br>
			<br>

			<br>

			<label class="left"><span id="span">*</span>On Road price</label> <input
				class="input" type="number" name="onRoadPrice"
				placeholder="On Road price" min="100100" max="100100000"
				value="<%=car.getOnRoadPrice()%>" required="required">
			<br>
			<br>
			<br>

			<br>

			<label class="left"><span id="span">*</span>Created Time</label> <input
				class="input" type="date" name="createdTime"
				value="<%=car.getCreatedTime()%>" required="required"
				readonly="readonly">
			<br>
			<br>
			<br>

			<label class="left"><span id="span">*</span>Created By</label> <input
				class="input" type="text" name="createdBy"
				value="<%=car.getCreatedBy()%>" placeholder="Created By"
				pattern="[a-zA-Z ]+" required="required" readonly="readonly">
			<br>
			<br>
			<br>



			<label class="left"><span id="span">*</span>Upload Image</label> <input
				class="input" type="file" name="imagePath"
				placeholder="Upload Image" required="required"
				value="<%=car.getImagePath()%>">
			<br>
			<br>
			<br>

			<input class="button" type="submit" value="Update"> </section>

		</form>
		<footer> <label>Copyright &copy 2015.&nbsp All rights
			reserved.</label> </footer>
	</div>
</body>
</html>