package org.ruoxue.java_147.list.copyonwritearraylist;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;

public class CopyOnWriteArrayListWithExamplesTest {

	@Test
	public void iteratorThrowException() {
		try {
			int poolSize = 3;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			List<String> list = new ArrayList<>();
			list.add("Apple");
			list.add("Banana");
			list.add("Cherry");
			IntStream.range(0, poolSize).forEach(e -> {
				executorService.execute(() -> {
					Iterator<String> it = list.iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			});

			IntStream.range(0, poolSize).forEach(e -> {
				executorService.execute(() -> {
					list.add("Grape");
				});
			});
			Thread.sleep(2_000L);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void iterator() {
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
	public void listIteratorThrowException() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		ListIterator<String> it = list.listIterator();

		assertThatCode(() -> {
			it.add("Grape");
		}).isInstanceOf(UnsupportedOperationException.class);

		assertThatCode(() -> {
			it.set("Apple");
		}).isInstanceOf(UnsupportedOperationException.class);

		assertThatCode(() -> {
			while (it.hasNext()) {
				it.remove();
			}
		}).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void loop() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	@Test
	public void forEach() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.forEach(e -> System.out.println(e));
	}

	@Test
	public void forEachRemaining() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Iterator<String> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void listIterator() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		ListIterator<String> it = list.listIterator(list.size());
		while (it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}

	@Test
	public void spliterator() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Spliterator<String> sit = list.spliterator();
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = list.spliterator();
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}

	@Test
	public void trySplit() {
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		Spliterator<String> sit = list.spliterator();
		Spliterator<String> sit2 = sit.trySplit();
		System.out.println(sit.getExactSizeIfKnown());
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		System.out.println(sit2.getExactSizeIfKnown());
		sit2.forEachRemaining(e -> System.out.println(e));
	}

	@Test
	public void toArray() {
		int expectedSize = 3;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		String[] array = new String[list.size()];
		list.toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 3;
		List<String> list = new CopyOnWriteArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		String[] array = list.stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
