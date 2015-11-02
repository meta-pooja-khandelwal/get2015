/**
 * @author Pooja Khandelwal
 * @created by 02/11/2015
 * @name EmployeeSystemException
 * @description it will handle all the exceptions 
 */
package com.employee.exception;

public class EmployeeSystemException extends EmployeeException {
	private static final long serialVersionUID = 1L;

	public EmployeeSystemException(String message) {
		super(message);
	}

	public EmployeeSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
