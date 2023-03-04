package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToByteWithExamplesTest {

	@Test
	public void decode() {
		String value = "102";
		byte result = Byte.decode(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "-60";
		result = Byte.decode(value);
		System.out.println(result);
		assertEquals(-60, result);
	}

	@Test(expected = NumberFormatException.class)
	public void decodeThrowException() {
		String value = "102";
		byte result = Byte.decode(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "httpclient-102";
		result = Byte.decode(value);
		System.out.println(result);
	}

	@Test
	public void constructor() {
		String value = "102";
		byte result = new Byte(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "-60";
		result = new Byte(value);
		System.out.println(result);
		assertEquals(-60, result);
	}

	@Test(expected = NumberFormatException.class)
	public void constructorThrowException() {
		String value = "102";
		byte result = new Byte(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "httpclient-102";
		result = new Byte(value);
		System.out.println(result);
	}

	@Test
	public void toByte_NumberUtils() {
		String value = "102";
		byte result = NumberUtils.toByte(value);
		System.out.println(result);
		assertEquals(102, result);

		value = "-60";
		result = NumberUtils.toByte(value);
		System.out.println(result);
		assertEquals(-60, result);

		value = "60";
		result = NumberUtils.toByte(value, (byte) 0);
		System.out.println(result);
		assertEquals(60, result);

		value = "httpclient-102";
		result = NumberUtils.toByte(value, (byte) 0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
