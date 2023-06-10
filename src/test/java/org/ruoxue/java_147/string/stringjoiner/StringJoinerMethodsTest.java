package org.ruoxue.java_147.string.stringjoiner;

import static org.junit.Assert.*;

import java.util.StringJoiner;

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
	public void join() {
		String[] array = { "Java", "Spring", "JUnit" };
		String result = String.join(",", array);
		System.out.println(result);
		assertEquals("Java,Spring,JUnit", result);
	}

	@Test
	public void merge() {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add("Java");
		joiner.add("Spring");
		StringJoiner joiner2 = new StringJoiner(",");
		joiner2.add("JUnit");
		joiner.merge(joiner2);
		System.out.println(joiner);
		assertEquals("Java,Spring,JUnit", joiner.toString());
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
}
