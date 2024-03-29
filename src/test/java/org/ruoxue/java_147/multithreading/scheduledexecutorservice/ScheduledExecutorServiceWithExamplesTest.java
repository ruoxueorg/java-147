package org.ruoxue.java_147.multithreading.scheduledexecutorservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class ScheduledExecutorServiceWithExamplesTest {

	@Test
	public void scheduleRunnable() {
		int poolSize = 2;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.schedule(() -> {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					System.out.println(String.format("%s T[%d] %d task: %d ready", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
					TimeUnit.SECONDS.sleep(1);
					System.out.println(String.format("%s T[%d] %d task: %d finished", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5_000, TimeUnit.MILLISECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void scheduleCallable() {
		int poolSize = 2;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<String>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<String> future = executorService.schedule(() -> {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					System.out.println(String.format("%s T[%d] %d task: %d ready", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
					TimeUnit.SECONDS.sleep(1);
					System.out.println(String.format("%s T[%d] %d task: %d finished", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				return e + " OK";
			}, 5_000, TimeUnit.MILLISECONDS);
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
		int poolSize = 2;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.scheduleAtFixedRate(() -> {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					System.out.println(String.format("%s T[%d] %d task: %d ready", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
					TimeUnit.SECONDS.sleep(1);
					System.out.println(String.format("%s T[%d] %d task: %d finished", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5_000, 1_000, TimeUnit.MILLISECONDS);
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
		int poolSize = 2;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(String.format("%s T[%d] init", df.format(new Date()), Thread.currentThread().getId()));
		long start = System.currentTimeMillis();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> future = executorService.scheduleWithFixedDelay(() -> {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					System.out.println(String.format("%s T[%d] %d task: %d ready", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
					TimeUnit.SECONDS.sleep(1);
					System.out.println(String.format("%s T[%d] %d task: %d finished", sdf.format(new Date()),
							Thread.currentThread().getId(), System.currentTimeMillis() - start, e));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5_000, 3_000, TimeUnit.MILLISECONDS);
			futures.add(future);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
