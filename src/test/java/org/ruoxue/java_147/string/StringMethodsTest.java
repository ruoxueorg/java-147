package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StringMethodsTest {

	@Test
	public void contains() {
		String value = "Assertj 155";
		boolean result = value.contains("155");
		System.out.println(result);
		assertTrue(result);

		result = value.contains("Java");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void toCharArray() {
		String value = "Assertj";
		char[] result = value.toCharArray();
		System.out.println(Arrays.toString(result));
		assertEquals(7, result.length);

		value = "Assertj 155";
		result = value.toCharArray();
		System.out.println(Arrays.toString(result));
		assertEquals(11, result.length);
	}

	@Test
	public void length() {
		String value = "Assertj 155";
		System.out.println(value.length());
		assertEquals(11, value.length());

		value = value + "Assertj 155";
		System.out.println(value.length());
		assertEquals(22, value.length());
	}

	@Test
	public void format() {
		String value = "Assertj 155";
		String result = String.format("String is: %s", value);
		System.out.println(result);
		assertEquals("String is: Assertj 155", result);

		result = String.format("String is: %15s", value);
		System.out.println(result);
		assertEquals("String is:     Assertj 155", result);
	}

	@Test
	public void concat() {
		String value = "Assertj";
		String result = value.concat("155");
		System.out.println(result);
		assertEquals("Assertj155", result);

		result = value.concat("Java");
		System.out.println(result);
		assertEquals("AssertjJava", result);
	}

	@Test
	public void trim() {
		String value = "Assertj 155  ";
		String result = value.trim();
		System.out.println(result);
		assertEquals("Assertj 155", result);

		value = "   Assertj 155";
		result = value.trim();
		System.out.println(result);
		assertEquals("Assertj 155", result);
	}

	@Test
	public void toLowerCase() {
		String value = "Assertj";
		String result = value.toLowerCase();
		System.out.println(result);
		assertEquals("assertj", result);

		value = "Assertj 155";
		result = value.toLowerCase();
		System.out.println(result);
		assertEquals("assertj 155", result);
	}

	@Test
	public void toUpperCase() {
		String value = "Assertj";
		String result = value.toUpperCase();
		System.out.println(result);
		assertEquals("ASSERTJ", result);

		value = "Assertj 155";
		result = value.toUpperCase();
		System.out.println(result);
		assertEquals("ASSERTJ 155", result);
	}
}
