package org.ruoxue.java_147.conversion.string;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringToStringArrayTest {

	@Test
	public void split() {
		int expectedSize = 5;
		String value = "java147,springboot168,junit151,bash460,it484";
		String[] array = value.split(",");
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void pattern() {
		int expectedSize = 5;
		String value = "java147,springboot168,junit151,bash460,it484";
		Pattern pattern = Pattern.compile(",");
		String[] array = pattern.split(value);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void stringTokenizer() {
		int expectedSize = 5;
		String value = "java147,springboot168,junit151,bash460,it484";
		StringTokenizer stringTokenizer = new StringTokenizer(value, ",");
		String[] array = new String[stringTokenizer.countTokens()];
		int index = 0;
		while (stringTokenizer.hasMoreTokens()) {
			array[index] = stringTokenizer.nextToken();
			index++;
		}
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void toArray() {
		int expectedSize = 5;
		List<String> list = Arrays.asList("java147", "springboot168", "junit151", "bash460", "it484");
		String[] array = new String[list.size()];
		list.toArray(array);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 5;
		List<String> list = Arrays.asList("java147", "springboot168", "junit151", "bash460", "it484");
		String[] array = list.stream().toArray(String[]::new);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}
}
