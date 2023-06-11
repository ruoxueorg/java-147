package org.ruoxue.java_147.string.stringbuilder;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StringBuilderClassTest {

	@Test
	public void charAt() {
		char expected = 'a';
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		char result = builder.charAt(3);
		System.out.println(result);
		assertEquals(expected, result);
		
		result = builder.charAt(5);
		System.out.println(result);
		assertEquals('1', result);
	}

	@Test
	public void getChars() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		IntStream stream = builder.chars();
		List<Integer> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
	}

	@Test
	public void indexOf() {
		int expected = 1;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.indexOf("a");
		System.out.println(result);
		assertEquals(expected, result);

		result = builder.indexOf("a", 2);
		System.out.println(result);
		assertEquals(3, result);
	}

	@Test
	public void lastIndexOf() {
		int expected = 3;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.lastIndexOf("a");
		System.out.println(result);
		assertEquals(expected, result);

		result = builder.lastIndexOf("a", 2);
		System.out.println(result);
		assertEquals(1, result);
	}

	@Test
	public void setCharAt() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		builder.setCharAt(3, 'A');
		System.out.println(builder);
		assertEquals("JavA 147", builder.toString());
	}

	@Test
	public void substring() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		String result = builder.substring(3);
		System.out.println(result);
		assertEquals("a 147", result);

		result = builder.substring(0, 3);
		System.out.println(result);
		assertEquals("Jav", result);
	}

	@Test
	public void replace() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		builder.replace(0, 2, "JAVA");
		System.out.println(builder);
		assertEquals("JAVAva 147", builder.toString());
	}
}
