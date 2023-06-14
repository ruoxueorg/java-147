package org.ruoxue.java_147.string.stringjoiner;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.junit.Test;

public class StringJoinerMethodsTest {

	@Test
	public void add() {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add("Java");
		joiner.add("Spring");
		joiner.add("JUnit");
		System.out.println(joiner);
		assertEquals("Java,Spring,JUnit", joiner.toString());
	}

	@Test
	public void prefixAndSuffix() {
		StringJoiner joiner = new StringJoiner(",", "[", "]");
		joiner.add("Java");
		joiner.add("Spring");
		joiner.add("JUnit");
		System.out.println(joiner);
		assertEquals("[Java,Spring,JUnit]", joiner.toString());
	}

	@Test
	public void merge() {
		StringJoiner joiner = new StringJoiner(",", "[", "]");
		joiner.add("Java");
		joiner.add("Spring");
		StringJoiner joiner2 = new StringJoiner("-", "[", "]");
		joiner2.add("JUnit");
		joiner2.add("Assertj");
		joiner.merge(joiner2);
		System.out.println(joiner);
		assertEquals("[Java,Spring,JUnit-Assertj]", joiner.toString());
	}

	@Test
	public void length() {
		int expectedSize = 17;
		StringJoiner joiner = new StringJoiner(",");
		joiner.add("Java");
		joiner.add("Spring");
		joiner.add("JUnit");
		System.out.println(joiner.length());
		assertEquals(expectedSize, joiner.length());
	}

	@Test
	public void setEmptyValue() {
		StringJoiner joiner = new StringJoiner(",");
		joiner.setEmptyValue("default");
		System.out.println(joiner);
		assertEquals("default", joiner.toString());
	}

	@Test
	public void String_join() {
		String[] array = { "Java", "Spring", "JUnit" };
		String result = String.join(",", array);
		System.out.println(result);
		assertEquals("Java,Spring,JUnit", result);
	}

	@Test
	public void Collectors_joining() {
		List<String> list = Arrays.asList("Java", "Spring", "JUnit");
		String result = list.stream().map(e -> e.toString()).collect(Collectors.joining(","));
		System.out.println(result);
		assertEquals("Java,Spring,JUnit", result);

		result = list.stream().map(e -> e.toString()).collect(Collectors.joining(",", "[", "]"));
		System.out.println(result);
		assertEquals("[Java,Spring,JUnit]", result);
	}
}
