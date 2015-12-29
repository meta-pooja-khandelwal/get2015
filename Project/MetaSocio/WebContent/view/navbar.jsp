<%@page import="com.metasocio.model.usermanagement.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>:: META-SOCIO ::</title>
</head>
<body>
	<%@ page session="false"%>
	<%@ page errorPage="../../exception/error.jsp"%>
	<nav class="navbar navbar-inverse navbar-fixed-top navbar-expanded"
		style="background-color: black">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<%
					if (request.getSession(false) == null) {
				%>
			<a class="navbar-brand page-scroll hidden-sm" href="index.jsp"> <img
				src="assets/img/logo.png" class="img-responsive hidden-sm" alt="">
			</a>
			<%
					} else {
				%>
			<a class="navbar-brand page-scroll hidden-sm" href="Home"> <img
				src="assets/img/logo.png" class="img-responsive hidden-sm" alt="">
			</a>
			<%
					}
				%>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="hidden"><a class="page-scroll" href="#page-top"></a>
				</li>
				<%
						HttpSession session = request.getSession(false);
						if (session == null) {
					%>
				<li><a class="page-scroll" href="About">What is Meta-Socio</a></li>
				<li><a class="page-scroll"
					href="https://accounts.google.com/o/oauth2/auth?scope=email&amp;redirect_uri=http://localhost:8080/MetaSocio/OAuth&amp;response_type=code&amp;client_id=
530623119057-nfsj6k1res5fh0gvpu3epncerdrj1594.apps.googleusercontent.com&amp;approval_prompt=force&hd=metacube.com">Log-in</a>
				</li>
				<%
						} else {
					%>
				<li class="hidden"><a class="page-scroll" href="#page-top"></a>
				</li>
				<li style="width: 400px;"><input type="text"
					name="searchUsersByKey" class="form-control" id="search"
					onkeyup="searchUsersByKey()" placeholder="Search People"
					style="margin-top: 10px; width: 400px;">
					<div id="searchBlock"
						style="display: none; width: 400px; position: absolute; background-color: white; overflow-y: hidden; overflow-x: hidden">
					</div></li>
				<li><a class="page-scroll" href="Home">Home</a></li>
				<li><a class="page-scroll" href="About">What is Meta-Socio</a></li>
				<li><a class="page-scroll" href="Followers">Followers</a></li>
				<li><a class="page-scroll" href="Following">Following</a></li>
				<li><a class="page-scroll" href="Groups">My Groups</a></li>
				<li><a class="page-scroll" href="LogOut">Logout</a></li>
				<%
						}
					%>
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>