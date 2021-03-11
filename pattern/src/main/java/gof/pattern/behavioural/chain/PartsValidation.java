package gof.pattern.behavioural.chain;

public class PartsValidation extends Production{

	@Override
	public boolean executeTask(Car car) {
		int engineCount = 0;
		int wheelCount = 0;
		for (Part p : car.getParts()) {
			if(p instanceof Engine) {
				engineCount++;
			} else if(p instanceof Wheel) {
				wheelCount++;
			} else {
				throw new IllegalArgumentException();
			}
		}
		if(engineCount != 1 || wheelCount != 4) {
			return false;
		}
		return forward(car);
	}
}
