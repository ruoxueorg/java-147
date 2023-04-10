package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.Test;

public class BiConsumerReferenceTest {

	@Test
	public void StringBuilder_append() {
		StringBuilder value = new StringBuilder();
		BiConsumer<StringBuilder, String> append = StringBuilder::append;
		append.accept(value, "Bacon, ");
		append.accept(value, "Ham, ");
		append.accept(value, "Pork");
		System.out.println(value);
	}

	@Test
	public void List_add() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		BiConsumer<List<String>, String> add = List::add;
		add.accept(list, "Bacon");
		add.accept(list, "Ham");
		add.accept(list, "Pork");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void List_remove() {
		int expectedSize = 2;
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiConsumer<List<String>, String> remove = List::remove;
		remove.accept(list, "Bacon");
		remove.accept(list, "Bread");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void Map_remove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);
		BiConsumer<Map<String, Integer>, String> remove = Map::remove;
		remove.accept(map, "Bacon");
		remove.accept(map, "Bread");
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void Queue_offer() {
		int expectedSize = 3;
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		BiConsumer<Queue<String>, String> offer = Queue::offer;
		offer.accept(queue, "Bacon");
		offer.accept(queue, "Ham");

		Consumer<String> queueOfferConsumer = queue::offer;
		queueOfferConsumer.accept("Pork");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}

	@Test
	public void Queue_remove() {
		int expectedSize = 2;
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiConsumer<Queue<String>, String> remove = Queue::remove;
		remove.accept(queue, "Bacon");
		remove.accept(queue, "Bread");
		System.out.println(queue);
		assertEquals(expectedSize, queue.size());
	}
}
