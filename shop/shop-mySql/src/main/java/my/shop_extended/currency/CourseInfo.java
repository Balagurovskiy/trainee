package my.shop_extended.currency;

public final class CourseInfo {
	private final String name;
	private final double koef;
	
	public CourseInfo(double koef, String name) {
		this.name = name;
		this.koef = koef;
	}
	
	public double getKoef() {
		return koef;
	}
	public String getName() {
		return name;
	}
}
