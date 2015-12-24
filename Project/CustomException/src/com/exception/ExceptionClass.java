package com.exception;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class ExceptionClass extends Exception {
	// class in which exception occurs
	public static Class<?> temp;
	// logger object
	private static Logger logger;
	// error message
	private static String MSG;

	public ExceptionClass(String message) {
		super(message);
		logger.error(Thread.currentThread().getStackTrace()[2].getLineNumber());
		logger.error(MSG);
	}

	public ExceptionClass(String message, Throwable cause) {
		super(message, cause);
		logger.error(Thread.currentThread().getStackTrace()[2].getLineNumber());
		logger.error(MSG, cause);
	}

	public static void setClass(Class<?> temp1) {
		temp = temp1;
		logger = Logger.getLogger(temp);
		MSG = "Exception in " + temp;
	}
}
