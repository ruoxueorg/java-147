package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class CollectorsToSetTest {

	@Test
	public void toSet() {
		int expectedSize = 3;
		Stream<String> stream = Stream.of("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		Set<String> result = stream.collect(Collectors.toSet());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void toCollection() {
		int expectedSize = 3;
		Stream<String> stream = Stream.of("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		Set<String> result = stream.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void integerToSet() {
		int expectedSize = 3;
		Stream<Integer> stream = Stream.of(1, 2, 3, 1, 2);
		Set<Integer> result = stream.collect(Collectors.toSet());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void arrayToSet() {
		int expectedSize = 3;
		int[] array = { 1, 2, 3, 1, 2 };
		Stream<int[]> stream = Stream.of(array);
		Set<Integer> result = stream.flatMapToInt(Arrays::stream).boxed().collect(Collectors.toSet());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void stringToSet() {
		int expectedSize = 7;
		String value = "Blueberry";
		IntStream stream = value.chars();
		Set<Character> result = stream.mapToObj(e -> (char) e).collect(Collectors.toSet());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}
}
