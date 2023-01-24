package org.ruoxue.java_147.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapComputeTest {

	public HashMapComputeTest() {

	}

	@Test
	public void compute() {
		Integer expected = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void computeFunction() {
		Integer expected = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		map.compute(key, (k, v) -> {
			v += 1;
			return v;
		});
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test(expected = NullPointerException.class)
	public void computeNullPointerException() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = null;
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
	}

	@Test
	public void computeCount() {
		String value = "Hello World, Tutorial Java 147";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < value.length(); i++) {
			String key = String.valueOf(value.charAt(i));
			map.compute(key, (k, v) -> {
				if (v == null) {
					v = 1;
				} else {
					v += 1;
				}
				return v;
			});
		}
		System.out.println(map);
	}

	@Test
	public void computeIfAbsent() {
		Integer expected = 4;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Mango";
		map.computeIfAbsent(key, (k) -> 4);
		System.out.println(map);
		assertEquals(expected, map.get(key));
		key = "Grape";
		map.computeIfAbsent(key, (k) -> 2);
		assertEquals(1, map.get(key).intValue());
	}

	@Test
	public void computeIfPresent() {
		Integer expected = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		map.computeIfPresent(key, (k, v) -> v + 1);
		System.out.println(map);
		assertEquals(expected, map.get(key));
		key = "Mango";
		map.computeIfPresent(key, (k, v) -> 4);
		assertNull(map.get(key));
	}
}
