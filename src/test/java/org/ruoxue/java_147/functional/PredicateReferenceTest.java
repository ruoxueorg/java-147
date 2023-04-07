package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicateReferenceTest {

	@Test
	public void Objects_nonNull() {
		Predicate<String> nonNull = Objects::nonNull;
		boolean result = nonNull.test("Bacon");
		System.out.println(result);
		assertTrue(result);

		result = nonNull.test(null);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void String_contains() {
		String value = "Bacon";
		Predicate<String> contains = value::contains;
		boolean result = contains.test("o");
		System.out.println(result);
		assertTrue(result);

		value = "Ham";
		contains = value::contains;
		result = contains.test("o");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void List_add() {
		List<String> list = new ArrayList<String>();
		Predicate<String> add = list::add;
		boolean result = add.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = add.test("Ham");
		System.out.println(result);
		assertTrue(result);
		result = add.test("Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(list);
	}

	@Test
	public void List_remove() {
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> remove = list::remove;
		boolean result = remove.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = remove.test("Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(list);
	}

	@Test
	public void List_contains() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> contains = list::contains;
		boolean result = contains.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = contains.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Map_containsKey() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		Predicate<String> containsKey = map::containsKey;
		boolean result = containsKey.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = containsKey.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Map_containsValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		Predicate<Integer> containsValue = map::containsValue;
		boolean result = containsValue.test(1);
		System.out.println(result);
		assertTrue(result);
		result = containsValue.test(9);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Queue_offer() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		Predicate<String> offer = queue::offer;
		boolean result = offer.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = offer.test("Ham");
		System.out.println(result);
		assertTrue(result);

		Predicate<String> queueOfferPredicate = queue::offer;
		result = queueOfferPredicate.test("Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(queue);
	}

	@Test
	public void Queue_remove() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> remove = queue::remove;
		boolean result = remove.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = remove.test("Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(queue);
	}

	@Test
	public void Queue_contains() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> contains = queue::contains;
		boolean result = contains.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = contains.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}
}
