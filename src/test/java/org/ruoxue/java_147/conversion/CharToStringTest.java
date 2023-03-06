package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharToStringTest {

	@Test
	public void toStringz() {
		char value = 'g';
		String result = Character.toString(value);
		System.out.println(result);
		assertEquals("g", result);

		value = '1';
		result = Character.toString(value);
		System.out.println(result);
		assertEquals("1", result);
	}

	@Test
	public void character() {
		Character value = new Character('g');
		String result = value.toString();
		System.out.println(result);
		assertEquals("g", result);

		value = new Character('1');
		result = value.toString();
		System.out.println(result);
		assertEquals("1", result);
	}

	@Test
	public void valueOf() {
		char value = 'g';
		String result = String.valueOf(value);
		System.out.println(result);
		assertEquals("g", result);

		char[] array = new char[] { 'g', 'r', 'a', 'd', 'l', 'e' };
		result = String.valueOf(array);
		System.out.println(result);
		assertEquals("gradle", result);

		result = String.valueOf(array, 1, 3);
		System.out.println(result);
		assertEquals("rad", result);
	}

	@Test(expected = NullPointerException.class)
	public void valueOfThrowException() {
		char value = 'g';
		String result = String.valueOf(value);
		System.out.println(result);
		assertEquals("g", result);

		char[] array = null;
		result = String.valueOf(array);
		System.out.println(result);
	}
}
