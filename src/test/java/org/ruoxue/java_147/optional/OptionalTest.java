package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	@Test
	public void empty() {
		Optional<String> empty = Optional.empty();
		assertFalse(empty.isPresent());
	}

	@Test
	public void of() {
		Optional<String> food = Optional.of("Beef");
		System.out.println(food.get());
		assertTrue(food.isPresent());

		food = Optional.of("Chicken");
		System.out.println(food.get());
		assertTrue(food.isPresent());

		food = Optional.of("Duck");
		System.out.println(food.get());
		assertTrue(food.isPresent());
	}

	@Test(expected = NullPointerException.class)
	public void ofThrowException() {
		Optional<String> food = Optional.of(null);
		assertFalse(food.isPresent());
	}

	@Test
	public void ofNullable() {
		Optional<String> food = Optional.ofNullable(null);
		assertFalse(food.isPresent());
	}

	@Test
	public void ifPresent() {
		Optional<String> food = Optional.ofNullable("Beef");
		food.ifPresent(e -> System.out.println(e.length()));
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		food.ifPresent(e -> System.out.println(e.length()));
		assertFalse(food.isPresent());
	}

}
