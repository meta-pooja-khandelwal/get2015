/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name EditCar
 * @description It will call the method in VehicleFacade to edit the data for a specified car
 */
package com.vehicle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vehicle.VehicleType;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.factory.VehicleFactory;
import com.vehicle.model.Car;
import com.vehicle.model.Vehicle;
import com.vehicle.service.VehicleService;

/**
 * Servlet implementation class EditCar
 */
@WebServlet("/EditCar")
public class EditCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditCar() {
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
		request.setAttribute("person", person);
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String stringEngineInCc = request.getParameter("engineInCc");
		int engineInCc = Integer.parseInt(stringEngineInCc);
		String stringFuelCapacity = request.getParameter("fuelCapacity");
		int fuelCapacity = Integer.parseInt(stringFuelCapacity);
		String stringMileage = request.getParameter("mileage");
		int mileage = Integer.parseInt(stringMileage);
		String ac = request.getParameter("ac");
		if (ac == null) {
			ac = "false";
		}
		String accessorykit = request.getParameter("accessorykit");
		String powerSteering = request.getParameter("powerSteering");
		if (powerSteering == null) {
			powerSteering = "false";
		}
		String stringShowRoomPrice = request.getParameter("showRoomPrice");
		int showRoomPrice = Integer.parseInt(stringShowRoomPrice);
		String roadTaxString = request.getParameter("roadTax");
		int roadTax = Integer.parseInt(roadTaxString);
		String onRoadPriceString = request.getParameter("onRoadPrice");
		int onRoadPrice = Integer.parseInt(onRoadPriceString);
		String createdTime = request.getParameter("createdTime");
		String createdBy = request.getParameter("createdBy");
		String imagePath = request.getParameter("imagePath");
		String carIdString = request.getParameter("carId");
		int carId = Integer.parseInt(carIdString);
		VehicleFactory vehicleFactory = VehicleFactory.getInstance();
		Vehicle iVehicle = vehicleFactory.getVehicle(VehicleType.car);
		Car car = (Car) iVehicle;
		car.setMake(make);
		car.setModel(model);
		car.setEngineInCc(engineInCc);
		car.setFuelCapacity(fuelCapacity);
		car.setMileage(mileage);
		car.setRoadTax(roadTax);
		car.setOnRoadPrice(onRoadPrice);
		car.setShowRoomPrice(showRoomPrice);
		car.setCreatedTime(createdTime);
		car.setCreatedBy(createdBy);
		car.setImagePath(imagePath);
		car.setVehicleId(carId);

		car.setCarId(carId);
		car.setAc(ac);
		car.setAccessorykit(accessorykit);
		car.setPowerSteering(powerSteering);
		request.setAttribute("car", car);
		VehicleService iService = VehicleService.getInstance();
		/*
		 * Connection connection = null; VehicleFacade vehicleFacade; try {
		 * connection = iService.getConnetion(); } catch (VehicleSystemException
		 * e) {
		 * System.out.println("Coult not create connection with database, [" +
		 * e.getMessage() + "]"); } vehicleFacade = VehicleFacade.getInstance();
		 */
		try {
			iService.editCar(car);
			String message = "Car edited successfully";
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/Specification.jsp").forward(
					request, response);
		} catch (VehicleSystemException e) {
			String message = e.getMessage();
			System.out.println("Coult not edit the car, [" + e.getMessage()
					+ "]");
			request.setAttribute("message", message);
			request.getRequestDispatcher("./view/EditCar.jsp").forward(request,
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
