package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.junit.Test;

import com.google.common.base.Objects;

public class RemoveElementsFromListTest {

	public RemoveElementsFromListTest() {

	}

	@Test
	public void removeByIndex() {
		int expectedSize = 2;
		String expectedValue = "Banana";
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		String removed = list.remove(1);

		System.out.println(removed);
		assertEquals(expectedValue, removed);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void removeByValue() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		boolean removed = list.remove("Cherry");

		System.out.println(removed);
		assertTrue(removed);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		removed = list.remove("Grape");
		System.out.println(removed);
		assertFalse(removed);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void removeIf() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		boolean removed = list.removeIf("Apple"::equals);

		System.out.println(removed);
		assertTrue(removed);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void iteratorRemove() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String e = it.next();
			if (Objects.equal("Banana", e)) {
				it.remove();
			}
		}
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
