package gof.pattern.behavioural.momento;

public class DistanceSave {
	private Car car;
	private int distance;
	
	public DistanceSave(Car car, int distance) {
		this.car = car;
		this.distance = distance;
	}
	
	public void restore() {
		car.setCarDrivenDistance(distance);
	}
}
