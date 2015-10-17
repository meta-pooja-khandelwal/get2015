/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name AnnotationRunnerMain 
 * @description this is the main class to execute the methods of AnnotationRunner class
 */
package com.main;

import com.annotationparser.TestAnnotationParser;
import com.employeehelper.AnnotationRunner;

public class AnnotationRunnerMain {
	public static void main(String[] args) throws Exception {
		TestAnnotationParser parser = new TestAnnotationParser();
		parser.parse(AnnotationRunner.class);
	}
}
