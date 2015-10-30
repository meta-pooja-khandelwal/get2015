package com.employee.db;

/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name ConnectionFactory
 * @description It will provide the connection object for database and have function to close the connection when transactions are completed
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exception.EmployeeSystemException;

public class ConnectionFactory {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/EmployeeManagement";
	private static final String USER = "root";
	private static final String PASSWORD = "mysql";

	/**
	 * @name getConnection()
	 * @description it will provide connection object to connect with database
	 * @param
	 * @return connection
	 * @throws VehicleSystemException
	 */
	public static Connection getConnection() throws EmployeeSystemException {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.out
					.println("Could not load driver class, please add mysql jdbc driver to classpath.");
			throw new EmployeeSystemException(
					"Could not load driver class, please add mysql jdbc driver to classpath.",
					e);
		} catch (SQLException e) {
			System.out.println("Coult not create connection with database, ["
					+ e.getMessage() + "]");
			throw new EmployeeSystemException(
					"Coult not create connection with database, ["
							+ e.getMessage() + "]", e);
		}
		return connection;
	}

	/**
	 * @name closeConnection
	 * @description it will close the connection
	 * @param connection
	 * @return
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out
						.println("Could not close connection due to following error, ["
								+ e.getMessage() + "]");
			}
		}
	}
}
