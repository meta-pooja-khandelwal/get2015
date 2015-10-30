/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name SpecificationController
 * @description It will forward the request to Specification.jsp page with the specified car to show the specification of that car
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
 * Servlet implementation class SpecificationController
 */
@WebServlet("/SpecificationController")
public class SpecificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SpecificationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Car> carsList = null;
		System.out.println("list" + request.getAttribute("carList"));
		String person = request.getParameter("person");
		request.setAttribute("person", person);
		String carPositionInListString = request
				.getParameter("carPositionInList");
		int carPositionInList = Integer.parseInt(carPositionInListString);
		HttpSession session = request.getSession(false);
		VehicleFactory vehicleFactory = VehicleFactory.getInstance();
		Vehicle ivehicle = vehicleFactory.getVehicle(VehicleType.car);
		Car car = (Car) ivehicle;
		if (session != null) {
			if (session.getAttribute("carList") != null) {
				carsList = (List<Car>) session.getAttribute("carList");
				car = carsList.get(carPositionInList);
			}
		}
		request.setAttribute("car", car);
		request.getRequestDispatcher("./view/Specification.jsp").forward(
				request, response);
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
