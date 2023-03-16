package org.ruoxue.java_147.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
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

		protected long calculate(int n) {
			try {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("stop calculate");
					throw new InterruptedException();
				}
				if (n <= 1)
					return n;
				else
					return calculate(n - 1) + calculate(n - 2);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
			return 0;
		}
	}

	@Test
	public void fibonacci() {
		for (int i = 0; i < 20; i++) {
			System.out.println(i + " = " + new FibonacciWorker(i).call());
		}
	}

	@Test
	public void cancelFalse() {
		int poolSize = 3;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		Future<Long> future = executorService.submit(new FibonacciWorker(47));
		long result = -1;
		try {
			result = future.get(3, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		} catch (TimeoutException ex) {
			ex.printStackTrace();
			future.cancel(false);
		}
		System.out.println(result);
	}

	@Test
	public void cancelTrue() {
		int poolSize = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		Future<Long> future = executorService.submit(new FibonacciWorker(47));
		long result = -1;
		try {
			result = future.get(3, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		} catch (TimeoutException ex) {
			ex.printStackTrace();
			future.cancel(true);
		}
		System.out.println(result);
	}
}
