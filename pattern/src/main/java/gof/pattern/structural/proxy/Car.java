package gof.pattern.structural.proxy;

public class Car implements Vehicle{
	private int weight;
	private int cargoWeight;
	
	public Car(int weight) {
		this.weight = weight;
	}


	@Override
	public void loadCargo(int cargoWeight) {
		this.cargoWeight += cargoWeight;		
	}


	@Override
	public void move() {
		if (cargoWeight > weight) {
			throw new RuntimeException();
		}
	}

	public int getWeight() {
		return weight;
	}

	public int getCargoWeight() {
		return cargoWeight;
	}
	
	
}
