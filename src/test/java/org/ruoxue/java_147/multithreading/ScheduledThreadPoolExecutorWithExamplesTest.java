package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class ScheduledThreadPoolExecutorWithExamplesTest {

	protected class RunWorker implements Runnable {

		private long start;
		private int id;

		public RunWorker(long start, int id) {
			this.start = start;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				System.out.println(String.format("%s T[%d] %d worker: %d ready", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start, id));
				TimeUnit.SECONDS.sleep(1);
				System.out.println(String.format("%s T[%d] %d worker: %d finished", sdf.format(new Date()),
						Thread.currentThread().getId(), System.currentTimeMillis() - start, id));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void scheduleRunnable() {
		int poolSize = 1;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.schedule(new RunWorker(start, e), 1, TimeUnit.SECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	protected class CallWorker implements Callable<String> {

		private long start;
		private int id;
		private String result;

		public CallWorker(long start, int id) {
			this.start = start;
			this.id = id;
		}

		public int getId() {
			return id;
		}

		@Override
		public String call() throws Exception {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(String.format("%s T[%d] %d worker: %d ready", sdf.format(new Date()),
					Thread.currentThread().getId(), System.currentTimeMillis() - start, id));
			TimeUnit.SECONDS.sleep(1);
			System.out.println(String.format("%s T[%d] %d worker: %d finished", sdf.format(new Date()),
					Thread.currentThread().getId(), System.currentTimeMillis() - start, id));

			result = id + " OK";
			return result;
		}
	}

	@Test
	public void scheduleCallable() {
		int poolSize = 1;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<String>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<String> future = executorService.schedule(new CallWorker(start, e), 1, TimeUnit.SECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void scheduleAtFixedRate() {
		int poolSize = 1;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.scheduleAtFixedRate(new RunWorker(start, e), 1, 1,
					TimeUnit.SECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void scheduleWithFixedDelay() {
		int poolSize = 1;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.scheduleWithFixedDelay(new RunWorker(start, e), 1, 3,
					TimeUnit.SECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
