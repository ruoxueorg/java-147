package org.ruoxue.java_147.map;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InitializeLinkedHashMapTest {

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
	public void doubleBrace() {
		int expectedSize = 3;
		Map<String, Fruit> map = new LinkedHashMap<String, Fruit>() {
			private static final long serialVersionUID = -1234223135233714632L;
			{
				put("Grape", new Fruit("Grape", 1, 1));
				put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
				put("Lemon", new Fruit("Lemon", 3, 1));
			}
		};
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 3;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		Map<String, Fruit> newMap = new LinkedHashMap<>();
		newMap.putAll(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void constructor() {
		int expectedSize = 3;
		Map<String, Fruit> map = new LinkedHashMap<>();
		map.put("Grape", new Fruit("Grape", 1, 1));
		map.put("Kiwifruit", new Fruit("Kiwifruit", 2, 1));
		map.put("Lemon", new Fruit("Lemon", 3, 1));
		Map<String, Fruit> newMap = new LinkedHashMap<>(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void immutableMap() {
		int expectedSize = 3;
		Map<String, Fruit> map = ImmutableMap.of("Grape", new Fruit("Grape", 1, 1), "Kiwifruit",
				new Fruit("Kiwifruit", 2, 1), "Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeThrowException() {
		int expectedSize = 3;
		Map<String, Fruit> map = ImmutableMap.of("Grape", new Fruit("Grape", 1, 1), "Kiwifruit",
				new Fruit("Kiwifruit", 2, 1), "Lemon", new Fruit("Lemon", 3, 1));
		System.out.println(map);
		assertEquals(expectedSize, map.size());
		map.remove("Grape");
	}
}
