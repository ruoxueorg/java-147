package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringMethodsTest {

//	@Test
//	public void append() {
//		int expectedSize = 26;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Sprin");
//		builder.append('g');
//		builder.append(" ");
//		builder.append("boot");
//		builder.append(" ");
//		builder.append(168);
//		builder.append(", ");
//		builder.append(new StringBuffer().append("JUnit 151"));
//		System.out.println(builder);
//		assertEquals(expectedSize, builder.length());
//	}

//	@Test
//	public void insert() {
//		int expectedSize = 24;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		builder.insert(12, "JUnit 151");
//		System.out.println(builder);
//		assertEquals(expectedSize, builder.length());
//	}

//	@Test
//	public void delete() {
//		int expectedSize = 8;
//		StringBuffer builder = new StringBuffer("Spring boot 168");
//		builder.delete(0, 7);
//		System.out.println(builder);
//		assertEquals(expectedSize, builder.length());
//	}
//
//	@Test
//	public void deleteCharAt() {
//		int expectedSize = 14;
//		StringBuffer builder = new StringBuffer("Spring boot 168");
//		builder.deleteCharAt(12);
//		System.out.println(builder);
//		assertEquals(expectedSize, builder.length());
//	}

	@Test
	public void length() {
		int expectedSize = 11;
		String value = "Assertj 155";
		System.out.println(value.length());
		assertEquals(expectedSize, value.length());
	}

	@Test
	public void format() {
		String value = "Assertj 155";
		String formatted = String.format("String is: %s", value);
		System.out.println(formatted);
		formatted = String.format("String is: %15s", value);
		System.out.println(formatted);
	}

//	@Test
//	public void capacity() {
//		int expectedSize = 34;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168, ");
//		builder.append("JUnit 151");
//		System.out.println(builder.capacity());
//		assertEquals(expectedSize, builder.capacity());
//	}
//
//	@Test
//	public void trimToSize() {
//		int expectedSize = 26;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168, ");
//		builder.append("JUnit 151");
//		System.out.println(builder.capacity());
//		builder.trimToSize();
//		System.out.println(builder.capacity());
//		assertEquals(expectedSize, builder.capacity());
//	}
}
