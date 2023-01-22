package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayListMethodsTest {

	public ArrayListMethodsTest() {

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
		String value = list.get(1);
		System.out.println(value);
		assertEquals(expected, value);
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

	@Test
	public void isEmpty() {
		List<String> list = new ArrayList<String>();
		System.out.println(list.isEmpty());
		assertTrue(list.isEmpty());
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list.isEmpty());
		assertFalse(list.isEmpty());
	}
}
