package gof.pattern.creational.sigleton;

public class Sigleton {
 
	private static class SigletonHandler{
		private final static Sigleton INST = new Sigleton();
	}
	
	private Sigleton() {
		// TODO Auto-generated constructor stub
	}
	
	public static Sigleton getInstance() {
		return SigletonHandler.INST;
	}
}
