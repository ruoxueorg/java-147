package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import org.junit.Test;

public class ConsumerReferenceTest {

	@Test
	public void List_add() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		Consumer<String> add = list::add;
		add.accept("Bacon");
		add.accept("Ham");
		add.accept("Pork");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void List_remove() {
		int expectedSize = 2;
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Consumer<String> remove = list::remove;
		remove.accept("Bacon");
		remove.accept("Bread");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void Queue_offer() {
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		Consumer<String> offer = queue::offer;
		offer.accept("Bacon");
		offer.accept("Ham");

		Consumer<String> queueOfferConsumer = queue::offer;
		queueOfferConsumer.accept("Pork");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void Queue_remove() {
		int expectedSize = 2;
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Consumer<String> remove = queue::remove;
		remove.accept("Bacon");
		remove.accept("Bread");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}
}
