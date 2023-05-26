package org.ruoxue.java_147.set;

import static org.junit.Assert.*;

import java.util.TreeSet;
import java.util.Set;

import org.junit.Test;

public class TreeSetMethodsTest {

	@Test
	public void add() {
		int expectedSize = 3;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.remove("Longan");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new TreeSet<String>();
		set2.add("Longan");
		set2.add("Tomato");
		set2.add("Mango");
		set.removeAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.clear();
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.size());
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		Set<String> set = new TreeSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new TreeSet<String>();
		set2.add("Grape");
		set2.add("Lemon");
		set2.add("Mango");

		set.addAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void isEmpty() {
		Set<String> set = new TreeSet<String>();
		System.out.println(set.isEmpty());
		assertTrue(set.isEmpty());
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.isEmpty());
		assertFalse(set.isEmpty());
	}
}
