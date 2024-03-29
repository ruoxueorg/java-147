package org.ruoxue.java_147.queue.linkedlist;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

import org.junit.Test;

public class LinkedListWithExamplesTest {

	@Test
	public void loop() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void forEach() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		List<String> list = new LinkedList<>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		Iterator<String> it = list.iterator();
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
		List<String> list = new LinkedList<>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void listIterator() {
		List<String> list = new LinkedList<>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		ListIterator<String> it = list.listIterator(list.size());
		while (it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

	@Test
	public void spliterator() {
		List<String> list = new LinkedList<>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		Spliterator<String> sit = list.spliterator();
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = list.spliterator();
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}

	@Test
	public void trySplit() {
		List<String> list = new LinkedList<>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		Spliterator<String> sit = list.spliterator();
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
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		String[] array = new String[list.size()];
		list.toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 3;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		String[] array = list.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

}
