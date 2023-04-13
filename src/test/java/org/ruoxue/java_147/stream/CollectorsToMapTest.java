package org.ruoxue.java_147.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsToMapTest {

	@Test
	public void toMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork", "Bacon", "Pork");
		Function<String, String> key = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Map<String, Integer> map = list.stream()
				.collect(Collectors.toMap(key, length, (oldValue, newValue) -> oldValue));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		map = list.stream()
				.collect(Collectors.toMap(Function.identity(), String::length, (oldValue, newValue) -> oldValue));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		Function<Integer, Integer> twice = i -> i * i;
		map = list.stream().collect(
				Collectors.toMap(key, length.andThen(twice), (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}
}
