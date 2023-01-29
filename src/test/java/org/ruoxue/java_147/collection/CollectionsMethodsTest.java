package org.ruoxue.java_147.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CollectionsMethodsTest {

	public CollectionsMethodsTest() {

	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		Collections.addAll(list, "Grape", "Lemon", "Mango");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		int result = Collections.binarySearch(list, "Banana");
		System.out.println(result);
		assertEquals(expectedIndex, result);
	}

	@Test
	public void copy() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		List<String> list2 = new ArrayList<String>();
		list2.add("Grape");
		list2.add("Lemon");
		Collections.copy(list, list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void disjoint() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new ArrayList<String>();
		list2.add("Grape");
		list2.add("Lemon");
		boolean result = Collections.disjoint(list, list2);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void shuffle() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		Collections.shuffle(list);
		System.out.println(list);
	}

	@Test
	public void sort() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Cherry");
		list.add("Banana");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
	}

	@Test
	public void reverse() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		Collections.reverse(list);
		System.out.println(list);
	}

	@Test
	public void swap() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		Collections.swap(list, 0, 2);
		System.out.println(list);
	}
}
