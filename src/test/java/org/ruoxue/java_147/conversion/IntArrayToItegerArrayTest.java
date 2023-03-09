package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class IntArrayToItegerArrayTest {

	@Test
	public void iteration() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		Integer[] integerArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			integerArray[i] = Integer.valueOf(array[i]);
		}
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

}
