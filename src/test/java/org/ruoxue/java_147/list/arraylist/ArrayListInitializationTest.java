package org.ruoxue.java_147.list.arraylist;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import com.google.common.collect.ImmutableList;

public class ArrayListInitializationTest {

	@Test
	public void constructor() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
		List<String> newList = new ArrayList<>(list);
		System.out.println(newList);
		assertEquals(expectedSize, newList.size());

		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> newIntList = new ArrayList<>(intList);
		System.out.println(newIntList);
		assertEquals(expectedSize, newIntList.size());
	}

	@Test
	public void immutableList() {
		int expectedSize = 3;
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = ImmutableList.of(1, 2, 3);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeThrowException() {
		int expectedSize = 3;
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
		list.remove(0);
	}

	@Test
	public void stream() {
		int expectedSize = 3;
		List<String> list = Stream.of("Apple", "Banana", "Cherry").collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());

		List<Integer> intArrayList = Stream.of(new int[] { 1, 2, 3 }).flatMapToInt(e -> Arrays.stream(e)).boxed()
				.collect(Collectors.toList());
		System.out.println(intArrayList);
		assertEquals(expectedSize, intArrayList.size());
	}
}
