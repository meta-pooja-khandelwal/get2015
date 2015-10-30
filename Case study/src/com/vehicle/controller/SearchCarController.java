/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name SearchCarController
 * @description It will call the method in Facade to get the all Company names(makes),and minimum and maximum budget(showRoom Price) of car
 */
package com.vehicle.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.service.VehicleService;

/**
 * Servlet implementation class SearchCarController
 */
@WebServlet("/SearchCarController")
public class SearchCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCarController() {
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
		String person = request.getParameter("person");
		VehicleService iService = VehicleService.getInstance();
		List<String> makes = null;
		int minBudget = 0;
		int maxBudget = 0;
		/*
		 * Connection connection = null; VehicleFacade vehicleFacade;
		 * 
		 * try { connection = iService.getConnetion(); } catch
		 * (VehicleSystemException e) { // TODO Auto-generated catch block
		 * System.out.println("Coult not create connection with database, [" +
		 * e.getMessage() + "]"); } vehicleFacade = VehicleFacade.getInstance();
		 */
		try {
			makes = iService.findAllMakesOfCar();
			minBudget = iService.findMinBudgetOfCar();
			maxBudget = iService.findMaxBudgetOfCar();
			request.setAttribute("person", person);
			request.setAttribute("makes", makes);
			request.setAttribute("minBudget", minBudget);
			request.setAttribute("maxBudget", maxBudget);
			request.getRequestDispatcher("./view/SearchCar.jsp").forward(
					request, response);
		} catch (VehicleSystemException e) {
			request.setAttribute("person", person);
			System.out.println("[" + e.getMessage() + "]");
			String exception = e.getMessage();
			request.setAttribute("exception", exception);
			request.getRequestDispatcher("./view/error.jsp").forward(request,
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
