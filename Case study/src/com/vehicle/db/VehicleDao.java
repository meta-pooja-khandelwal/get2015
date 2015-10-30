/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name VehicleDao
 * @description It will handle all the database query and execute them 
 */
package com.vehicle.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vehicle.VehicleType;
import com.vehicle.exception.VehicleSystemException;
import com.vehicle.factory.VehicleFactory;
import com.vehicle.model.Car;
import com.vehicle.model.Login;
import com.vehicle.model.Vehicle;

public class VehicleDao {
	private static VehicleDao vehicleDao;

	/**
	 * @name VehicleDao()
	 * @description it is a private constructor of VehicleDao class ,so that its
	 *              object can't be created with new operator by outside world
	 */
	private VehicleDao() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if null and provide the object of VehicleDao
	 * @param
	 * @return vehicleDao(instance of VehicleDao class)
	 */
	public static VehicleDao getInstance() {
		if (vehicleDao == null) {
			vehicleDao = new VehicleDao();
		}
		return vehicleDao;
	}

	/**
	 * @name FetchLoginData
	 * @description it will fetch authentication data from Login table of
	 *              VehicleDekho database
	 * @param connection
	 * @return login(Login model)
	 * @throws VehicleSystemException
	 */
	public Login FetchLoginData(Connection connection)
			throws VehicleSystemException {
		String query = "select *from login";
		Statement statement = null;
		ResultSet resultSet = null;
		Login login = new Login();
		try {
			/* creating statement to execute the query */
			statement = connection.createStatement();
			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery(query);
			/* getting data fron result set */
			if (resultSet.next()) {
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				login.setEmail(email);
				login.setPassword(password);
			}
		} catch (SQLException e) {
			System.out.println("Can't fetch login data from database, ["
					+ e.getMessage() + "]");
			throw new VehicleSystemException(
					"Can't fetch login data from database, [" + e.getMessage()
							+ "]", e);
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return login;
	}

	/**
	 * @param id
	 * @name createCar()
	 * @description it will insert the new car data into Car table of
	 *              VehicleDekho database
	 * @param vehicle
	 *            (object of Car)
	 * @param connection
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public void createCar(int id, Car car, Connection connection)
			throws VehicleSystemException, SQLException {

		String query = "INSERT INTO car(carId,ac,powerSteering,accessoryKit)"
				+ "VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		int carId = id;
		String ac = car.getAc();
		String powerSteering = car.getPowerSteering();
		String accessoryKit = car.getAccessorykit();
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);
			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setInt(1, carId);
			preparedStatement.setString(2, ac);
			preparedStatement.setString(3, powerSteering);
			preparedStatement.setString(4, accessoryKit);
			preparedStatement.executeUpdate();
		} finally {
			try {
				/* close prepared statement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
	}

	/**
	 * @name createVehicle()
	 * @description it will insert the new car data into Vehicle table of
	 *              VehicleDekho database
	 * @param vehicle
	 *            (object of Car)
	 * @param connection
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public void createVehicle(Car car, Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "INSERT INTO vehicle(make,model,engineInCC,fuelCapacity,mileage, showRoomPrice,roadTax,"
				+ "onRoadPrice,created_time,createdBy,imagePath)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		String make = car.getMake();
		String model = car.getModel();
		int engineInCC = car.getEngineInCc();
		int fuelCapacity = car.getFuelCapacity();
		int mileage = car.getMileage();
		int showRoomPrice = car.getShowRoomPrice();
		int roadTax = car.getRoadTax();
		int onRoadPrice = car.getOnRoadPrice();
		String created_time = car.getCreatedTime();
		String createdBy = car.getCreatedBy();
		String imagePath = car.getImagePath();
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);
			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setString(1, make);
			preparedStatement.setString(2, model);
			preparedStatement.setInt(3, engineInCC);
			preparedStatement.setInt(4, fuelCapacity);
			preparedStatement.setInt(5, mileage);
			preparedStatement.setInt(6, showRoomPrice);
			preparedStatement.setInt(7, roadTax);
			preparedStatement.setInt(8, onRoadPrice);
			preparedStatement.setString(9, created_time);
			preparedStatement.setString(10, createdBy);
			preparedStatement.setString(11, imagePath);
			preparedStatement.executeUpdate();
		} finally {
			try {
				/* close prepared statement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
	}

	/**
	 * @name getId()
	 * @description this method will find the new id for car
	 * @param connection
	 * @return id(latest id value in vehicle table)
	 * @throws VehicleSystemException
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public static int getId(Connection connection)
			throws VehicleSystemException, NumberFormatException, SQLException {
		/* select query to be executed to get the id */
		String query = "SELECT vehicleId FROM vehicle ORDER BY vehicleId DESC LIMIT 0,1";
		/* getting connection with database */
		Statement statement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			/* creating statement to execute the query */
			statement = connection.createStatement();
			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery(query);
			/* getting data fron result set */
			if (resultSet.next()) {
				String id1 = resultSet.getString("vehicleId");
				id = Integer.parseInt(id1);
			}
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (statement != null) {
					statement.close();
				}
				/* close connection */
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return id;
	}

