package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.set.LinkedHashSetClassTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LinkedHashMapMethodsTest {

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
	public void put() {
		int expectedSize = 3;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putIfAbsent() {
		int expectedSize = 3;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		Fruit put = map.putIfAbsent("Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(put);
		assertNull(put);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void get() {
		double expected = 2d;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		Fruit value = map.get("Kiwifruit");
		System.out.println(value);
		assertEquals(expected, value.getQuantity(), 0);
	}
xxx
	@Test
	public void getOrDefault() {
		Integer expected = -1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer value = map.getOrDefault("", -1);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void update() {
		Integer expected = 10;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);

		Integer put = map.put("Grape", 10);
		System.out.println(put);
		assertEquals(1, put.intValue());
		System.out.println(map);
		assertEquals(expected, map.get("Grape"));
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.remove("Grape");
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.clear();
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map.size());
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 6;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Apple", 1);
		map.put("Banana", 2);
		map.put("Cherry", 3);

		Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
		map.put("Grape", 4);
		map.put("Kiwifruit", 5);
		map.put("Lemon", 6);

		map.putAll(map2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void isEmpty() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		System.out.println(map.isEmpty());
		assertTrue(map.isEmpty());
		map.put("Grape", 4);
		map.put("Kiwifruit", 5);
		map.put("Lemon", 6);
		System.out.println(map.isEmpty());
		assertFalse(map.isEmpty());
	}
}
