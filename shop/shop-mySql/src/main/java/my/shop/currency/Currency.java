package my.shop.currency;

import java.io.Serializable;

public class Currency implements Serializable{

	private static final long serialVersionUID = 3057262570009657551L;
	private final Course course;
	private double amount;
	
	public Currency() {
		amount = 0.0;
		this.course = Course.DOLLAR;
	}
	
	public Currency(Course course) {
		amount = 0.0;
		this.course = course;
	}
	
	public Currency(double amount, Course course) {
		this.course = course;
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Course getCourse() {
		return course;
	}
	
	public double convertTo(Course course) {
		return this.course.convertTo(course, amount);
	}
}
