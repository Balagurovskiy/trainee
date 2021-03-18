package my.threads.create_threads_with_different_ways;

import my.threads.SomeData;
import my.threads.SomeRunnable;

public class TraineeThread {
	public static void main(String[] args) {
		new Thread(new SomeRunnable( new SomeData("data for thread"))).start();
	}
}
