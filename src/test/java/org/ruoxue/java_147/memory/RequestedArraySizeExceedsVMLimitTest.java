package org.ruoxue.java_147.memory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RequestedArraySizeExceedsVMLimitTest {

	@Test
	public void intArray() {
		int[] array = new int[Integer.MAX_VALUE];
		assertNotNull(array);
	}

	@Test
	public void stringArray() {
		String[] array = new String[Integer.MAX_VALUE];
		assertNotNull(array);
	}

	@Test
	public void byteArray() {
		Byte[] array = new Byte[Integer.MAX_VALUE];
		assertNotNull(array);
	}

	@Test
	public void normal() {
		for (int i = 0; i < 4; i++) {
			int[] array = new int[Integer.MAX_VALUE - 2];
			assertNotNull(array);
		}
	}
}
