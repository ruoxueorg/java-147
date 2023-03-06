package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class SynchronizationWithExamplesTest {

	protected class NoSyncWorker {

		private int count;

		public NoSyncWorker() {
		}

		public void increment() {
			try {
				TimeUnit.SECONDS.sleep(1);
				count = getCount() + 1;
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void noSync() {
		int expected = 500;
		int taskSize = 500;
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
		NoSyncWorker worker = new NoSyncWorker();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(worker::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class Worker {

		private int count;

		public Worker() {
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
	public void sync() {
		int expected = 500;
		int taskSize = 500;
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
		Worker worker = new Worker();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(worker::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class SyncBlockWorker {

		private int count;

		public SyncBlockWorker() {
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
		int expected = 500;
		int taskSize = 500;
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
		Worker worker = new Worker();
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(worker::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}
}
