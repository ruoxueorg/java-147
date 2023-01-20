package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JavaListTest {

	public JavaListTest() {

	}

	@Test
	public void add() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addByIndex() {
		int expectedSize = 4;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.add(2, "Grape");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void get() {
		String expected = "Banana";
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);
		assertEquals(expected, list.get(1));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.remove(0);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.clear();
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list.size());
		assertEquals(expectedSize, list.size());
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
	public void set() {
		String expected = "Grape";
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		list.set(0, "Grape");
		System.out.println(list);
		assertEquals(expected, list.get(0));
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
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new ArrayList<String>();
		list2.add("Grape");
		list2.add("Lemon");
		list2.add("Mango");

		list.addAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
