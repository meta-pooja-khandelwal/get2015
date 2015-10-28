/**
 * @author Pooja Khandelwal
 * @create by 28/10/2015
 * @name WS_Client
 * @description this class will take input  from client and provide the response to client
 */
package com.temperature;

import java.util.Scanner;
import com.temperature.webservices.FahrenheitToCelsius;
import com.temperature.webservices.FahrenheitToCelsiusServiceLocator;

public class WS_Client {
	public static void main(String args[]) {
		FahrenheitToCelsiusServiceLocator fahrenheitToCelsiusServiceLocator = new FahrenheitToCelsiusServiceLocator();
		fahrenheitToCelsiusServiceLocator
				.setFahrenheitToCelsiusEndpointAddress("http://localhost:8080/TemperatureWS/services/FahrenheitToCelsius");
		try {
			System.out
					.println("Enter fahrenheit value which you want to convert in celsius");
			float fahrenheit;
			Scanner scanner = new Scanner(System.in);
			fahrenheit = scanner.nextFloat();

			FahrenheitToCelsius fahrenheitToCelsius = fahrenheitToCelsiusServiceLocator
					.getFahrenheitToCelsius();
			float celsius = fahrenheitToCelsius
					.fahrenheitToCelsiusConvertor(fahrenheit);
			System.out.println("\nfahrenheit=" + fahrenheit
					+ "fahrenheit ,celsius=" + celsius + "celsius");
			scanner.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
