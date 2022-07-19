import java.util.concurrent.*;
import java.util.*;
import java.io.*;
import static java.util.concurrent.TimeUnit.*;

class Learn {

	static Callable<String> callable(String result, long sleepSeconds) {
		return () -> {
			TimeUnit.SECONDS.sleep(sleepSeconds);
			System.out.println("*" + result);
			return result;
		};
	}


	public static void main(String[] args) {


		ExecutorService executor = Executors.newWorkStealingPool();
		Callable<String> t1 = callable("task1", 2);
		Callable<String> t2 = callable("task2", 1);
		Callable<String> t3 = callable("task3", 3);
		List<Callable<String>> callables = Arrays.asList(t1, t2, t3);
		try {
			executor.invokeAll(callables)
			.parallelStream()
			.map(future -> {
			try {
			return future.get();
			}
			catch (Exception e) {
			throw new IllegalStateException(e);
			}
			})
			.forEach(System.out::println);

			System.out.println("ljdfsas");
		} catch (Exception e) {
			
		}



	}
}