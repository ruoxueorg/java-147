package org.ruoxue.java_147.queue;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinkedListClassTest {

	@Test
	public void contains() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		boolean contains = list.contains("Papaya");
		System.out.println(contains);
		assertTrue(contains);

		contains = list.contains("Grape");
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void containsAll() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		List<String> list2 = new LinkedList<String>();
		list2.add("Papaya");
		list2.add("Strawberry");

		boolean contains = list.containsAll(list2);
		System.out.println(contains);
		assertTrue(contains);

		contains = list2.containsAll(list);
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void indexOf() {
		int expected = 1;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		int index = list.indexOf("Strawberry");
		System.out.println(index);
		assertEquals(expected, index);

		index = list.indexOf("Grape");
		System.out.println(index);
		assertEquals(-1, index);
	}

	@Test
	public void lastIndexOf() {
		int expected = 3;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.add("Papaya");

		int index = list.lastIndexOf("Papaya");
		System.out.println(index);
		assertEquals(expected, index);

		index = list.indexOf("Grape");
		System.out.println(index);
		assertEquals(-1, index);
	}

	@Test
	public void sort() {
		List<String> list = new LinkedList<String>();
		list.add("Strawberry");
		list.add("Papaya");
		list.add("Watermelon");
		list.sort(Comparator.naturalOrder());
		System.out.println(list);

		list.add("Grape");
		list.sort(Comparator.reverseOrder());
		System.out.println(list);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list = list.stream().filter(e -> e.length() > 6).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void parallelStream() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.parallelStream().forEach(System.out::println);
		System.out.println("----------");
		list.parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void subList() {
		int expectedSize = 2;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		System.out.println(list);
		List<String> list2 = list.subList(0, 2);
		System.out.println(list2);

		assertEquals(expectedSize, list2.size());
	}

	@Test
	public void replaceAll() {
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");
		list.replaceAll(e -> e.toUpperCase());
		System.out.println(list);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		List<String> list = new LinkedList<String>();
		list.add("Papaya");
		list.add("Strawberry");
		list.add("Watermelon");

		List<String> list2 = new LinkedList<String>();
		list2.add("Papaya");
		list2.add("Lemon");
		list2.add("Mango");

		list.retainAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
