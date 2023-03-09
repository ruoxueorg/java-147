package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class ArrayToListWithExamplesTest {

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
	public void ArrayUtils_toObject() {
		int expectedSize = 5;
		int[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = Arrays.asList(ArrayUtils.toObject(intArray));
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void Lists_newArrayList() {
		int expectedSize = 5;
		String[] array = { "Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484" };
		List<String> list = Lists.newArrayList(array);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		int[] intArray = { 147, 168, 151, 460, 484 };
		Integer[] boxedArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
		List<Integer> intList = Lists.newArrayList(boxedArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());

		Integer[] integerArray = { 147, 168, 151, 460, 484 };
		List<Integer> integerList = Lists.newArrayList(integerArray);
		System.out.println(integerList);
		assertEquals(expectedSize, integerList.size());
	}

	@Test
	public void Ints_asList() {
		int expectedSize = 5;
		int[] intArray = { 147, 168, 151, 460, 484 };
		List<Integer> intList = Ints.asList(intArray);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}
}
