package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicateExamplesTest {

	@Test
	public void stringContains() {
		String value = "Bacon";
		Predicate<String> contains = value::contains;
		boolean result = contains.test(value);
		System.out.println(result);
		assertTrue(result);

		value = "Bread";
		result = contains.test(value);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void listAdd() {
		List<String> list = new ArrayList<String>();
		Predicate<String> listAdd = list::add;
		boolean result = listAdd.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listAdd.test("Ham");
		System.out.println(result);
		assertTrue(result);
		result = listAdd.test("Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(list);
	}

	@Test
	public void listRemove() {
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> listRemove = list::remove;
		boolean result = listRemove.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listRemove.test("Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(list);
	}

	@Test
	public void listContains() {
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
	public void mapContainsKey() {
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
