package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class OptionalWithExamplesTest {

	public static String getDefaultValue() {
		System.out.println("Call getDefaultValue()");
		return "DEFAULT_VALUE";
	}

	@Test
	public void orElse() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElse("DEFAULT_VALUE");
		System.out.println(result);
		assertEquals("Beef", result);

		opt = Optional.ofNullable(null);
		result = opt.orElse("DEFAULT_VALUE");
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);

		Optional<Integer> intOpt = Optional.ofNullable(147);
		int intResult = intOpt.orElse(0);
		System.out.println(intResult);
		assertEquals(147, intResult);

		intOpt = Optional.ofNullable(null);
		intResult = intOpt.orElse(0);
		System.out.println(intResult);
		assertEquals(0, intResult);

		Optional<List<String>> listOpt = Optional.ofNullable(new ArrayList<>(Arrays.asList("Beef", "Chicken", "Duck")));
		List<String> listResult = listOpt.orElse(new ArrayList<>());
		System.out.println(listResult);
		assertEquals(3, listResult.size());

		listOpt = Optional.ofNullable(null);
		listResult = listOpt.orElse(new ArrayList<>());
		System.out.println(listResult);
		assertEquals(0, listResult.size());
	}

	@Test
	public void orElseGet() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElseGet(() -> "DEFAULT_VALUE");
		System.out.println(result);
		assertEquals("Beef", result);

		opt = Optional.ofNullable(null);
		result = opt.orElseGet(() -> "DEFAULT_VALUE");
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);

		opt = Optional.ofNullable(null);
		result = opt.orElseGet(OptionalWithExamplesTest::getDefaultValue);
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);

		Optional<Integer> intOpt = Optional.ofNullable(147);
		int intResult = intOpt.orElseGet(() -> 0);
		System.out.println(intResult);
		assertEquals(147, intResult);

		intOpt = Optional.ofNullable(null);
		intResult = intOpt.orElseGet(() -> 0);
		System.out.println(intResult);
		assertEquals(0, intResult);

		Optional<List<String>> listOpt = Optional.ofNullable(new ArrayList<>(Arrays.asList("Beef", "Chicken", "Duck")));
		List<String> listResult = listOpt.orElseGet(() -> new ArrayList<>());
		System.out.println(listResult);
		assertEquals(3, listResult.size());

		listOpt = Optional.ofNullable(null);
		listResult = listOpt.orElseGet(ArrayList::new);
		System.out.println(listResult);
		assertEquals(0, listResult.size());
	}

	@Test
	public void orElse_vs_orElseGet() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result);

		opt = Optional.ofNullable("Chicken");
		result = opt.orElseGet(() -> getDefaultValue());
		System.out.println(result);
		assertEquals("Chicken", result);

		opt = Optional.ofNullable(null);
		result = opt.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);

		opt = Optional.ofNullable(null);
		result = opt.orElseGet(OptionalWithExamplesTest::getDefaultValue);
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void orElseThrow() {
		Optional<String> opt = Optional.ofNullable(null);
		String result = opt.orElseThrow(IllegalArgumentException::new);
		System.out.println(result);
		assertFalse(opt.isPresent());
	}
}
