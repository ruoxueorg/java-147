package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Test;

public class RemoveItemsFromListTest {
	
	public RemoveItemsFromListTest() {
		
	}

	@Test(expected = ConcurrentModificationException.class)
	public void removeThrowException() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (String e : list) {
			if ("Apple".equals(e)) {
				list.remove(e);
			}
		}
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
	public void removeIfEquals() {
		int expectedSize = 1;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.removeIf(e -> e.equals("Apple") || e.equals("Cherry"));
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void removeIfPredicate() {
		int expectedSize = 1;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Predicate<String> predicate = v -> v.equals("Apple") || v.equals("Cherry");
		list.removeIf(predicate);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> willRemove = new ArrayList<>();
		for (String e : list) {
			if ("Apple".equals(e)) {
				willRemove.add(e);
			}
		}
		for (String e : willRemove) {
			list.remove(e);
		}
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
			if ("Apple".equals(e)) {
				it.remove();
			}
		}
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void filterCollect() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = new ArrayList<>();
		for (String e : list) {
			if (!"Apple".equals(e)) {
				result.add(e);
			}
		}
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void streamCollect() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = list.stream().filter(e -> !"Apple".equals(e)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}
}
