/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name   EmployeeModel 
 * @description this is the model class for employee which can be converted to JSon and vice-versa
 */
package com.employeehelper;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class EmployeeModel {
	@JsonIgnore
	private String employeeName;
	@JsonIgnore
	private int employeeId;
	@JsonIgnore
	private String emailId;
	private String dateOfBirth;
	private String dateOfJoining;
	@JsonIgnore
	private double ctcPerAnnum;

	/**
	 * @return the employeeName
	 */
	@JsonProperty
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 *            the employeeName to set
	 */
	@JsonProperty
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employeeId
	 */
	@JsonProperty
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	@JsonProperty
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the emailId
	 */
	@JsonProperty
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	@JsonIgnore
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the dateOfBirth
	 */
	@JsonIgnore
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	@JsonProperty
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the dateOfJoining
	 */
	@JsonProperty
	public String getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining
	 *            the dateOfJoining to set
	 */
	@JsonIgnore
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the ctcPerAnnum
	 */
	@JsonIgnore
	public double getCtcPerAnnum() {
		return ctcPerAnnum;
	}

	/**
	 * @param ctcPerAnnum
	 *            the ctcPerAnnum to set
	 */
	@JsonIgnore
	public void setCtcPerAnnum(double ctcPerAnnum) {
		this.ctcPerAnnum = ctcPerAnnum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", emailId=" + emailId
				+ ", dateOfBirth=" + dateOfBirth + ", dateOfJoining="
				+ dateOfJoining + ", ctcPerAnnum=" + ctcPerAnnum
				+ ", employeeName=" + employeeName + "]";
	}

}
