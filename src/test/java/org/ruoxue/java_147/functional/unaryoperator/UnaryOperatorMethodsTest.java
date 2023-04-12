package org.ruoxue.java_147.functional.unaryoperator;

import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.junit.Test;

public class UnaryOperatorMethodsTest {

	@Test
	public void apply() {
		UnaryOperator<String> upperCase = s -> s.toUpperCase();
		String result = upperCase.apply("Bacon");
		System.out.println(result);
		assertEquals("BACON", result);
		result = upperCase.apply("Ham");
		System.out.println(result);
		assertEquals("HAM", result);

		UnaryOperator<Integer> addition = i -> i + 3;
		Integer intResult = addition.apply(5);
		System.out.println(result);
		assertEquals(new Integer(8), intResult);
	}

	@Test
	public void andThen() {
		UnaryOperator<Double> half = d -> d / 2;
		UnaryOperator<Double> twice = d -> d * d;
		double result = half.andThen(twice).apply(5d);
		System.out.println(result);
		assertEquals(6.25d, result, 2);

		UnaryOperator<String> lowerCase = s -> s.toLowerCase();
		UnaryOperator<String> concat = s -> s.concat("B");
		String stringResult = lowerCase.andThen(concat).apply("Bacon");
		System.out.println(stringResult);
		assertEquals("baconB", stringResult);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		UnaryOperator<Double> half = d -> d / 2;
		half = (UnaryOperator) half.andThen(null);
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
