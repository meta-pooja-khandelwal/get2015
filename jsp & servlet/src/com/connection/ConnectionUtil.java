/**
 * @author Pooja Khandelwal
 * @createdDate 13/10/2015
 * @name ConnectionUtil
 * @description this class load the driver and  set the connection between java code and database
 */
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.exception.ExceptionHandler;

public class ConnectionUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Registration";
	private static final String USER = "root";
	private static final String PASSWORD = "mysql";
	private static Connection connection = null;

	/**
	 * @name getConnection()
	 * @description this method will return get & return connection object
	 * @param
	 * @return connection(reference of Connection interface)
	 */
	public static Connection getConnection() {

		/* register driver */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			String error = "error when loading driver-Class not found";
			ExceptionHandler.errorOnGetconnection(error);
		}

		/* open connection */
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			String error = "error when getting connection-Database not found";
			ExceptionHandler.errorOnGetconnection(error);
		}
		return connection;
	}
}
