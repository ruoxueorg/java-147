package org.ruoxue.java_147.set.copyonwritearrayset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import org.junit.Test;

public class CopyOnWriteArraySetClassTest {

	@Test
	public void contains() {
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");

		boolean contains = set.contains("Apple");
		System.out.println(contains);
		assertTrue(contains);

		contains = set.contains("Grape");
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void containsAll() {
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");

		Set<String> set2 = new CopyOnWriteArraySet<>();
		set2.add("Apple");
		set2.add("Banana");

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
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");
		set = set.stream().filter(e -> e.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");
		set.parallelStream().forEach(System.out::println);
		System.out.println("----------");
		set.parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");

		Set<String> set2 = new CopyOnWriteArraySet<>();
		set2.add("Apple");
		set2.add("Lemon");
		set2.add("Mango");

		set.retainAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}
}
