/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name VehicleException
 * @description It will handle all the exceptions
 */
package com.vehicle.exception;

public class VehicleException extends Exception {
	public VehicleException(String message) {
		super(message);
	}

	public VehicleException(String message, Throwable cause) {
		super(message, cause);
	}
}
