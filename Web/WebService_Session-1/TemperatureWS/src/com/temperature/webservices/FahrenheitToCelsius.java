/**
 * @author Pooja Khandelwal
 * @create by 28/10/2015
 * @name FahrenheitToCelsius
 * @description it will provide the method to convert fahrenheit value into celsius
 */
package com.temperature.webservices;

import javax.jws.WebService;

@WebService
public class FahrenheitToCelsius {
	/**
	 * @name fahrenheitToCelsiusConvertor
	 * @description it converts fahrenheit value into celsius
	 * @param fahrenheit
	 *            (input temperature value in fahrenheit)
	 * @return celsius(output temperature value in celsius)
	 */
	public float fahrenheitToCelsiusConvertor(float fahrenheit) {
		float celsius = (fahrenheit - 32) * 5 / 9;
		return celsius;
	}
}
