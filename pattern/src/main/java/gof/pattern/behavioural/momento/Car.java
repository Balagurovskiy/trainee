package gof.pattern.behavioural.momento;

public class Car {
	
	int carDrivenDistance;
	
	public Car() {
	}
	
	public void setCarDrivenDistance(int carDrivenDistance) {
		this.carDrivenDistance = carDrivenDistance;
	}

	public int getDistance() {
		return carDrivenDistance;
	}
	
	public DistanceSave saveCurrentDistance() {
		return new DistanceSave(this, carDrivenDistance);
	}
}
