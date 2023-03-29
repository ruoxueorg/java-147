package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.Test;

public class BiPredicateMethodsTest {

	@Test
	public void test() {
		BiPredicate<String, String> startsWith = (s, s2) -> s.startsWith(s2);
		boolean result = startsWith.test("Bacon", "B");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.test("Ham", "B");
		System.out.println(result);
		assertFalse(result);

		BiPredicate<String, Integer> greaterThan = (s, i) -> s.length() > i;
		result = greaterThan.test("Bacon", 3);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.test("Ham", 3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void negate() {
		BiPredicate<String, String> startsWith = (s, s2) -> s.startsWith(s2);
		boolean result = startsWith.negate().test("Bacon", "B");
		System.out.println(result);
		assertFalse(result);
		result = startsWith.negate().test("Ham", "B");
		System.out.println(result);
		assertTrue(result);

		BiPredicate<String, Integer> greaterThan = (s, i) -> s.length() > i;
		result = greaterThan.negate().test("Bacon", 3);
		System.out.println(result);
		assertFalse(result);
		result = greaterThan.negate().test("Ham", 3);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void and() {
		BiPredicate<String, String> startsWith = (s, s2) -> s.startsWith(s2);
		BiPredicate<String, String> endsWith = (s, s2) -> s.endsWith(s2);
		boolean result = startsWith.and(endsWith).test("BaconB", "B");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.and(endsWith).test("Ham", "B");
		System.out.println(result);
		assertFalse(result);

		BiPredicate<String, Integer> greaterThan = (s, i) -> s.length() > i;
		BiPredicate<String, Integer> mod = (s, i) -> s.length() % i == 1;
		result = greaterThan.and(mod).test("BaconB", 5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.and(mod).test("Ham", 6);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void or() {
		BiPredicate<String, String> startsWith = (s, s2) -> s.startsWith(s2);
		BiPredicate<String, String> endsWith = (s, s2) -> s.endsWith(s2);
		boolean result = startsWith.or(endsWith).test("BaconB", "B");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.or(endsWith).test("Ham", "B");
		System.out.println(result);
		assertFalse(result);

		BiPredicate<String, Integer> greaterThan = (s, i) -> s.length() > i;
		BiPredicate<String, Integer> mod = (s, i) -> s.length() % i == 1;
		result = greaterThan.or(mod).test("BaconB", 4);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.or(mod).test("Ham", 3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void chaining() {
		BiPredicate<String, String> contains = (s, s2) -> s.contains(s2);
		BiPredicate<String, String> startsWith = (s, s2) -> s.startsWith(s2);
		BiPredicate<String, String> endsWith = (s, s2) -> s.endsWith(s2);
		boolean result = contains.and(startsWith).or(endsWith).test("BaconB", "B");
		System.out.println(result);
		assertTrue(result);
		result = contains.and(startsWith).or(endsWith).test("Ham", "B");
		System.out.println(result);
		assertFalse(result);

		BiPredicate<String, Integer> parseInt = (s, i) -> {
			if (i < Integer.parseInt(s))
				return true;
			return false;
		};
		BiPredicate<String, Integer> greaterThan = (s, i) -> s.length() > i;
		BiPredicate<String, Integer> mod = (s, i) -> s.length() % i == 1;
		result = parseInt.and(greaterThan).or(mod).test("777", 2);
		System.out.println(result);
		assertTrue(result);
		result = parseInt.and(greaterThan).or(mod).test("7", 2);
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
	public void predicate() {
		Predicate<String> lengthGreaterThan = new LengthGreaterThan<String>();
		Predicate<String> contains = o -> o.contains("o");
		boolean result = lengthGreaterThan.and(contains).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = lengthGreaterThan.and(contains).test("Ham");
		System.out.println(result);
		assertFalse(result);
	}
}
