/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name EditCarController
 * @description It will forward the request to EditCar.jsp page to edit the specified car
 */
package com.vehicle.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vehicle.VehicleType;
import com.vehicle.factory.VehicleFactory;
import com.vehicle.model.Car;
import com.vehicle.model.Vehicle;

/**
 * Servlet implementation class EditCarController
 */
@WebServlet("/EditCarController")
public class EditCarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCarController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Car> carsList = null;
		String person = request.getParameter("person");
		request.setAttribute("person", person);
		String carPositionInListString = request
				.getParameter("carPositionInList");
		int carPositionInList = Integer.parseInt(carPositionInListString);
		HttpSession session = request.getSession(false);
		VehicleFactory vehicleFactory = VehicleFactory.getInstance();
		Vehicle iVehicle = vehicleFactory.getVehicle(VehicleType.car);
		Car car = (Car) iVehicle;
		if (session != null) {
			if (session.getAttribute("carList") != null) {
				carsList = (List<Car>) session.getAttribute("carList");
				car = carsList.get(carPositionInList);
			}
		}
		request.setAttribute("car", car);
		request.getRequestDispatcher("./view/EditCar.jsp").forward(request,
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
