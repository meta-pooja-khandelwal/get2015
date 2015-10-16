/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name EditEmployeeDetailsController
 * @description this class is a controller that was called by view:EmployeeListPage.jsp and it will call the view :RegistrationPage.jsp to edit the employee data
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
 * Servlet implementation class EditEmployeeDetailsController
 */

public class EditEmployeeDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditEmployeeDetailsController() {
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
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		for (int i = 1; i <= employeesList.size(); i++) {
			Employee employee = new Employee();
			employee = employeesList.get(i);

			if (employee.getEmployeeID() == id) {

				request.setAttribute("employee", employee);
				request.getRequestDispatcher("/RegistrationPage.jsp").forward(
						request, response);
			}
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
