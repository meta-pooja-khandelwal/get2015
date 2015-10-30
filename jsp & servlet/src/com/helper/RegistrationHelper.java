/**
 * @author Pooja Khandelwal
 * @created date 13/10/2015
 * @name RegistrationHelper
 * @description helper class(pojo class) having all the attributes of registration form and have getter-setters to set and get the data of registration 
 */
package com.helper;

public class RegistrationHelper {
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private double age;
	private String state;
	private String city;
	private String address;
	private String email;

	/**
	 * @name getEmail()
	 * @description return email
	 * @param
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @name setEmail()
	 * @description set email
	 * @param email
	 * @return
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @name getLastName()
	 * @description return lastName
	 * @param
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @name setLastName()
	 * @description set lastName
	 * @param lastName
	 * @return
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @name getPassword()
	 * @description return password
	 * @param
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @name setPassword()
	 * @description set password
	 * @param password
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @name getConfirmPassword()
	 * @description return confirmPassword
	 * @param
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @name setConfirmPassword()
	 * @description set confirmPassword
	 * @param confirmPassword
	 * @return
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @name getAge()
	 * @description return age
	 * @param
	 * @return age
	 */
	public double getAge() {
		return age;
	}

	/**
	 * @name setAge()
	 * @description set age
	 * @param age
	 * @return
	 */
	public void setAge(double age) {
		this.age = age;
	}

	/**
	 * @name getState()
	 * @description return state
	 * @param
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @name setState()
	 * @description set state
	 * @param state
	 * @return
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @name getCity()
	 * @description return city
	 * @param
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @name setCity()
	 * @description set city
	 * @param city
	 * @return
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @name getAddress()
	 * @description return address
	 * @param
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @name setAddress()
	 * @description set address
	 * @param address
	 * @return
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @name getFirstName()
	 * @description return firstName
	 * @param
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @name setFirstName()
	 * @description set firstName
	 * @param firstName
	 * @return
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
