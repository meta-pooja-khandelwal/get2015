/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name Login
 * @description It is  Model class for authentication 
 */
package com.vehicle.model;

import lombok.Data;

@Data
public class Login {
	private String email;
	private String password;
}
