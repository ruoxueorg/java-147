package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToByteTest {

	@Test
	public void parseByte() {
		String value = "102";
		byte result = Byte.parseByte(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "-60";
		result = Byte.parseByte(value);
		System.out.println(result);
		assertEquals(-60, result);

		value = "60";
		// (6)*16^1 + (0)*16^0 = 96
		// 96 + 0 = 96
		result = Byte.parseByte(value, 16);
		System.out.println(result);
		assertEquals(96, result);

		value = "111110";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (1)*2^2 + (1)*2^1 + (0)*2^0 = 62
		// 32 + 16 + 8 + 4 + 2 = 62
		result = Byte.parseByte(value, 2);
		System.out.println(result);
		assertEquals(62, result);
	}

	@Test(expected = NumberFormatException.class)
	public void parseByteThrowException() {
		String value = "102";
		byte result = Byte.parseByte(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "httpclient-102";
		result = Byte.parseByte(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "102";
		Byte result = Byte.valueOf(value);
		System.out.println(result);
		assertEquals(102, result.byteValue());

		value = "-60";
		result = Byte.valueOf(value);
		System.out.println(result);
		assertEquals(-60, result.byteValue());

		value = "60";
		// (6)*16^1 + (0)*16^0 = 96
		result = Byte.valueOf(value, 16);
		System.out.println(result);
		assertEquals(96, result.byteValue());

		value = "111110";
		// (1)*2^5 + (1)*2^4 + (1)*2^3 + (1)*2^2 + (1)*2^1 + (0)*2^0 = 62
		// 32 + 16 + 8 + 4 + 2 = 62
		result = Byte.valueOf(value, 2);
		System.out.println(result);
		assertEquals(62, result.byteValue());
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "102";
		Byte result = Byte.valueOf(value);
		System.out.println(result);
		assertEquals(102, result.byteValue());

		value = "httpclient-102";
		result = Byte.valueOf(value);
		System.out.println(result);
	}
}
