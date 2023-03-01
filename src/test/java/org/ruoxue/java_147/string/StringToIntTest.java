package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToIntTest {

	@Test
	public void parseInt() {
		String value = "147";
		int result = Integer.parseInt(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "-20";
		result = Integer.parseInt(value);
		System.out.println(result);
		assertEquals(-20, result);

		value = "20";
		// (2)*16^1 + (0)*16^0 = 32
		// 32 + 0 = 32
		result = Integer.parseInt(value, 16);
		System.out.println(result);
		assertEquals(32, result);

		value = "101010";
		// (1)*2^5 + (0)*2^4 + (1)*2^3 + (0)*2^2 + (1)*2^1 + (0)*2^0 = 42
		// 32 + 0 + 8 + 0 + 2 = 42
		result = Integer.parseInt(value, 2);
		System.out.println(result);
		assertEquals(42, result);
	}

	@Test(expected = NumberFormatException.class)
	public void parseIntThrowException() {
		String value = "147";
		int result = Integer.parseInt(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "java-147";
		result = Integer.parseInt(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "147";
		Integer result = Integer.valueOf(value);
		System.out.println(result);
		assertEquals(147, result.intValue());

		value = "-20";
		result = Integer.valueOf(value);
		System.out.println(result);
		assertEquals(-20, result.intValue());

		value = "20";
		// (2)*16^1 + (0)*16^0 = 32
		result = Integer.valueOf(value, 16);
		System.out.println(result);
		assertEquals(32, result.intValue());

		value = "101010";
		// (1)*2^5 + (0)*2^4 + (1)*2^3 + (0)*2^2 + (1)*2^1 + (0)*2^0 = 42
		// 32 + 0 + 8 + 0 + 2 = 42
		result = Integer.valueOf(value, 2);
		System.out.println(result);
		assertEquals(42, result.intValue());
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "147";
		Integer result = Integer.valueOf(value);
		System.out.println(result);
		assertEquals(147, result.intValue());

		value = "java-147";
		result = Integer.valueOf(value);
		System.out.println(result);
	}

	@Test
	public void decode() {
		String value = "147";
		int result = Integer.decode(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "-20";
		result = Integer.decode(value);
		System.out.println(result);
		assertEquals(-20, result);
	}

	@Test(expected = NumberFormatException.class)
	public void decodeThrowException() {
		String value = "147";
		int result = Integer.decode(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "java-147";
		result = Integer.decode(value);
		System.out.println(result);
	}

	@Test
	public void constructor() {
		String value = "147";
		int result = new Integer(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "-20";
		result = new Integer(value);
		System.out.println(result);
		assertEquals(-20, result);
	}

	@Test
	public void commons_lang3_NumberUtils() {
		String value = "147";
		int result = NumberUtils.toInt(value);
		System.out.println(result);
		assertEquals(147, result);

		value = "-20";
		result = NumberUtils.toInt(value);
		System.out.println(result);
		assertEquals(-20, result);

		value = "20";
		result = NumberUtils.toInt(value, 0);
		System.out.println(result);
		assertEquals(20, result);

		value = "java-147";
		result = NumberUtils.toInt(value, 0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
