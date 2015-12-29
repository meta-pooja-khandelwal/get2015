package com.metasocio.exception;

/**************************************************
 * @name MetaSocioException
 * @description It will handle all the exceptions
 **************************************************/
@SuppressWarnings("serial")
public class MetaSocioException extends Exception {
	public MetaSocioException(String message) {
		super(message);
	}

	public MetaSocioException(String message, Throwable cause) {
		super(message, cause);
	}
}
