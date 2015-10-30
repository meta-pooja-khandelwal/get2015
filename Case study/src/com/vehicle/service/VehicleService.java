/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name VehicleService
 * @description It is  a singleton class which provides the connection object
 */
package com.vehicle.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.vehicle.db.ConnectionFactory;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.facade.VehicleFacade;
import com.vehicle.model.Car;
import com.vehicle.model.Login;

public class VehicleService {
	private static VehicleService iService;

	/**
	 * @name VehicleService()
	 * @description it is a private constructor of VehicleService class ,so that
	 *              its object can't be created with new operator by outside
	 *              world
	 */
	private VehicleService() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if null and provide the object of
	 *              VehicleService
	 * @param
	 * @return iService(instance of VehicleService class)
	 */
	public static VehicleService getInstance() {

		if (iService == null) {
			iService = new VehicleService();

		}
		return iService;
	}

	/**
	 * @name getLoginModel()
	 * @description it will find authentication data from Login table of
	 *              VehicleDekho database by calling the getLoginModel() method
	 *              of VehicleFacade
	 * @param connection
	 * @return login(Login model)
	 * @throws VehicleSystemException
	 */
	public Login getLoginModel() throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();
		Login login;
		try {
			login = vehicleFacade.getLoginModel(connection);

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
				System.out.println("could not get Login data,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"could not get Login data,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return login;
	}

	/**
	 * @name createCar()
	 * @description it will insert the new car data into Car table of
	 *              VehicleDekho database by calling the createCar() method of
	 *              VehicleFacade
	 * @param vehicle
	 *            (object of Car)
	 * @param connection
	 * @throws VehicleSystemException
	 */
	public void createCar(Car car) throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();
		try {
			vehicleFacade.createCar(car, connection);
			// int id = vehicleDao.getId(connection);
			// vehicleDao.createCar(id, vehicle, connection);
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
				System.out.println("[" + e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	/**
	 * @name findAllMakesOfCar()
	 * @description it will fetch all the distinct companies of car from Vehicle
	 *              table of VehicleDekho database by calling the
	 *              findAllMakesOfCar() method of VehicleFacade
	 * @param connection
	 * @return makes(list of all the distinct companies of car)
	 * @throws VehicleSystemException
	 */
	public List<String> findAllMakesOfCar() throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();

		// VehicleDao vehicleDao = VehicleDao.getInstance();
		List<String> makes;
		try {
			makes = vehicleFacade.findAllMakesOfCar(connection);
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
				System.out.println("Could not fetch makes of car,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not fetch makes of car,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return makes;
	}

	/**
	 * @name findMinBudgetOfCar()
	 * @description it will fetch the minimum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database by calling the
	 *              findMinBudgetOfCar() method of VehicleFacade
	 * @param connection
	 * @return minBudgetOfcar(min showRoom price from all the cars)
	 * @throws VehicleSystemException
	 */
	public int findMinBudgetOfCar() throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();

		// VehicleDao vehicleDao = VehicleDao.getInstance();
		int minBudgetOfCar;
		try {
			minBudgetOfCar = vehicleFacade.findMinBudgetOfCar(connection);
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
				System.out.println("Could not fetch min budget of cars,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not fetch min budget of cars,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}

		return minBudgetOfCar;
	}

	/**
	 * @name findMaxBudgetOfCar()
	 * @description it will fetch the maximum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database by calling the
	 *              findMaxBudgetOfCar() method of VehicleFacade
	 * @param connection
	 * @return maxBudgetOfcar(max showRoom price from all the cars)
	 * @throws VehicleSystemException
	 */
	public int findMaxBudgetOfCar() throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();

		// VehicleDao vehicleDao = VehicleDao.getInstance();
		int maxBudgetOfCar;
		try {
			maxBudgetOfCar = vehicleFacade.findMaxBudgetOfCar(connection);

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
				System.out.println("Could not fetch max budget of cars,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not fetch max budget of cars,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}

		return maxBudgetOfCar;
	}

	/**
	 * @name getCarData()
	 * @description it will fetch the details of specified car from Vehicle and
	 *              car table of VehicleDekho database on the basis of
	 *              make(company name) and budget by calling the getCarData()
	 *              method of VehicleFacade
	 * @param make
	 *            (company name)
	 * @param budget
	 * @param connection
	 * @return carList(list of selected cars)
	 * @throws VehicleSystemException
	 */
	public List<Car> getCarData(String make, int budget)
			throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();
		List<Car> carList;
		try {
			carList = vehicleFacade.getCarData(make, budget, connection);

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
				System.out.println("Could not get the car data,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not get the car data,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return carList;
	}

	/**
	 * @name editCar()
	 * @description it will edit the data of specified car from Car table of
	 *              VehicleDekho database by calling the editCar() method of
	 *              VehicleFacade
	 * @param vehicle
	 *            (car which has to be edited)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 */
	public void editCar(Car vehicle) throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();

		// VehicleDao vehicleDao = VehicleDao.getInstance();
		try {
			vehicleFacade.editCar(vehicle, connection);
			// vehicleDao.editCar(vehicle, connection);
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
				System.out.println("Could not edit the car data,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not edit the car data,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}

	}

	/**
	 * @name deleteCar()
	 * @description it will delete the specified car from Car table of
	 *              VehicleDekho database by calling the deleteCar() method of
	 *              VehicleFacade
	 * @param vehicleId
	 *            (vehicleId of car which has to be deleted)
	 * @param carId
	 *            (carId of car which has to be deleted)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 */
	public void deleteCar(int vehicleId) throws VehicleSystemException {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (VehicleSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		VehicleFacade vehicleFacade = VehicleFacade.getInstance();
		// VehicleDao vehicleDao = VehicleDao.getInstance();
		try {
			vehicleFacade.deleteCar(vehicleId, connection);
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
				System.out.println("Could not delete the car data,["
						+ e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new VehicleSystemException(
						"Could not delete the car data,Transaction roll back,["
								+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new VehicleSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}

		} finally {
			ConnectionFactory.closeConnection(connection);
		}

	}

}
