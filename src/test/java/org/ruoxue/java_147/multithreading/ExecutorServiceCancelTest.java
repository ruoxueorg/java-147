package org.ruoxue.java_147.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public class ExecutorServiceCancelTest {

	protected class FibonacciWorker implements Callable<Long> {

		int input = 0;

		public FibonacciWorker() {
		}

		public FibonacciWorker(int input) {
			this.input = input;
		}

		public Long call() {
			return calculate(input);
		}

		private long calculate(int n) {
			if (Thread.currentThread().isInterrupted())
				return 0;
			if (n <= 1)
				return n;
			else
				return calculate(n - 1) + calculate(n - 2);
		}
	}

	@Test
	public void fibonacci() {
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " = " + new FibonacciWorker(i).call());
		}
	}

	@Test
	public void cancel() {
		int poolSize = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		Future<Long> future = executorService.submit(new FibonacciWorker(50));
		long result = -1;
		try {
			result = future.get(3, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException ex) {
			ex.printStackTrace();
			future.cancel(true);
		}
		System.out.println(result);
	}
}
