package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class RemoveFromMapTest {

	@Test(expected = ConcurrentModificationException.class)
	public void removeThrowException() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			String key = e.getKey();
			if ("Apple".equals(key)) {
				map.remove(key);
			}
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void entrySetRemoveIf() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		map.entrySet().removeIf(e -> e.getKey().equals("Apple"));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void keySetRemoveIf() {
		int expectedSize = 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		map.keySet().removeIf(k -> k.equals("Apple") || k.equals("Cherry"));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void valuesRemoveIf() {
		int expectedSize = 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		map.values().removeIf(v -> v == 1 || v == 3);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		List<String> willRemove = new ArrayList<>();
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			String key = e.getKey();
			if ("Apple".equals(key)) {
				willRemove.add(key);
			}
		}
		for (String e : willRemove) {
			map.remove(e);
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void entrySetIteratorRemove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> e = it.next();
			String key = e.getKey();
			if ("Apple".equals(key)) {
				it.remove();
			}
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void keySetIteratorRemove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String e = it.next();
			if ("Apple".equals(e)) {
				it.remove();
			}
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void valuesIteratorRemove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Iterator<Integer> it = map.values().iterator();
		while (it.hasNext()) {
			Integer e = it.next();
			if (1 == e.intValue()) {
				it.remove();
			}
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void filterCollect() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			String key = e.getKey();
			if (!"Apple".equals(key)) {
				result.put(e.getKey(), e.getValue());
			}
		}
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void streamCollect() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Map<String, Integer> result = map.entrySet().stream().filter(e -> !"Apple".equals(e.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}
}
