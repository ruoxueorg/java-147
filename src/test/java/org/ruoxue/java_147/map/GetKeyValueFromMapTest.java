package org.ruoxue.java_147.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GetKeyValueFromMapTest {

	public GetKeyValueFromMapTest() {

	}

	@Test
	public void entrySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			String key = e.getKey();
			int value = e.getValue();
			System.out.println(key + ", " + value);
		}
	}

	@Test
	public void keySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		for (String key : map.keySet()) {
			System.out.println(key);
		}
	}

	@Test
	public void values() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		for (Integer value : map.values()) {
			System.out.println(value);
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
}
