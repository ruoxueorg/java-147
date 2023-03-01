package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToLongWithExamplesTest {

	@Test
	public void decode() {
		String value = "168";
		long result = Long.decode(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "-30";
		result = Integer.decode(value);
		System.out.println(result);
		assertEquals(-30, result);
	}

	@Test(expected = NumberFormatException.class)
	public void decodeThrowException() {
		String value = "168";
		long result = Long.decode(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "java-168";
		result = Long.decode(value);
		System.out.println(result);
	}

	@Test
	public void constructor() {
		String value = "168";
		long result = new Long(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "-30";
		result = new Long(value);
		System.out.println(result);
		assertEquals(-30, result);
	}

	@Test
	public void commons_lang3_NumberUtils() {
		String value = "168";
		long result = NumberUtils.toLong(value);
		System.out.println(result);
		assertEquals(168, result);

		value = "-30";
		result = NumberUtils.toLong(value);
		System.out.println(result);
		assertEquals(-30, result);

		value = "30";
		result = NumberUtils.toLong(value, 0);
		System.out.println(result);
		assertEquals(30, result);

		value = "springboot-168";
		result = NumberUtils.toLong(value, 0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
