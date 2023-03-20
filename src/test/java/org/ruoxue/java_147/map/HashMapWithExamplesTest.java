package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class HashMapWithExamplesTest {

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
	public void keyForEach() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.keySet().forEach(e -> System.out.println(e));
	}

	@Test
	public void keyForEachRemaining() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
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
	public void keyStreamToArray() {
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
}
