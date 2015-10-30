/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name DatabaseUtil
 * @description this class will execute all the query for registration database
 */
package com.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.connection.ConnectionUtil;
import com.exception.ExceptionHandler;

public class DatabaseUtil {
	/**
	 * @name insertRegistrationDataIntoDatabase()
	 * @description this method will insert registration data into login table
	 *              of registration database
	 * @param registrationHelper
	 *            (object of RegistrationHelper class which contains all the
	 *            attributes of registration form)
	 * @return isInserted(may be either true or false)
	 */
	public boolean insertRegistrationDataIntoDatabase(
			RegistrationHelper registrationHelper) {
		boolean isInserted = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = QueryAndStatementHelper
					.getStatementToinsertRegistrationDataIntoDatabase(
							connection, registrationHelper);
			preparedStatement.executeUpdate();
			isInserted = true;
		} catch (Exception exception) {
			isInserted = false;
			return isInserted;
		} finally {
			/* close connection */
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				isInserted = false;
				String error = "error while closing connection or prepared statement";
				ExceptionHandler.errorOnCloseConnection(error);
			}
		}
		return isInserted;
	}

	/**
	 * @name fetchLoginDataFromDatabase()
	 * @description this method will fetch data from login table of registration
	 *              database
	 * @param
	 * @return registrationHelpersList(contains all objects of
	 *         RegistrationHelper class,each object is corresponds to a row
	 *         which is selected from database
	 */
	public List<RegistrationHelper> fetchLoginDataFromDatabase() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = QueryAndStatementHelper
					.getStatementToFetchLoginDataFromDatabase(connection);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception exception) {
			String error = "error when fetching login data from database";
			ExceptionHandler.errorOnFetchLoginDataFromDatabase(error);
		}
		List<RegistrationHelper> registrationHelpersList = new ArrayList<RegistrationHelper>();
		try {
			while (resultSet.next()) {
				String emailInDatabase = resultSet.getString("email");
				String passwordInDatabase = resultSet.getString("password");

				RegistrationHelper registrationHelper = new RegistrationHelper();
				registrationHelper.setEmail(emailInDatabase);
				registrationHelper.setPassword(passwordInDatabase);
				registrationHelpersList.add(registrationHelper);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String error = "error while iteratting result set";
			ExceptionHandler.errorOnIterationOfResultSet(error);
		} finally {
			/* close connection */
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				String error = "error while closing connection or prepared statement";
				ExceptionHandler.errorOnCloseConnection(error);
			}
		}
		return registrationHelpersList;
	}
}
