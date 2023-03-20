package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class OptionalMethodsTest {

	@Test
	public void empty() {
		Optional<String> empty = Optional.empty();
		System.out.println(empty);
		assertEquals(Optional.empty(), empty);

		System.out.println(empty);
		empty = Optional.ofNullable(null);
		assertEquals(Optional.empty(), empty);
	}

	@Test
	public void of() {
		Optional<String> food = Optional.of("Beef");
		System.out.println(food);
		assertTrue(food.isPresent());

		food = Optional.of("Chicken");
		System.out.println(food);
		assertTrue(food.isPresent());

		food = Optional.of("Duck");
		System.out.println(food);
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
		System.out.println(food);
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Chicken");
		System.out.println(food);
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Duck");
		System.out.println(food);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		System.out.println(food);
		assertFalse(food.isPresent());
	}

	@Test
	public void get() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.get();
		System.out.println(result);
		assertEquals("Beef", result);

		food = Optional.ofNullable("Chicken");
		result = food.get();
		System.out.println(result);
		assertEquals("Chicken", result);

		food = Optional.ofNullable("Duck");
		result = food.get();
		System.out.println(result);
		assertEquals("Duck", result);
	}

	@Test(expected = NoSuchElementException.class)
	public void getThrowException() {
		Optional<String> food = Optional.ofNullable(null);
		System.out.println(food.get());
		assertFalse(food.isPresent());
	}

	@Test
	public void isPresent() {
		Optional<String> food = Optional.ofNullable("Beef");
		boolean result = food.isPresent();
		System.out.println(result);
		assertTrue(result);

		food = Optional.ofNullable("Chicken");
		result = food.isPresent();
		System.out.println(result);
		assertTrue(result);

		food = Optional.ofNullable("Duck");
		result = food.isPresent();
		System.out.println(result);
		assertTrue(result);

		food = Optional.ofNullable(null);
		result = food.isPresent();
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void ifPresent() {
		Optional<String> food = Optional.ofNullable("Beef");
		food.ifPresent(e -> System.out.println(e + ", " + e.length()));
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Chicken");
		food.ifPresent(System.out::println);
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Duck");
		food.ifPresent(e -> {
			System.out.println(e);
		});
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		food.ifPresent(e -> System.out.println(e));
		assertFalse(food.isPresent());
	}
}
