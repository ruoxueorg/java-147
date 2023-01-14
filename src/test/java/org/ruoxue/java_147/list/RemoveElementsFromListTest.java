package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.junit.Test;

public class RemoveElementsFromListTest {
	
	public RemoveElementsFromListTest() {
		
	}

	@Test
	public void removeByIndex() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.remove(1);
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
		list.remove("Cherry");
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
		list.removeIf("Apple"::equals);
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
			if ("Banana".equals(e)) {
				it.remove();
			}
		}
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
