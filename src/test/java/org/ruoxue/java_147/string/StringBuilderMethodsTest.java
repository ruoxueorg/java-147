package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.TreeSet;
import java.util.Set;

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
	public void delete() {
		int expectedSize = 3;
		StringBuilder builder = new StringBuilder("Java 147");
		builder.delete(0, 5);
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}

	@Test
	public void length() {
		int expectedSize = 36;
		StringBuilder builder = new StringBuilder();
		builder.append("Java 147, ");
		builder.append("Spring boot 168, ");
		builder.append("Junit 151");
		System.out.println(builder);
		assertEquals(expectedSize, builder.length());
	}


	@Test
	public void isEmpty() {
		Set<String> set = new TreeSet<String>();
		System.out.println(set.isEmpty());
		assertTrue(set.isEmpty());
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.isEmpty());
		assertFalse(set.isEmpty());
	}
}
