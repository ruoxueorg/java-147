package org.ruoxue.java_147.queue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class LinkedListMethodsTest {

	public LinkedListMethodsTest() {

	}

	@Test
	public void add() {
		int expectedSize = 3;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addByIndex() {
		int expectedSize = 4;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.add(2, "Durian");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		List<String> list2 = new LinkedList<String>();
		list2.add("Durian");
		list2.add("Guava");
		list2.add("Pitaya");

		list.addAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void get() {
		String expected = "Strawberry";
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		String value = list.get(1);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void set() {
		String expected = "Durian";
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		System.out.println(list);

		list.set(0, "Durian");
		System.out.println(list);
		assertEquals(expected, list.get(0));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.remove(0);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		List<String> list2 = new LinkedList<String>();
		list2.add("Papaya");
		list2.add("Strawberry");
		list2.add("Pitaya");
		list.removeAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.clear();
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		System.out.println(list.size());
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void isEmpty() {
		List<String> list = new LinkedList<String>();
		System.out.println(list.isEmpty());
		assertTrue(list.isEmpty());
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		System.out.println(list.isEmpty());
		assertFalse(list.isEmpty());
	}

}
