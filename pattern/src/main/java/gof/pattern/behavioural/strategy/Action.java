package gof.pattern.behavioural.strategy;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Action {
	public void execute(AtomicBoolean state);
}
