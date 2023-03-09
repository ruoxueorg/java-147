package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

public class CharToStringWithExamplesTest {

	@Test
	public void concatenation() {
		char value = 'g';
		String result = "" + value;
		System.out.println(result);
		assertEquals("g", result);

		result = value + "";
		System.out.println(result);
		assertEquals("g", result);

		String nullValue = null;
		result = value + nullValue;
		System.out.println(result);
		assertEquals("gnull", result);
	}

	@Test
	public void constructor() {
		char value = 'g';
		String result = new String(new char[] { value });
		System.out.println(result);
		assertEquals("g", result);

		char[] array = new char[] { 'g', 'r', 'a', 'd', 'l', 'e' };
		result = new String(array);
		System.out.println(result);
		assertEquals("gradle", result);
	}

	@Test(expected = NullPointerException.class)
	public void constructorThrowException() {
		char value = 'g';
		String result = new String(new char[] { value });
		System.out.println(result);
		assertEquals("g", result);

		char[] array = null;
		result = new String(array);
		System.out.println(result);
		assertEquals("gradle", result);
	}

	@Test
	public void CharUtils_toString() {
		char value = 'g';
		String result = CharUtils.toString(value);
		System.out.println(result);
		assertEquals("g", result);

		value = '1';
		result = CharUtils.toString(value);
		System.out.println(result);
		assertEquals("1", result);
	}
}
