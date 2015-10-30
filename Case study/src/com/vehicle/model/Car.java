/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name Car
 * @description It is  Model class for Car
 */
package com.vehicle.model;

import lombok.Data;

@Data
public class Car extends Vehicle {

	private int carId;
	private String ac;
	private String accessorykit;
	private String powerSteering;

}
