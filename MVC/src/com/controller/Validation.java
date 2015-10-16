/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name  Validation
 * @description this class was called by RegistrationPage.jsp to validate the registered data 
 */
package com.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Introspection;

import com.helper.EmployeeMain;
import com.models.Employee;

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
		String stringId = request.getParameter("id");
		int id = Integer.parseInt(stringId);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String stringAge = request.getParameter("age");
		int age = Integer.valueOf(stringAge);
		String registrationDate = request.getParameter("date");
		DateFormat format = new SimpleDateFormat("MM-dd-yy");
		Date date = new Date();
		try {
			date = format.parse(registrationDate);
		} catch (ParseException e) {

			System.out.println("date error");
		}
		String idRegex = "[0-9]+";
		String nameRegex = "[a-zA-Z]+";
		String message = "";
		// System.out.println("state name"+state);
		int flag;
		boolean isExist = false;
		if (!stringId.matches(idRegex)) {
			flag = 0;
			message = "invlid ID,it should be an integer";
		}

		else if (!name.matches(nameRegex)) {
			flag = 0;
			message = "invlid  name";
		} else if (!email.endsWith("@gmail.com")) {
			flag = 0;
			message = "Invalid Email";
		}

		else {
			flag = 1;
			message = "Registration Successful";
		}
		String stringEmployeeId = request.getParameter("employeeId");
		int employeeId = Integer.parseInt(stringEmployeeId);
		if (flag == 0) {

			request.setAttribute("newUserId", employeeId);

			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("RegistrationPage.jsp");
			request.setAttribute("message", message);
			requestDispatcher.forward(request, response);

		} else {

			Map<Integer, Employee> employeeCache = new ConcurrentHashMap<Integer, Employee>();
			employeeCache = EmployeeMain.getAllEmployees();

			EmployeeMain.employeesCache.put(id, new Employee(name, email, age,
					id, date));
			
			response.sendRedirect("EmployeeListPageController");
			

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
