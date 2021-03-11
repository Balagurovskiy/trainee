package gof.pattern.behavioural.strategy;

import java.util.concurrent.atomic.AtomicBoolean;

public class Stop implements Action {

	@Override
	public void execute(AtomicBoolean state) {
		state.set(false);
	}

}
