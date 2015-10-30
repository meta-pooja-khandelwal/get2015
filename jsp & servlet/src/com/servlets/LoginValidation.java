/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name Validation
 * @description this class will validate the fields of registration form 
 */
package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.helper.DatabaseUtil;
import com.helper.RegistrationHelper;

/**
 * Servlet implementation class LoginValidation
 */
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public LoginValidation() {
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

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println(email);
		System.out.println(password);
		DatabaseUtil databaseUtil = new DatabaseUtil();
		List<RegistrationHelper> registrationHelpersList = new ArrayList<RegistrationHelper>();

		registrationHelpersList = databaseUtil.fetchLoginDataFromDatabase();

		boolean isValid = false;

		for (int i = 0; i < registrationHelpersList.size(); i++) {
			RegistrationHelper registrationHelper = new RegistrationHelper();
			registrationHelper = registrationHelpersList.get(i);
			String emailInDatabase = registrationHelper.getEmail();
			String passwordInDatabase = registrationHelper.getPassword();
			System.out.println(emailInDatabase);
			System.out.println(passwordInDatabase);
			if (email.equals(emailInDatabase)
					&& password.equals(passwordInDatabase)) {
				isValid = true;
				System.out.println(isValid);
			}
		}
		if (isValid == false) {
			System.out.println("Invalid Email Id or password");
			String message = "Invalid Email Id or password";
			response.sendRedirect("ErrorPage.jsp?message="
					+ URLEncoder.encode(message, "UTF-8"));
		} else {
			System.out.println("You have successfully logged in");
			String message = "You have successfully logged in";
			response.sendRedirect("Result.jsp?message="
					+ URLEncoder.encode(message, "UTF-8"));
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
