package gof.pattern.creational.builder;

public class Director {

	public void setupCasualCar(VehicleBuilder vehicleBuilder) {
		vehicleBuilder.setEngineLiters(2);
		vehicleBuilder.setNumberOfDoors(4);
	}
	
	public void setupSportCar(VehicleBuilder vehicleBuilder) {
		vehicleBuilder.setEngineLiters(5);
		vehicleBuilder.setNumberOfDoors(2);
	}
}
