package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringInitializationTest {

	@Test
	public void withLiteral() {
		String value = "Assertj";
		System.out.println(value);
		assertEquals("Assertj", value);

		String value2 = "155";
		System.out.println(value2);
		assertEquals("155", value2);
	}

	@Test
	public void withNew() {
		String value = new String("Assertj");
		System.out.println(value);
		assertEquals("Assertj", value);

		String value2 = new String("155");
		System.out.println(value2);
		assertEquals("155", value2);
	}

	@Test
	public void withByteArray() {
		byte[] value = new byte[] { 65, 115, 115, 101, 114, 116, 106 };
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		byte[] value2 = new byte[] { 65, 115, 115, 101, 114, 116, 106, 32, 49, 53, 53 };
		String result2 = new String(value2, 8, 3);
		System.out.println(result2);
		assertEquals("155", result2);
	}

	@Test
	public void withCharArray() {
		char[] value = new char[] { 'A', 's', 's', 'e', 'r', 't', 'j' };
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		char[] value2 = new char[] { 'A', 's', 's', 'e', 'r', 't', 'j', ' ', '1', '5', '5' };
		String result2 = new String(value2, 8, 3);
		System.out.println(result2);
		assertEquals("155", result2);
	}
}
