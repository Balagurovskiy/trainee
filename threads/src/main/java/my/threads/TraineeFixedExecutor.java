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
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		SomeData data = new SomeData();
		
		Supplier<SomeCallable> create = () -> new SomeCallable(data);
		
		List<SomeCallable> tasks = Stream.generate( create::get ).limit(20).collect(Collectors.toList());
		
		Function<Future<SomeCallable>, SomeCallable> convert = (f) -> {
			SomeCallable call = null;
			try {
				call = f.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return call;
		};
		
		Map<String, List<SomeCallable>> futures = executorService.invokeAll(tasks)
														.stream()
														.map(convert::apply)
														.collect( 
																Collectors.groupingBy(SomeCallable::getThreadName)
																);
		BiConsumer<String, List<SomeCallable>> print = (k, v) ->{
			System.out.println(k + " :");
			v.stream().forEach(System.out::println);
		};
		futures.forEach(print::accept);
		System.out.println("\n * Incremented result data : " + data.getData()+ " *");
		executorService.shutdown();
	}
}
