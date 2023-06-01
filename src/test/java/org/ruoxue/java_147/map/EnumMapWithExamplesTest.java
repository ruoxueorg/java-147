package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class EnumMapWithExamplesTest {

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
	public void entrySet() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		for (Map.Entry<Fruit, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void forEach() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	public void keyForEach() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.keySet().forEach(e -> System.out.println(e));
	}

	@Test
	public void keyForEachRemaining() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Set<Fruit> set = map.keySet();
		Iterator<Fruit> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void keyIterator() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Iterator<Fruit> it = map.keySet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void valueForEach() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		map.values().forEach(System.out::println);
	}

	@Test
	public void valueForEachRemaining() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Collection<Integer> collection = map.values();
		Iterator<Integer> it = collection.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void valueIterator() {
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);
		Iterator<Integer> it = map.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void keyToArray() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);

		Fruit[] array = new Fruit[map.size()];
		map.keySet().toArray(array);
		for (Fruit e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void keyStreamToArray() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new EnumMap<>(Fruit.class);
		map.put(Fruit.GRAPE, 1);
		map.put(Fruit.KIWIFRUIT, 2);
		map.put(Fruit.LEMON, 3);

		Fruit[] array = map.keySet().stream().toArray(Fruit[]::new);
		for (Fruit e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
