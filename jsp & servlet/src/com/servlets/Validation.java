/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name Validation
 * @description this class will validate the fields of registration form 
 */
package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.helper.RegistrationHelper;

/**
 * Servlet implementation class Validation
 */
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Validation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// destroy();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		double age = Double.valueOf(request.getParameter("age"));
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String address = request.getParameter("addressLine");

		String regex = "[a-zA-Z]+";
		String message = "";
		// System.out.println("state name"+state);
		int flag;
		if (!firstName.matches(regex)) {
			flag = 0;
			message = "invlid first name";
		}

		else if (!lastName.matches(regex)) {
			flag = 0;
			message = "invlid last name";
		} else if (!email.endsWith("@gmail.com")) {
			flag = 0;
			message = "invalid email Id";
		} else if (!email.endsWith("@gmail.com")) {
			flag = 0;
			message = "invalid email Id";
		} else if (password.length() < 8) {
			flag = 0;
			message = "Password Length should be greater than 8";
		} else if (!confirmPassword.equals(password)) {
			flag = 0;
			message = "Password mismatch!!";
		} else if (state == null) {
			flag = 0;
			message = "State is not selected";
		} else if (city == null) {
			flag = 0;
			message = "City is not selected";
		}

		else if (address == null) {
			flag = 0;
			message = "Please fill address field";
		}

		else {
			flag = 1;
			message = "Registration Successful";
		}

		if (flag == 0) {
			response.sendRedirect("Registration.jsp?message="
					+ URLEncoder.encode(message, "UTF-8"));
		} else {
			RegistrationHelper registrationHelper = new RegistrationHelper();

			registrationHelper.setFirstName(firstName);
			registrationHelper.setLastName(lastName);
			registrationHelper.setEmail(email);
			registrationHelper.setPassword(password);
			registrationHelper.setConfirmPassword(confirmPassword);
			registrationHelper.setAge(age);
			registrationHelper.setState(state);
			registrationHelper.setCity(city);
			registrationHelper.setAddress(address);

			request.setAttribute("registrationHelperObject", registrationHelper);
			request.getRequestDispatcher("DatabaseHelper").forward(request,
					response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
