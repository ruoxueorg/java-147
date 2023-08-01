package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsToConcurrentMapTest {

	@Test
	public void toConcurrentMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Function<String, String> key = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Map<String, Integer> result = list.stream().collect(Collectors.toConcurrentMap(key, length));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		result = list.stream().collect(Collectors.toConcurrentMap(Function.identity(), String::length));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Function<Integer, Integer> twice = i -> i * i;
		result = list.stream().collect(Collectors.toConcurrentMap(key, length.andThen(twice)));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test(expected = IllegalStateException.class)
	public void toConcurrentMapThrowException() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		Function<String, String> key = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Map<String, Integer> result = list.stream().collect(Collectors.toConcurrentMap(key, length));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void toConcurrentMapWithDuplicateKey() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		Function<String, String> key = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Map<String, Integer> result = list.stream()
				.collect(Collectors.toConcurrentMap(key, length, (oldValue, newValue) -> oldValue));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		result = list.stream()
				.collect(Collectors.toConcurrentMap(Function.identity(), String::length, (oldValue, newValue) -> oldValue));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void toMapTreeMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Blueberry", "Melon");
		Function<String, String> key = s -> s.toLowerCase();
		Function<String, Integer> length = s -> s.length();
		Function<Integer, Integer> twice = i -> i * i;
		Map<String, Integer> result = list.stream()
				.collect(Collectors.toMap(key, length.andThen(twice), (oldValue, newValue) -> oldValue, TreeMap::new));
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

}
