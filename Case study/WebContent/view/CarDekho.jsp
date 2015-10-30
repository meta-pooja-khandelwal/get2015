<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.vehicle.model.Car"%>
<%@page import="com.vehicle.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CarDekho.com</title>
<link rel="styleSheet" type="text/css" href="./view/CarDekhoStyle.css">
</head>
<body>
	<%@ page errorPage="error.jsp"%>
	<div class="wrapper">
		<%String person=request.getParameter("person");
   
	
	%>
 
 

		<header id="head"> <img src="./view/logo.png"
			alt="car dekho logo" width="400px" height="100px" align="left">
		<%
      if(UserType.admin.equals(UserType.valueOf(person))){
    	%> <input type='button'
			onclick="location.href='./VehicleController'" value='Log Out'
			id='logOut' /> <input type='button'
			onclick="location.href='./CreateCarController'" value='Create Car'
			id='createCar' /> <%
      }
      %> <input type="button"
			onclick="location.href='./SearchCarController?person=<%=person%>'"
			value="Search Car" id="searchCar" /> <input type="button"
			onclick="location.href='./VehicleHomeController?person=<%=person%>'"
			value="Home" id="goHome" /> <!--  input type="hidden" value="<%=person %>" name="person"/>	 -->
		</header>
		<%
		List<Car> carsList;
		carsList=(List<Car>)request.getAttribute("carList");
	if(carsList.size()==0){
	%>
		<p align="center"
			style="color: red; font-size: 20px; font-weight: bold;">${message}</p>
		<%
	     }else{
		%>

		<nav>
		<table>

			<%
			request.setAttribute("carList", carsList);
			session=request.getSession(true);
			session.setAttribute("carList", carsList);
			for(int i=0;i<carsList.size();i++){
				Car car=carsList.get(i);
				 
			%>
			<!-- input type="hidden" value="<%=i %>" name="carPositionInList"/>  -->
			<tr>
				<td><img src="./view/<%=car.getImagePath() %>" alt="car image"
					width="300px" height="200px">
				<td>
				<td><%=car.getMake() %><br> <br><%=car.getModel()%><br>
					<br>
				<font color="red">Rs. <%=car.getShowRoomPrice() %>*
				</font><br> <br> <a
					href="./SpecificationController?person=<%=person%>&carPositionInList=<%=i%>">View
						Specifications</a> <br> <br> <%
			      if(UserType.admin.equals(UserType.valueOf(person))){
			    	 %> <a id='edit3'
					href='./EditCarController?person=<%=person%>&carPositionInList=<%=i%>'>Edit</a><br>
					<br> <a id='delete'
					href='./DeleteCarController?person=<%=person%>&carPositionInList=<%=i%>'>Delete</a>
					<%
			      }
			      %></td>
			</tr>

			<%
			}
			%>



		</table>
		</nav>
		<%
	     }
       %>
		<footer> <label>Copyright &copy 2015.&nbsp All rights
			reserved.</label> </footer>

	</div>

</body>
</html>