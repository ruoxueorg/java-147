package org.ruoxue.java_147.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StreamMethodsTest {

	@Test
	public void allMatch() {
		String[] array = new String[] { null, null };
		boolean result = Arrays.stream(array).allMatch(Objects::isNull);
		System.out.println(result);
		assertTrue(result);
		
		array = new String[] { "Coconut", null };
		result = Arrays.stream(array).allMatch(Objects::isNull);
		System.out.println(result);
		assertFalse(result);

		array = new String[] { "Coconut", null };
		result = Arrays.stream(array).allMatch(StringUtils::isNotEmpty);
		System.out.println(result);
		assertFalse(result);
		
		result = Arrays.stream((String[])null).allMatch(StringUtils::isNotEmpty);
		System.out.println(result);
		assertFalse(result);
	}

}
