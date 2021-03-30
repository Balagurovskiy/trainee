package my.shop_extended.currency;

import java.io.Serializable;

public enum Course  implements Serializable{

	
    DOLLAR(new CourseInfo(1.0, "Dollar")),
    HRYVNIA(new CourseInfo(27.0, "Hryvnia")),
    EURO(new CourseInfo(0.84, "Euro")),
    POUND(new CourseInfo(0.72, "Pound"));

	private static final long serialVersionUID = 3333L;
	
    private CourseInfo ci;
    
    public double getKoef() {
    	return ci.getKoef();
    }
    public String getName() {
    	return ci.getName();
    }
	public double convertTo(Course dest, double amount) {
		double koef = 1.0;
		
		if (Math.abs(this.ci.getKoef() - dest.getKoef()) > 0.001) {
			koef = dest.getKoef() / this.ci.getKoef();
		}
		return amount * koef;
	}
    private Course(CourseInfo ci) {
		this.ci = ci;
	}
}
