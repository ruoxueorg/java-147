package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitializeStringTest {

	@Test
	public void withIntArray() {
		int[] value = new int[] { 65, 115, 115, 101, 114, 116, 106 };
		String result = new String(value, 0, value.length);
		System.out.println(result);
		assertEquals("Assertj", result);

		int[] value2 = new int[] { 65, 115, 115, 101, 114, 116, 106, 32, 49, 53, 53 };
		String result2 = new String(value2, 8, 3);
		System.out.println(result2);
		assertEquals("155", result2);
	}

	@Test
	public void withString() {
		String value = "Assertj";
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		String value2 = "155";
		String result2 = new String(value2);
		System.out.println(result2);
		assertEquals("155", result2);
	}

	@Test
	public void withStringBuffer() {
		StringBuffer value = new StringBuffer("Assertj");
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		value.append(" 155");
		String result2 = new String(value);
		System.out.println(result2);
		assertEquals("Assertj 155", result2);
	}

	@Test
	public void withStringBuilder() {
		StringBuilder value = new StringBuilder("Assertj");
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		value.append(" 155");
		String result2 = new String(value);
		System.out.println(result2);
		assertEquals("Assertj 155", result2);
	}
}
