/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name LoggerWrapper
 * @description this is the class which has the methods for creating custom log file and to log exceptions into that log file
 * 
 */
package com.logger;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerWrapper {
	private static FileHandler myFileHandler;
	public static final Logger myLogger = Logger.getLogger("LoggerMain");
	private static LoggerWrapper instance = null;

	/**
	 * @name LoggerWrapper()
	 * @description it is a private constructor of LoggerWrapper class so that
	 *              its instance can't be created by outSide world
	 * @param
	 */
	private LoggerWrapper() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if instance is null and return the instance
	 *              of LoggerWrapper class whenever required
	 * @param
	 * @return instance(object/instance of LoggerWrapper class)
	 */
	public static LoggerWrapper getInstance() {
		if (instance == null) {
			prepareLogger();
			instance = new LoggerWrapper();
		}
		return instance;
	}

	/**
	 * @name log()
	 * @description here check if the file you're using has been changed! If so,
	 *              re-do the file setting and then log the exceptions into that
	 *              file
	 * @param level
	 *            (level of exception)
	 * @param message
	 *            (exception message)
	 * @return
	 * 
	 */
	public void log(Level level, String message) {
		myLogger.removeHandler(myFileHandler);
		myLogger.log(level, message);
	}

	/**
	 * @name prepareLogger()
	 * @description it will create the custom log file(logFile.log) if it does
	 *              not exists,then set the level for a log file(level defines
	 *              the exception level which can be stored by the log file)
	 * @param
	 * @return
	 */
	private static void prepareLogger() {
		try {
			myFileHandler = new FileHandler(
					"C:\\Users\\Pooja\\training\\DesignPattern_Session-1\\src\\com\\logger\\logFile.log",
					true);
			myFileHandler.setFormatter(new SimpleFormatter());
			myLogger.addHandler(myFileHandler);
			myLogger.setUseParentHandlers(false);
			myLogger.setLevel(Level.ALL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
