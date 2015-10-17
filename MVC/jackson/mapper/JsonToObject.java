/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name JsonToObject 
 * @description this is the class having method to convert JSon file to employee object
 */
package com.jackson.mapper;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.employeehelper.EmployeeModel;
import com.exception_handler.ExceptionHandler;

public class JsonToObject {
	/**
	 * @name convertJsonToObject()
	 * @description this method converts JSon file to employee object
	 * @param
	 * @return
	 */
	public static void convertJsonToObject() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			EmployeeModel employee = mapper
					.readValue(
							new File(
									"c:\\Users\\Pooja\\training\\MVC_Session-2\\src\\com\\jackson\\files\\employee.json"),
							EmployeeModel.class);
			System.out.println(employee.toString());
		} catch (JsonGenerationException e) {
			ExceptionHandler.handleException(e.getMessage());

		} catch (JsonMappingException e) {

			ExceptionHandler.handleException(e.getMessage());

		} catch (IOException e) {

			ExceptionHandler.handleException(e.getMessage());

		}

	}

}
