package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import org.junit.Test;

import com.google.common.base.Objects;

public class RemoveElementsFromMapTest {

	public RemoveElementsFromMapTest() {

	}

	@Test
	public void removeByKey() {
		int expectedSize = 2;
		Integer expectedValue = 1;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer removed = map.remove("Grape");

		System.out.println(removed);
		assertEquals(expectedValue, removed);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void removeByKeyValue() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		boolean removed = map.remove("Lemon", 3);

		System.out.println(removed);
		assertTrue(removed);
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		removed = map.remove("Lemon", 10);
		System.out.println(removed);
		assertFalse(removed);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void keyIteratorRemove() {
		int expectedSize = 2;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String e = it.next();
			if (Objects.equal("Kiwifruit", e)) {
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
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Iterator<Integer> it = map.values().iterator();
		while (it.hasNext()) {
			Integer e = it.next();
			if (Objects.equal(3, e)) {
				it.remove();
			}
		}
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}
}
