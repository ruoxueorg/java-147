package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

public class EnumMapMethodsTest {

	public enum Fruit {
		GRAPE,
		KIWIFRUIT,
		LEMON,
		EMPTY,
		APPLE,
		BANANA,
		CHERRY
	}
	
	@Test
	public void put() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		Integer put = map.putIfAbsent(Fruit.LEMON, 3);
		System.out.println(put);
		assertNull(put);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Integer value = map.get(Fruit.KIWIFRUIT);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void getOrDefault() {
		Integer expected = -1;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Integer value = map.getOrDefault(Fruit.EMPTY, -1);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void update() {
		Integer expected = 10;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);

		Integer put = map.put(Fruit.GRAPE, 10);
		System.out.println(put);
		assertEquals(1, put.intValue());
		System.out.println(map);
		assertEquals(expected, map.get(Fruit.GRAPE));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.remove(Fruit.GRAPE);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.clear();
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.APPLE, 1);
		map.put(Fruit.BANANA, 2);
		map.put(Fruit.CHERRY, 3);

		Map<Fruit, Integer> map2 = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);

		map.putAll(map2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void isEmpty() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		System.out.println(map.isEmpty());
		assertTrue(map.isEmpty());
		map.put(Fruit.GRAPE, 4);
		map.put(Fruit.KIWIFRUIT, 5);
		map.put(Fruit.LEMON, 6);
		System.out.println(map.isEmpty());
		assertFalse(map.isEmpty());
	}
}
