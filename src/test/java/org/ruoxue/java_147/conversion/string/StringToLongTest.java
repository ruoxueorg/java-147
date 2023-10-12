package org.ruoxue.java_147.conversion.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToLongTest {

	@Test
	public void parseLong() {
		String value = "168";
		long result = Long.parseLong(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "-30";
		result = Long.parseLong(value);
		System.out.println(result);
		assertEquals(-30, result);

		value = "30";
		// (3)*16^1 + (0)*16^0 = 48
		// 48 + 0 = 48
		result = Long.parseLong(value, 16);
		System.out.println(result);
		assertEquals(48, result);

		value = "111010";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (0)*2^2 + (1)*2^1 + (0)*2^0 = 42
		// 32 + 16 + 8 + 0 + 2 = 58
		result = Long.parseLong(value, 2);
		System.out.println(result);
		assertEquals(58, result);
	}

	@Test(expected = NumberFormatException.class)
	public void parseLongThrowException() {
		String value = "168";
		long result = Long.parseLong(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "springboot-168";
		result = Long.parseLong(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "168";
		Long result = Long.valueOf(value);
		System.out.println(result);
		assertEquals(168, result.longValue());

		value = "-30";
		result = Long.valueOf(value);
		System.out.println(result);
		assertEquals(-30, result.longValue());

		value = "30";
		// (3)*16^1 + (0)*16^0 = 48
		result = Long.valueOf(value, 16);
		System.out.println(result);
		assertEquals(48, result.longValue());

		value = "111010";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (0)*2^2 + (1)*2^1 + (0)*2^0 = 42
		// 32 + 16 + 8 + 0 + 2 = 58
		result = Long.valueOf(value, 2);
		System.out.println(result);
		assertEquals(58, result.longValue());
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "168";
		Long result = Long.valueOf(value);
		System.out.println(result);
		assertEquals(168, result.longValue());

		value = "springboot-168";
		result = Long.valueOf(value);
		System.out.println(result);
	}
}
