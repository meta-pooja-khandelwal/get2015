/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name EmployeeUsingEnhancementRequestAnnotationMain 
 * @description this is the main class to execute the methods of EmployeeUsingAnnotations class
 */
package com.main;

import com.annotationparser.TestAnnotationParser;
import com.employeehelper.EmployeeUsingAnnotations;

public class EmployeeUsingEnhancementRequestAnnotationMain {
	public static void main(String[] args) throws Exception {
		TestAnnotationParser parser = new TestAnnotationParser();
		parser.parse(EmployeeUsingAnnotations.class);
	}
}
