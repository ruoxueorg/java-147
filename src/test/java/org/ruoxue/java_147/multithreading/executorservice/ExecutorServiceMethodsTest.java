package org.ruoxue.java_147.multithreading.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ExecutorServiceMethodsTest {

	@Test
	public void execute() {
		int poolSize = 2;
		int maxPoolSize = 5;
		ExecutorService executorService = new ThreadPoolExecutor(poolSize, maxPoolSize, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int taskSize = 3;
		CountDownLatch latch = new CountDownLatch(taskSize);
		AtomicInteger ids = new AtomicInteger();
		for (int i = 0; i < taskSize; i++) {
			executorService.execute(() -> {
				try {
					int id = ids.incrementAndGet();
					System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					latch.countDown();
				}
			});
		}

		try {
			latch.await();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void awaitTermination() {
		int poolSize = 2;
		int maxPoolSize = 5;
		ExecutorService executorService = new ThreadPoolExecutor(poolSize, maxPoolSize, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int taskSize = 3;
		AtomicInteger ids = new AtomicInteger();
		for (int i = 0; i < taskSize; i++) {
			executorService.execute(() -> {
				try {
					int id = ids.incrementAndGet();
					System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}

	@Test
	public void submitRunnable() {
		int poolSize = 2;
		int maxPoolSize = 5;
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(poolSize, maxPoolSize, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int taskSize = 3;
		AtomicInteger ids = new AtomicInteger();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < taskSize; i++) {
			int id = ids.incrementAndGet();
			Future<String> future = executorService.submit(() -> {
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
			}, id + " OK");
			futures.add(future);
		}

		futures.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Test
	public void submitCallable() {
		int poolSize = 2;
		int maxPoolSize = 5;
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(poolSize, maxPoolSize, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int taskSize = 3;
		AtomicInteger ids = new AtomicInteger();
		List<Future<String>> futures = new ArrayList<Future<String>>();
		for (int i = 0; i < taskSize; i++) {
			Future<String> future = executorService.submit(() -> {
				int id = ids.incrementAndGet();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " ready");
				TimeUnit.SECONDS.sleep(1);
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + id + " finished");
				return id + " OK";
			});
			futures.add(future);
		}

		futures.forEach(e -> {
			try {
				String result = e.get();
				System.out.println("T[" + Thread.currentThread().getId() + "] worker: " + result);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}
}
