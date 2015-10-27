/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name VehicleService
 * @description It is  a singleton class which provides the connection object
 */
package com.vehicle.service;

import java.sql.Connection;
import com.vehicle.db.ConnectionFactory;
import com.vehicle.exception.VehicleSystemException;

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
	 * @name getConnetion()
	 * @description it will find the connection object from ConnetionFactory
	 * @param
	 * @return connection
	 * @throws VehicleSystemException
	 */
	public Connection getConnetion() throws VehicleSystemException {
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

		return connection;
	}

	/**
	 * @name closeConnetion()
	 * @description it will close the connetion in ConnetionFactory
	 * @param connection
	 * @return
	 * @throws VehicleSystemException
	 */
	public void closeConnetion(Connection connection) {
		ConnectionFactory.closeConnection(connection);

	}
}
