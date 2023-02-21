package org.ruoxue.java_147.synchronization;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ReentrantLockWithConditionsTest {

	protected class Worker {

		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		private AtomicInteger count = new AtomicInteger();
		private static final int MAX_COUNT = 2;

		public Worker() {
		}

		public void doWork() throws Exception {
			await();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] ready");
				TimeUnit.SECONDS.sleep(3);
			} finally {
				signal();
			}
		}

		public void await() throws InterruptedException {
			lock.lock();
			try {
				while (count.get() >= MAX_COUNT) {
					System.out.println("T[" + Thread.currentThread().getId() + "] waiting");
					condition.await();
				}
				count.incrementAndGet();
			} finally {
				lock.unlock();
			}
		}

		public void signal() throws InterruptedException {
			lock.lock();
			try {
				count.decrementAndGet();
				System.out.println("T[" + Thread.currentThread().getId() + "] finished");
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	@Test
	public void await() {
		Worker worker = new Worker();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				worker.doWork();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
	}

	protected class TimeoutWorker {

		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		private AtomicInteger count = new AtomicInteger();
		private static final int MAX_COUNT = 2;

		public TimeoutWorker() {
		}

		public void doWork() throws Exception {
			await();
			try {
				System.out.println("T[" + Thread.currentThread().getId() + "] ready");
				TimeUnit.SECONDS.sleep(3);
			} finally {
				signal();
			}
		}

		public void await() throws InterruptedException, TimeoutException {
			lock.lock();
			try {
				boolean awaited = false;
				long timeoutRemaining = 2000;
				long awaitStarted = System.currentTimeMillis();
				while (count.get() >= MAX_COUNT && !awaited && timeoutRemaining > 0) {
					System.out.println("T[" + Thread.currentThread().getId() + "] waiting");
					awaited = condition.await(timeoutRemaining, TimeUnit.MILLISECONDS);
					timeoutRemaining -= System.currentTimeMillis() - awaitStarted;
				}
				if (count.get() >= MAX_COUNT) {
					throw new TimeoutException("T[" + Thread.currentThread().getId() + "] ");
				}
				count.incrementAndGet();
			} finally {
				lock.unlock();
			}
		}

		public void signal() throws InterruptedException {
			lock.lock();
			try {
				count.decrementAndGet();
				System.out.println("T[" + Thread.currentThread().getId() + "] finished");
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	@Test
	public void awaitTimeout() {
		TimeoutWorker worker = new TimeoutWorker();
		List<Thread> threads = Stream.generate(() -> new Thread(() -> {
			try {
				worker.doWork();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		threads.forEach(e -> e.start());

		threads.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
	}
}
