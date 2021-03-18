package my.threads;

public class SomeData {
	private String data;
	public SomeData() {
	}

	public SomeData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data += data;
	}
	
	
}
