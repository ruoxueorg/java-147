package org.ruoxue.java_147.map.linkedhashmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

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
		double expected = 2d;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		String key = "Grape";
		map.compute(key, (k, v) -> {
			v.setQuantity(v.getQuantity() + 1);
			return v;
		});
		System.out.println(map);
		assertEquals(expected, map.get(key).getQuantity(), 0);
	}

	@Test
	public void computeCount() {
		String value = "Hello World, Java Learn";
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
		double expected = 4d;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		String key = "Mango";
		Fruit result = map.computeIfAbsent(key, k -> new Fruit("Mango", 4, 1));
		System.out.println(map);
		assertEquals(expected, result.getQuantity(), 0);
		key = "Grape";
		result = map.computeIfAbsent(key, k -> new Fruit("Grape", 2, 1));
		assertEquals(1d, result.getQuantity(), 0);
	}

	@Test
	public void computeIfPresent() {
		double expected = 2d;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		String key = "Grape";
		Fruit result = map.computeIfPresent(key, (k, v) -> {
			v.setQuantity(v.getQuantity() + 1);
			return v;
		});
		System.out.println(map);
		assertEquals(expected, result.getQuantity(), 0);
		key = "Mango";
		result = map.computeIfPresent(key, (k, v) -> {
			v.setQuantity(4);
			return v;
		});
		assertNull(result);
	}
}
