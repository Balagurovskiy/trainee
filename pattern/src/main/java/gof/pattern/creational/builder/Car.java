package gof.pattern.creational.builder;

public class Car implements Vehicle{
	private int numberOfDoors;
	private int engineL;
	
	public int getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(int amountOfDoors) {
		this.numberOfDoors = amountOfDoors;
	}
	public int getEngineL() {
		return engineL;
	}
	public void setEngineL(int engineL) {
		this.engineL = engineL;
	}
	
}
