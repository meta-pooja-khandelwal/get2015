/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name CarDekhoController
 * @description It will call the VehicleFacade to get the cars of selected type and then send it to CarDekho.jsp page
 */
package com.vehicle.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.facade.VehicleFacade;
import com.vehicle.model.Car;
import com.vehicle.service.VehicleService;

/**
 * Servlet implementation class CarDekho
 */
@WebServlet("/CarDekhoController")
public class CarDekhoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarDekhoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String make = request.getParameter("make");
		String person = request.getParameter("person");
		String budgetString = request.getParameter("budget");
		String message;
		request.setAttribute("person", person);
		if (make == null) {
			HttpSession session = request.getSession(false);
			List<String> makes = null;
			if (session.getAttribute("companyList") != null) {
				makes = (List<String>) session.getAttribute("companyList");
			}
			int minBudget = 0;
			if (request.getParameter("minBudget") != null) {
				minBudget = Integer.parseInt(request.getParameter("minBudget"));
			}
			int maxBudget = 0;
			if (request.getParameter("maxBudget") != null) {
				maxBudget = Integer.parseInt(request.getParameter("maxBudget"));
			}
			request.setAttribute("makes", makes);
			request.setAttribute("minBudget", minBudget);
			request.setAttribute("maxBudget", maxBudget);
			message = "please select company name";
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/SearchCar.jsp").forward(
					request, response);
		} else if (budgetString == null) {
			HttpSession session = request.getSession(false);
			List<String> makes = null;
			if (session.getAttribute("companyList") != null) {
				makes = (List<String>) session.getAttribute("companyList");
			}
			int minBudget = 0;
			if (request.getParameter("minBudget") != null) {
				minBudget = Integer.parseInt(request.getParameter("minBudget"));
			}
			int maxBudget = 0;
			if (request.getParameter("maxBudget") != null) {
				maxBudget = Integer.parseInt(request.getParameter("maxBudget"));
			}
			request.setAttribute("makes", makes);
			request.setAttribute("minBudget", minBudget);
			request.setAttribute("maxBudget", maxBudget);
			message = "please enter budget";
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/SearchCar.jsp").forward(
					request, response);
		} else {
			int budget = Integer.parseInt(budgetString);
			VehicleService iService = VehicleService.getInstance();
			Connection connection = null;
			List<Car> carList;
			try {
				connection = iService.getConnetion();
			} catch (VehicleSystemException e) {
				System.out
						.println("Coult not create connection with database, ["
								+ e.getMessage() + "]");
			}
			VehicleFacade vehicleFacade = VehicleFacade.getInstance();
			try {
				carList = vehicleFacade.getCarData(make, budget, connection);
				System.out.println(carList);
				if (carList.size() == 0) {
					message = "No such car found";
					request.setAttribute("message", message);
					request.setAttribute("carList", carList);
					request.getRequestDispatcher("./view/CarDekho.jsp")
							.forward(request, response);
				} else {
					request.setAttribute("carList", carList);
					request.getRequestDispatcher("./view/CarDekho.jsp")
							.forward(request, response);
				}
			} catch (VehicleSystemException e) {
				System.out.println("Coult not get Car data from database, ["
						+ e.getMessage() + "]");
				message = "Can't search the car,[" + e.getMessage() + "]";
				HttpSession session = request.getSession(false);
				List<String> makes = null;
				if (session.getAttribute("companyList") != null) {
					makes = (List<String>) session.getAttribute("companyList");
				}
				int minBudget = 0;
				if (request.getParameter("minBudget") != null) {
					minBudget = Integer.parseInt(request
							.getParameter("minBudget"));
				}
				int maxBudget = 0;
				if (request.getParameter("maxBudget") != null) {
					maxBudget = Integer.parseInt(request
							.getParameter("maxBudget"));
				}
				request.setAttribute("message", message);
				request.setAttribute("makes", makes);
				request.setAttribute("minBudget", minBudget);
				request.setAttribute("maxBudget", maxBudget);
				request.getRequestDispatcher("./view/SearchCar.jsp").forward(
						request, response);
			} finally {
				iService.closeConnetion(connection);
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
