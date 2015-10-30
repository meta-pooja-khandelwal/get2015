package com.vehicle.factory;

import com.vehicle.VehicleType;
import com.vehicle.facade.VehicleFacade;
import com.vehicle.model.Bike;
import com.vehicle.model.Car;
import com.vehicle.model.Vehicle;

public class VehicleFactory {
	private static VehicleFactory vehicleFactory;

	/**
	 * @name VehicleFactory()
	 * @description it is a private constructor of VehicleFactory class ,so that
	 *              its object can't be created with new operator by outside
	 *              world
	 */
	private VehicleFactory() {

	}

	/**
	 * @name getInstance()
	 * @description it will create if null and provide the object of
	 *              VehicleFacade
	 * @param
	 * @return vehicleFacade(instance of VehicleFacade class)
	 */
	public static VehicleFactory getInstance() {

		if (vehicleFactory == null) {
			vehicleFactory = new VehicleFactory();
		}
		return vehicleFactory;
	}

	public Vehicle getVehicle(VehicleType vehicleType) {
		Vehicle iVehicle;
		if (vehicleType.equals(VehicleType.car)) {
			iVehicle = new Car();
		} else if (vehicleType.equals(VehicleType.bike)) {
			iVehicle = new Bike();
		} else {
			iVehicle = null;
		}
		return iVehicle;
	}
}
