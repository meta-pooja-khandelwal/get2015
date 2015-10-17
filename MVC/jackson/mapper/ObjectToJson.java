/**
 * @author Pooja Khandelwal
 * @created date 16/10/2015
 * @Name ObjectToJson 
 * @description this is the class having method to convert  employee object to JSon file
 */
package com.jackson.mapper;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.employeehelper.EmployeeModel;
import com.exception_handler.ExceptionHandler;

public class ObjectToJson {
	/**
	 * @name convertObjectToJson()
	 * @description this method converts employee object to JSon file
	 * @param
	 * @return
	 */
	public static void convertObjectToJson() {
		EmployeeModel employee = new EmployeeModel();
		employee.setEmployeeName("pooja");
		employee.setEmployeeId(101);
		employee.setCtcPerAnnum(100000000);
		employee.setDateOfBirth("08/03/1995");
		employee.setEmailId("pooja@gmail.com");
		employee.setDateOfJoining("03/08/2015");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(
					new File(
							"c:\\Users\\Pooja\\training\\MVC_Session-2\\src\\com\\jackson\\files\\employeeJsonFromEmployeeModel.json"),
					employee);
			System.out.println(mapper.writeValueAsString(employee));
		} catch (JsonGenerationException e) {
			ExceptionHandler.handleException(e.getMessage());

		} catch (JsonMappingException e) {
			ExceptionHandler.handleException(e.getMessage());

		} catch (IOException e) {
			ExceptionHandler.handleException(e.getMessage());

		}

	}

}
