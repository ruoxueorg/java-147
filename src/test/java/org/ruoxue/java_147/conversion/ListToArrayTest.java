package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.google.common.primitives.Ints;

public class ListToArrayTest {

	@Test
	public void iteration() {
		int expectedSize = 5;
		List<String> list = Arrays.asList("Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484");
		String[] array = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);

		List<Integer> intList = Arrays.asList(147, 168, 151, 460, 484);
		int[] intArray = new int[list.size()];
		for (int i = 0; i < intList.size(); i++) {
			intArray[i] = intList.get(i);
		}
		System.out.println(Arrays.toString(intArray));
		assertEquals(expectedSize, intArray.length);

		Integer[] integerArray = new Integer[list.size()];
		for (int i = 0; i < intList.size(); i++) {
			integerArray[i] = intList.get(i);
		}
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void toArray() {
		int expectedSize = 5;
		List<String> list = Arrays.asList("Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484");
		String[] array = list.toArray(new String[0]);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);

		List<Integer> intList = Arrays.asList(147, 168, 151, 460, 484);
		Integer[] integerArray = intList.toArray(new Integer[0]);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void toArrayByStream() {
		int expectedSize = 5;
		List<String> list = Arrays.asList("Java 147", "Spring boot 168", "Junit 151", "Bash 460", "IT 484");
		String[] array = list.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);

		List<Integer> intList = Arrays.asList(147, 168, 151, 460, 484);
		int[] intArray = intList.stream().mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(intArray));
		assertEquals(expectedSize, intArray.length);

		Integer[] integerArray = intList.stream().toArray(Integer[]::new);
		System.out.println(Arrays.toString(integerArray));
		assertEquals(expectedSize, integerArray.length);
	}

	@Test
	public void ArrayUtils_toPrimitive() {
		int expectedSize = 5;
		List<Integer> intList = Arrays.asList(147, 168, 151, 460, 484);
		Integer[] integerArray = intList.toArray(new Integer[0]);
		int[] intArray = ArrayUtils.toPrimitive(integerArray);
		System.out.println(Arrays.toString(intArray));
		assertEquals(expectedSize, intArray.length);
	}

	@Test
	public void Ints_toArray() {
		int expectedSize = 5;
		List<Integer> intList = Arrays.asList(147, 168, 151, 460, 484);
		int[] intArray = Ints.toArray(intList);
		System.out.println(Arrays.toString(intArray));
		assertEquals(expectedSize, intArray.length);
	}
}
