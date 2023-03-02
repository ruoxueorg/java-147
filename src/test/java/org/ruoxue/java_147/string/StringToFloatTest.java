package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToFloatTest {

	@Test
	public void parseFloat() {
		String value = "151.88";
		float result = Float.parseFloat(value);
		System.out.println(result);
		assertEquals(151.88f, result, 0);

		value = "-40.88";
		result = Float.parseFloat(value);
		System.out.println(result);
		assertEquals(-40.88f, result, 0);
	}

	@Test(expected = NumberFormatException.class)
	public void parseFloatThrowException() {
		String value = "151.88";
		float result = Float.parseFloat(value);
		System.out.println(result);
		assertEquals(151.88f, result, 0);

		value = "junit-151.88";
		result = Float.parseFloat(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "151.88";
		Float result = Float.valueOf(value);
		System.out.println(result);
		assertEquals(151.88f, result.floatValue(), 0);

		value = "-40.88";
		result = Float.valueOf(value);
		System.out.println(result);
		assertEquals(-40.88f, result.floatValue(), 0);
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "151.88";
		Float result = Float.valueOf(value);
		System.out.println(result);
		assertEquals(151.88f, result.floatValue(), 0);

		value = "junit-151.88";
		result = Float.valueOf(value);
		System.out.println(result);
	}
}
