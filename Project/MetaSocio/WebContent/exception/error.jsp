<!DOCTYPE html>
<%@ page isErrorPage="true"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<html lang="en">
<%@include file="../view/header.html"%>
<%@include file="../view/scripts.html"%>
<body id="page-top">
	<%@ page session="false"%>
	<%@include file="../view/navbar.jsp"%>
	<header style="background-image: url('assets/img/exception.png');">
		<div class="intro-content">
			<div class="brand-name">Sorry An Error Occurred while
				processing your request.</div>
			<hr class="colored">
			<div class="brand-name-subtext">
				<%if(exception!=null) {%>
				<%=exception%>
				<%}%>
				<p>${message}</p>
			</div>
		</div>
	</header>
</body>
</html>