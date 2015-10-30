/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name Developer
 * @description this is the class which implements IEmployee interface and provide the definition to its override methods
 * 
 */
package com.employee;

public class Developer implements IEmployee {
	private String name;
	private String role;

	/**
	 * @name Developer()
	 * @description it is a private constructor of Developer class so that its
	 *              instance can't be created by outSide world
	 * @param
	 */
	private Developer() {
	}

	/**
	 * @name getInstance()
	 * @description it will create and return the instance of Developer class
	 *              whenever required
	 * @param
	 * @return iDeveloper(object/instance of Developer class)
	 */
	public static Developer getInstance() {
		Developer iDeveloper = new Developer();
		return iDeveloper;

	}

	// create new Employee
	@Override
	public void createEmployee(String name, String role) {
		this.name = name;
		this.role = role;

	}

	// return the employee:-developer as string
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String developer = "[Employee Type:-" + EmployeeType.developer
				+ ",Name:-" + name + ",Role:-" + role + "]";
		return developer;
	}

}
