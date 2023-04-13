package org.ruoxue.java_147.functional.unaryoperator;

import static org.junit.Assert.*;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		UnaryOperator<Double> half = d -> d / 2;
		half = (UnaryOperator) half.andThen(null);
	}

	@Test
	public void compose() {
		UnaryOperator<Double> half = d -> d / 2;
		UnaryOperator<Double> twice = d -> d * d;
		double result = half.compose(twice).apply(5d);
		System.out.println(result);
		assertEquals(12.5, result, 2);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = NullPointerException.class)
	public void composeThrowException() {
		UnaryOperator<Double> half = d -> d / 2;
		half = (UnaryOperator) half.compose(null);
	}

	@Test
	public void identity() {
		UnaryOperator<String> identity = UnaryOperator.identity();
		String result = identity.apply("Bacon");
		System.out.println(result);
		assertEquals("Bacon", result);

		Object objResult = UnaryOperator.identity().apply("Ham");
		System.out.println(objResult);
		assertEquals("Ham", objResult);

		UnaryOperator<Integer> intIdentity = i -> i;
		int intResult = intIdentity.apply(7);
		System.out.println(intResult);
		assertEquals(7, intResult);
	}

	public static class ToUpperCase<E> implements UnaryOperator<String> {
		@Override
		public String apply(String t) {
			return t.toUpperCase();
		}
	}

	@Test
	public void traditional() {
		UnaryOperator<String> toUpperCase = new ToUpperCase<String>();
		UnaryOperator<String> toString = s -> s.toString();
		String result = toUpperCase.andThen(toString).apply("Bacon");
		System.out.println(result);
		assertEquals("BACON", result);
		result = toUpperCase.andThen(toString).apply("Ham");
		System.out.println(result);
		assertEquals("HAM", result);
	}
}
