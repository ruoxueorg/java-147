package org.ruoxue.java_147.multithreading;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ExecutorServiceWithExamplesTest {

	@Test
	public void linkedBlockingQueue() {
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(2));
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
			System.out.println("corePoolSize: " + executorService.getCorePoolSize());
			System.out.println("maximumPoolSize: " + executorService.getMaximumPoolSize());
			System.out.println("poolSize: " + executorService.getPoolSize());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void synchronousQueue() {
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new NamedThreadFactory("SYS"),
				new ThreadPoolExecutor.CallerRunsPolicy());
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
			System.out.println("corePoolSize: " + executorService.getCorePoolSize());
			System.out.println("maximumPoolSize: " + executorService.getMaximumPoolSize());
			System.out.println("poolSize: " + executorService.getPoolSize());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public class NamedThreadFactory implements ThreadFactory {
		private final AtomicInteger count = new AtomicInteger();
		private final String prefix;

		public NamedThreadFactory(String prefix) {
			this.prefix = prefix;
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			int c = count.incrementAndGet();
			thread.setName(prefix + "-" + c);
			return thread;
		}
	}
}
