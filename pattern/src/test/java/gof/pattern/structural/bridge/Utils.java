package gof.pattern.structural.bridge;

public class Utils {
	public static boolean compareDouble(double a, double b, double accuracy) {
		double substract = a - b;
		if (substract < 0.) {
			substract *= -1.;
		}
		if (substract > accuracy) {
			return false;
		}
		return true;
	}
}
