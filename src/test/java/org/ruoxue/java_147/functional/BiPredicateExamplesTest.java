package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.Test;

public class BiPredicateExamplesTest {

	@Test
	public void listAdd() {
		List<String> list = new ArrayList<String>();
		BiPredicate<List<String>, String> listAdd = List::add;
		boolean result = listAdd.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listAdd.test(list, "Ham");
		System.out.println(result);
		assertTrue(result);

		Predicate<String> listAddPredicate = list::add;
		result = listAddPredicate.test("Pork");
		System.out.println(result);
		assertTrue(result);
		System.out.println(list);
	}

	@Test
	public void listRemove() {
		List<String> list = new ArrayList<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiPredicate<List<String>, String> listRemove = List::remove;
		boolean result = listRemove.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listRemove.test(list, "Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(list);
	}

	@Test
	public void listContains() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<List<String>, String> listContains = List::contains;
		boolean result = listContains.test(list, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = listContains.test(list, "Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void mapContainsKey() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		BiPredicate<Map<String, Integer>, String> mapContainsKey = Map::containsKey;
		boolean result = mapContainsKey.test(map, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = mapContainsKey.test(map, "Bread");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void mapContainsValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);

		BiPredicate<Map<String, Integer>, Integer> mapContainsValue = Map::containsValue;
		boolean result = mapContainsValue.test(map, 1);
		System.out.println(result);
		assertTrue(result);
		result = mapContainsValue.test(map, 9);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void queueOffer() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		BiPredicate<Queue<String>, String> queueOffer = Queue::offer;
		boolean result = queueOffer.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueOffer.test(queue, "Ham");
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
		BiPredicate<Queue<String>, String> queueRemove = Queue::remove;
		boolean result = queueRemove.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueRemove.test(queue, "Bread");
		System.out.println(result);
		assertFalse(result);
		System.out.println(queue);
	}

	@Test
	public void queueContains() {
		Queue<String> queue = new ConcurrentLinkedQueue<String>(Arrays.asList("Bacon", "Ham", "Pork"));
		BiPredicate<Queue<String>, String> queueContains = Queue::contains;
		boolean result = queueContains.test(queue, "Bacon");
		System.out.println(result);
		assertTrue(result);
		result = queueContains.test(queue, "Bread");
		System.out.println(result);
		assertFalse(result);
	}
}
