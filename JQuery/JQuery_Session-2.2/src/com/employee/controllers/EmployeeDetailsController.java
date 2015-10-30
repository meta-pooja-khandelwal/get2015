package com.employee.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.service.EmployeeService;
import com.exception.EmployeeSystemException;
import com.model.Employee;

/**
 * Servlet implementation class EmployeeDetailsController
 */
@WebServlet("/EmployeeDetailsController")
public class EmployeeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeDetailsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String empName;
		String dob;
		String email;
		String address;
		Employee employee;
		String employeeDetails;
		EmployeeService iService = EmployeeService.getInstance();
		try {
			employee = iService.getEmployeeDetails(name);

			empName = employee.getName();

			if (empName == null) {

				employeeDetails = "Data does not exist for " + name;
			} else {
				dob = employee.getDateOfBirth();
				email = employee.getEmail();
				address = employee.getAddress();
				// System.out.println(employee.getAddress());
				employeeDetails = "Name :" + empName + "<br>Email :" + email
						+ "<br>Date of Birth:" + dob + "<br>Address :"
						+ address;
			}

			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(employeeDetails);

		} catch (EmployeeSystemException e) {
			System.out.println("Coult not get Employee data from database, ["
					+ e.getMessage() + "]");
			employeeDetails = "No such data exist";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(employeeDetails);
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
