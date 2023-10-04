package org.ruoxue.java_147.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionsClassTest {

	public static Comparator<String> nameComparator = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	@Test
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);

		Collections.addAll(list, "Papaya", "Strawberry", "Watermelon");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");

		int result = Collections.binarySearch(list, "Orange");
		System.out.println(result);
		assertEquals(expectedIndex, result);
	}

	@Test
	public void copy() {
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
	public void disjoint() {
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
	public void shuffle() {
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
	public void sortWithComparator() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list, nameComparator);
		System.out.println(list);
	}

	@Test
	public void sortWithLambda() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> {
			return o2.compareTo(o1);
		});
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
