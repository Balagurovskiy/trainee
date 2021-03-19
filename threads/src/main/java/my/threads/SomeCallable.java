package my.threads;

import java.time.LocalTime;
import java.util.concurrent.Callable;

import lombok.Getter;
import lombok.Setter;


public class SomeCallable implements Callable<SomeCallable>{

	@Getter @Setter private Integer prev;
	@Getter @Setter private Integer current;
	
	@Getter @Setter private LocalTime startModTime;
	@Getter @Setter private LocalTime endModTime;
	
	
	@Getter @Setter private String threadName;
	
	private SomeData dataProvider;
	
	public SomeCallable(SomeData data) {
		this.dataProvider = data;
	}
	@Override
	public SomeCallable call() throws Exception {
		startModTime = LocalTime.now();
		prev = dataProvider.getData();
		synchronized(dataProvider) {
        	dataProvider.increment();
		}
		threadName = Thread.currentThread().getName();
		current = dataProvider.getData();
		endModTime = LocalTime.now();
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\t* Call hash :" + this.hashCode());
		builder.append("\n");
		builder.append("\t");
		builder.append("start time : " + startModTime + " | before value : " + prev);
		builder.append("\n");
		builder.append("\t");
		builder.append("end time : " + endModTime + " | after value : " + current);
		builder.append("\n");
		return builder.toString();
	}
}
