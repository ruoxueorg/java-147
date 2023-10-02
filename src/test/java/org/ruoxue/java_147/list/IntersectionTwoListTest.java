package org.ruoxue.java_147.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class IntersectionTwoListTest {

	@Test
	public void intersection() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "1", "2");

		List<String> result = list.stream().distinct().filter(otherList::contains).collect(Collectors.toList());
		// a
		System.out.println(result);
	}

	@Test
	public void delete() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = list.stream().distinct().filter(e -> {
			return (false == otherList.contains(e));
		}).collect(Collectors.toList());
		// c
		System.out.println(result);
	}

	@Test
	public void add() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = otherList.stream().distinct().filter(e -> {
			return (false == list.contains(e));
		}).collect(Collectors.toList());
		// 1,2
		System.out.println(result);
	}
}
