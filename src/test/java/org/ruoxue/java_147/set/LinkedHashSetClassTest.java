package org.ruoxue.java_147.set;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinkedHashSetClassTest {

	@Test
	public void contains() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		boolean contains = set.contains("Longan");
		System.out.println(contains);
		assertTrue(contains);

		contains = set.contains("Grape");
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void containsAll() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new LinkedHashSet<String>();
		set2.add("Longan");
		set2.add("Tomato");

		boolean contains = set.containsAll(set2);
		System.out.println(contains);
		assertTrue(contains);

		contains = set2.containsAll(set);
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void stream() {
		int expectedSize = 1;
		Set<String> set = new LinkedHashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set = set.stream().filter(e -> e.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.parallelStream().forEach(System.out::println);
		System.out.println("----------");
		set.parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		Set<String> set = new LinkedHashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new LinkedHashSet<String>();
		set2.add("Longan");
		set2.add("Lemon");
		set2.add("Mango");

		set.retainAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}
}
