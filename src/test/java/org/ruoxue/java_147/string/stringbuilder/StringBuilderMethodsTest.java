package org.ruoxue.java_147.string.stringbuilder;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringBuilderMethodsTest {

	@Test
	public void append() {
		int expectedSize = 25;
		StringBuilder builder = new StringBuilder();
		builder.append("Jav");
		builder.append('a');
		builder.append(" ");
		builder.append(147);
		builder.append(", ");
		builder.append(new StringBuilder().append("Spring boot 168"));
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void insert() {
		int expectedSize = 23;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147");
		builder.insert(5, "Spring boot 168");
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void delete() {
		int expectedSize = 3;
		StringBuilder builder = new StringBuilder("Java 147");
		builder.delete(0, 5);
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void deleteCharAt() {
		int expectedSize = 7;
		StringBuilder builder = new StringBuilder("Java 147");
		builder.deleteCharAt(5);
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void length() {
		int expectedSize = 36;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147, ");
		builder.append("Spring boot 168, ");
		builder.append("JUnit 151");
		System.out.println(builder.length());
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void capacity() {
		int expectedSize = 70;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147, ");
		builder.append("Spring boot 168, ");
		builder.append("JUnit 151");
		System.out.println(builder.capacity());
		assertEquals(expectedSize, builder.capacity());
	}

	@Test
	public void trimToSize() {
		int expectedSize = 36;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147, ");
		builder.append("Spring boot 168, ");
		builder.append("JUnit 151");
		System.out.println(builder.capacity());
		builder.trimToSize();
		System.out.println(builder.capacity());
		assertEquals(expectedSize, builder.capacity());
	}
}
