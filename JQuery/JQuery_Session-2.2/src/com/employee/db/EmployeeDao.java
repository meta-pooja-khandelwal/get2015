package com.employee.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exception.EmployeeException;
import com.exception.EmployeeSystemException;
import com.model.Employee;

public class EmployeeDao {
	private static EmployeeDao employeeDao;

	/**
	 * @name VehicleDao()
	 * @description it is a private constructor of VehicleDao class ,so that its
	 *              object can't be created with new operator by outside world
	 */
	private EmployeeDao() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if null and provide the object of VehicleDao
	 * @param
	 * @return vehicleDao(instance of VehicleDao class)
	 */
	public static EmployeeDao getInstance() {
		if (employeeDao == null) {
			employeeDao = new EmployeeDao();
		}
		return employeeDao;
	}

	public Employee fetchEmployeeDetails(String name, Connection connection)
			throws SQLException, EmployeeSystemException {
		String query = "Select * From employee where name=?";

		ResultSet resultSet = null;
		Employee employee = new Employee();
		PreparedStatement statement = null;
		try {
			/* creating statement to execute the query */
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			/* executing select query & getting data from table in result set */
			resultSet = statement.executeQuery();

			int count = 0;
			/* getting data fron result set */
			while (resultSet.next()) {
				employee.setName(resultSet.getString("name"));
				employee.setDateOfBirth(resultSet.getString("dateOfBirth"));
				employee.setEmail(resultSet.getString("email"));
				employee.setAddress(resultSet.getString("address"));

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
				throw new EmployeeSystemException("[" + e.getMessage() + "]", e);
			}
		}
		return employee;

	}
}
