package gof.pattern.creational.builder;

public abstract class VehicleBuilder{
	private Vehicle vehicle;
	public VehicleBuilder(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setNumberOfDoors(int n) {
		this.vehicle.setNumberOfDoors(n);
	}
	
	public void setEngineLiters(int l) {
		this.vehicle.setEngineL(l);
	}
	
	public Vehicle getVehicle(){
		return vehicle;
	}
}
