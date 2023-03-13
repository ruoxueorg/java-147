package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.google.common.primitives.Ints;

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

	@Test
	public void IntStream_of() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		IntStream intStream = IntStream.of(array);
		Integer[] integerArray = intStream.boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void Arrays_stream() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		IntStream intStream = Arrays.stream(array);
		Integer[] integerArray = intStream.boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void Stream_of() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		Stream<int[]> stream = Stream.of(array);
		Integer[] integerArray = stream.flatMapToInt(e -> Arrays.stream(e)).boxed().toArray(Integer[]::new);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void ArrayUtils_toObject() {
		int expectedSize = 5;
		int[] intArray = { 147, 168, 151, 460, 484 };
		Integer[] integerArray = ArrayUtils.toObject(intArray);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void Ints_asList_toArray() {
		int expectedSize = 5;
		int[] array = { 147, 168, 151, 460, 484 };
		Integer[] integerArray = Ints.asList(array).toArray(new Integer[array.length]);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}
}
