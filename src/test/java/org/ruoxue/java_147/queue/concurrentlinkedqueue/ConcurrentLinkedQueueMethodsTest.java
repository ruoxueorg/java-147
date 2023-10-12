package org.ruoxue.java_147.queue.concurrentlinkedqueue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class ConcurrentLinkedQueueMethodsTest {

	@Test
	public void add() {
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		Queue<String> queue2 = new ConcurrentLinkedQueue<String>();
		queue2.add("Durian");
		queue2.add("Guava");
		queue2.add("Pitaya");

		queue.addAll(queue2);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void peek() {
		String expected = "Papaya";
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		String value = queue.peek();
		System.out.println(value);
		assertEquals(null, value);
		
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		value = queue.peek();
		System.out.println(value);
		assertEquals(expected, value);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void element() {
		String expected = "Papaya";
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		String value = queue.element();
		System.out.println(value);
		assertEquals(expected, value);
		assertEquals(expectedSize, queue.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void elementWhenEmpty() {
		int expectedSize = 0;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		String value = queue.element();
		System.out.println(value);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		queue.remove();
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
		queue.remove("Watermelon");
		System.out.println(queue);
		assertEquals(1, queue.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void removeWhenEmpty() {
		int expectedSize = 0;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.remove();
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		Queue<String> queue2 = new ConcurrentLinkedQueue<String>();
		queue2.add("Papaya");
		queue2.add("Strawberry");
		queue2.add("Pitaya");
		queue.removeAll(queue2);
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		queue.clear();
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue.size());
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void isEmpty() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		System.out.println(queue.isEmpty());
		assertTrue(queue.isEmpty());
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue.isEmpty());
		assertFalse(queue.isEmpty());
	}
}
