package org.ruoxue.java_147.string;

import static org.junit.Assert.*;
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

	}

	@Test
	public void charArray() {

	}

	@Test
	public void intArray() {

	}
}
