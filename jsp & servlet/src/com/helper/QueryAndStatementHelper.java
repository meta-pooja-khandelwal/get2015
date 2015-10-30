/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name QueryAndStatementHelper
 * @description this class will generate all the queries to be executed on registration database and create statements to execute all those queries
 */
package com.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.exception.ExceptionHandler;

public class QueryAndStatementHelper {
	/**
	 * @name PreparedStatement
	 *       getStatementToinsertRegistrationDataIntoDatabase()
	 * @description this function will create query and prepared statement to
	 *              insert registration data into login table of registration
	 *              database
	 * @param connection
	 *            (connection object)
	 * @param registrationHelper
	 *            (object of RegistrationHelper class having all the attributes
	 *            of a registration form)
	 * @return preparedStatement(statement that will execute the query)
	 */
	public static PreparedStatement getStatementToinsertRegistrationDataIntoDatabase(
			Connection connection, RegistrationHelper registrationHelper) {
		String query = "INSERT INTO Login (firstName,lastName,email,password,confirmPassword,age,state,city,address) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, registrationHelper.getFirstName());
			preparedStatement.setString(2, registrationHelper.getLastName());
			preparedStatement.setString(3, registrationHelper.getEmail());
			preparedStatement.setString(4, registrationHelper.getPassword());
			preparedStatement.setString(5,
					registrationHelper.getConfirmPassword());
			preparedStatement.setDouble(6, registrationHelper.getAge());
			preparedStatement.setString(7, registrationHelper.getState());
			preparedStatement.setString(8, registrationHelper.getCity());
			preparedStatement.setString(9, registrationHelper.getAddress());
		} catch (SQLException e) {
			String error = "error When Creating Prepared Statement";
			ExceptionHandler.errorOnCreationOfPreparedStatement(error);
		}
		return preparedStatement;
	}

	/**
	 * @name getStatementToFetchLoginDataFromDatabase()
	 * @description this function will create query and prepared statement to
	 *              fetch data from login table of registration database
	 * @param connection
	 *            (connection object)
	 * @return preparedStatement(statement that will execute the query)
	 */
	public static PreparedStatement getStatementToFetchLoginDataFromDatabase(
			Connection connection) {
		// TODO Auto-generated method stub
		String query = "select email,password from login";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			String error = "error When Creating Prepared Statement";
			ExceptionHandler.errorOnCreationOfPreparedStatement(error);
		}
		return preparedStatement;
	}
}
