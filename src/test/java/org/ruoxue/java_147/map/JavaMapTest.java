package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JavaMapTest {

	public JavaMapTest() {

	}

	@Test
	public void put() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.putIfAbsent("Lemon", 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		Integer expected = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		assertEquals(expected, map.get("Kiwifruit"));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
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
		Map<String, Integer> map = new HashMap<String, Integer>();
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
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void entrySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void forEach() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	public void update() {
		Integer expected = 10;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);

		map.put("Grape", 10);
		System.out.println(map);
		assertEquals(expected, map.get("Grape"));
	}

	@Test
	public void keyToArray() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);

		String[] array = new String[map.size()];
		map.keySet().toArray(array);
		for (String e : array) {
			System.out.println(e);
		}

		assertEquals(expectedSize, array.length);
	}

	@Test
	public void keyToArrayByStream() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);

		String[] array = map.keySet().stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);

		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map.put("Grape", 4);
		map.put("Kiwifruit", 5);
		map.put("Lemon", 6);

		map.putAll(map2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}
}
