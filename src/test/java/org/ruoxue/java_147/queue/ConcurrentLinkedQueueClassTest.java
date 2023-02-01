package org.ruoxue.java_147.queue;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import org.junit.Test;

public class ConcurrentLinkedQueueClassTest {

	@Test
	public void offer() {
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.offer("Papaya");
		queue.offer("Strawberry");
		queue.offer("Watermelon");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void poll() {
		int expectedSize = 2;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		String value = queue.poll();
		System.out.println(value);
		assertNull(value);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void contains() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		Queue<String> queue2 = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		List<String> list = queue.stream().filter(e -> e.length() > 6).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void parallelStream() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		Queue<String> queue2 = new ConcurrentLinkedQueue<String>();
		queue2.add("Papaya");
		queue2.add("Lemon");
		queue2.add("Mango");

		queue.retainAll(queue2);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}
}
