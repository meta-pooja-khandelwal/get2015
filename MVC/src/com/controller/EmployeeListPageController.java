/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name EmployeeListPageController
 * @description this class is a controller that was called by view:LandingPage.html and it will  call the view :EmployeeListPage.jsp to show the list of registered employees
 */
package com.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helper.EmployeeMain;
import com.models.Employee;

/**
 * Servlet implementation class RegistrationController
 */

public class EmployeeListPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeListPageController() {
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
		Map<Integer, Employee> employeesList = new ConcurrentHashMap<Integer, Employee>();
		// employeesList =(Map<Integer,
		// Employee>)getServletContext().getAttribute("employeeCache");
		employeesList = EmployeeMain.getAllEmployees();
		request.setAttribute("employeesList", employeesList);
		request.getRequestDispatcher("/EmployeeListPage.jsp").forward(request,
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
