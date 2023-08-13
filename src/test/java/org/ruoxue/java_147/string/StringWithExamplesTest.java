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
		int result = value.compareTo("155");
		System.out.println(result);
		assertTrue(result > 0);

		result = value.compareTo("assertj");
		System.out.println(result);
		assertTrue(result < 0);

		result = value.compareTo("Assertj 155");
		System.out.println(result);
		assertTrue(result == 0);
	}

//	@Test
//	public void valueOf() {
//		int expected = 110;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		int result = builder.codePointBefore(5);
//		System.out.println(result);
//		assertEquals(expected, result);
//	}
//
//	@Test
//	public void startsWith() {
//		int expected = 5;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		int result = builder.codePointCount(0, 5);
//		System.out.println(result);
//		assertEquals(expected, result);
//	}
//
//	@Test
//	public void endsWith() {
//		int expected = 5;
//		StringBuffer builder = new StringBuffer();
//		builder.append("Spring boot 168");
//		int result = builder.offsetByCodePoints(1, 4);
//		System.out.println(result);
//		assertEquals(expected, result);
//	}

	@Test
	public void intern() {

	}
}
