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
				count++;
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("T[" + Thread.currentThread().getId() + "] count: " + count);
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void nolock() {
		int expected = 3;
		int taskSize = 3;
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

	protected class LockWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public LockWorker() {
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] lock acquired");
				count++;
				TimeUnit.SECONDS.sleep(1);
				System.out.println("T[" + Thread.currentThread().getId() + "] count: " + count);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				System.out.println("T[" + Thread.currentThread().getId() + "] lock released");
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void lock() {
		int expected = 3;
		int taskSize = 3;
		LockWorker worker = new LockWorker();
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

	protected class InterruptiblyWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public InterruptiblyWorker() {
		}

		@Override
		public void run() {
			try {
				lock.lockInterruptibly();
				try {
					System.out.println("T[" + Thread.currentThread().getId() + "] lock acquired");
					count++;
					TimeUnit.SECONDS.sleep(1);
					System.out.println("T[" + Thread.currentThread().getId() + "] count: " + count);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					lock.unlock();
					System.out.println("T[" + Thread.currentThread().getId() + "] lock released");
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
		InterruptiblyWorker worker = new InterruptiblyWorker();
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
				System.out.println("T[" + Thread.currentThread().getId() + "] isLockAcquired: " + isLockAcquired);
				if (isLockAcquired) {
					try {
						System.out.println("T[" + Thread.currentThread().getId() + "] lock acquired");
						count++;
						TimeUnit.SECONDS.sleep(1);
						System.out.println("T[" + Thread.currentThread().getId() + "] count: " + count);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println("T[" + Thread.currentThread().getId() + "] lock released");
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
