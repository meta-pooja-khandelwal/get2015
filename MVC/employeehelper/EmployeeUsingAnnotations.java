/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name   EmployeeUsingAnnotations 
 * @description this is the class having some methods using custom annotation (EnhancementRequest)  
 */
package com.employeehelper;

import com.annotations.EnhancementRequest;

public class EmployeeUsingAnnotations {

	@EnhancementRequest(id = 1, synopsis = "Pooja")
	public static void employeeDetail(int id, String synopsis, String engineer,
			String date) {
		System.out.println("Hii" + id + "->" + synopsis + "->" + engineer
				+ "->" + date);
	}

	@EnhancementRequest(id = 1, synopsis = "Pooja", engineer = "software", date = "10/14/2015")
	public static void employeeDetails(int id, String synopsis,
			String engineer, String date) {
		System.out.println("Hii" + id + "->" + synopsis + "->" + engineer
				+ "->" + date);
	}

	public static void employeeDetail() {
		System.out.println("Not Annotation");
	}
}
