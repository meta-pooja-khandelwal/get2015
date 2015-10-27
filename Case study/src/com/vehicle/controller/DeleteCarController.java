/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name DeleteCarController
 * @description It will call the method in VehicleFacade to delete the specified car
 */
package com.vehicle.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
 * Servlet implementation class DeleteCarController
 */
@WebServlet("/DeleteCarController")
public class DeleteCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCarController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Car> carsList = new ArrayList<Car>();
		if (session != null) {
			if (session.getAttribute("carList") != null) {
				carsList = (List<Car>) session.getAttribute("carList");
			}
		}
		String carPositionInListString = request
				.getParameter("carPositionInList");
		int carPositionInList = Integer.parseInt(carPositionInListString);
		String person = request.getParameter("person");
		request.setAttribute("person", person);
		Car car = new Car();
		car = carsList.get(carPositionInList);
		int carId = car.getCarId();
		int vehicleId = car.getVehicleId();
		VehicleService iService = VehicleService.getInstance();
		Connection connection = null;
		VehicleFacade vehicleFacade;
		try {
			connection = iService.getConnetion();
		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database, ["
					+ e.getMessage() + "]");
		}
		vehicleFacade = VehicleFacade.getInstance();
		try {
			vehicleFacade.deleteCar(vehicleId, carId, connection);
			carsList.remove(carPositionInList);
			request.setAttribute("carList", carsList);
			String message = "Car deleted succefully";
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/CarDekho.jsp").forward(
					request, response);
		} catch (VehicleSystemException e) {
			String message = e.getMessage();
			request.setAttribute("carList", carsList);
			System.out.println("Coult not delete the car, [" + e.getMessage()
					+ "]");
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/CarDekho.jsp").forward(
					request, response);
		} finally {
			iService.closeConnetion(connection);
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
