package org.ruoxue.java_147.multithreading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class ScheduledThreadPoolExecutorWithExamplesTest {

	@Test
	public void scheduleRunnable() {
		int poolSize = 2;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> scheduledFuture = executorService.schedule(() -> {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " finished");
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5, TimeUnit.SECONDS);
			futures.add(scheduledFuture);
		});

		futures.forEach(e -> {
			try {
				Object result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] task: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Test
	public void scheduleCallable() {
		int poolSize = 2;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		List<ScheduledFuture<String>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<String> scheduledFuture = executorService.schedule(() -> {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " finished");
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				return e + " OK";
			}, 5, TimeUnit.SECONDS);
			futures.add(scheduledFuture);
		});

		futures.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] task: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Test
	public void scheduleAtFixedRate() {
		int poolSize = 2;
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(() -> {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " finished");
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5, 1, TimeUnit.SECONDS);
			futures.add(scheduledFuture);
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
		ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(poolSize);
		int taskSize = 3;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		IntStream.range(0, taskSize).forEach(e -> {
			ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(() -> {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: " + e
							+ " finished");
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}, 5, 3, TimeUnit.SECONDS);
			futures.add(scheduledFuture);
		});

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
