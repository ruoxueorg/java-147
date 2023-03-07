package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;

public class SynchronizedKeywordTest {

	protected static class SyncStaticCounter {

		private static int count;

		public static synchronized void increment() {
			count = getCount() + 1;
			System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
		}

		public static int getCount() {
			return count;
		}
	}

	@Test
	public void syncStatic() {
		int expected = 1000;
		int taskSize = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		IntStream.range(0, taskSize).forEach(e -> {
			executorService.submit(SyncStaticCounter::increment);
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		int count = SyncStaticCounter.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class ReentrantCounter {

		private int count;

		public ReentrantCounter() {
		}

		public synchronized void increment() {
			synchronized (this) {
				doIncrement();
			}
		}

		public synchronized void doIncrement() {
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
	public void reentrant() {
		int expected = 1000;
		int taskSize = 1000;
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		ReentrantCounter counter = new ReentrantCounter();
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

	@Test
	public void deadlock() {
		Object lock1 = new Object();
		Object lock2 = new Object();
		Thread threadA = new Thread(() -> {
			String id = "A";
			synchronized (lock1) {
				System.out
						.println(String.format("T[%d] counter: %s lock1 acquired", Thread.currentThread().getId(), id));
				try {
					Thread.sleep(3);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println(
							String.format("T[%d] counter: %s lock2 acquired", Thread.currentThread().getId(), id));

				}
			}
		});

		Thread threadB = new Thread(() -> {
			String id = "B";
			synchronized (lock2) {
				System.out
						.println(String.format("T[%d] counter: %s lock2 acquired", Thread.currentThread().getId(), id));
				try {
					Thread.sleep(3);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				synchronized (lock1) {
					System.out.println(
							String.format("T[%d] counter: %s lock1 acquired", Thread.currentThread().getId(), id));

				}
			}
		});

		threadB.start();
		threadA.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void deadlockSolution() {
		Object lock1 = new Object();
		Object lock2 = new Object();
		Thread threadA = new Thread(() -> {
			String id = "A";
			synchronized (lock1) {
				System.out
						.println(String.format("T[%d] counter: %s lock1 acquired", Thread.currentThread().getId(), id));
				try {
					Thread.sleep(3);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println(
							String.format("T[%d] counter: %s lock2 acquired", Thread.currentThread().getId(), id));

				}
			}
		});

		Thread threadB = new Thread(() -> {
			String id = "B";
			synchronized (lock1) {
				System.out
						.println(String.format("T[%d] counter: %s lock2 acquired", Thread.currentThread().getId(), id));
				try {
					Thread.sleep(3);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println(
							String.format("T[%d] counter: %s lock1 acquired", Thread.currentThread().getId(), id));

				}
			}
		});

		threadB.start();
		threadA.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
