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
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.Test;

public class BiPredicateExamplesTest {

	@Test
	public void Objects_equals() {
		BiPredicate<String, String> equals = Objects::equals;
		boolean result = equals.test("Bacon", "Bacon");
		System.out.println(result);
		assertTrue(result);

		result = equals.test("Bacon", null);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void String_contains() {
		String value = "Bacon";
		BiPredicate<String, String> contains = String::contains;
		boolean result = contains.test(value, "o");
		System.out.println(result);
		assertTrue(result);

		value = "Ham";
		result = contains.test(value, "o");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void List_add() {
		List<String> list = new ArrayList<String>();
		BiPredicate<List<String>, String> add = List::add;
		boolean result = add.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = add.test(list, "Ham");
		System.out.println(result);
		assertTrue(result);
		result = add.test(list, "Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(list);
	}

	@Test
	public void List_remove() {
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiPredicate<List<String>, String> remove = List::remove;
		boolean result = remove.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = remove.test(list, "Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(list);
	}

	@Test
	public void List_contains() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<List<String>, String> contains = List::contains;
		boolean result = contains.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = contains.test(list, "Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Map_containsKey() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		BiPredicate<Map<String, Integer>, String> containsKey = Map::containsKey;
		boolean result = containsKey.test(map, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = containsKey.test(map, "Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Map_containsValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		BiPredicate<Map<String, Integer>, Integer> containsValue = Map::containsValue;
		boolean result = containsValue.test(map, 1);
		System.out.println(result);
		assertTrue(result);
		result = containsValue.test(map, 9);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void Queue_offer() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		BiPredicate<Queue<String>, String> offer = Queue::offer;
		boolean result = offer.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = offer.test(queue, "Ham");
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
		BiPredicate<Queue<String>, String> remove = Queue::remove;
		boolean result = remove.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = remove.test(queue, "Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(queue);
	}

	@Test
	public void Queue_contains() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiPredicate<Queue<String>, String> contains = Queue::contains;
		boolean result = contains.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = contains.test(queue, "Bread");
		System.out.println(result);
		assertFalse(result);
	}
}
