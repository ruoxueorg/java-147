package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ArrayToListTest {

	@Test
	public void iteration() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = new ArrayList<>();
		for (String e : array) {
			list.add(e);
		}
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		int[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = new ArrayList<>();
		for (int e : intArray) {
			intList.add(e);
		}
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void Arrays_asList() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = Arrays.asList(array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		Integer[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = Arrays.asList(intArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void Collections_addAll() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = new ArrayList<>();
		Collections.addAll(list, array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		Integer[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = new ArrayList<>();
		Collections.addAll(intList, intArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void Arrays_stream() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = Arrays.stream(array).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		list = Arrays.stream(array).collect(Collectors.toCollection(LinkedList::new));
		System.out.println(list);
		assertEquals(LinkedList.class, list.getClass());
	}

	@Test
	public void Stream_of() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = Stream.of(array).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		list = Stream.of(array).collect(Collectors.toCollection(LinkedList::new));
		System.out.println(list);
		assertEquals(LinkedList.class, list.getClass());
	}

	@Test
	public void Arrays_stream_intArray() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		IntStream stream = Arrays.stream(array);
		List<Integer> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		stream = Arrays.stream(array);
		list = stream.boxed().collect(Collectors.toCollection(LinkedList::new));
		System.out.println(list);
		assertEquals(LinkedList.class, list.getClass());
	}

	@Test
	public void Stream_of_intArray() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		Stream<int[]> stream = Stream.of(array);
		List<Integer> list = stream.flatMapToInt(e -> Arrays.stream(e)).boxed().collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		stream = Stream.of(array);
		list = stream.flatMapToInt(e -> Arrays.stream(e)).boxed().collect(Collectors.toCollection(LinkedList::new));
		System.out.println(list);
		Collectors.toCollection(LinkedList::new);
	}

	@Test
	public void Lists_newArrayList() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = Lists.newArrayList(array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		Integer[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = Lists.newArrayList(intArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}
}
