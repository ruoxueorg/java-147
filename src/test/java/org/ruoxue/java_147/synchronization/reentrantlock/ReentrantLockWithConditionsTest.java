package org.ruoxue.java_147.synchronization.reentrantlock;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class ReentrantLockWithConditionsTest {

	protected class BlockQueue<E> {
		private int maxSize;
		private List<E> list = new ArrayList<E>();
		private final Lock lock = new ReentrantLock();
		private Condition notEmpty = lock.newCondition();
		private Condition notFull = lock.newCondition();

		public BlockQueue(int maxSize) {
			this.maxSize = maxSize;
		}

		public void put(E e) throws InterruptedException {
			lock.lock();
			try {
				while (list.size() == maxSize) {
					System.out.println(String.format("T[%d] producer waiting", Thread.currentThread().getId()));
					notEmpty.await();
				}
				boolean added = list.add(e);
				if (added) {
					notFull.signal();
				}
			} finally {
				lock.unlock();
			}
		}

		public E take() throws InterruptedException {
			E result = null;
			lock.lock();
			try {
				while (list.size() == 0) {
					System.out.println(String.format("T[%d] comsumer waiting", Thread.currentThread().getId()));
					notFull.await();
				}
				result = list.remove(0);
				if (result != null) {
					notEmpty.signal();
				}
			} finally {
				lock.unlock();
			}
			return result;
		}

		public int size() {
			return list.size();
		}

		@Override
		public String toString() {
			ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
			builder.appendSuper(super.toString());
			builder.append("maxSize", maxSize);
			builder.append("list", list);
			return builder.toString();
		}
	}

	@Test
	public void consumeAndProduce() {
		int expectedSize = 1;
		BlockQueue<Integer> queue = new BlockQueue<Integer>(2);
		List<Thread> consumers = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println(String.format("T[%d] consumer take: %d", Thread.currentThread().getId(), value));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		consumers.forEach(e -> e.start());

		AtomicInteger ids = new AtomicInteger();
		List<Thread> producers = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println(String.format("T[%d] producer put: %d", Thread.currentThread().getId(), value));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		producers.forEach(e -> e.start());

		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}

	protected class DoWorker {

		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		private AtomicInteger count = new AtomicInteger();
		private static final int MAX_COUNT = 2;

		public DoWorker() {
		}

		public void doWork() throws Exception {
			await();
			try {
				System.out.println(String.format("T[%d] ready", Thread.currentThread().getId()));
				TimeUnit.SECONDS.sleep(3);
			} finally {
				signal();
			}
		}

		public void await() throws InterruptedException {
			lock.lock();
			try {
				while (count.get() >= MAX_COUNT) {
					System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
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
				System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	@Test
	public void doWorker() {
		DoWorker worker = new DoWorker();
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
				System.out.println(String.format("T[%d] ready", Thread.currentThread().getId()));
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
					System.out.println(String.format("T[%d] waiting", Thread.currentThread().getId()));
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
				System.out.println(String.format("T[%d] finished", Thread.currentThread().getId()));
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	@Test
	public void timeoutWorker() {
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
