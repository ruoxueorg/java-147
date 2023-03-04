package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class StringToShortWithExamplesTest {

	@Test
	public void decode() {
		String value = "155";
		short result = Short.decode(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "-70";
		result = Short.decode(value);
		System.out.println(result);
		assertEquals(-70, result);
	}

	@Test(expected = NumberFormatException.class)
	public void decodeThrowException() {
		String value = "155";
		short result = Short.decode(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "assertj-155";
		result = Short.decode(value);
		System.out.println(result);
	}

	@Test
	public void constructor() {
		String value = "155";
		short result = new Short(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "-70";
		result = new Short(value);
		System.out.println(result);
		assertEquals(-70, result);
	}

	@Test(expected = NumberFormatException.class)
	public void constructorThrowException() {
		String value = "155";
		short result = new Short(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "assertj-155";
		result = new Short(value);
		System.out.println(result);
	}

	@Test
	public void toShort_NumberUtils() {
		String value = "155";
		short result = NumberUtils.toShort(value);
		System.out.println(result);
		assertEquals(155, result);

		value = "-70";
		result = NumberUtils.toShort(value);
		System.out.println(result);
		assertEquals(-70, result);

		value = "70";
		result = NumberUtils.toShort(value, (short) 0);
		System.out.println(result);
		assertEquals(70, result);

		value = "assertj-155";
		result = NumberUtils.toShort(value, (short) 0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
