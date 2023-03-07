package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class SynchronizationWithExamplesTest {

	protected class NoSyncCounter {

		private int count;

		public NoSyncCounter() {
		}

		public void increment() {
			count = getCount() + 1;
			System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void noSync() {
		int expected = 1000;
		int taskSize = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		NoSyncCounter counter = new NoSyncCounter();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(counter::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = counter.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class SyncMethodCounter {

		private int count;

		public SyncMethodCounter() {
		}

		public synchronized void increment() {
			count = getCount() + 1;
			System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void syncMethod() {
		int expected = 1000;
		int taskSize = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		SyncMethodCounter counter = new SyncMethodCounter();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(counter::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = counter.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class SyncBlockCounter {

		private int count;

		public SyncBlockCounter() {
		}

		public void increment() {
			synchronized (this) {
				count = getCount() + 1;
				System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void syncBlock() {
		int expected = 1000;
		int taskSize = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		SyncBlockCounter counter = new SyncBlockCounter();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(counter::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = counter.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}
}
