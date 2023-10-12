package org.ruoxue.java_147.queue.concurrentlinkedqueue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Queue;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class ConcurrentLinkedQueueWithExamplesTest {

	@Test
	public void forEach() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");
		queue.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
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
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("Papaya");
		queue.add("Strawberry");
		queue.add("Watermelon");

		String[] array = queue.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
