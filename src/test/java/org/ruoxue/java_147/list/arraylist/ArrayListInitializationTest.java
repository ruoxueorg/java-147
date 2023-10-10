package org.ruoxue.java_147.list.arraylist;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ArrayListInitializationTest {

	@Test
	public void constructor() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
		List<String> newList = new ArrayList<>(list);
		System.out.println(newList);
		assertEquals(expectedSize, newList.size());

		List<Integer> intList = Arrays.asList(1, 2, 3);
		List<Integer> newIntList = new ArrayList<>(intList);
		System.out.println(newIntList);
		assertEquals(expectedSize, newIntList.size());
	}

	@Test
	public void Stream_of() {
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

	@Test
	public void Collections_addAll() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>();
		Collections.addAll(list, "Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = new ArrayList<>();
		Collections.addAll(intList, 1, 2, 3);
		System.out.println(intList);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void Collections_unmodifiableList() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = Collections.unmodifiableList(list);
		System.out.println(result);
		assertThatCode(() -> result.add("Grape")).isInstanceOf(UnsupportedOperationException.class);

		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		List<Integer> intResult = Collections.unmodifiableList(intList);
		System.out.println(intResult);
		assertThatCode(() -> intResult.add(Integer.MAX_VALUE)).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void ImmutableList_of() {
		int expectedSize = 3;
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
		assertThatCode(() -> list.add("Grape")).isInstanceOf(UnsupportedOperationException.class);

		List<Integer> intList = ImmutableList.of(1, 2, 3);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
		assertThatCode(() -> intList.add(Integer.MAX_VALUE)).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void Lists_newArrayList() {
		int expectedSize = 3;
		List<String> list = Lists.newArrayList("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = Lists.newArrayList(1, 2, 3);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}
}
