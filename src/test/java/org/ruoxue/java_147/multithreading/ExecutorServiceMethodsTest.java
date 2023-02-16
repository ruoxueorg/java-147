package org.ruoxue.java_147.multithreading;

import java.util.concurrent.Callable;
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
		ExecutorService executorService = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		int taskSize = 6;
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
	public void submit() {
//		ExecutorService executorService = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS,
//				new LinkedBlockingQueue<Runnable>());
//		int taskSize = 6;
//		CountDownLatch latch = new CountDownLatch(taskSize);
//		AtomicInteger ids = new AtomicInteger();
//		for (int i = 0; i < taskSize; i++) {
//			Future<String> future = executorService.submit(new Callable<String>() {
//				public String call() throws Exception {
//					return "OK";
//				}
//			});
//		}
//
//		try {
//			latch.await();
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
	}
}
