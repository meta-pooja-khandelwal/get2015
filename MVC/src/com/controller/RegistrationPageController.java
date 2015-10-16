/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name RegistrationPageController
 * @description this class is a controller that was called by view:LandingPage.html or ViewDetails.jsp and it will call the view :RegistrationPage.jsp to register the employee data
 */
package com.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.helper.EmployeeMain;
import com.models.Employee;

/**
 * Servlet implementation class RegistrationPageController
 */

public class RegistrationPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationPageController() {
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
		Map<Integer, Employee> employeeMapNew = EmployeeMain.getAllEmployees();
		int idValue = 0;

		for (Integer i : employeeMapNew.keySet()) {
			idValue = i;
		}

		request.setAttribute("newUserId", (idValue + 1));

		request.getRequestDispatcher("/RegistrationPage.jsp").forward(request,
				response);
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
