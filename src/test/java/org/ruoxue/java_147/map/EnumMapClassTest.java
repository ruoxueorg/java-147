package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class EnumMapClassTest {

	public enum Fruit {
		GRAPE, KIWIFRUIT, LEMON,

		EMPTY,

		APPLE, BANANA, CHERRY
	}

	@Test
	public void containsKey() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		boolean containsKey = map.containsKey(Fruit.LEMON);
		System.out.println(containsKey);
		assertTrue(containsKey);
	}

	@Test
	public void containsValue() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		boolean containsValue = map.containsValue(3);
		System.out.println(containsValue);
		assertTrue(containsValue);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Set<Fruit> set = map.keySet().stream().filter(e -> e.name().length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.keySet().parallelStream().forEach(System.out::println);
		System.out.println("----------");
		map.keySet().parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void replace() {
		Integer expected = 1;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Integer replaced = map.replace(Fruit.GRAPE, 10);
		System.out.println(map);
		assertEquals(expected, replaced);
		boolean repl = map.replace(Fruit.GRAPE, 10, 1);
		System.out.println(map);
		assertEquals(true, repl);
	}

	@Test
	public void replaceAll() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.replaceAll((k, v) -> {
			v = v * 10;
			return v;
		});
		System.out.println(map);
	}

	@Test
	public void merge() {
		Integer expected = 11;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Integer replaced = map.merge(Fruit.GRAPE, 10, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(expected, replaced);

		replaced = map.merge(Fruit.APPLE, 4, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(4, replaced.intValue());
	}
}
