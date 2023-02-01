package org.ruoxue.java_147.queue;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinkedBlockingQueueClassTest {

	@Test
	public void offer() {
		int expectedSize = 3;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.offer("Papaya");
		queue.offer("Strawberry");
		queue.offer("Watermelon");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void offerWhenFull() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
		queue.offer("Papaya");
		queue.offer("Strawberry");
		boolean offered = queue.offer("Watermelon");
		System.out.println(offered);
		assertFalse(offered);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void offerTimeOutWhenFull() {
		try {
			int expectedSize = 2;
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
			queue.offer("Papaya");
			queue.offer("Strawberry");
			boolean offered = queue.offer("Watermelon", 3, TimeUnit.SECONDS);
			System.out.println(offered);
			assertFalse(offered);
			System.out.println(queue);
			assertEquals(expectedSize, queue.size());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void poll() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.offer("Papaya");
		queue.offer("Strawberry");
		queue.offer("Watermelon");
		String value = queue.poll();
		System.out.println(value);
		assertNotNull(value);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void pollWhenEmpty() {
		int expectedSize = 0;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		String value = queue.poll();
		System.out.println(value);
		assertNull(value);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void pollTimeOutWhenEmpty() {
		try {
			int expectedSize = 0;
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
			String value = queue.poll(3, TimeUnit.SECONDS);
			System.out.println(value);
			assertNull(value);
			System.out.println(queue);
			assertEquals(expectedSize, queue.size());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void put() {
		try {
			int expectedSize = 2;
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);

			Thread thread = new Thread(() -> {
				try {
					String value = queue.take();
					System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			thread.start();

			queue.put("Papaya");
			queue.put("Strawberry");
			queue.put("Watermelon");
			System.out.println("T[" + Thread.currentThread().getId() + "] put: " + queue);
			assertEquals(expectedSize, queue.size());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void take() {
		try {
			int expectedSize = 2;
			BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);

			Thread thread = new Thread(() -> {
				try {
					queue.put("Papaya");
					queue.put("Strawberry");
					queue.put("Watermelon");
					System.out.println("T[" + Thread.currentThread().getId() + "] put: " + queue);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
			thread.start();

			String value = queue.take();
			System.out.println("T[" + Thread.currentThread().getId() + "] take: " + value);
			assertEquals(expectedSize, queue.size());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void contains() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		boolean contains = queue.contains("Papaya");
		System.out.println(contains);
		assertTrue(contains);

		contains = queue.contains("Grape");
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void containsAll() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
		queue2.add("Papaya");
		queue2.add("Strawberry");

		boolean contains = queue.containsAll(queue2);
		System.out.println(contains);
		assertTrue(contains);

		contains = queue2.containsAll(queue);
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		List<String> list = queue.stream().filter(e -> e.length() > 6).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void parallelStream() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		queue.parallelStream().forEach(System.out::println);
		System.out.println("----------");
		queue.parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
		queue2.add("Papaya");
		queue2.add("Lemon");
		queue2.add("Mango");

		queue.retainAll(queue2);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}
}
