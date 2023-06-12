package org.ruoxue.java_147.map.hashmap;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.google.common.collect.ImmutableMap;

public class InitializeHashMapTest {

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
	public void doubleBrace() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>() {
			private static final long serialVersionUID = -5487223135233714632L;
			{
				put("Grape", 1);
				put("Kiwifruit", 2);
				put("Lemon", 3);
			}
		};
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Map<String, Integer> newMap = new HashMap<String, Integer>();
		newMap.putAll(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void constructor() {
		int expectedSize = 3;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Map<String, Integer> newMap = new HashMap<String, Integer>(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void immutableMap() {
		int expectedSize = 3;
		Map<String, Integer> map = ImmutableMap.of("Grape", 1, "Kiwifruit", 2, "Lemon", 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeThrowException() {
		int expectedSize = 3;
		Map<String, Integer> map = ImmutableMap.of("Grape", 1, "Kiwifruit", 2, "Lemon", 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
		map.remove("Grape");
	}
}
