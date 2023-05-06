package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class ComparatorMethodsTest {

	@Test
	public void comparing() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		list.sort(Comparator.comparing(String::length));
		System.out.println(list);
		assertEquals("Mango", list.get(0));
		assertEquals("Peach", list.get(1));
		assertEquals("Orange", list.get(2));

		Integer[] array = new Integer[] { 3, 1, 2 };
		System.out.println(Arrays.toString(array));
		Arrays.sort(array, Comparator.comparing(Integer::intValue));
		System.out.println(Arrays.toString(array));
		assertEquals(1, array[0].intValue());
		assertEquals(2, array[1].intValue());
		assertEquals(3, array[2].intValue());
	}

	@Test
	public void comparingWithKeyComparator() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		list.sort(Comparator.comparing(String::toUpperCase, (o, o2) -> o.compareTo(o2)));
		System.out.println(list);
		assertEquals("Mango", list.get(0));
		assertEquals("Orange", list.get(1));
		assertEquals("Peach", list.get(2));

		Integer[] array = new Integer[] { 3, 1, 2 };
		System.out.println(Arrays.toString(array));
		Arrays.sort(array, Comparator.comparing(Integer::intValue, (i, i2) -> Integer.compare(i, i2)));
		System.out.println(Arrays.toString(array));
		assertEquals(1, array[0].intValue());
		assertEquals(2, array[1].intValue());
		assertEquals(3, array[2].intValue());
	}

	@Test
	public void thenComparing() {
		List<String> list = Arrays.asList("Orange", "Peach", "Mango   ");
		System.out.println(list);
		list.sort(Comparator.comparing(String::trim).thenComparing(String::length));
		System.out.println(list);
		assertEquals("Mango   ", list.get(0));
		assertEquals("Orange", list.get(1));
		assertEquals("Peach", list.get(2));

		Integer[] array = new Integer[] { 3, 1, 2 };
		System.out.println(Arrays.toString(array));
		Arrays.sort(array, Comparator.comparing(Integer::intValue).thenComparing(Integer::byteValue));
		System.out.println(Arrays.toString(array));
		assertEquals(1, array[0].intValue());
		assertEquals(2, array[1].intValue());
		assertEquals(3, array[2].intValue());
	}

	@Test
	public void nullsFirst() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange", null);
		System.out.println(list);
		Comparator<String> lengthComparator = Comparator.comparing(String::length);
		list.sort(Comparator.nullsFirst(lengthComparator));
		System.out.println(list);
		assertNull(list.get(0));
		assertEquals("Mango", list.get(1));
		assertEquals("Peach", list.get(2));
		assertEquals("Orange", list.get(3));

		Integer[] array = new Integer[] { 3, 1, 2, null };
		System.out.println(Arrays.toString(array));
		Comparator<Integer> intComparator = Comparator.comparing(Integer::intValue);
		Arrays.sort(array, Comparator.nullsFirst(intComparator));
		System.out.println(Arrays.toString(array));
		assertNull(array[0]);
		assertEquals(1, array[1].intValue());
		assertEquals(2, array[2].intValue());
		assertEquals(3, array[3].intValue());
	}

	@Test
	public void nullsLast() {
		List<String> list = Arrays.asList(null, "Orange", "Mango", "Peach");
		System.out.println(list);
		Comparator<String> lengthComparator = Comparator.comparing(String::length);
		list.sort(Comparator.nullsLast(lengthComparator));
		System.out.println(list);
		assertEquals("Mango", list.get(0));
		assertEquals("Peach", list.get(1));
		assertEquals("Orange", list.get(2));
		assertNull(list.get(3));

		Integer[] array = new Integer[] { null, 3, 1, 2 };
		System.out.println(Arrays.toString(array));
		Comparator<Integer> intComparator = Comparator.comparing(Integer::intValue);
		Arrays.sort(array, Comparator.nullsLast(intComparator));
		System.out.println(Arrays.toString(array));
		assertEquals(1, array[0].intValue());
		assertEquals(2, array[1].intValue());
		assertEquals(3, array[2].intValue());
		assertNull(array[3]);
	}
}
