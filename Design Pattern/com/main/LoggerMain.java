/**
 * @author Pooja Khandelwal
 * @created date :21/10/2015
 * @name LoggerMain
 * @description this is the class which handles the logging of exceptions into custom log file
 * 
 */
package com.main;

import java.util.logging.Level;

import com.logger.LoggerWrapper;

public class LoggerMain {
	public static void main(String args[]) {
		LoggerWrapper loggerWrapper = LoggerWrapper.getInstance();
		try {
			int c = 5 / 0;
		} catch (Exception e) {
			LoggerWrapper.myLogger.log(Level.SEVERE,
					"some text here" + e.fillInStackTrace());
		}
		try {
			int c = 5 / 0;
		} catch (Exception e) {
			LoggerWrapper.myLogger.log(Level.SEVERE,
					"some text here" + e.fillInStackTrace());

		}
	}
}
