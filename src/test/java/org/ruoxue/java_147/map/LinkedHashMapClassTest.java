package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
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

public class LinkedHashMapClassTest {

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
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		boolean containsKey = map.containsKey("Lemon");
		System.out.println(containsKey);
		assertTrue(containsKey);
	}

	@Test
	public void containsValue() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		boolean containsValue = map.containsValue(3);
		System.out.println(containsValue);
		assertTrue(containsValue);
	}

	@Test
	public void stream() {
		int expectedSize = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Set<String> set = map.keySet().stream().filter(e -> e.length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.keySet().parallelStream().forEach(System.out::println);
		System.out.println("----------");
		map.keySet().parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void replace() {
		Integer expected = 1;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer replaced = map.replace("Grape", 10);
		System.out.println(map);
		assertEquals(expected, replaced);
		boolean repl = map.replace("Grape", 10, 1);
		System.out.println(map);
		assertEquals(true, repl);
	}

	@Test
	public void replaceAll() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.replaceAll((k, v) -> {
			v = v * 10;
			return v;
		});
		System.out.println(map);
	}

	@Test
	public void merge() {
		Integer expected = 11;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Integer replaced = map.merge("Grape", 10, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(expected, replaced);

		replaced = map.merge("Papaya", 4, (oldValue, newValue) -> oldValue + newValue);
		System.out.println(map);
		assertEquals(4, replaced.intValue());
	}
}
