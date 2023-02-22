package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ReentrantLockClassTest {

	protected class NonfairLockWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public NonfairLockWorker() {
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] lock acquired");
				count++;
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
	public void nonfairLock() {
		int expected = 5;
		int taskSize = 5;
		NonfairLockWorker worker = new NonfairLockWorker();
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

	protected class FairLockWorker implements Runnable {

		private final Lock lock = new ReentrantLock(true);
		private int count;

		public FairLockWorker() {
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] lock acquired");
				count++;
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
	public void fairLock() {
		int expected = 5;
		int taskSize = 5;
		FairLockWorker worker = new FairLockWorker();
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

	protected class ReentrantWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public ReentrantWorker() {
		}

		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] run() lock acquired");
				doCount();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				System.out.println("T[" + Thread.currentThread().getId() + "] run() lock released");
			}
		}

		public void doCount() {
			lock.lock();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] doCount() lock acquired");
				count++;
				TimeUnit.SECONDS.sleep(1);
				System.out.println("T[" + Thread.currentThread().getId() + "] count: " + count);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				lock.unlock();
				System.out.println("T[" + Thread.currentThread().getId() + "] doCount() lock released");
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void reentrant() {
		int expected = 1;
		int taskSize = 1;
		ReentrantWorker worker = new ReentrantWorker();
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

	protected class BrokenUnlockWorker implements Runnable {

		private final Lock lock = new ReentrantLock();
		private int count;

		public BrokenUnlockWorker() {
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
				lock.unlock();
				System.out.println("T[" + Thread.currentThread().getId() + "] lock released");
			}
		}

		public int getCount() {
			return count;
		}
	}

	@Test
	public void brokenUnlock() {
		int expected = 1;
		int taskSize = 1;
		BrokenUnlockWorker worker = new BrokenUnlockWorker();
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
