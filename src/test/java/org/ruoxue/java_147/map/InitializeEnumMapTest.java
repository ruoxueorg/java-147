package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Map;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class InitializeEnumMapTest {

	public enum Fruit {
		GRAPE, KIWIFRUIT, LEMON,

		EMPTY,

		APPLE, BANANA, CHERRY
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
	public void doubleBrace() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		{
			{
				map.put(Fruit.GRAPE, 1);
				map.put(Fruit.KIWIFRUIT, 2);
				map.put(Fruit.LEMON, 3);
			}
		}
		;
		System.out.println(map);
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
	public void constructor() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Map<Fruit, Integer> newMap = new EnumMap<>(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void immutableMap() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = ImmutableMap.of(Fruit.GRAPE, 1, Fruit.KIWIFRUIT, 2, Fruit.LEMON, 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeThrowException() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = ImmutableMap.of(Fruit.GRAPE, 1, Fruit.KIWIFRUIT, 2, Fruit.LEMON, 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
		map.remove(Fruit.GRAPE);
	}
}
