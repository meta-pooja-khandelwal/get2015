/**
 * @author Pooja Khandelwal
 * @createdDate 15/10/2015
 * @name Employee
 * @description this class is a model class that will set the attributes of employee
 */
package com.models;

import java.util.Date;

public class Employee {
	private String name;
	private String email;
	private int age;
	private int employeeID;
	private java.util.Date dateOfRegistration;

	public Employee(String name, String email, int age, int employeeID,
			Date dateOfRegistration) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.employeeID = employeeID;
		this.dateOfRegistration = dateOfRegistration;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		String employee = "[Employee Id=" + employeeID + ",Name=" + name
				+ ",Email=" + email + ",Age=" + age + ","
				+ " Date Of Registration=" + dateOfRegistration + "]";
		return employee;

	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getRegisteredEmployees() {
		String registerdEmployee = "[Employee Id=" + employeeID + ",Name="
				+ name + ",Email=" + email + "]";
		return registerdEmployee;

	}
}
