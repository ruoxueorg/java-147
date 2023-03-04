package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

public class StringToBooleanWithExamplesTest {

	@Test
	public void constructor() {
		String value = "true";
		boolean result = new Boolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "True";
		result = new Boolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "TRUE";
		result = new Boolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "ok";
		result = new Boolean(value);
		System.out.println(result);
		assertEquals(false, result);
	}


	@Test
	public void toBoolean_BooleanUtils() {
		String value = "true";
		boolean result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);

		value = "True";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);
		
		value = "TRUE";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);
		
		value = "ok";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(false, result);
		
		value = "y";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);
		
		value = "yes";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);
		
		value = "on";
		result = BooleanUtils.toBoolean(value);
		System.out.println(result);
		assertEquals(true, result);
	}
}
