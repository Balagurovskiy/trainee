package gof.pattern.behavioural.strategy;

import java.util.concurrent.atomic.AtomicBoolean;

public class Move implements Action {

	@Override
	public void execute(AtomicBoolean state) {
		state.set(true);
	}

}
