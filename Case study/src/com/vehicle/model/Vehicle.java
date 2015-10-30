/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name Vehicle
 * @description It is  Model class for Vehicle 
 */
package com.vehicle.model;

import lombok.Data;

@Data
public abstract class Vehicle {
	private int vehicleId;
	private String make;
	private String model;
	private int engineInCc;
	private int fuelCapacity;
	private int mileage;
	private int showRoomPrice;
	private int roadTax;
	private int onRoadPrice;
	private String createdTime;
	private String createdBy;
	private String imagePath;

}
