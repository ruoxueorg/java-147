package org.ruoxue.java_147.string.stringbuilder;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StringBuilderWithExamplesTest {

	@Test
	public void appendCodePoint() {
		int expectedSize = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		builder.appendCodePoint(32);
		builder.appendCodePoint(65);
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void codePoints() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		IntStream stream = builder.codePoints();
		List<Integer> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
	}

	@Test
	public void codePointAt() {
		int expected = 97;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.codePointAt(3);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void codePointBefore() {
		int expected = 118;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.codePointBefore(3);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void codePointCount() {
		int expected = 3;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.codePointCount(0, 3);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void offsetByCodePoints() {
		int expected = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		int result = builder.offsetByCodePoints(1, 4);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void subSequence() {
		CharSequence expected = "Java";
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		CharSequence result = builder.subSequence(0, 4);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void reverse() {
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147, ");
		builder.append("Spring boot 168");
		StringBuilder result = builder.reverse();
		System.out.println(result);
	}

}
