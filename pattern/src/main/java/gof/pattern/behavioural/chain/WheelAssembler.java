package gof.pattern.behavioural.chain;

public class WheelAssembler extends Production{
	 @Override
	public boolean executeTask(Car car) {
		 car.assemble(new Wheel());
		 return forward(car);
	}
}

