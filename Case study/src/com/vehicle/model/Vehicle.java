/**
 * @author Pooja Khandelwal
 * @created date 27/10/2015
 * @name Vehicle
 * @description It is  Model class for Vehicle 
 */
package com.vehicle.model;

public class Vehicle {
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

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getEngineInCc() {
		return engineInCc;
	}

	public void setEngineInCc(int engineInCc) {
		this.engineInCc = engineInCc;
	}

	public int getFuelCapacity() {
		return fuelCapacity;
	}

	public void setFuelCapacity(int fuelCapacity) {
		this.fuelCapacity = fuelCapacity;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getShowRoomPrice() {
		return showRoomPrice;
	}

	public void setShowRoomPrice(int showRoomPrice) {
		this.showRoomPrice = showRoomPrice;
	}

	public int getRoadTax() {
		return roadTax;
	}

	public void setRoadTax(int roadTax) {
		this.roadTax = roadTax;
	}

	public int getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(int onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
