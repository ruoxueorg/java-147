package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalClassTest {

	@Test
	public void filter() {
		Optional<String> food = Optional.ofNullable("Beef");
		Optional<String> optional = food.filter(e -> e.contains("B"));
		System.out.println(optional);
		boolean result = optional.isPresent();
		System.out.println(result);
		assertTrue(result);

		food = Optional.ofNullable(null);
		optional = food.filter(e -> e.contains("B"));
		System.out.println(optional);
		assertEquals(Optional.empty(), optional);
		result = optional.isPresent();
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void map() {
		Optional<String> food = Optional.ofNullable("Beef");
		Optional<Integer> optional = food.map(e -> e.length());
		System.out.println(optional);
		int result = optional.orElse(0);
		System.out.println(result);
		assertEquals(4, result);

		food = Optional.ofNullable(null);
		optional = food.map(String::length);
		System.out.println(optional);
		assertEquals(Optional.empty(), optional);
		result = optional.orElse(0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
