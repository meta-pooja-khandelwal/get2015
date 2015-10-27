/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name VehicleFacade
 * @description It is  the layer between VehicleDao and user request.It will call the methods in VehicleDao to perform database operations
 */
package com.vehicle.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.vehicle.db.VehicleDao;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.model.Car;
import com.vehicle.model.Login;

public class VehicleFacade {
	private static VehicleFacade vehicleFacade;

	/**
	 * @name VehicleFacade()
	 * @description it is a private constructor of VehicleFacade class ,so that
	 *              its object can't be created with new operator by outside
	 *              world
	 */
	private VehicleFacade() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if null and provide the object of
	 *              VehicleFacade
	 * @param
	 * @return vehicleFacade(instance of VehicleFacade class)
	 */
	public static VehicleFacade getInstance() {

		if (vehicleFacade == null) {
			vehicleFacade = new VehicleFacade();
		}
		return vehicleFacade;
	}

	/**
	 * @name getLoginModel
	 * @description it will find authentication data from Login table of
	 *              VehicleDekho database
	 * @param connection
	 * @return login(Login model)
	 * @throws VehicleSystemException
	 */
	public Login getLoginModel(Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		Login login;
		try {
			login = vehicleDao.FetchLoginData(connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
		return login;
	}

	/**
	 * @name createCar()
	 * @description it will insert the new car data into Car table of
	 *              VehicleDekho database
	 * @param vehicle
	 *            (object of Car)
	 * @param connection
	 * @throws VehicleSystemException
	 */
	public void createCar(Car vehicle, Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		try {
			vehicleDao.createVehicle(vehicle, connection);
			int id = vehicleDao.getId(connection);
			vehicleDao.createCar(id, vehicle, connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
	}

	/**
	 * @name findAllMakesOfCar()
	 * @description it will fetch all the distinct companies of car from Vehicle
	 *              table of VehicleDekho database
	 * @param connection
	 * @return makes(list of all the distinct companies of car)
	 * @throws VehicleSystemException
	 */
	public List<String> findAllMakesOfCar(Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		List<String> makes = new ArrayList<String>();
		try {
			makes = vehicleDao.fetchMakesOfcar(connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
		return makes;
	}

	/**
	 * @name findMinBudgetOfCar()
	 * @description it will fetch the minimum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database
	 * @param connection
	 * @return minBudgetOfcar(min showRoom price from all the cars)
	 * @throws VehicleSystemException
	 */
	public int findMinBudgetOfCar(Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		int minBudgetOfCar;
		try {
			minBudgetOfCar = vehicleDao.fetchMinBudgetOfCar(connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
		return minBudgetOfCar;
	}

	/**
	 * @name findMaxBudgetOfCar()
	 * @description it will fetch the maximum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database
	 * @param connection
	 * @return maxBudgetOfcar(max showRoom price from all the cars)
	 * @throws VehicleSystemException
	 */
	public int findMaxBudgetOfCar(Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		int maxBudgetOfCar;
		try {
			maxBudgetOfCar = vehicleDao.fetchMaxBudgetOfCar(connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
		return maxBudgetOfCar;
	}

	/**
	 * @name getCarData()
	 * @description it will fetch the details of specified car from Vehicle and
	 *              car table of VehicleDekho database on the basis of
	 *              make(company name) and budget
	 * @param make
	 *            (company name)
	 * @param budget
	 * @param connection
	 * @return carList(list of selected cars)
	 * @throws VehicleSystemException
	 */
	public List<Car> getCarData(String make, int budget, Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		List<Car> carList;
		try {
			carList = vehicleDao.fetchCarData(make, budget, connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
		return carList;
	}

	/**
	 * @name editCar()
	 * @description it will edit the data of specified car from Car table of
	 *              VehicleDekho database
	 * @param vehicle
	 *            (car which has to be edited)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 */
	public void editCar(Car vehicle, Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		try {
			vehicleDao.editVehicle(vehicle, connection);
			vehicleDao.editCar(vehicle, connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}
	}

	/**
	 * @name deleteCar()
	 * @description it will delete the specified car from Car table of
	 *              VehicleDekho database
	 * @param vehicleId
	 *            (vehicleId of car which has to be deleted)
	 * @param carId
	 *            (carId of car which has to be deleted)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 */
	public void deleteCar(int vehicleId, int carId, Connection connection)
			throws VehicleSystemException {
		VehicleDao vehicleDao = VehicleDao.getInstance();
		try {
			vehicleDao.deleteCar(vehicleId, carId, connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new VehicleSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
			System.out
					.println("Transaction roll back,[" + e.getMessage() + "]");
			throw new VehicleSystemException("Transaction roll back,["
					+ e.getMessage() + "]", e);
		}

	}
}
