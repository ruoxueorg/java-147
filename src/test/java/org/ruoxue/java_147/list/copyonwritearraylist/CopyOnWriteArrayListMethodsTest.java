package org.ruoxue.java_147.list.copyonwritearraylist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class CopyOnWriteArrayListMethodsTest {

	@Test
	public void readWriteThrowException() {
		try {
			int poolSize = 3;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			List<String> list = new ArrayList<>();
			list.add("Apple");
			list.add("Banana");
			list.add("Cherry");
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = list.iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					list.add("Grape");
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
			List<String> list = new CopyOnWriteArrayList<>();
			list.add("Apple");
			list.add("Banana");
			list.add("Cherry");
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = list.iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					list.add("Grape");
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
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addByIndex() {
		int expectedSize = 4;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.add(2, "Grape");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new CopyOnWriteArrayList<>();
		list2.add("Grape");
		list2.add("Lemon");
		list2.add("Mango");

		list.addAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void get() {
		String expected = "Banana";
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		String value = list.get(1);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void set() {
		String expected = "Grape";
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);

		list.set(0, "Grape");
		System.out.println(list);
		assertEquals(expected, list.get(0));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.remove(0);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new CopyOnWriteArrayList<>();
		list2.add("Apple");
		list2.add("Banana");
		list2.add("Mango");
		list.removeAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.clear();
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list.size());
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void isEmpty() {
		List<String> list = new CopyOnWriteArrayList<>();
		System.out.println(list.isEmpty());
		assertTrue(list.isEmpty());
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list.isEmpty());
		assertFalse(list.isEmpty());
	}

}
