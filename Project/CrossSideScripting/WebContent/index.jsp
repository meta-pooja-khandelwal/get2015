
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Cross Side Script Example</title>
</head>

<body>
	<form action="CrossSideScriptHandler" method="get">

		<textarea rows="2" cols="6" name="input"
			style="border: 1px solid black;"></textarea>
		<input type="submit" value="submit" />
	</form>
	<%
		String result = "";
		if (session.getAttribute("output") != null) {
			result = (String) session.getAttribute("output");
		} else {
			result = "Please enter your name first";
		}
	%>
	<p>
		Output =<%=result%></p>
</body>
</html>
