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
	public void delete() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = list.stream().distinct().filter(e -> {
			return (false == otherList.contains(e));
		}).collect(Collectors.toList());
		// c delete
		System.out.println(result);
	}

	@Test
	public void add() {
		List<String> list = Arrays.asList("a", "b", "c");
		List<String> otherList = Arrays.asList("a", "b", "1", "2");

		List<String> result = otherList.stream().distinct().filter(e -> {
			return (false == list.contains(e));
		}).collect(Collectors.toList());
		// 1,2 add
		System.out.println(result);
	}
	
	@Test
	public void bool() {
		System.out.println(true && true); //true
		System.out.println(true || false); //true
		System.out.println(false || true); //true
		System.out.println(true && false); //false
		System.out.println(false || false); //false
	}

}