	/**
	 * @name fetchMakesOfcar()
	 * @description it will fetch all the distinct companies of car from Vehicle
	 *              table of VehicleDekho database
	 * @param connection
	 * @return makes(list of all the distinct companies of car)
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public List<String> fetchMakesOfcar(Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "Select distinct v.make From vehicle v,car c where v.vehicleId=c.carId";
		Statement statement = null;
		ResultSet resultSet = null;
		List<String> makes = new ArrayList<String>();
		try {
			/* creating statement to execute the query */
			statement = connection.createStatement();

			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery(query);
			int count = 0;
			/* getting data fron result set */
			while (resultSet.next()) {
				String make = resultSet.getString("make");
				makes.add(make);
				// count++;
			}
			// System.out.println(count);
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (statement != null) {
					statement.close();
				}
				/* close connection */

			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return makes;

	}

	/**
	 * @name fetchMinBudgetOfCar()
	 * @description it will fetch the minimum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database
	 * @param connection
	 * @return minBudgetOfcar(min showRoom price from all the cars)
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public int fetchMinBudgetOfCar(Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "select min(v.showRoomPrice) as minBudget From vehicle v,car c where v.vehicleId=c.carId";
		Statement statement = null;
		ResultSet resultSet = null;
		int minBudgetOfcar = 0;
		try {
			/* creating statement to execute the query */
			statement = connection.createStatement();

			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery(query);

			/* getting data fron result set */
			if (resultSet.next()) {
				minBudgetOfcar = resultSet.getInt("minBudget");
			}
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (statement != null) {
					statement.close();
				}
				/* close connection */
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return minBudgetOfcar;
	}

	/**
	 * @name fetchMaxBudgetOfCar()
	 * @description it will fetch the maximum budget(showRoom Price) of car from
	 *              Vehicle table of VehicleDekho database
	 * @param connection
	 * @return maxBudgetOfcar(max showRoom price from all the cars)
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public int fetchMaxBudgetOfCar(Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "select max(v.showRoomPrice) as maxBudget From vehicle v,car c where v.vehicleId=c.carId";
		Statement statement = null;
		ResultSet resultSet = null;
		int maxBudgetOfcar = 0;
		try {
			/* creating statement to execute the query */
			statement = connection.createStatement();

			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery(query);

			/* getting data fron result set */
			if (resultSet.next()) {
				maxBudgetOfcar = resultSet.getInt("maxBudget");
			}
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (statement != null) {
					statement.close();
				}
				/* close connection */
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return maxBudgetOfcar;
	}

	/**
	 * @name fetchCarData()
	 * @description it will fetch the details of specified car from Vehicle and
	 *              car table of VehicleDekho database on the basis of
	 *              make(company name) and budget
	 * @param make
	 *            (company name)
	 * @param budget
	 * @param connection
	 * @return carList(list of selected cars)
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public List<Car> fetchCarData(String make, int budget, Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "select * from vehicle v,car c where v.vehicleId=c.carId And v.make=? and v.showRoomPrice<=?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Car> carList = new ArrayList<Car>();
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);
			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setString(1, make);
			preparedStatement.setInt(2, budget);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				VehicleFactory vehicleFactory = VehicleFactory.getInstance();
				Vehicle iVehicle = vehicleFactory.getVehicle(VehicleType.car);
				Car car = (Car) iVehicle;
				car.setVehicleId(resultSet.getInt("vehicleId"));
				car.setMake(resultSet.getString("make"));
				car.setModel(resultSet.getString("model"));
				car.setEngineInCc(resultSet.getInt("engineInCC"));
				car.setFuelCapacity(resultSet.getInt("fuelCapacity"));
				car.setMileage(resultSet.getInt("mileage"));
				car.setRoadTax(resultSet.getInt("roadTax"));
				car.setOnRoadPrice(resultSet.getInt("onRoadPrice"));
				car.setShowRoomPrice(resultSet.getInt("showRoomPrice"));
				car.setCreatedTime(resultSet.getString("created_time"));
				car.setCreatedBy(resultSet.getString("createdBy"));
				car.setImagePath(resultSet.getString("imagePath"));

				car.setCarId(resultSet.getInt("carId"));
				car.setAc(resultSet.getString("ac"));
				car.setAccessorykit(resultSet.getString("accessoryKit"));
				car.setPowerSteering(resultSet.getString("powerSteering"));
				carList.add(car);
			}
		} finally {
			try {
				/* close result set */
				if (resultSet != null) {
					resultSet.close();
				}
				/* close stetement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				/* close connection */
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
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
	public void editCar(Car car, Connection connection)
			throws VehicleSystemException {

		String query = "UPDATE car SET ac=?,powerSteering=?,accessoryKit=? WHERE carId=?";
		PreparedStatement preparedStatement = null;
		int carId = car.getCarId();
		String ac = car.getAc();
		String powerSteering = car.getPowerSteering();
		String accessoryKit = car.getAccessorykit();
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);
			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setString(1, ac);
			preparedStatement.setString(2, powerSteering);
			preparedStatement.setString(3, accessoryKit);
			preparedStatement.setInt(4, carId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Coult not Edit car table, [" + e.getMessage()
					+ "]");
			throw new VehicleSystemException("Coult not Edit car table, ["
					+ e.getMessage() + "]", e);
		} finally {
			try {
				/* close prepared statement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
	}

	/**
	 * @name editVehicle()
	 * @description it will edit the data of specified car from vehicle table of
	 *              VehicleDekho database
	 * @param vehicle
	 *            (car which has to be edited)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public void editVehicle(Car car, Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "UPDATE vehicle SET make=?,model=?,engineInCC=?,fuelCapacity=?,mileage=?,showRoomPrice=?,roadTax=?,onRoadPrice=?,created_time=?,createdBy=?,imagePath=? WHERE vehicleId=?";
		PreparedStatement preparedStatement = null;
		int vehicleId = car.getVehicleId();
		String make = car.getMake();
		String model = car.getModel();
		int engineInCC = car.getEngineInCc();
		int fuelCapacity = car.getFuelCapacity();
		int mileage = car.getMileage();
		int showRoomPrice = car.getShowRoomPrice();
		int roadTax = car.getRoadTax();
		int onRoadPrice = car.getOnRoadPrice();
		String created_time = car.getCreatedTime();
		String createdBy = car.getCreatedBy();
		String imagePath = car.getImagePath();
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);

			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setString(1, make);
			preparedStatement.setString(2, model);
			preparedStatement.setInt(3, engineInCC);
			preparedStatement.setInt(4, fuelCapacity);
			preparedStatement.setInt(5, mileage);
			preparedStatement.setInt(6, showRoomPrice);
			preparedStatement.setInt(7, roadTax);
			preparedStatement.setInt(8, onRoadPrice);
			preparedStatement.setString(9, created_time);
			preparedStatement.setString(10, createdBy);
			preparedStatement.setString(11, imagePath);
			preparedStatement.setInt(12, vehicleId);
			preparedStatement.executeUpdate();
		} finally {
			try {
				/* close prepared statement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}
	}

	/**
	 * @name deleteVehicle()
	 * @description it will delete the specified car from Vehicle table of
	 *              VehicleDekho database
	 * @param vehicleId
	 *            (vehicleId of car which has to be deleted)
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 * @throws SQLException
	 */
	public void deleteVehicle(int vehicleId, Connection connection)
			throws VehicleSystemException, SQLException {
		String query = "DELETE FROM vehicle WHERE vehicleId=?";
		PreparedStatement preparedStatement = null;
		try {
			/* creating prepared statement to execute the query */
			preparedStatement = connection.prepareStatement(query);

			/* setting the parameters in prepared statement for insert query */
			preparedStatement.setInt(1, vehicleId);
			preparedStatement.executeUpdate();
		} finally {
			try {
				/* close prepared statement */
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				System.out.println("[" + e.getMessage() + "]");
				throw new VehicleSystemException("[" + e.getMessage() + "]", e);
			}
		}

	}

}
