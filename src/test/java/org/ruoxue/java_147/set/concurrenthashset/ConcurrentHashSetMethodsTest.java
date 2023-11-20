package org.ruoxue.java_147.set.concurrenthashset;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ConcurrentHashSetMethodsTest {

	@Test
	public void newKeySet() {
		int expectedSize = 3;
		Set<String> set = ConcurrentHashMap.newKeySet();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void keySet() {
		int expectedSize = 3;
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		map.put("Longan", 1);
		map.put("Tomato", 2);
		map.put("Pear", 3);
		Set<String> set = map.keySet();
		System.out.println(set);
		System.out.println(map);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void keySetDefaultValue() {
		int expectedSize = 3;
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		Set<String> set = map.keySet(0);
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set);
		System.out.println(map);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void Sets_newConcurrentHashSet() {
		int expectedSize = 3;
		Set<String> set = Sets.newConcurrentHashSet();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		Set<String> set = ConcurrentHashMap.newKeySet();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = ConcurrentHashMap.newKeySet();
		set2.add("Grape");
		set2.add("Lemon");
		set2.add("Mango");

		set.addAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Set<String> set = ConcurrentHashMap.newKeySet();
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
		Set<String> set = ConcurrentHashMap.newKeySet();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = ConcurrentHashMap.newKeySet();
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
		Set<String> set = ConcurrentHashMap.newKeySet();
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
		Set<String> set = ConcurrentHashMap.newKeySet();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.size());
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void isEmpty() {
		Set<String> set = ConcurrentHashMap.newKeySet();
		System.out.println(set.isEmpty());
		assertTrue(set.isEmpty());
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.isEmpty());
		assertFalse(set.isEmpty());
	}
}
