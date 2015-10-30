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

import com.helper.DatabaseUtil;
import com.helper.RegistrationHelper;

/**
 * Servlet implementation class DatabaseHelper
 */
public class DatabaseHelper extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatabaseHelper() {
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

		RegistrationHelper registrationHelper = (RegistrationHelper) request
				.getAttribute("registrationHelperObject");

		DatabaseUtil databaseUtil = new DatabaseUtil();
		boolean isinserted = databaseUtil
				.insertRegistrationDataIntoDatabase(registrationHelper);
		if (isinserted == true) {
			System.out.println("Data successfully iserted into database");
			String message = "You have registered succefully";
			response.sendRedirect("Result.jsp?message="
					+ URLEncoder.encode(message, "UTF-8"));
		} else {
			System.out.println("insertion error");
			String message = "Insertion Error";
			response.sendRedirect("ErrorPage.jsp?message="
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
