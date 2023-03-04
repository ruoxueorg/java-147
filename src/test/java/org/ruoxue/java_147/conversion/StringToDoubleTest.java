package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToDoubleTest {

	@Test
	public void parseDouble() {
		String value = "460.88";
		double result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "-50.88";
		result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(-50.88, result, 0);
	}

	@Test(expected = NumberFormatException.class)
	public void parseDoubleThrowException() {
		String value = "460.88";
		double result = Double.parseDouble(value);
		System.out.println(result);
		assertEquals(460.88, result, 0);

		value = "bash-460.88";
		result = Double.parseDouble(value);
		System.out.println(result);
	}

	@Test
	public void valueOf() {
		String value = "460.88";
		Double result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(460.88, result.doubleValue(), 0);

		value = "-50.88";
		result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(-50.88, result.doubleValue(), 0);
	}

	@Test(expected = NumberFormatException.class)
	public void valueOfThrowException() {
		String value = "460.88";
		Double result = Double.valueOf(value);
		System.out.println(result);
		assertEquals(460.88, result.doubleValue(), 0);

		value = "bash-460.88";
		result = Double.valueOf(value);
		System.out.println(result);
	}
}
