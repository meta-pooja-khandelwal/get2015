/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Company
 * @description this is the singleton class which have functions to add the new employee in company and to show the all employees list of company 
 */
package com.company;

import java.util.ArrayList;
import java.util.List;
import com.employee.IEmployee;

public class Company {
	private static List<IEmployee> employees = new ArrayList<IEmployee>();
	private static Company instance = null;

	/**
	 * @name Company()
	 * @description it is a private constructor of Company class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private Company() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of Company class whenever required
	 * @param
	 * @return instance(object/instance of Company class)
	 */
	public static Company getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {

			instance = new Company();
		}
		return instance;
	}

	/**
	 * @name addEmployee()
	 * @description it will add the new employee in the employee List of company
	 * @param iEmployee
	 *            (object of either Developer or SalesPerson class)
	 * @return
	 */
	public void addEmployee(IEmployee iEmployee) {
		employees.add(iEmployee);

	}

	/**
	 * @name getEmployeesList()
	 * @description it will provide the list of all employees of company
	 *              whenever it is required
	 * @param
	 * @return employees(list of all employees of company)
	 */
	public List<IEmployee> getEmployeesList() {
		return employees;

	}

}
