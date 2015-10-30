/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name LoginValidation
 * @description It will check the login data whether it is valid or not ,if invalid then redirect to login page with error message ,otherwise go to VehicleHome page
 */
package com.vehicle.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.model.Login;
import com.vehicle.service.VehicleService;
import com.vehicle.user.UserType;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
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
		String person = request.getParameter("person");
		System.out.println(person);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		VehicleService iService = VehicleService.getInstance();
		Login login = null;
		/*
		 * Connection connection = null;
		 * 
		 * try { connection = iService.getConnetion(); } catch
		 * (VehicleSystemException e) {
		 * System.out.println("Coult not create connection with database, [" +
		 * e.getMessage() + "]"); } VehicleFacade vehicleFacade =
		 * VehicleFacade.getInstance();
		 */
		try {
			login = iService.getLoginModel();
		} catch (VehicleSystemException e) {
			System.out.println("Coult not get Login data from database, ["
					+ e.getMessage() + "]");
		}
		if (login.getEmail().equalsIgnoreCase(email)
				&& login.getPassword().equalsIgnoreCase(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("admin", UserType.admin);
			request.setAttribute("person", person);
			request.getRequestDispatcher("./view/VehicleHome.jsp").forward(
					request, response);
		} else {
			String message;
			if (email == null) {
				message = "Please fill email field";
			} else if (password == null) {
				message = "Please enter password";
			} else if (!email.equalsIgnoreCase(login.getEmail())) {
				message = "Invalid Email";
			} else {
				message = "Invalid Password";
			}
			request.setAttribute("person", person);
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/Login.jsp").forward(request,
					response);
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
