package com.employee.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.employee.db.ConnectionFactory;
import com.employee.facade.EmployeeFacade;
import com.exception.EmployeeSystemException;
import com.model.Employee;

public class EmployeeService {
	private static EmployeeService iService;

	private EmployeeService() {

	}

	public static EmployeeService getInstance() {
		if (iService == null) {
			iService = new EmployeeService();

		}
		return iService;
	}

	public Employee getEmployeeDetails(String name)
			throws EmployeeSystemException {
		Employee employee;
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();

		} catch (EmployeeSystemException e) {
			System.out.println("Coult not create connection with database ["
					+ e.getMessage() + "]");
			throw new EmployeeSystemException(
					"Coult not create connection with database ["
							+ e.getMessage() + "]", e);
		}
		EmployeeFacade employeeFacade = EmployeeFacade.getInstance();
		try {
			employee = employeeFacade.getEmployeeDetails(name, connection);
			// int id = vehicleDao.getId(connection);
			// vehicleDao.createCar(id, vehicle, connection);
			try {
				connection.commit();
			} catch (SQLException e) {
				System.out.println("Coult not commit the transaction, ["
						+ e.getMessage() + "]");
				throw new EmployeeSystemException(
						"Coult not commit the transaction, [" + e.getMessage()
								+ "]", e);
			}
		} catch (Exception e) {
			try {
				System.out.println("[" + e.getMessage() + "]");
				connection.rollback();
				System.out.println("Transaction roll back");
				throw new EmployeeSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (SQLException e1) {
				System.out.println("Error in Transaction roll back,["
						+ e1.getMessage() + "]");
				throw new EmployeeSystemException(
						"Error in  Transaction roll back,[" + e1.getMessage()
								+ "]", e);
			}
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
		return employee;
	}
}
