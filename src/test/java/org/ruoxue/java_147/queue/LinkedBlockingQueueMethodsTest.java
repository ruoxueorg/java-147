package org.ruoxue.java_147.queue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class LinkedBlockingQueueMethodsTest {

	@Test
	public void add() {
		int expectedSize = 3;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test(expected = IllegalStateException.class)
	public void addWhenFull() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
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
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		String value = queue.peek();
		System.out.println(value);
		assertEquals(expected, value);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void element() {
		String expected = "Papaya";
		int expectedSize = 3;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
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
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		String value = queue.element();
		System.out.println(value);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
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
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.remove();
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
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
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
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
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue.size());
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void isEmpty() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		System.out.println(queue.isEmpty());
		assertTrue(queue.isEmpty());
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue.isEmpty());
		assertFalse(queue.isEmpty());
	}

	@Test
	public void remainingCapacity() {
		int expectedSize = Integer.MAX_VALUE - 3;
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue.remainingCapacity());
		assertEquals(expectedSize, queue.remainingCapacity());
	}
}
