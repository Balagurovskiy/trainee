package gof.pattern.behavioural.chain;

public class PartsValidation extends Production{

	@Override
	public boolean executeTask(Car car) {
//		car.getParts().stream().filter(p -> CarExaminator.validatePart(p));
//		if(CarExaminator.validateAssembly()) {
//			System.out.println("ok");
//		} else {
//			System.out.println("not ok");
//			System.out.println(CarExaminator.wheelCount);
//		}
		int engineCount = 0;
		int wheelCount = 0;
		for (Part p : car.getParts()) {
			if(p instanceof Engine) {
				engineCount++;
			} else if(p instanceof Wheel) {
				wheelCount++;
			} else {
				throw new IllegalArgumentException();
			}
		}
		if(engineCount != 1 || wheelCount != 4) {
			return false;
		}
		return forward(car);
	}
	
	
//	private static class CarExaminator{
//		
//		private static int engineCount = 0;
//		private static int wheelCount = 0;
//		
//		public static boolean validatePart(Part part) {
//			
//			if(part instanceof Engine) {
//				engineCount++;
//			} else if(part instanceof Wheel) {
//				wheelCount++;
//			} else {
//				return false;
//			}
//			return true;
//		}
//		
//		public static boolean validateAssembly() {
//			if(engineCount == 1 && wheelCount == 4) {
//				return true;
//			}
//			return false;
//		}
//	}
}
