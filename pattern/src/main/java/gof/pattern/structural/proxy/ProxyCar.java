package gof.pattern.structural.proxy;

public class ProxyCar implements Vehicle{
	private Car car;
	
	public ProxyCar(int weight) {
		this.car = new Car(weight);
	}

	@Override
	public void loadCargo(int cargoWeight) {
		int newWeight = car.getCargoWeight() + cargoWeight;
		if (newWeight > car.getWeight() || newWeight < 0) {
			return ;
		}
		car.loadCargo(newWeight);
	}

	@Override
	public void move() {
		car.move();		
	}
 
}
