package org.ruoxue.java_147.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedHashMap;
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

public class LinkedHashMapComputeTest {

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
	public void compute() {
		Integer expected = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void computeFunction() {
		Integer expected = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		map.compute(key, (k, v) -> {
			v += 1;
			return v;
		});
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test(expected = NullPointerException.class)
	public void computeNullPointerException() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = null;
		map.compute(key, (k, v) -> v + 1);
		System.out.println(map);
	}

	@Test
	public void computeCount() {
		String value = "Hello World, Tutorial Java 147";
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < value.length(); i++) {
			String key = String.valueOf(value.charAt(i));
			map.compute(key, (k, v) -> {
				v = (v == null ? 1 : v + 1);
				return v;
			});
		}
		System.out.println(map);
	}

	@Test
	public void computeIfAbsent() {
		Integer expected = 4;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Mango";
		Integer result = map.computeIfAbsent(key, k -> 4);
		System.out.println(map);
		assertEquals(expected, result);
		key = "Grape";
		result = map.computeIfAbsent(key, k -> 2);
		assertEquals(1, result.intValue());
	}

	@Test
	public void computeIfPresent() {
		Integer expected = 2;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		System.out.println(map);
		String key = "Grape";
		Integer result = map.computeIfPresent(key, (k, v) -> v + 1);
		System.out.println(map);
		assertEquals(expected, result);
		key = "Mango";
		result = map.computeIfPresent(key, (k, v) -> 4);
		assertNull(result);
	}
}
