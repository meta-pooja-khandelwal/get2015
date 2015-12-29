<!DOCTYPE html>
<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@page import="com.metasocio.model.usermanagement.User"%>
<%@ page import="java.io.*,java.util.*"%>
<html lang="en">
<%@include file="../header.html"%>
<%@include file="../scripts.html"%>
<body id="page-top">
	<%@ page errorPage="../../exception/error.jsp"%>
	<%@ page session="false"%>
	<!-- Navigation -->
	<!-- Note: navbar-default and navbar-inverse are both supported with this theme. -->
	<%@include file="../navbar.jsp"%>
	<%
		User userObject = (User) session.getAttribute("userObejct");
	%>
	<section>
		<div class="container wow fadeIn">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2>Complete Your Profile</h2>
					<h3 style="color: red">${message}</h3>
					<hr class="colored">
				</div>
			</div>
			<div class="row content-row">
				<form action="UserProfile" method="post">
					<input type="hidden" value="<%=request.getAttribute("first")%>"
						name="first">
					<%
						userObject = (User) session.getAttribute("userObject");
						String gender = "gender";
						String date = "";
						if (!userObject.getGender().isEmpty()) {
							gender = userObject.getGender();
						}
					%>
					<div class="col-md-12">
						<p>
							Fields marked with (<span style="color: red">*</span>) asterisks
							are compulsory.
						</p>
					</div>
					<div class="col-lg-12">
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseOne" style="color: orangered">1. Personal
											Info &nbsp;(Compulsory)&nbsp;&nbsp;<span class="text-danger">${messagePersonalInfo}</span>
										</a>
									</h4>
								</div>
								<div id="collapseOne" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Name<span style="color: red">*</span></label> <input
													type="text" class="form-control" required="required"
													style="background-color: transparent; font-weight: bolder;"
													value="<%=userObject.getName()%>"
													<%if (userObject.getName().equalsIgnoreCase(" ")) {%>
													readonly <%}%>
													placeholder="Name *" name="name" required="required">
												<p class="text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Email Address</label> <input type="email"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Email Address"
													value="<%=userObject.getEmail()%>" name="email"
													readonly="readonly">
												<p class="text-danger"></p>
											</div>
										</div>
										<div class="row control-group">
											<div
												class="form-group col-xs-12 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Address</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Address" name="address"
													value="<%=userObject.getAddress()%>">
												<p class="help-block text-danger"></p>
											</div>
										</div>
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Phone Number</label> <input type="number"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Phone Number" name="phoneNumber"
													value="<%=userObject.getPhoneNo()%>">
												<p class="help-block text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>City</label> <input type="text" class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="City" name="city"
													value="<%=userObject.getCity()%>">
												<p class="help-block text-danger"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseTwo" style="color: orangered">2. Work and
											Education (Compulsory)<span class="text-danger">${messageWorkAndEducation}</span>
										</a>
									</h4>
								</div>
								<div id="collapseTwo" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Department*<span style="color: red"></span></label> <input
													type="text" class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Department*" name="department"
													value="<%=userObject.getDepartment()%>"
													required="required">
												<p class="text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Role*<span style="color: red"></span></label> <input
													type="text" class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Role*" name="role"
													value="<%=userObject.getRole()%>" required="required">
												<p class="text-danger"></p>
											</div>
										</div>
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>College</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="College" name="college"
													value="<%=userObject.getCollege()%>">
												<p class="help-block text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Course</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Course" name="course"
													value="<%=userObject.getCourse()%>">
												<p class="help-block text-danger"></p>
											</div>
										</div>
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>High School</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="High School" name="highSchool"
													value="<%=userObject.getHighSchool()%>">
												<p class="help-block text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Stream</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Stream" name="stream"
													value="<%=userObject.getStream()%>">
												<p class="text-danger"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseThree">3. Basic Info</a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1; height: 100px">
												<label>Date Of Birth</label>
												<div id="sandbox-container">
													<input class="form-control"
														style="background-color: transparent; font-weight: bolder;"
														placeholder="Date Of Birth" type="text" name="dateOfBirth"
														value="<%=userObject.getDateOfBirth()%>">
												</div>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1; height: 100px">
												<label>Gender</label>
												<div class="row" style="padding-left: 20px; margin: 0px;">
													<div class="col-xs-6">
														<div class="radio">
															<input type="radio" name="gender" value="male"
																<%if (gender.equalsIgnoreCase("male")) {%> checked <%}%>>Male
														</div>
													</div>
													<div class="col-xs-6">
														<div class="radio">
															<input type="radio" name="gender" value="female"
																<%if (gender.equalsIgnoreCase("female")) {%> checked
																<%}%>>Female
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#collapseFour">4. Details About You</a>
									</h4>
								</div>
								<div id="collapseFour" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="row control-group">
											<div
												class="form-group col-xs-12 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>About You</label>
												<textarea class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Write Something about yourself" name="about"><%=userObject.getAbout()%></textarea>
											</div>
										</div>
										<div class="row control-group">
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Nick Name</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Nick Name" name="nickName"
													value="<%=userObject.getNickName()%>">
												<p class="help-block text-danger"></p>
											</div>
											<div
												class="form-group col-xs-6 floating-label-form-group controls"
												style="border-left: 1px solid #e1e1e1; border-right: 1px solid #e1e1e1">
												<label>Relationship Status</label> <input type="text"
													class="form-control"
													style="background-color: transparent; font-weight: bolder;"
													placeholder="Relationship Status" name="relationship"
													value="<%=userObject.getRelationshipStatus()%>">
												<p class="help-block text-danger"></p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 text-center">
							<input type="submit" class="btn btn-outline-dark text-center"
								value="Save">
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<script>
		$('#sandbox-container input').datepicker({
			autoclose : true
		});
	</script>
</body>
</html>
