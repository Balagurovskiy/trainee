package my.threads;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SomeData {
	private volatile Integer data;
	public SomeData() {
		data = 0;
	}
	public void increment() {
		data++;
	}
}
