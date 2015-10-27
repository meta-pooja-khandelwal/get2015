/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name VehicleSystemException
 * @description It will handle all the system exceptions
 */
package com.vehicle.exception;

public class VehicleSystemException extends VehicleException {

	public VehicleSystemException(String message) {
		super(message);
	}

	public VehicleSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
