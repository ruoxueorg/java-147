package org.ruoxue.java_147.functional.function;

import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

public class FunctionMethodsTest {

	@Test
	public void apply() {
		Function<String, Boolean> startsWith = s -> s.startsWith("B");
		boolean result = startsWith.apply("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.apply("Ham");
		System.out.println(result);
		assertFalse(result);

		Function<Integer, Boolean> greaterThan = i -> i > 3;
		result = greaterThan.apply(5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.apply("Ham".length());
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void andThen() {
		Function<Double, Double> half = d -> d / 2;
		Function<Double, Double> twice = d -> d * d;
		double result = half.andThen(twice).apply(5d);
		System.out.println(result);
		assertEquals(6.25d, result, 2);

		Function<String, Integer> length = s -> s.length();
		Function<Integer, Integer> multiply = i -> i * 2;
		int intResult = length.andThen(multiply).apply("Bacon");
		System.out.println(intResult);
		assertEquals(10, intResult);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		Function<Double, Double> half = d -> d / 2;
		half = half.andThen(null);
	}

	@Test
	public void compose() {
		Function<Double, Double> half = d -> d / 2;
		Function<Double, Double> twice = d -> d * d;
		double result = half.compose(twice).apply(5d);
		System.out.println(result);
		assertEquals(12.5, result, 2);
	}

	@Test(expected = NullPointerException.class)
	public void composeThrowException() {
		Function<Double, Double> half = d -> d / 2;
		half = half.compose(null);
	}

	@Test
	public void identity() {
		Function<String, String> identity = Function.identity();
		String result = identity.apply("Bacon");
		System.out.println(result);
		assertEquals("Bacon", result);

		Object objResult = Function.identity().apply("Ham");
		System.out.println(objResult);
		assertEquals("Ham", objResult);

		Function<Integer, Integer> intIdentity = i -> i;
		int intResult = intIdentity.apply(7);
		System.out.println(intResult);
		assertEquals(7, intResult);
	}

	public static class Length<E, F> implements Function<String, Integer> {
		@Override
		public Integer apply(String t) {
			return t.length();
		}
	}

	@Test
	public void traditional() {
		Function<String, Integer> length = new Length<String, Integer>();
		Function<Integer, Integer> multiply = i -> i * 2;
		int result = length.andThen(multiply).apply("Bacon");
		System.out.println(result);
		assertEquals(10, result);
		result = length.andThen(multiply).apply("Ham");
		System.out.println(result);
		assertEquals(6, result);
	}
}
