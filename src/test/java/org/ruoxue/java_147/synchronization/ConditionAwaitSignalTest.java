package org.ruoxue.java_147.synchronization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class ConditionAwaitSignalTest {

	protected class BlockQueue<E> {
		private int maxSize;
		private List<E> list = new ArrayList<E>();
		private final Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public BlockQueue(int maxSize) {
			this.maxSize = maxSize;
		}

		public void put(E e) throws InterruptedException {
			lock.lock();
			try {
				while (list.size() == maxSize) {
					System.out.println("T[" + Thread.currentThread().getId() + "] put waiting");
					condition.await();
				}
				list.add(e);
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public E take() throws InterruptedException {
			E result = null;
			lock.lock();
			try {
				while (list.size() == 0) {
					System.out.println("T[" + Thread.currentThread().getId() + "] take waiting");
					condition.await();
				}
				result = list.remove(0);
				condition.signal();
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
		List<Thread> takeThreads = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		takeThreads.forEach(e -> e.start());

		AtomicInteger ids = new AtomicInteger();
		List<Thread> putThreads = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println("T[" + Thread.currentThread().getId() + "] put: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		putThreads.forEach(e -> e.start());

		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void produceAndConsume() {
		int expectedSize = 1;
		BlockQueue<Integer> queue = new BlockQueue<Integer>(2);
		AtomicInteger ids = new AtomicInteger();
		List<Thread> putThreads = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println("T[" + Thread.currentThread().getId() + "] put: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		putThreads.forEach(e -> e.start());

		List<Thread> takeThreads = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		takeThreads.forEach(e -> e.start());

		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}
}
