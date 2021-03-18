package my.threads.create_threads_with_different_ways;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import my.threads.SomeCallable;
import my.threads.SomeData;

public class TraineeSingleExecutorWithCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<SomeData> future = executorService.submit( new SomeCallable(new SomeData("Data for executor service with callable ! ")));
		Thread.sleep(100);
		if (future.isDone()) {
			System.out.println("All done! " + future.get().getData());
		}else {
			System.out.println("Smth goes wrong!");
		}
		executorService.shutdown();
	}
}
