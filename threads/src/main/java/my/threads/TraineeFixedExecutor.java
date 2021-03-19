package my.threads;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TraineeFixedExecutor {

	private static SomeData data = new SomeData();
	private static final int NUMBER_OF_THREADS = 10;
	private static final int NUMBER_OF_CALLS = 20;

	public static Function<Future<SomeCallable>, SomeCallable> convert() {
		return (f) -> {
			SomeCallable call = null;
			try {
				call = f.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return call;
		};
	}

	public static BiConsumer<String, List<SomeCallable>> print() {
		return (k, v) -> {
			System.out.println(k + " :");
			v.stream().forEach(System.out::println);
		};
	}

	public static Supplier<SomeCallable> create() {
		return () -> new SomeCallable(data);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

		List<SomeCallable> tasks = Stream.generate(create()::get).limit(NUMBER_OF_CALLS).collect(Collectors.toList());

		Map<String, List<SomeCallable>> futures = executorService.invokeAll(tasks)
													.stream()
													.map(convert()::apply)
													.collect(Collectors.groupingBy(SomeCallable::getThreadName));

		futures.forEach(print()::accept);
		
		executorService.shutdown();
		
		System.out.println("\n * Incremented result data : " + data.getData() + " *");
	}
}
