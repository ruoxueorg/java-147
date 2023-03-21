package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class LinkedHashMapMethodsTest {

	@Test
	public void put() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		Integer put = map.putIfAbsent("Lemon", 3);
		System.out.println(put);
		assertNull(put);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		Integer expected = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer value = map.get("Kiwifruit");
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void getOrDefault() {
		Integer expected = -1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer value = map.getOrDefault("", -1);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void update() {
		Integer expected = 10;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);

		Integer put = map.put("Grape", 10);
		System.out.println(put);
		assertEquals(1, put.intValue());
		System.out.println(map);
		assertEquals(expected, map.get("Grape"));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.remove("Grape");
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.clear();
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);

		Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
		map.put("Grape", 4);
		map.put("Kiwifruit", 5);
		map.put("Lemon", 6);

		map.putAll(map2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void isEmpty() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		System.out.println(map.isEmpty());
		assertTrue(map.isEmpty());
		map.put("Grape", 4);
		map.put("Kiwifruit", 5);
		map.put("Lemon", 6);
		System.out.println(map.isEmpty());
		assertFalse(map.isEmpty());
	}
}
