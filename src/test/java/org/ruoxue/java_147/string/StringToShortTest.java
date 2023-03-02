package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToShortTest {

	@Test
	public void parseShort() {
		String value = "155";
		short result = Short.parseShort(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "-70";
		result = Short.parseShort(value);
		System.out.println(result);
		assertEquals(-70, result);

		value = "70";
		// (7)*16^1 + (0)*16^0 = 112
		// 96 + 0 = 112
		result = Short.parseShort(value, 16);
		System.out.println(result);
		assertEquals(112, result);

		value = "111111";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (1)*2^2 + (1)*2^1 + (1)*2^0 = 63
		// 32 + 16 + 8 + 4 + 2 + 1= 63
		result = Short.parseShort(value, 2);
		System.out.println(result);
		assertEquals(63, result);
	}

	@Test(expected = NumberFormatException.class)
	public void parseShortThrowException() {
		String value = "155";
		short result = Short.parseShort(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "assertj-155";
		result = Short.parseShort(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "155";
		Short result = Short.valueOf(value);
		System.out.println(result);
		assertEquals(155, result.shortValue());

		value = "-70";
		result = Short.valueOf(value);
		System.out.println(result);
		assertEquals(-70, result.shortValue());

		value = "70";
		// (7)*16^1 + (0)*16^0 = 112
		result = Short.valueOf(value, 16);
		System.out.println(result);
		assertEquals(112, result.shortValue());

		value = "111111";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (1)*2^2 + (1)*2^1 + (1)*2^0 = 63
		// 32 + 16 + 8 + 4 + 2 + 1= 63
		result = Short.valueOf(value, 2);
		System.out.println(result);
		assertEquals(63, result.shortValue());
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "155";
		Short result = Short.valueOf(value);
		System.out.println(result);
		assertEquals(155, result.shortValue());

		value = "assertj-155";
		result = Short.valueOf(value);
		System.out.println(result);
	}
}
