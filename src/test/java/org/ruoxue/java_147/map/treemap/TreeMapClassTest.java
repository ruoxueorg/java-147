package org.ruoxue.java_147.map.treemap;

import static org.junit.Assert.*;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TreeMapClassTest {

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
	public void containsKey() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		Fruit key = new Fruit("Lemon", 3, 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		boolean containsKey = map.containsKey(key);
		System.out.println(containsKey);
		assertTrue(containsKey);
	}

	@Test
	public void containsValue() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		boolean containsValue = map.containsValue(3);
		System.out.println(containsValue);
		assertTrue(containsValue);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Set<Fruit> set = map.keySet().stream().filter(e -> e.name.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.keySet().parallelStream().forEach(System.out::println);
		System.out.println("----------");
		map.keySet().parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void replace() {
		Integer expected = 10;
		Map<Fruit, Integer> map = new TreeMap<>();
		Fruit key = new Fruit("Grape", 1, 1);
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.replace(key, 10);
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void replaceAll() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.replaceAll((k, v) -> {
			v = (int) k.getQuantity() * 10;
			return v;
		});
		System.out.println(map);
	}

	@Test
	public void merge() {
		Integer expected = 11;
		Map<Fruit, Integer> map = new TreeMap<>();
		Fruit key = new Fruit("Grape", 1, 1);
		map.put(key, 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Integer replaced = map.merge(key, 10, (oldValue, newValue) -> {
			newValue += oldValue;
			return newValue;
		});
		System.out.println(map);
		assertEquals(expected, replaced);

		Fruit newKey = new Fruit("Papaya", 4, 1);
		replaced = map.merge(newKey, 4, (oldValue, newValue) -> {
			newValue += oldValue;
			return newValue;
		});
		System.out.println(map);
		assertEquals(new Integer(4), replaced);
	}
}
