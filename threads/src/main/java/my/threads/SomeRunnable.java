package my.threads;

public class SomeRunnable implements Runnable{

	private SomeData data;
	
	public SomeRunnable(SomeData data) {
		this.data = data;
	}

	@Override
	public void run() {
		this.data.setData("modified by runnable | Thread : " + Thread.currentThread().getName());
	}
}
