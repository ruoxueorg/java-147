package org.ruoxue.java_147.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class LoopMapTest {

	@Test
	public void entrySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void keySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		for (String key : map.keySet()) {
			System.out.println(key);

		}
	}

	@Test
	public void values() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		for (Integer value : map.values()) {
			System.out.println(value);

		}
	}

	@Test
	public void iterator() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> e = iterator.next();
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void forEach() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);
		map.forEach((k, v) -> System.out.println(k + ", " + v));
	}
}
