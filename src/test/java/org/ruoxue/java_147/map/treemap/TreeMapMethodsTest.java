package org.ruoxue.java_147.map.treemap;

import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TreeMapMethodsTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit implements Comparable<Fruit> {

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

		@Override
		public int compareTo(Fruit o) {
			return this.name.compareTo(o.name);
		}
	}

	@Test
	public void put() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		Integer put = map.putIfAbsent(new Fruit("Lemon", 3, 1), 3);
		System.out.println(put);
		assertNull(put);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		Fruit key = new Fruit("Kiwifruit", 2, 1);
		map.put(key, 2);
		Integer value = map.get(key);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void getOrDefault() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Integer result = map.getOrDefault(new Fruit("Empty", 0, 0), new Integer(10));
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void update() {
		Integer expected = 10;
		Map<Fruit, Integer> map = new TreeMap<>();
		Fruit key = new Fruit("Grape", 1, 1);
		map.put(key, 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);

		Integer put = map.put(key, new Integer(10));
		assertEquals(1, put.intValue());
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<Fruit, Integer> map = new TreeMap<>();
		Fruit key = new Fruit("Grape", 1, 1);
		map.put(key, 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.remove(key);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.clear();
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);

		Map<Fruit, Integer> newMap = new TreeMap<>();
		newMap.put(new Fruit("Apple", 4, 1), 4);
		newMap.put(new Fruit("Cherry", 6, 1), 6);
		newMap.put(new Fruit("Banana", 5, 1), 5);

		map.putAll(newMap);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void isEmpty() {
		Map<Fruit, Integer> map = new TreeMap<>();
		System.out.println(map.isEmpty());
		assertTrue(map.isEmpty());
		map.put(new Fruit("Grape", 1, 1), 1);
		System.out.println(map.isEmpty());
		assertFalse(map.isEmpty());
	}
}
