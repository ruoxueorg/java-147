package org.ruoxue.java_147.map.concurrenthashmap;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ConcurrentHashMapMethodsTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit {

		private String name;
		private double quantity;
		private int type;

		public Fruit(String name, double quantity, int type) {
			this.name = name;
			this.quantity = quantity;
			this.type = type;
		}

		public String toString() {
			ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
			builder.appendSuper(super.toString());
			builder.append("name", name);
			builder.append("quantity", quantity);
			builder.append("type", type);
			return builder.toString();
		}

		public boolean equals(Object object) {
			if (!(object instanceof Fruit)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Fruit other = (Fruit) object;
			return new EqualsBuilder().append(getName(), other.getName()).isEquals();
		}

		public int hashCode() {
			return new HashCodeBuilder().append(getName()).toHashCode();
		}
	}

	@Test
	public void readWriteThrowException() {
		try {
			int poolSize = 5;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			Map<String, Fruit> map = new HashMap<>();
			map.put("Grape", new Fruit("Grape", -1, 1));
			map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
			map.put("Lemon", new Fruit("Lemon", 1, 3));
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = map.keySet().iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					map.put("Papaya", new Fruit("Papaya", 1, 1));
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
			int poolSize = 5;
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			Map<String, Fruit> map = new ConcurrentHashMap<>();
			map.put("Grape", new Fruit("Grape", -1, 1));
			map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
			map.put("Lemon", new Fruit("Lemon", 1, 3));
			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					Iterator<String> it = map.keySet().iterator();
					while (it.hasNext()) {
						System.out.println(String.format("[%d] %s", Thread.currentThread().getId(), it.next()));
					}
				});
			}

			for (int i = 0; i < poolSize; i++) {
				executorService.execute(() -> {
					map.put("Papaya", new Fruit("Papaya", 1, 1));
				});
			}
			Thread.sleep(2_000L);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void put() {
		int expectedSize = 3;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		Fruit put = map.putIfAbsent("Lemon", new Fruit("Lemon", 1, 3));
		System.out.println(put);
		assertNull(put);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		double expected = Double.MAX_VALUE;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		Fruit value = map.get("Kiwifruit");
		System.out.println(value);
		assertEquals(expected, value.getQuantity(), 2);
	}

	@Test
	public void getOrDefault() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		Fruit result = map.getOrDefault("", new Fruit("Empty", 0, 0));
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void update() {
		double expected = 10d;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		System.out.println(map);

		Fruit put = map.put("Grape", new Fruit("Grape", 10, 1));
		assertEquals(-1d, put.getQuantity(), 0);
		System.out.println(map);
		assertEquals(expected, map.get("Grape").getQuantity(), 0);
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		map.remove("Grape");
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		map.clear();
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));

		Map<String, Fruit> newMap = new ConcurrentHashMap<>();
		newMap.put("Apple", new Fruit("Apple", 4, 1));
		newMap.put("Banana", new Fruit("Banana", 5, 1));
		newMap.put("Cherry", new Fruit("Cherry", 6, 1));

		map.putAll(newMap);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void isEmpty() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		System.out.println(map.isEmpty());
		assertTrue(map.isEmpty());
		map.put("Grape", new Fruit("Grape", -1, 1));
		System.out.println(map.isEmpty());
		assertFalse(map.isEmpty());
	}
}
