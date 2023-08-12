package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.LinkedMap;
import org.junit.Test;

public class DifferenceCollectorsGroupingByPartitioningByTest {

	@Test
	public void partitioningBy() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.partitioningBy(e -> e > 3));
		System.out.println(result);
		assertEquals(3, result.get(Boolean.FALSE).size());
		assertEquals(2, result.get(Boolean.TRUE).size());
	}

	@Test
	public void groupingBy() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Map<Integer, List<Integer>> result = list.stream().collect(Collectors.groupingBy(e -> e % 3));
		System.out.println(result);
		assertEquals(3, result.size());
	}

	@Test
	public void partitioningByWithMapping() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3,
				Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(4, result.get(Boolean.TRUE).size());
	}

	@Test
	public void groupingByWithMapping() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, Long> result = list.stream().collect(
				Collectors.groupingBy(String::length, Collectors.mapping(Function.identity(), Collectors.counting())));
		System.out.println(result);
		assertEquals(3, result.size());
	}

	@Test
	public void groupingByWithSupplier() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, List<String>> result = list.stream().collect(Collectors.groupingBy(String::length, LinkedMap::new,
				Collectors.mapping(String::toUpperCase, Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(3).size());
		assertEquals(2, result.get(5).size());
		assertEquals(2, result.get(9).size());
	}
}
