package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class SemaphoreWithExamplesTest {

	protected class Worker implements Runnable {

		private Semaphore semaphore = new Semaphore(1);
		private int count;

		public Worker() {
		}

		@Override
		public void run() {
			try {
				System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
				semaphore.acquire();
				try {
					System.out.println(String.format("T[%d] acquire", Thread.currentThread().getId()));
					TimeUnit.SECONDS.sleep(1);
					count++;
					System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					semaphore.release();
					System.out.println(String.format("T[%d] release", Thread.currentThread().getId()));
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
	public void lock() {
		int expected = 3;
		int taskSize = 3;
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

	protected class AcquireWorker implements Runnable {

		private Semaphore semaphore = new Semaphore(2);
		private AtomicInteger count = new AtomicInteger();

		public AcquireWorker() {
		}

		@Override
		public void run() {
			try {
				System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
				semaphore.acquire();
				try {
					System.out.println(String.format("T[%d] acquire", Thread.currentThread().getId()));
					TimeUnit.SECONDS.sleep(1);
					count.incrementAndGet();
					System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count.get()));
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} finally {
					semaphore.release();
					System.out.println(String.format("T[%d] release", Thread.currentThread().getId()));
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public int getCount() {
			return count.get();
		}
	}

	@Test
	public void acquire() {
		int expected = 3;
		int taskSize = 3;
		AcquireWorker worker = new AcquireWorker();
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

	@Test
	public void acquireInterruptibly() {
		int expected = 2;
		int taskSize = 3;
		AcquireWorker worker = new AcquireWorker();
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

	protected class AcquireUninterruptiblyWorker implements Runnable {

		private Semaphore semaphore = new Semaphore(2);
		private AtomicInteger count = new AtomicInteger();

		public AcquireUninterruptiblyWorker() {
		}

		@Override
		public void run() {
			System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
			semaphore.acquireUninterruptibly();
			try {
				System.out.println(String.format("T[%d] acquire", Thread.currentThread().getId()));
				count.incrementAndGet();
				System.out.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count.get()));
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				semaphore.release();
				System.out.println(String.format("T[%d] release", Thread.currentThread().getId()));
			}
		}

		public int getCount() {
			return count.get();
		}
	}

	@Test
	public void acquireUninterruptibly() {
		int expected = 3;
		int taskSize = 3;
		AcquireUninterruptiblyWorker worker = new AcquireUninterruptiblyWorker();
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

	protected class TryAcquireWorker implements Runnable {

		private Semaphore semaphore = new Semaphore(2);
		private AtomicInteger count = new AtomicInteger();

		public TryAcquireWorker() {
		}

		@Override
		public void run() {
			try {
				System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
				boolean isLockAcquired = semaphore.tryAcquire(100, TimeUnit.MILLISECONDS);
				System.out.println(
						String.format("T[%d] isLockAcquired: %b", Thread.currentThread().getId(), isLockAcquired));
				if (isLockAcquired) {
					try {
						System.out.println(String.format("T[%d] acquire", Thread.currentThread().getId()));
						TimeUnit.SECONDS.sleep(1);
						count.incrementAndGet();
						System.out
								.println(String.format("T[%d] count: %d", Thread.currentThread().getId(), count.get()));
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} finally {
						semaphore.release();
						System.out.println(String.format("T[%d] release", Thread.currentThread().getId()));
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public int getCount() {
			return count.get();
		}
	}

	@Test
	public void tryAcquire() {
		int expected = 2;
		int taskSize = 3;
		TryAcquireWorker worker = new TryAcquireWorker();
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
