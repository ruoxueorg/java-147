package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class ArrayListWithExamplesTest {

	public ArrayListWithExamplesTest() {

	}

	@Test
	public void loop() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void forEach() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
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
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void listIterator() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		ListIterator<String> it = list.listIterator(list.size());
		while (it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

	@Test
	public void toArray() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		String[] array = new String[list.size()];
		list.toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void toArrayByStream() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		String[] array = list.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new ArrayList<String>();
		list2.add("Apple");
		list2.add("Lemon");
		list2.add("Mango");

		list.retainAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}