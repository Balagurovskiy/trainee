package gof.pattern.structural.composite;

public abstract class Composite {
	private String name;
	private int value;
	
	public Composite(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getDescription() {
		return name + ", value : " + value + ";";
	}
	public int getValue() {
		return value;
	}
}
