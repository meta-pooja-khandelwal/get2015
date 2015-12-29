package com.metasocio.controller.usermanagement;

import java.io.IOException;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.metasocio.authentication.EmailUtil;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.usermanagement.UserService;
import com.metasocio.validation.usermanagement.ProfileValidation;

/**********************************************************************
 * This controller is to interact with user profile creation layer and Servlet
 * implementation class UserProfile
 ***********************************************************************/
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/****************************************************************
	 * Creating constructor
	 * 
	 * @see HttpServlet#HttpServlet()
	 ****************************************************************/
	public UserProfile() {
		super();
	}

	/***************************************************************************
	 * This method will redirect user to profile page to edit his profile
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 **************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./view/usermanagement/profile.jsp")
				.forward(request, response);
	}

	/***************************************************************************
	 * This method will either create or edit a user profile
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 **************************************************************************/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		String address = request.getParameter("address").trim();
		String phnNo = request.getParameter("phoneNumber").trim();
		String city = request.getParameter("city").trim();
		String department = request.getParameter("department").trim();
		String role = request.getParameter("role").trim();
		String college = request.getParameter("college").trim();
		String course = request.getParameter("course").trim();
		String highSchool = request.getParameter("highSchool").trim();
		String stream = request.getParameter("stream").trim();
		String dateOfBirth = request.getParameter("dateOfBirth").trim();
		String gender = request.getParameter("gender");
		String about = request.getParameter("about").trim();
		String nickName = request.getParameter("nickName").trim();
		String relationshipStatus = request.getParameter("relationship").trim();
		User userObject = new User();
		userObject = (User) session.getAttribute("userObject");
		userObject.setName(name);
		userObject.setEmail(email);
		userObject.setAddress(address);
		userObject.setPhoneNo(phnNo);
		userObject.setCity(city);
		userObject.setDepartment(department);
		userObject.setRole(role);
		userObject.setCollege(college);
		userObject.setCourse(course);
		userObject.setHighSchool(highSchool);
		userObject.setStream(stream);
		userObject.setDateOfBirth(dateOfBirth);
		userObject.setGender(gender);
		userObject.setAbout(about);
		userObject.setNickName(nickName);
		userObject.setRelationshipStatus(relationshipStatus);
		session.setAttribute("userObject", userObject);

		UserService userService = new UserService();

		try {

			ProfileValidation profileValidation = new ProfileValidation();
			String errorMessageForPersonalInfo = profileValidation
					.validateUserPersonalInfo(userObject);
			String errorMessageForWorkAndEducationInfo = profileValidation
					.validateUserWorkAndEducationInfo(userObject);
			if (!errorMessageForPersonalInfo.isEmpty()) {
				request.setAttribute("messagePersonalInfo",
						errorMessageForPersonalInfo);
				if ("first".equalsIgnoreCase(request.getParameter("first"))) {
					request.setAttribute("first", "first");
				}

				request.getRequestDispatcher(
						"./view/usermanagement/profile.jsp").forward(request,
						response);
			} else if (!errorMessageForWorkAndEducationInfo.isEmpty()) {
				request.setAttribute("messageWorkAndEducation",
						errorMessageForWorkAndEducationInfo);
				if ("first".equalsIgnoreCase(request.getParameter("first"))) {
					request.setAttribute("first", "first");
				}
				request.getRequestDispatcher(
						"./view/usermanagement/profile.jsp").forward(request,
						response);
			} else {

				User userProfile = userService.getUserByEmail(email);
				Date date = new Date();
				if (userProfile == null) {

					userObject.setCreatedAt(date);
					userObject.setCreatedBy(userObject.getName());
					userObject.setUpdatedAt(new Timestamp(date.getTime()));
					userService.setUserInfo(userObject);
					userObject = userService.getUserByEmail(email);

					session.setAttribute("userObject", userObject);
					EmailUtil sendEmail = new EmailUtil();
					String emailSubject = userObject.getName()
							+ ", welcome to META-SOCIO ";

					String emailBody = "Welcome to META-SOCIO, "
							+ userObject.getName()
							+ " !"
							+ "\n"
							+ "You're now a part of Metacube's private, "
							+ "social collaboration network. META-SOCIO makes it easy to work together in teams "
							+ "and stay up to date on what others are working on.";
					sendEmail.sendEmail(userObject.getEmail(), emailSubject,
							emailBody);

				}

				else {
					userProfile.setName(name);
					userProfile.setEmail(email);
					userProfile.setAddress(address);
					userProfile.setPhoneNo(phnNo);
					userProfile.setCity(city);
					userProfile.setDepartment(department);
					userProfile.setRole(role);
					userProfile.setCollege(college);
					userProfile.setCourse(course);
					userProfile.setHighSchool(highSchool);
					userProfile.setStream(stream);
					userProfile.setDateOfBirth(dateOfBirth);
					userProfile.setGender(gender);
					userProfile.setAbout(about);
					userProfile.setNickName(nickName);
					userProfile.setRelationshipStatus(relationshipStatus);
					userProfile.setUpdatedAt(new Timestamp(date.getTime()));
					userService.updateUserInfo(userProfile);
					userObject = userService.getUserByEmail(email);
					session.setAttribute("userObject", userObject);
				}
				if ("first".equalsIgnoreCase(request.getParameter("first"))) {
					request.getRequestDispatcher(
							"./view/usermanagement/profile-picture.jsp")
							.forward(request, response);
				} else {
					response.sendRedirect("Home");
				}

			}
		} catch (MetaSocioSystemException e) {
			request.setAttribute("message",
					"Can't create or update profile now. Please try after some time.");
			request.getRequestDispatcher("./view/exception/error.jsp").forward(
					request, response);
		}
	}

}
