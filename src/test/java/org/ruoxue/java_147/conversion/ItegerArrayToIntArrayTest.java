package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.google.common.primitives.Ints;

public class ItegerArrayToIntArrayTest {

	@Test
	public void iteration() {
		int expectedSize = 5;
		Integer[] array = { 147, 168, 151, 460, 484 };
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].intValue();
		}
		System.out.println(Arrays.toString(result));
		assertEquals(expectedSize, result.length);
	}

	@Test
	public void ArrayUtils_toPrimitive() {
		int expectedSize = 5;
		Integer[] array = { 147, 168, 151, 460, 484 };
		int[] result = ArrayUtils.toPrimitive(array);
		System.out.println(Arrays.toString(result));
		assertEquals(expectedSize, result.length);
	}

	@Test
	public void Ints_toArray() {
		int expectedSize = 5;
		Integer[] array = { 147, 168, 151, 460, 484 };
		List<Integer> list = Arrays.asList(array);
		int[] result = Ints.toArray(list);
		System.out.println(Arrays.toString(result));
		assertEquals(expectedSize, result.length);
	}
}
