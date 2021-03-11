package gof.pattern.behavioural.chain;

public class EngineAssembler extends Production{
	 @Override
	public boolean executeTask(Car car) {
		 car.assemble(new Engine());
		 return forward(car);
	}
}
