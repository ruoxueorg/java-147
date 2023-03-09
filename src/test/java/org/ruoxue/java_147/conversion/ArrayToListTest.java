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

		Integer[] integerArray = { 147, 168, 151, 460, 484 };
		List<Integer> integerList = new ArrayList<>();
		for (int e : integerArray) {
			integerList.add(e);
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

		int[] intArray = { 147, 168, 151, 460, 484 };
		Integer[] boxedArray = IntStream.of(intArray).boxed().toArray(Integer[]::new);
		List<Integer> intList = Arrays.asList(boxedArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());

		Integer[] integerArray = { 147, 168, 151, 460, 484 };
		List<Integer> integerList = Arrays.asList(integerArray);
		System.out.println(integerList);
		assertEquals(expectedSize, integerList.size());
	}

	@Test
	public void Collections_addAll() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = new ArrayList<>();
		Collections.addAll(list, array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		int[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = new ArrayList<>();
		Integer[] boxedArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
		Collections.addAll(intList, boxedArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());

		Integer[] integerArray = { 147, 168, 151, 460, 484 };
		List<Integer> integerList = new ArrayList<>();
		Collections.addAll(integerList, integerArray);
		System.out.println(integerList);
		assertEquals(expectedSize, integerList.size());
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
}
