package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
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
		Optional<String> food = Optional.ofNullable("Beef");
		System.out.println(food.get());
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		assertFalse(food.isPresent());
	}

	@Test
	public void get() {
		Optional<String> food = Optional.ofNullable("Beef");
		System.out.println(food.get());
		assertTrue(food.isPresent());
	}

	@Test(expected = NoSuchElementException.class)
	public void getThrowException() {
		Optional<String> food = Optional.ofNullable(null);
		System.out.println(food.get());
		assertFalse(food.isPresent());
	}

	@Test
	public void ifPresent() {
		Optional<String> food = Optional.ofNullable("Beef");
		food.ifPresent(e -> System.out.println(e + ", " + e.length()));
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		food.ifPresent(e -> System.out.println(e + ", " + e.length()));
		assertFalse(food.isPresent());
	}

	public String getDefaultValue() {
		System.out.println("call getDefaultValue()");
		return "defaultValue";
	}

	@Test
	public void orElse() {
		Optional<String> food = Optional.ofNullable("Beef");
		String value = food.orElse("defaultValue");
		System.out.println(value);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		value = food.orElse("defaultValue");
		System.out.println(value);
		assertFalse(food.isPresent());

		food = Optional.ofNullable(null);
		value = food.orElse(getDefaultValue());
		System.out.println(value);
		assertFalse(food.isPresent());
	}

	@Test
	public void orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String value = food.orElseGet(() -> "defaultValue");
		System.out.println(value);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		value = food.orElseGet(() -> "defaultValue");
		System.out.println(value);
		assertFalse(food.isPresent());

		food = Optional.ofNullable(null);
		value = food.orElseGet(this::getDefaultValue);
		System.out.println(value);
		assertFalse(food.isPresent());
	}

	@Test
	public void orElse_orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String value = food.orElse(getDefaultValue());
		System.out.println(value);
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Beef");
		value = food.orElseGet(this::getDefaultValue);
		System.out.println(value);
		assertTrue(food.isPresent());
	}
}
