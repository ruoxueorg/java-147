package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class IntersectionTwoListsTest {

	@Test
	public void test() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "1", "2");

		List<String> result = list.stream().distinct().filter(otherList::contains).collect(Collectors.toList());
		// a
		System.out.println(result);
	}

	@Test
	public void test2() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = list.stream().distinct().filter(e -> {
			return !otherList.contains(e);
		}).collect(Collectors.toList());
		// c delete
		System.out.println(result);
	}

	@Test
	public void test3() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = otherList.stream().distinct().filter(e -> {
			return !list.contains(e);
		}).collect(Collectors.toList());
		// 1,2 add
		System.out.println(result);
	}

}
