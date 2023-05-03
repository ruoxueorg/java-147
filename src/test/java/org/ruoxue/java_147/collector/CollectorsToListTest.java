package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class CollectorsToListTest {

	@Test
	public void toList() {
		int expectedSize = 5;
		Stream<String> stream = Stream.of("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		List<String> result = stream.collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void toCollection() {
		int expectedSize = 5;
		Stream<String> stream = Stream.of("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		List<String> result = stream.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void integerToList() {
		int expectedSize = 5;
		Stream<Integer> stream = Stream.of(1, 2, 3, 1, 2);
		List<Integer> result = stream.collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void intToList() {
		int expectedSize = 5;
		int[] array = { 1, 2, 3, 1, 2 };
		Stream<int[]> stream = Stream.of(array);
		List<Integer> result = stream.flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void stringToList() {
		int expectedSize = 9;
		String value = "Blueberry";
		IntStream stream = value.chars();
		List<Character> result = stream.mapToObj(e -> (char) e).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}
}
