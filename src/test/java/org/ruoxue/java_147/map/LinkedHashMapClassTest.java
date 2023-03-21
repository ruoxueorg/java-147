package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinkedHashMapClassTest {

	@Test
	public void containsKey() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		boolean containsKey = map.containsKey("Lemon");
		System.out.println(containsKey);
		assertTrue(containsKey);
	}

	@Test
	public void containsValue() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		boolean containsValue = map.containsValue(3);
		System.out.println(containsValue);
		assertTrue(containsValue);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Set<String> set = map.keySet().stream().filter(e -> e.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.keySet().parallelStream().forEach(System.out::println);
		System.out.println("----------");
		map.keySet().parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void replace() {
		Integer expected = 1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer replaced = map.replace("Grape", 10);
		System.out.println(map);
		assertEquals(expected, replaced);
		boolean repl = map.replace("Grape", 10, 1);
		System.out.println(map);
		assertEquals(true, repl);
	}

	@Test
	public void replaceAll() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.replaceAll((k, v) -> {
			v = v * 10;
			return v;
		});
		System.out.println(map);
	}

	@Test
	public void merge() {
		Integer expected = 11;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer replaced = map.merge("Grape", 10, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(expected, replaced);

		replaced = map.merge("Papaya", 4, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(4, replaced.intValue());
	}
}
