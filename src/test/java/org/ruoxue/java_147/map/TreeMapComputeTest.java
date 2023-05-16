package org.ruoxue.java_147.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.TreeMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.map.TreeMapMethodsTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TreeMapComputeTest {

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
	public void compute() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		Fruit key = new Fruit("Grape", 1, 1);
		map.compute(key, (k, v) -> {
			v += 1;
			return v;
		});
		System.out.println(map);
		assertEquals(expected, map.get(key));
	}

	@Test
	public void computeCount() {
		String value = "Hello World, Java Learn";
		Map<String, Integer> map = new TreeMap<String, Integer>();
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
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		Fruit key = new Fruit("Mango", 4, 1);
		Integer result = map.computeIfAbsent(key, k -> 4);
		System.out.println(map);
		assertEquals(expected, result);
		key = new Fruit("Grape", 1, 1);
		result = map.computeIfAbsent(key, k -> 2);
		assertEquals(new Integer(1), result);
	}

	@Test
	public void computeIfPresent() {
		Integer expected = 2;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		Fruit key = new Fruit("Grape", 1, 1);
		Integer result = map.computeIfPresent(key, (k, v) -> {
			v += 1;
			return v;
		});
		System.out.println(map);
		assertEquals(expected, result);
		key = new Fruit("Mango", 4, 1);
		result = map.computeIfPresent(key, (k, v) -> {
			v = 4;
			return v;
		});
		assertNull(result);
	}
}
