package gof.pattern.behavioural.chain;

import java.util.ArrayList;
import java.util.List;

public class Car {
	private List<Part> parts;
	
	public Car() {
		parts = new ArrayList<>();
	}
	
	public void assemble(Part part) {
		parts.add(part);
	}

	public List<Part> getParts() {
		return parts;
	}
}
