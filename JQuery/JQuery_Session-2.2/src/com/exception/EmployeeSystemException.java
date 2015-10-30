package com.exception;

public class EmployeeSystemException extends EmployeeException {
	public EmployeeSystemException(String message) {
		super(message);
	}

	public EmployeeSystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
