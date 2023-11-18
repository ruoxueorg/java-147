package org.ruoxue.java_147.map.concurrenthashmap;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
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

public class ConcurrentHashMapClassTest {

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
	public void containsKey() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		boolean containsKey = map.containsKey("Lemon");
		System.out.println(containsKey);
		assertTrue(containsKey);
	}

	@Test
	public void containsValue() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		boolean containsValue = map.containsValue(new Fruit("Grape", -1, 1));
		System.out.println(containsValue);
		assertTrue(containsValue);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		Set<String> set = map.keySet().stream().filter(e -> e.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		map.keySet().parallelStream().forEach(System.out::println);
		System.out.println("----------");
		map.keySet().parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void replace() {
		double expected = 10d;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		map.replace("Grape", new Fruit("Grape", 10, 1));
		System.out.println(map);
		assertEquals(expected, map.get("Grape").getQuantity(), 0);
	}

	@Test
	public void replaceAll() {
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		map.replaceAll((k, v) -> {
			v.setQuantity(v.getQuantity() * 10);
			return v;
		});
		System.out.println(map);
	}

	@Test
	public void merge() {
		double expected = 9d;
		Map<String, Fruit> map = new ConcurrentHashMap<>();
		map.put("Grape", new Fruit("Grape", -1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", Double.MAX_VALUE, 2));
		map.put("Lemon", new Fruit("Lemon", 1, 3));
		Fruit replaced = map.merge("Grape", new Fruit("Grape", 10, 1), (oldValue, newValue) -> {
			newValue.setQuantity(oldValue.getQuantity() + newValue.getQuantity());
			return newValue;
		});
		System.out.println(map);
		assertEquals(expected, replaced.getQuantity(), 0);

		replaced = map.merge("Papaya", new Fruit("Papaya", 4, 1), (oldValue, newValue) -> {
			newValue.setQuantity(oldValue.getQuantity() + newValue.getQuantity());
			return newValue;
		});
		System.out.println(map);
		assertEquals(4d, replaced.getQuantity(), 0);
	}
}
