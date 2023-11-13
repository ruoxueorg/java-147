package org.ruoxue.java_147.set.copyonwritearrayset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CopyOnWriteArraySetMethodsTest {

	@Test
	public void readWriteThrowException() {
		try {
			int poolSize = 3;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			Set<String> set = new HashSet<>();
			set.add("Longan");
			set.add("Tomato");
			set.add("Pear");
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = set.iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					set.add("Grape");
				});
			}
			Thread.sleep(2_000L);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void readWrite() {
		try {
			int poolSize = 3;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			Set<String> set = new CopyOnWriteArraySet<>();
			set.add("Longan");
			set.add("Tomato");
			set.add("Pear");
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = set.iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					set.add("Grape");
				});
			}
			Thread.sleep(2_000L);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void add() {
		int expectedSize = 3;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new CopyOnWriteArraySet<>();
		set2.add("Grape");
		set2.add("Lemon");
		set2.add("Mango");

		set.addAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.remove("Longan");
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");

		Set<String> set2 = new CopyOnWriteArraySet<>();
		set2.add("Longan");
		set2.add("Tomato");
		set2.add("Mango");
		set.removeAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		set.clear();
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Set<String> set = new CopyOnWriteArraySet<>();
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.size());
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void isEmpty() {
		Set<String> set = new CopyOnWriteArraySet<>();
		System.out.println(set.isEmpty());
		assertTrue(set.isEmpty());
		set.add("Longan");
		set.add("Tomato");
		set.add("Pear");
		System.out.println(set.isEmpty());
		assertFalse(set.isEmpty());
	}
}
