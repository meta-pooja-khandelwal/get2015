<!DOCTYPE html>
<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@ page import="java.io.*,java.util.*"%>
<html lang="en">
<%@include file="../header.html" %>
<%@include file="../scripts.html" %>
<body id="page-top">
	  <%@ page errorPage="../../exception/error.jsp"%> 
	<%@ page session="false"%>
	<%@include file="../navbar.jsp" %>
	<section>
		<div class="container wow fadeIn">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>UPLOAD A PROFILE PICTURE</h2>
					<hr class="colored">
				</div>
			</div>
			<div class="row content-row">
				<div class="col-lg-4 col-lg-offset-4">
					<div class="row control-group text-center">
						<div
							class="form-group col-xs-12 floating-label-form-group controls">
							<%User userObject = (User)session.getAttribute("userObject"); %>
							<img class="img-responsive " id="pictureId"
								src="<%=userObject.getPicture()%>">
							<form action="PhotoUploader" method="post" 
								enctype="multipart/form-data" id="imageFormId">
								<input type="file" name="file" size="50" id="imagePathId"
									value="hi" onchange="checkImage()" /> <br /> <input id="uploadButton"
									class="btn btn-outline-dark text-center"
									style="border: 1px solid black; padding: 0 5px" type="submit"
									value="Upload" />
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<a class="col-xs-12 btn btn-outline-dark text-center" href="Home">Skip</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
