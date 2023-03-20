package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalWithExamplesTest {

	public static String getDefaultValue() {
		System.out.println("call getDefaultValue()");
		return "defaultValue";
	}

	@Test
	public void orElse() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElse("defaultValue");
		System.out.println(result);
		assertEquals("Beef", result);

		food = Optional.ofNullable("Chicken");
		result = food.orElse("defaultValue");
		System.out.println(result);
		assertEquals("Chicken", result);

		food = Optional.ofNullable("Duck");
		result = food.orElse("defaultValue");
		System.out.println(result);
		assertEquals("Duck", result);

		food = Optional.ofNullable(null);
		result = food.orElse("defaultValue");
		System.out.println(result);
		assertEquals("defaultValue", result);
	}

	@Test
	public void orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertEquals("Beef", result);

		food = Optional.ofNullable("Chicken");
		result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertEquals("Chicken", result);

		food = Optional.ofNullable("Duck");
		result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertEquals("Duck", result);

		food = Optional.ofNullable(null);
		result = food.orElseGet(() -> "defaultValue");
		System.out.println(result);
		assertEquals("defaultValue", result);

		food = Optional.ofNullable(null);
		result = food.orElseGet(OptionalWithExamplesTest::getDefaultValue);
		System.out.println(result);
		assertEquals("defaultValue", result);
	}

	@Test
	public void orElse_orElseGet() {
		Optional<String> food = Optional.ofNullable("Beef");
		String result = food.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result);

		food = Optional.ofNullable("Chicken");
		result = food.orElseGet(OptionalWithExamplesTest::getDefaultValue);
		System.out.println(result);
		assertEquals("Chicken", result);

		food = Optional.ofNullable(null);
		result = food.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("defaultValue", result);

		food = Optional.ofNullable(null);
		result = food.orElseGet(OptionalWithExamplesTest::getDefaultValue);
		System.out.println(result);
		assertEquals("defaultValue", result);
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
		assertEquals("Beef", result);

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
}
