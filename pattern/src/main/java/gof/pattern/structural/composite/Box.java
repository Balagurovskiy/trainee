package gof.pattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Box extends Composite {
	
	private List<Composite> stash;
	
	public Box(String name, int value) {
		super(name, value);
		stash = new ArrayList<>();
	}
	
	public void add(Composite c) {
		stash.add(c);
	}
	
	public List<Composite> getStash() {
		return stash;
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " [inside : " + stash.size() + " item(s)]";
	}
}
