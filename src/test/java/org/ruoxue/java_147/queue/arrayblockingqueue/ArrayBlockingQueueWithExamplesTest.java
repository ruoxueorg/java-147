package org.ruoxue.java_147.queue.arrayblockingqueue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

public class ArrayBlockingQueueWithExamplesTest {

	@Test
	public void forEach() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		queue.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		Iterator<String> it = queue.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void iterator() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		Iterator<String> it = queue.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void spliterator() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		Spliterator<String> sit = queue.spliterator();
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = queue.spliterator();
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}

	@Test
	public void trySplit() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		Spliterator<String> sit = queue.spliterator();
		Spliterator<String> sit2 = sit.trySplit();
		System.out.println(sit.getExactSizeIfKnown());
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		System.out.println(sit2.getExactSizeIfKnown());
		sit2.forEachRemaining(e -> System.out.println(e));
	}

	@Test
	public void toArray() {
		int expectedSize = 3;
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		String[] array = new String[queue.size()];
		queue.toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 3;
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		String[] array = queue.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void drainTo() {
		int expectedSize = 0;
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue);
		BlockingQueue<String> queue2 = new ArrayBlockingQueue<String>(10);
		queue.drainTo(queue2);
		System.out.println(queue);
		System.out.println(queue2);
		assertEquals(expectedSize, queue.size());
		assertEquals(3, queue2.size());
	}

	@Test
	public void drainToMaxElements() {
		int expectedSize = 2;
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		System.out.println(queue);
		BlockingQueue<String> queue2 = new ArrayBlockingQueue<String>(10);
		queue.drainTo(queue2, 1);
		System.out.println(queue);
		System.out.println(queue2);
		assertEquals(expectedSize, queue.size());
		assertEquals(1, queue2.size());
	}
}
