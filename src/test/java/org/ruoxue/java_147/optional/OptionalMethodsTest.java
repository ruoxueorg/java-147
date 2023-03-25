package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class OptionalMethodsTest {

	@Test
	public void empty() {
		Optional<String> emptyOpt = Optional.empty();
		System.out.println(emptyOpt);
		assertFalse(emptyOpt.isPresent());

		System.out.println(emptyOpt);
		emptyOpt = Optional.ofNullable(null);
		assertFalse(emptyOpt.isPresent());
	}

	@Test
	public void of() {
		Optional<String> opt = Optional.of("Beef");
		System.out.println(opt);
		assertTrue(opt.isPresent());

		Optional<Integer> intOpt = Optional.of(147);
		System.out.println(intOpt);
		assertTrue(intOpt.isPresent());

		Optional<List<String>> listOpt = Optional.of(new ArrayList<>());
		System.out.println(listOpt);
		assertTrue(listOpt.isPresent());
	}

	@Test(expected = NullPointerException.class)
	public void ofThrowException() {
		Optional<String> opt = Optional.of(null);
		assertFalse(opt.isPresent());
	}

	@Test
	public void ofNullable() {
		Optional<String> opt = Optional.ofNullable("Beef");
		System.out.println(opt);
		assertTrue(opt.isPresent());

		Optional<Integer> intOpt = Optional.ofNullable(147);
		System.out.println(intOpt);
		assertTrue(intOpt.isPresent());

		Optional<List<String>> listOpt = Optional.ofNullable(new ArrayList<>());
		System.out.println(listOpt);
		assertTrue(listOpt.isPresent());

		Optional<Object> objOpt = Optional.ofNullable(null);
		System.out.println(objOpt);
		assertFalse(objOpt.isPresent());
	}

	@Test
	public void get() {
		Optional<String> opt = Optional.of("Beef");
		System.out.println(opt);
		String result = opt.get();
		System.out.println(result);
		assertEquals("Beef", result);

		Optional<Integer> intOpt = Optional.of(147);
		System.out.println(intOpt);
		int intResult = intOpt.get();
		System.out.println(intResult);
		assertEquals(147, intResult);

		Optional<List<String>> listOpt = Optional.of(new ArrayList<>());
		System.out.println(listOpt);
		List<String> listResult = listOpt.get();
		System.out.println(listResult);
		assertEquals(0, listResult.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void getThrowException() {
		Optional<String> opt = Optional.ofNullable(null);
		System.out.println(opt.get());
		assertFalse(opt.isPresent());
	}

	@Test
	public void isPresent() {
		Optional<String> opt = Optional.ofNullable("Beef");
		boolean result = opt.isPresent();
		System.out.println(result);
		assertTrue(result);

		Optional<Integer> intOpt = Optional.ofNullable(147);
		result = intOpt.isPresent();
		System.out.println(result);
		assertTrue(result);

		Optional<List<String>> listOpt = Optional.ofNullable(new ArrayList<>());
		result = listOpt.isPresent();
		System.out.println(result);
		assertTrue(result);

		Optional<Object> objOpt = Optional.ofNullable(null);
		result = objOpt.isPresent();
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void ifPresent() {
		Optional<String> opt = Optional.ofNullable("Beef");
		opt.ifPresent(e -> System.out.println(e + ", " + e.length()));
		assertTrue(opt.isPresent());

		Optional<Integer> intOpt = Optional.ofNullable(147);
		intOpt.ifPresent(System.out::println);
		assertTrue(intOpt.isPresent());

		Optional<List<String>> listOpt = Optional.ofNullable(new ArrayList<>());
		listOpt.ifPresent(e -> {
			System.out.println(e);
		});
		assertTrue(listOpt.isPresent());

		Optional<Object> objOpt = Optional.ofNullable(null);
		objOpt.ifPresent(e -> System.out.println(e));
		assertFalse(objOpt.isPresent());
	}
}
