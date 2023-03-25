package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalClassTest {

	@Test
	public void filter() {
		Optional<String> opt = Optional.ofNullable("Beef");
		Optional<String> newOpt = opt.filter(e -> e.contains("B"));
		System.out.println(newOpt);
		boolean result = newOpt.isPresent();
		System.out.println(result);
		assertTrue(result);

		opt = Optional.ofNullable(null);
		newOpt = opt.filter(e -> e.contains("B"));
		System.out.println(newOpt);
		assertEquals(Optional.empty(), newOpt);
		result = newOpt.isPresent();
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void map() {
		Optional<String> opt = Optional.ofNullable("Beef");
		Optional<Integer> newOpt = opt.map(e -> e.length());
		System.out.println(newOpt);
		int result = newOpt.orElse(0);
		System.out.println(result);
		assertEquals(4, result);

		opt = Optional.ofNullable(null);
		newOpt = opt.map(String::length);
		System.out.println(newOpt);
		assertEquals(Optional.empty(), newOpt);
		result = newOpt.orElse(0);
		System.out.println(result);
		assertEquals(0, result);
	}
}
