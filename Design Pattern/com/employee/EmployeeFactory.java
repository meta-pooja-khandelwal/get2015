/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name EmployeeFactory
 * @description this is the class which will provide the instance of either Developer or SalesPerson class based on input employee type
 * 
 */
package com.employee;

public class EmployeeFactory {
	/**
	 * @name getIEmployee()
	 * @description it will return the instance of either Developer or
	 *              SalesPerson class based on input employee type
	 * @param employeeType
	 *            (may be either developer or salesPerson)
	 * @return iEmployee(instance of either Developer or SalesPerson class )
	 */
	public static IEmployee getIEmployee(EmployeeType employeeType) {
		// TODO Auto-generated method stub
		IEmployee iEmployee;
		if (employeeType.equals(EmployeeType.developer)) {
			iEmployee = Developer.getInstance();

		} else if (employeeType.equals(EmployeeType.salesPerson)) {
			iEmployee = SalesPerson.getInstance();

		} else {
			iEmployee = null;
		}
		return iEmployee;
	}

}
