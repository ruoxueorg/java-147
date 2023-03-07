package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ReentrantLockWithExamplesTest {

	protected class NoLockWorker implements Runnable {

		private int count;

		public NoLockWorker() {
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
				count++;
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
	public void nolock() {
		int expected = 500;
		int taskSize = 500;
		NoLockWorker worker = new NoLockWorker();
		List<Thread> threads = Stream.generate(() -> new Thread(worker)).limit(taskSize).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class Worker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public Worker() {
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println(String.format("T[%d] lock acquired", Thread.currentThread().getId()));
				count++;
				System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				System.out.println(String.format("T[%d] lock released", Thread.currentThread().getId()));
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void lock() {
		int expected = 500;
		int taskSize = 500;
		Worker worker = new Worker();
		List<Thread> threads = Stream.generate(() -> new Thread(worker)).limit(taskSize).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class LockInterruptiblyWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public LockInterruptiblyWorker() {
		}

		@Override
		public void run() {
			try {
				lock.lockInterruptibly();
				try {
					System.out.println(String.format("T[%d] lock acquired", Thread.currentThread().getId()));
					TimeUnit.SECONDS.sleep(1);
					count++;
					System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					lock.unlock();
					System.out.println(String.format("T[%d] lock released", Thread.currentThread().getId()));
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void lockInterruptibly() {
		int expected = 2;
		int taskSize = 3;
		LockInterruptiblyWorker worker = new LockInterruptiblyWorker();
		List<Thread> threads = Stream.generate(() -> new Thread(worker)).limit(taskSize).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		Thread thread = threads.get(1);
		thread.interrupt();

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}

	protected class TryLockWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public TryLockWorker() {
		}

		@Override
		public void run() {
			try {
				boolean isLockAcquired = lock.tryLock(100, TimeUnit.MILLISECONDS);
				System.out.println(
						String.format("T[%d] isLockAcquired: %b", Thread.currentThread().getId(), isLockAcquired));
				if (isLockAcquired) {
					try {
						System.out.println(String.format("T[%d] lock acquired", Thread.currentThread().getId()));
						TimeUnit.SECONDS.sleep(1);
						count++;
						System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println(String.format("T[%d] lock released", Thread.currentThread().getId()));
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void tryLock() {
		int expected = 1;
		int taskSize = 3;
		TryLockWorker worker = new TryLockWorker();
		List<Thread> threads = Stream.generate(() -> new Thread(worker)).limit(taskSize).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		int count = worker.getCount();
		System.out.println(count);
		assertEquals(expected, count);
	}
}
