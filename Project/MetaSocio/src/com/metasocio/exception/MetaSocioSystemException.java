package com.metasocio.exception;

/********************************************************
 * @name MetaSocioSystemException
 * @description It will handle all the system exceptions
 ********************************************************/
@SuppressWarnings("serial")
public class MetaSocioSystemException extends MetaSocioException {
	public MetaSocioSystemException(String message) {
		super(message);
	}

	public MetaSocioSystemException(String message, Throwable cause) {
		super(message, cause);
	}
}
