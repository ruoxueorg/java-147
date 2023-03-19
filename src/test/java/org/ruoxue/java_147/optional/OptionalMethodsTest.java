package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class OptionalMethodsTest {

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

		food = Optional.ofNullable("Beef");
		food.ifPresent(System.out::println);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		food.ifPresent(e -> System.out.println(e + ", " + e.length()));
		assertFalse(food.isPresent());
	}

	public static String getDefaultValue() {
		System.out.println("call getDefaultValue()");
		return "defaultValue";
	}

	@Test
	public void orElse() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElse("defaultValue");
		System.out.println(result);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElse("defaultValue");
		System.out.println(result);
		assertFalse(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElse(getDefaultValue());
		System.out.println(result);
		assertFalse(food.isPresent());
	}

	@Test
	public void orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertFalse(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElseGet(OptionalMethodsTest::getDefaultValue);
		System.out.println(result);
		assertFalse(food.isPresent());
	}

	@Test
	public void orElse_orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElse(getDefaultValue());
		System.out.println(result);
		assertTrue(food.isPresent());

		food = Optional.ofNullable("Beef");
		result = food.orElseGet(OptionalMethodsTest::getDefaultValue);
		System.out.println(result);
		assertTrue(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElse(getDefaultValue());
		System.out.println(result);
		assertFalse(food.isPresent());

		food = Optional.ofNullable(null);
		result = food.orElseGet(OptionalMethodsTest::getDefaultValue);
		System.out.println(result);
		assertFalse(food.isPresent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void orElseThrow() {
		Optional<String> food = Optional.ofNullable(null);
		String result = food.orElseThrow(IllegalArgumentException::new);
		System.out.println(result);
		assertFalse(food.isPresent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void findById() {
		String result = findById_orElseThrow("Beef");
		System.out.println(result);
		assertNotNull(result);

		result = findById_orElseThrow(null);
		System.out.println(result);
		assertNull(result);
	}

	public static String findById(String id) {
		if (id != null) {
			return id;
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	public static String findById_get(String id) {
		Optional<String> optional = Optional.ofNullable(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	public static String findById_orElseThrow(String id) {
		Optional<String> optional = Optional.ofNullable(id);
		return optional.orElseThrow(() -> new IllegalArgumentException(id));
	}

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
