package my.threads.create_threads_with_different_ways;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import my.threads.SomeCallable;
import my.threads.SomeData;

public class TraineeFixedExecutor {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<SomeCallable> tasks = Arrays.asList(
				new SomeCallable(new SomeData("Here comes first task for the fixed pool ! ")),
				new SomeCallable(new SomeData("SECOND ! ")),
				new SomeCallable(new SomeData("whoami ? "))
				) ;
		List<Future<SomeData>> futures = executorService.invokeAll(tasks);
		futures.stream()
					.filter(f -> f.isDone())
					.map(f -> {
						String data = "";
						try {
							data = f.get().getData();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						return data;
					})
					.forEach(System.out::println);
		executorService.shutdown();
	}
}
