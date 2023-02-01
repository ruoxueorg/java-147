package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CollectionsWithExamplesTest {

	@Test
	public void fill() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		Collections.fill(list, "Orange");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void replaceAll() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		Collections.replaceAll(list, "Peach", "Mango");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void indexOfSubList() {
		int expected = 1;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		List<String> list2 = new ArrayList<String>();
		list2.add("Orange");
		list2.add("Peach");
		int index = Collections.indexOfSubList(list, list2);
		System.out.println(index);
		assertEquals(expected, index);

		list2 = new ArrayList<String>();
		list2.add("Orange");
		list2.add("Mango");
		index = Collections.indexOfSubList(list, list2);
		System.out.println(index);
		assertEquals(-1, index);
	}

	@Test
	public void lastIndexOfSubList() {
		int expected = 0;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		List<String> list2 = new ArrayList<String>();
		list2.add("Mango");
		list2.add("Orange");
		int index = Collections.lastIndexOfSubList(list, list2);
		System.out.println(index);
		assertEquals(expected, index);

		list2 = new ArrayList<String>();
		list2.add("Orange");
		list2.add("Mango");
		index = Collections.lastIndexOfSubList(list, list2);
		System.out.println(index);
		assertEquals(-1, index);
	}

	@Test
	public void nCopies() {
		int expectedSize = 2;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		List<List<String>> list2 = Collections.nCopies(2, list);
		System.out.println(list2);
		assertEquals(expectedSize, list2.size());
	}

	@Test
	public void rotate() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		Collections.rotate(list, 2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void max() {
		String expected = "Peach";
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		String value = Collections.max(list);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void min() {
		String expected = "Mango";
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		String value = Collections.min(list);
		System.out.println(value);
		assertEquals(expected, value);
	}
}
