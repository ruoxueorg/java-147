package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.function.Supplier;

import org.junit.Test;

public class SupplierMethodsTest {

	@Test
	public void get() {
		Supplier<String> value = () -> "Bacon";
		String result = value.get();
		System.out.println(result);
		assertNotNull(result);
		value = () -> "Ham";
		result = value.get();
		System.out.println(result);
		assertNotNull(result);

		Supplier<Integer> intValue = () -> 5;
		int intResult = intValue.get();
		System.out.println(intResult);
		assertTrue(intResult > 0);
		intValue = () -> "Ham".length();
		intResult = intValue.get();
		System.out.println(intResult);
	}

	public static class DefaultValue<E> implements Supplier<String> {
		@Override
		public String get() {
			return "defaultValue";
		}
	}

	@Test
	public void traditional() {
		DefaultValue<String> defaultValue = new DefaultValue<String>();
		String result = defaultValue.get();
		System.out.println(result);
		assertNotNull(result);
	}
}
