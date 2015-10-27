/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name Car
 * @description It is  Model class for Car
 */
package com.vehicle.model;

public class Car extends Vehicle {

	private int carId;
	private String ac;
	private String accessorykit;
	private String powerSteering;

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getAccessorykit() {
		return accessorykit;
	}

	public void setAccessorykit(String accessorykit) {
		this.accessorykit = accessorykit;
	}

	public String getPowerSteering() {
		return powerSteering;
	}

	public void setPowerSteering(String powerSteering) {
		this.powerSteering = powerSteering;
	}

}
