package my.threads.create_threads_with_different_ways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import my.threads.SomeData;
import my.threads.SomeRunnable;

public class TraineeSingleExecutorWithRunnable {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future future = executorService.submit( new SomeRunnable(new SomeData("executor service with runnable !")));
		Thread.sleep(100);
		if (future.isDone()) {
			System.out.println("All done!");
		}else {
			System.out.println("Smth goes wrong!");
		}
		executorService.shutdown();
	}
}
