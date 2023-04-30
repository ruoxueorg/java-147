package org.ruoxue.java_147.functional.binaryoperator;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.junit.Test;

public class BinaryOperatorMethodsTest {

	@Test
	public void apply() {
		BinaryOperator<String> concact = (s, s2) -> s.concat(s2);
		String result = concact.apply("Bacon", "Ham");
		System.out.println(result);
		assertNotNull(result);
		result = concact.apply("Ham", "Bacon");
		System.out.println(result);
		assertNotNull(result);

		BinaryOperator<Integer> addition = (i, i2) -> i + i2;
		Integer intResult = addition.apply(1, 1);
		System.out.println(intResult);
		assertEquals(2, intResult.intValue());
		intResult = addition.apply(1, 10);
		System.out.println(intResult);
		assertEquals(11, intResult.intValue());
	}

	@Test
	public void andThen() {
		BinaryOperator<Double> addition = (d, d1) -> d + d1;
		Function<Double, Double> twice = d -> d * d;
		double result = addition.andThen(twice).apply(1d, 2d);
		System.out.println(result);
		assertEquals(9d, result, 2);

		BinaryOperator<String> concact = (s, s2) -> s.concat(s2);
		Function<String, String> toUpperCase = s -> s.toUpperCase();
		String stringResult = concact.andThen(toUpperCase).apply("Bacon", "Ham");
		System.out.println(stringResult);
		assertEquals("BACONHAM", stringResult);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		BinaryOperator<Double> addition = (d, d1) -> d + d1;
		addition.andThen(null);
	}

	@Test
	public void maxBy() {
		BinaryOperator<String> maxBy = BinaryOperator.maxBy(Comparator.comparingInt(String::length));
		String result = maxBy.apply("Bacon", "Ham");
		System.out.println(result);
		assertEquals("Bacon", result);
		result = maxBy.apply("Ham", "Port");
		System.out.println(result);
		assertEquals("Port", result);

		BinaryOperator<Integer> intMaxBy = BinaryOperator.maxBy(Comparator.comparing(Integer::intValue));
		Integer intResult = intMaxBy.apply(1, 1);
		System.out.println(intResult);
		assertEquals(1, intResult.intValue());
		intResult = intMaxBy.apply(1, 10);
		System.out.println(intResult);
		assertEquals(10, intResult.intValue());
	}

	@Test
	public void minBy() {
		BinaryOperator<String> minBy = BinaryOperator.minBy(Comparator.comparingInt(String::length));
		String result = minBy.apply("Bacon", "Ham");
		System.out.println(result);
		assertEquals("Ham", result);
		result = minBy.apply("Ham", "Port");
		System.out.println(result);
		assertEquals("Ham", result);

		BinaryOperator<Integer> intMinBy = BinaryOperator.minBy(Comparator.comparing(Integer::intValue));
		Integer intResult = intMinBy.apply(1, 1);
		System.out.println(intResult);
		assertEquals(1, intResult.intValue());
		intResult = intMinBy.apply(1, 10);
		System.out.println(intResult);
		assertEquals(1, intResult.intValue());
	}

	public static class Addition<E> implements BinaryOperator<Double> {
		@Override
		public Double apply(Double t, Double u) {
			return t + u;
		}
	}

	@Test
	public void traditional() {
		BinaryOperator<Double> addition = new Addition<Double>();
		Function<Double, Double> multiply = i -> i * 2;
		double result = addition.andThen(multiply).apply(1d, 2d);
		System.out.println(result);
		assertEquals(6.0, result, 2);
		result = addition.andThen(multiply).apply(5d, 6d);
		System.out.println(result);
		assertEquals(22.0, result, 2);
	}
}
