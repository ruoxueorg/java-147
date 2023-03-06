package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StringToCharTest {

	@Test
	public void charAt() {
		String value = "gradle 161";
		char result = value.charAt(2);
		System.out.println(result);
		assertEquals('a', result);

		result = value.charAt(7);
		System.out.println(result);
		assertEquals('1', result);

		for (int i = 0; i < value.length(); i++) {
			result = value.charAt(i);
			System.out.print(result);
		}
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void charAtThrowException() {
		String value = "gradle 161";
		char result = value.charAt(2);
		System.out.println(result);
		assertEquals('a', result);

		result = value.charAt(11);
		System.out.println(result);
	}

	@Test
	public void toCharArray() {
		String value = "gradle 161";
		char[] result = value.toCharArray();
		System.out.println(Arrays.toString(result));
		assertEquals(10, result.length);

		value = "161";
		result = value.toCharArray();
		System.out.println(Arrays.toString(result));
		assertEquals(3, result.length);
	}

	@Test
	public void getChars() {
		String value = "gradle 161";
		char[] result = new char[6];
		value.getChars(0, 6, result, 0);
		System.out.println(Arrays.toString(result));
		assertEquals(6, result.length);

		result = new char[] { '*', '*', '*', '*', '*', '*', '*', '*' };
		value.getChars(0, 6, result, 1);
		System.out.println(Arrays.toString(result));
		assertEquals(8, result.length);
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void getCharsThrowException() {
		String value = "gradle 161";
		char[] result = new char[3];
		value.getChars(0, 6, result, 0);
		System.out.println(Arrays.toString(result));
	}
}
