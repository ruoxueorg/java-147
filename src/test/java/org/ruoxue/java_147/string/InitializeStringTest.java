package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class InitializeStringTest {

	@Test
	public void withLiteral() {
		String value = "Assertj 155";
		System.out.println(value);
		assertEquals("Assertj 155", value);

		String value2 = "Assertj 155";
		System.out.println(value2);
		assertEquals("Assertj 155", value2);
	}

	@Test
	public void withNew() {
		String value = new String("Assertj 155");
		System.out.println(value);
		assertEquals("Assertj 155", value);

		String value2 = new String("Assertj 155");
		System.out.println(value2);
		assertEquals("Assertj 155", value2);
	}

	@Test
	public void byteArray() {
		byte[] value = new byte[] { 65, 115, 115, 101, 114, 116, 106 };
		String result = new String(value);
		System.out.println(result);
		assertEquals("Assertj", result);

		value = new byte[] { 65, 115, 115, 101, 114, 116, 106, 32, 49, 53, 53 };
		result = new String(value);
		System.out.println(result);
		assertEquals("Assertj 155", result);
	}

	@Test
	public void charArray() {

	}

	@Test
	public void intArray() {

	}
}
