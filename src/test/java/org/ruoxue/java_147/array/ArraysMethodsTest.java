package org.ruoxue.java_147.array;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ArraysMethodsTest {


//setAll
//fill
//
//toString
//deepToString
//
//equals
//deepEquals
//
//spliterator
//stream

	@Test
	public void asList() {
		int expectedSize = 3;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		List<String> list = Arrays.asList(array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		int result = Arrays.binarySearch(array, "Guava");
		System.out.println(result);
		assertEquals(expectedIndex, result);
	}

	@Test
	public void copyOf() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);

		List<String> list2 = new ArrayList<String>();
		list2.add("Papaya");
		list2.add("Strawberry");
		Collections.copy(list, list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void copyOfRange() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");

		List<String> list2 = new ArrayList<String>();
		list2.add("Papaya");
		list2.add("Strawberry");
		boolean result = Collections.disjoint(list, list2);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void mismatch() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);

		Collections.shuffle(list);
		System.out.println(list);
	}

	@Test
	public void sort() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
	}

	@Test
	public void reverse() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);

		Collections.reverse(list);
		System.out.println(list);
	}

	@Test
	public void swap() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);

		Collections.swap(list, 0, 2);
		System.out.println(list);
	}

}
