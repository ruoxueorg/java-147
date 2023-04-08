package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.function.Predicate;
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

	public static class LengthGreaterThan<E> implements Predicate<String> {
		@Override
		public boolean test(String t) {
			return t.length() > 3;
		}
	}

	@Test
	public void traditional() {
		Predicate<String> lengthGreaterThan = new LengthGreaterThan<String>();
		Predicate<String> contains = s -> s.contains("o");
		boolean result = lengthGreaterThan.and(contains).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = lengthGreaterThan.and(contains).test("Ham");
		System.out.println(result);
		assertFalse(result);
	}
}
