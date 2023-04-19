package org.ruoxue.java_147.functional.bifunction;

import static org.junit.Assert.*;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;

public class BiFunctionMethodsTest {

	@Test
	public void apply() {
		BiFunction<String, String, Boolean> compareLength = (s, s2) -> s.length() > s2.length();
		boolean result = compareLength.apply("Bacon", "Ham");
		System.out.println(result);
		assertTrue(result);
		result = compareLength.apply("Ham", "Bacon");
		System.out.println(result);
		assertFalse(result);

		BiFunction<String, Integer, Boolean> greaterThan = (s, i) -> s.length() > i;
		result = greaterThan.apply("Bacon", 3);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.apply("Ham", 3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void andThen() {
		BiFunction<Double, Double, Double> addition = (d, d1) -> d + d1;
		Function<Double, Double> twice = d -> d * d;
		double result = addition.andThen(twice).apply(1d, 2d);
		System.out.println(result);
		assertEquals(9d, result, 2);

		BiFunction<String, String, Integer> length = (s, s1) -> s.length() + s1.length();
		Function<Integer, Integer> multiply = i -> i * 2;
		int intResult = length.andThen(multiply).apply("Bacon", "Ham");
		System.out.println(intResult);
		assertEquals(16, intResult);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		BiFunction<Double, Double, Double> addition = (d, d1) -> d + d1;
		addition = addition.andThen(null);
	}

	public static class CompareLength<E, F, D> implements BiFunction<String, String, Boolean> {
		@Override
		public Boolean apply(String t, String u) {
			return t.length() > u.length();
		}
	}

	@Test
	public void traditional() {
		Function<String, Integer> compareLength = new CompareLength<String, Integer>();
		Function<Integer, Integer> multiply = i -> i * 2;
		int result = length.andThen(multiply).apply("Bacon");
		System.out.println(result);
		assertEquals(10, result);
		result = length.andThen(multiply).apply("Ham");
		System.out.println(result);
		assertEquals(6, result);
	}
}
