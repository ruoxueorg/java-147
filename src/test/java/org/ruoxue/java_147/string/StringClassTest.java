package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Test;

public class StringClassTest {

	@Test
	public void charAt() {
		String value = "Assertj 155";
		char result = value.charAt(2);
		System.out.println(result);
		assertEquals('s', result);

		result = value.charAt(8);
		System.out.println(result);
		assertEquals('1', result);
	}

	@Test
	public void getChars() {
		char[] result = new char[10];
		String value = "Assertj 155";
		value.getChars(0, 7, result, 0);
		System.out.println(Arrays.toString(result));
		assertEquals(10, result.length);

		value.getChars(8, 11, result, 7);
		System.out.println(Arrays.toString(result));
		assertEquals(10, result.length);
	}

	@Test
	public void getBytes() {
		String value = "Assertj";
		byte[] result = value.getBytes();
		System.out.println(Arrays.toString(result));
		assertEquals(7, result.length);

		value = "Assertj 155";
		result = value.getBytes(StandardCharsets.UTF_8);
		System.out.println(Arrays.toString(result));
		assertEquals(11, result.length);
	}

	@Test
	public void indexOf() {
		String value = "Assertj 155";
		int result = value.indexOf("s");
		System.out.println(result);
		assertEquals(1, result);

		result = value.indexOf("1", 2);
		System.out.println(result);
		assertEquals(8, result);
	}

	@Test
	public void lastIndexOf() {
		String value = "Assertj 155";
		int result = value.lastIndexOf("s");
		System.out.println(result);
		assertEquals(2, result);

		result = value.lastIndexOf("1", 2);
		System.out.println(result);
		assertEquals(-1, result);
	}

	@Test
	public void substring() {
		String value = "Assertj 155";
		String result = value.substring(8);
		System.out.println(result);
		assertEquals("155", result);

		result = value.substring(0, 7);
		System.out.println(result);
		assertEquals("Assertj", result);
	}

	@Test
	public void replace() {
		String value = "Assertj 155";
		String result = value.replace("Assertj", "ASSERTJ");
		System.out.println(result);
		assertEquals("ASSERTJ 155", result);

		result = value.replace("155", "978");
		System.out.println(result);
		assertEquals("Assertj 978", result);
	}

	@Test
	public void intern() {
		String value = "Assertj";
		String result = value.intern();
		System.out.println(result);
		assertEquals("Assertj", result);

		value = "Assertj 155";
		result = value.intern();
		System.out.println(result);
		assertEquals("Assertj 155", result);
	}
}
