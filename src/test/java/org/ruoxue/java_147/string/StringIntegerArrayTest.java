package org.ruoxue.java_147.string;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class StringIntegerArrayTest {

	@Test
	public void replaceAll() {
		int expectedSize = 5;
		String value = "[147,168,151,460,484]";
		String[] stringArray = value.replaceAll("\\[", "").replaceAll("]", "").split(",");
		System.out.println(Arrays.toString(stringArray));

		int[] array = new int[stringArray.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.valueOf(stringArray[i]);
		}
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void split() {
		int expectedSize = 5;
		String value = "147,168,151,460,484";
		String[] stringArray = value.split(",");
		System.out.println(Arrays.toString(stringArray));

		int[] array = new int[stringArray.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(stringArray[i]);
		}
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void stream() {
		int expectedSize = 5;
		String value = "147,168,151,460,484";
		String[] stringArray = value.split(",");
		System.out.println(Arrays.toString(stringArray));

		int[] array = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}
}
