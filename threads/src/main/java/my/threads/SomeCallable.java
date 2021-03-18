package my.threads;

import java.util.concurrent.Callable;

public class SomeCallable implements Callable<SomeData>{

	private SomeData data;
	private static long modifyCount;
	
	public SomeCallable(SomeData data) {
		this.data = data;
		modifyCount = 1;
	}
	@Override
	public SomeData call() throws Exception {
		SomeData clone = new SomeData(data.getData());
		clone.setData("modified by callable | modification (" + modifyCount + ") | Thread : " + Thread.currentThread().getName());
		modifyCount++;
		return clone;
	}

}
