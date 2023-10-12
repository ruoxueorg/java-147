package org.ruoxue.java_147.synchronization.thread;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

public class WaitNotifyTest {

	protected class BlockQueue<E> {
		private int maxSize;
		private List<E> list = new ArrayList<E>();

		public BlockQueue(int maxSize) {
			this.maxSize = maxSize;
		}

		public synchronized void put(E e) throws InterruptedException {
			while (list.size() == maxSize) {
				System.out.println("T[" + Thread.currentThread().getId() + "] producer waiting");
				wait();
			}
			boolean added = list.add(e);
			if (added) {
				notify();
			}
		}

		public synchronized E take() throws InterruptedException {
			E result = null;
			while (list.size() == 0) {
				System.out.println("T[" + Thread.currentThread().getId() + "] comsumer waiting");
				wait();
			}
			result = list.remove(0);
			if (result != null) {
				notify();
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
		List<Thread> comsumers = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println("T[" + Thread.currentThread().getId() + "] comsumer take: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		comsumers.forEach(e -> e.start());

		AtomicInteger ids = new AtomicInteger();
		List<Thread> producers = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println("T[" + Thread.currentThread().getId() + "] producer put: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		producers.forEach(e -> e.start());

		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void produceAndConsume() {
		int expectedSize = 1;
		BlockQueue<Integer> queue = new BlockQueue<Integer>(2);
		AtomicInteger ids = new AtomicInteger();
		List<Thread> producers = Stream.generate(() -> new Thread(() -> {
			try {
				int value = ids.getAndIncrement();
				queue.put(value);
				System.out.println("T[" + Thread.currentThread().getId() + "] producer put: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(3).collect(Collectors.toList());
		producers.forEach(e -> e.start());

		List<Thread> comsumers = Stream.generate(() -> new Thread(() -> {
			try {
				Integer value = queue.take();
				System.out.println("T[" + Thread.currentThread().getId() + "] comsumer take: " + value);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		})).limit(2).collect(Collectors.toList());
		comsumers.forEach(e -> e.start());

		System.out.println("T[" + Thread.currentThread().getId() + "] " + queue);
		assertEquals(expectedSize, queue.size());
	}
}
