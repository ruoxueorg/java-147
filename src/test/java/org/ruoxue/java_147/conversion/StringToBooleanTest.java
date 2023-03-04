package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringToBooleanTest {

	@Test
	public void parseBoolean() {
		String value = "true";
		boolean result = Boolean.parseBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "True";
		result = Boolean.parseBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "TRUE";
		result = Boolean.parseBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "ok";
		result = Boolean.parseBoolean(value);
		System.out.println(result);
		assertEquals(false, result);
	}

	@Test
	public void valueOf() {
		String value = "true";
		Boolean result = Boolean.valueOf(value);
		System.out.println(result);
		assertEquals(true, result.booleanValue());

		value = "True";
		result = Boolean.valueOf(value);
		System.out.println(result);
		assertEquals(true, result.booleanValue());

		value = "TRUE";
		result = Boolean.valueOf(value);
		System.out.println(result);
		assertEquals(true, result.booleanValue());

		value = "ok";
		result = Boolean.valueOf(value);
		System.out.println(result);
		assertEquals(false, result.booleanValue());
	}

	@Test
	public void getBoolean() {
		String value = "TEST_MODE";
		boolean result = Boolean.getBoolean(value);
		System.out.println(result);
		assertEquals(false, result);

		System.setProperty("TEST_MODE", "true");
		result = Boolean.getBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		System.setProperty("TEST_MODE", "True");
		result = Boolean.getBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		System.setProperty("TEST_MODE", "TRUE");
		result = Boolean.getBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		System.setProperty("TEST_MODE", "ok");
		result = Boolean.getBoolean(value);
		System.out.println(result);
		assertEquals(false, result);
	}
}
