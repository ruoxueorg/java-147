package org.ruoxue.java_147.set.hashset;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

import org.junit.Test;

public class HashSetWithExamplesTest {

	@Test
	public void forEach() {
		Set<String> set = new HashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		Set<String> set = new HashSet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
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
	public void iterator() {
		Set<String> set = new HashSet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void spliterator() {
		Set<String> set = new HashSet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		Spliterator<String> sit = set.spliterator();
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = set.spliterator();
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}

	@Test
	public void trySplit() {
		Set<String> set = new HashSet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		Spliterator<String> sit = set.spliterator();
		Spliterator<String> sit2 = sit.trySplit();
		System.out.println(sit.getExactSizeIfKnown());
		sit.forEachRemaining(e -> System.out.println(e));
		
		System.out.println("----------");
		System.out.println(sit2.getExactSizeIfKnown());
		sit2.forEachRemaining(e -> System.out.println(e));
	}

	@Test
	public void toArray() {
		int expectedSize = 3;
		Set<String> set = new HashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		String[] array = new String[set.size()];
		set.toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 3;
		Set<String> set = new HashSet<String>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		String[] array = set.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
