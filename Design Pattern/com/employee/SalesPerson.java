/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name SalesPerso
 * @description this is the class which implements IEmployee interface and provide the definition to its override methods
 * 
 */
package com.employee;

public class SalesPerson implements IEmployee {
	private String name;
	private String role;

	/**
	 * @name SalesPerson()
	 * @description it is a private constructor of SalesPerson class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private SalesPerson() {

	}

	/**
	 * @name getInstance()
	 * @description it will create and return the instance of SalesPerson class
	 *              whenever required
	 * @param
	 * @return iSalesPerson(object/instance of SalesPerson class)
	 */
	public static SalesPerson getInstance() {
		SalesPerson iSalesPerson = new SalesPerson();
		return iSalesPerson;

	}

	// create new Employee
	@Override
	public void createEmployee(String name, String role) {
		this.name = name;
		this.role = role;

	}

	// return the employee:-salesPerson as string
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String salesPerson = "[Employee Type:-" + EmployeeType.salesPerson
				+ ",Name:-" + name + ",Role:-" + role + "]";
		return salesPerson;
	}
}
