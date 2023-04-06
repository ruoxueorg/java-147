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

public class PredicateExamplesTest {

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
		Predicate<String> listContains = list::contains;
		boolean result = listContains.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listContains.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Map_containsKey() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		Predicate<String> mapContainsKey = map::containsKey;
		boolean result = mapContainsKey.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = mapContainsKey.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void mapContainsValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		Predicate<Integer> mapContainsValue = map::containsValue;
		boolean result = mapContainsValue.test(1);
		System.out.println(result);
		assertTrue(result);
		result = mapContainsValue.test(9);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void queueOffer() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		Predicate<String> queueOffer = queue::offer;
		boolean result = queueOffer.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueOffer.test("Ham");
		System.out.println(result);
		assertTrue(result);

		Predicate<String> queueOfferPredicate = queue::offer;
		result = queueOfferPredicate.test("Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(queue);
	}

	@Test
	public void queueRemove() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> queueRemove = queue::remove;
		boolean result = queueRemove.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueRemove.test("Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(queue);
	}

	@Test
	public void queueContains() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> queueContains = queue::contains;
		boolean result = queueContains.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueContains.test("Bread");
		System.out.println(result);
		assertFalse(result);
	}
}
