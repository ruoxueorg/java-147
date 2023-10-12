package org.ruoxue.java_147.multithreading.executorservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class ThreadPoolWithExamplesTest {

	@Test
	public void newSingleThreadExecutor() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.execute(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " ready");
						TimeUnit.SECONDS.sleep(3);
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				});
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void newFixedThreadPool() {
		int poolSize = 2;
		ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.execute(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " ready");
						TimeUnit.SECONDS.sleep(3);
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				});
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void newCachedThreadPool() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.execute(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " ready");
						TimeUnit.SECONDS.sleep(3);
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				});
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void newScheduledThreadPool() {
		int poolSize = 2;
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(poolSize);
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.scheduleAtFixedRate(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " ready");
						TimeUnit.SECONDS.sleep(5);
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				}, 5_000, 5_000, TimeUnit.MILLISECONDS);
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void newSingleThreadScheduledExecutor() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(5);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(df.format(new Date()) + " T[" + Thread.currentThread().getId() + "] init");
		try {
			IntStream.range(0, taskSize).forEach(e -> {
				executorService.scheduleAtFixedRate(() -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					try {
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " ready");
						TimeUnit.SECONDS.sleep(3);
						System.out.println(sdf.format(new Date()) + " T[" + Thread.currentThread().getId() + "] task: "
								+ e + " finished");
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						latch.countDown();
					}
				}, 5_000, 5_000, TimeUnit.MILLISECONDS);
			});
			latch.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
