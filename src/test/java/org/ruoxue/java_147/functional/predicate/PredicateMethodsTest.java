package org.ruoxue.java_147.functional.predicate;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicateMethodsTest {

	@Test
	public void test() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		boolean result = startsWith.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		result = greaterThan.test(5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.test("Ham".length());
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void negate() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		boolean result = startsWith.negate().test("Bacon");
		System.out.println(result);
		assertFalse(result);
		result = startsWith.negate().test("Ham");
		System.out.println(result);
		assertTrue(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		result = greaterThan.negate().test(5);
		System.out.println(result);
		assertFalse(result);
		result = greaterThan.negate().test("Ham".length());
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void and() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = startsWith.and(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.and(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = greaterThan.and(lessThan).test(5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.and(lessThan).test(6);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void or() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = startsWith.or(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.or(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = greaterThan.or(lessThan).test(7);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.or(lessThan).test(2);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void chaining() {
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = nonNull.and(startsWith).or(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = nonNull.and(startsWith).or(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> intNonNull = Objects::nonNull;
		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = intNonNull.and(greaterThan).or(lessThan).test(7);
		System.out.println(result);
		assertTrue(result);
		result = intNonNull.and(greaterThan).or(lessThan).test(2);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void isEqual() {
		Predicate<String> isEqual = Predicate.isEqual("Bacon");
		boolean result = isEqual.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = isEqual.test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> intIsEqual = Predicate.isEqual(6);
		result = intIsEqual.test(6);
		System.out.println(result);
		assertTrue(result);
		result = intIsEqual.test(3);
		System.out.println(result);
		assertFalse(result);
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
