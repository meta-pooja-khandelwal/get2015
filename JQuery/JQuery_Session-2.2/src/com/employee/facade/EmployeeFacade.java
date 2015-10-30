package com.employee.facade;

import java.sql.Connection;
import java.sql.SQLException;

import com.employee.db.EmployeeDao;
import com.employee.service.EmployeeService;
import com.exception.EmployeeSystemException;
import com.model.Employee;

public class EmployeeFacade {
	private static EmployeeFacade employeeFacade;

	private EmployeeFacade() {

	}

	public static EmployeeFacade getInstance() {
		if (employeeFacade == null) {
			employeeFacade = new EmployeeFacade();

		}
		return employeeFacade;
	}

	public Employee getEmployeeDetails(String name, Connection connection)
			throws EmployeeSystemException, SQLException {
		// TODO Auto-generated method stub
		EmployeeDao employeeDao = EmployeeDao.getInstance();
		Employee employee;
		employee = employeeDao.fetchEmployeeDetails(name, connection);
		return employee;
	}
}
