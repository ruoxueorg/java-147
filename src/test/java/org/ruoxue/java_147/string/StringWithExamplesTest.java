package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StringWithExamplesTest {

	@Test
	public void split() {
		String value = "Assertj 155";
		String[] result = value.split(" ");
		System.out.println(Arrays.toString(result));
		assertEquals(2, result.length);

		result = value.split(",");
		System.out.println(Arrays.toString(result));
		assertEquals(1, result.length);
	}

	@Test
	public void compareTo() {
		String value = "Assertj 155";
		int result = value.compareTo("assertj 155");
		System.out.println(result);
		assertTrue(result < 0);

		value = "assertj 155";
		result = value.compareTo("Assertj 155");
		System.out.println(result);
		assertTrue(result > 0);

		result = value.compareTo("assertj 155");
		System.out.println(result);
		assertTrue(result == 0);
	}

	@Test
	public void compareToIgnoreCase() {
		String value = "Assertj 155";
		int result = value.compareToIgnoreCase("assertj 155");
		System.out.println(result);
		assertTrue(result == 0);

		value = "assertj 155";
		result = value.compareToIgnoreCase("Assertj 155");
		System.out.println(result);
		assertTrue(result == 0);

		result = value.compareToIgnoreCase("assertj 155");
		System.out.println(result);
		assertTrue(result == 0);
	}

	@Test
	public void valueOf() {
		int value = 155;
		String result = String.valueOf(value);
		System.out.println(result);
		assertEquals("155", result);

		result = String.valueOf((Object) null);
		System.out.println(result);
		assertEquals("null", result);
	}

	@Test
	public void startsWith() {
		String value = "Assertj 155";
		boolean result = value.startsWith("Assertj");
		System.out.println(result);
		assertTrue(result);

		result = value.startsWith("155");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void endsWith() {
		String value = "Assertj 155";
		boolean result = value.endsWith("155");
		System.out.println(result);
		assertTrue(result);

		result = value.endsWith("Assertj");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void equals() {
		String value = "Assertj 155";
		boolean result = value.equals("Assertj 155");
		System.out.println(result);
		assertTrue(result);

		result = value.equals("Assertj");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void equalsIgnoreCase() {
		String value = "Assertj 155";
		boolean result = value.equalsIgnoreCase("assertj 155");
		System.out.println(result);
		assertTrue(result);

		result = value.equalsIgnoreCase("assertj");
		System.out.println(result);
		assertFalse(result);
	}
}
