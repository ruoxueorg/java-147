package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class StringBufferWithExamplesTest {

	@Test
	public void appendCodePoint() {
		int expectedSize = 17;
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		builder.appendCodePoint(32);
		builder.appendCodePoint(65);
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void codePoints() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		IntStream stream = builder.codePoints();
		List<Integer> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
	}

	@Test
	public void codePointAt() {
		int expected = 103;
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		int result = builder.codePointAt(5);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void codePointBefore() {
		int expected = 110;
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		int result = builder.codePointBefore(5);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void codePointCount() {
		int expected = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		int result = builder.codePointCount(0, 5);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void offsetByCodePoints() {
		int expected = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		int result = builder.offsetByCodePoints(1, 4);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void subSequence() {
		CharSequence expected = "Spring";
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168");
		CharSequence result = builder.subSequence(0, 6);
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void reverse() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spring boot 168, ");
		builder.append("JUnit 151");
		StringBuilder result = builder.reverse();
		System.out.println(result);
	}

}
