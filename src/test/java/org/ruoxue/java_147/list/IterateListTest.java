package org.ruoxue.java_147.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class IterateListTest {

	public IterateListTest() {

	}

	@Test
	public void classic() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void advanced() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (String e : list) {
			System.out.println(e);
		}
	}

	@Test
	public void iterator() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void listIterator() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		ListIterator<String> it = list.listIterator(list.size());
		while (it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

	@Test
	public void loop() {
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void whileLoop() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		int i = 0;
		while (i < list.size()) {
			System.out.println(list.get(i));
			i++;
		}
	}

	@Test
	public void forEach() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachAction() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.forEach(System.out::println);
		list.forEach(e -> System.out.println(e));
		list.forEach(e -> {
			e += "_Store";
			System.out.println(e);
		});
		System.out.println(list);
	}

	@Test
	public void stream() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.stream().forEach(e -> System.out.println(e));
	}

}
