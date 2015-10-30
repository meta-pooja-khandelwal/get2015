<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="styleSheet" href="LoginStyle.css" type="text/css">
<script type="text/javascript" src="Script.js"></script>
</head>

<body>
	<%@ page errorPage="error.jsp"%>

	<div id="wrapper">
		<h1>Login</h1>
		<form action="LoginValidation">
			<table>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" class="input"
						required="required" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" class="input"
						required="required" /></td>
				<tr id="error">
				</tr>
			</table>
			<button>Login</button>
		</form>



	</div>
	<div id="register">
		<a href='Registration.jsp'><img src="loginImage.png" id="image" /></a>
		<p>Register new account</p>
	</div>

</body>

</html>