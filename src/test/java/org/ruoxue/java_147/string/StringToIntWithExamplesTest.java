package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToIntWithExamplesTest {

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
