package org.ruoxue.java_147.map.enummap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;

public class EnumMapComputeTest {

	public enum Fruit {
		GRAPE, KIWIFRUIT, LEMON,

		EMPTY,

		APPLE, BANANA, CHERRY
	}

	@Test
	public void compute() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		Fruit key = Fruit.GRAPE;
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void computeFunction() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		Fruit key = Fruit.GRAPE;
		map.compute(key, (k, v) -> {
			v += 1;
			return v;
		});
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test(expected = NullPointerException.class)
	public void computeNullPointerException() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		Fruit key = null;
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
	}

	@Test
	public void computeIfAbsent() {
		Integer expected = 4;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		Fruit key = Fruit.APPLE;
		Integer result = map.computeIfAbsent(key, k -> 4);
		System.out.println(result);
		System.out.println(map);
		assertEquals(expected, result);
		key = Fruit.GRAPE;
		result = map.computeIfAbsent(key, k -> 2);
		System.out.println(result);
		System.out.println(map);
		assertEquals(1, result.intValue());
	}

	@Test
	public void computeIfPresent() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		System.out.println(map);
		Fruit key = Fruit.GRAPE;
		Integer result = map.computeIfPresent(key, (k, v) -> v + 1);
		System.out.println(result);
		System.out.println(map);
		assertEquals(expected, result);
		key = Fruit.APPLE;
		result = map.computeIfPresent(key, (k, v) -> 4);
		System.out.println(result);
		System.out.println(map);
		assertNull(result);
	}
}
