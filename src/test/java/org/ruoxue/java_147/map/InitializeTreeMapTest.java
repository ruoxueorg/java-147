package org.ruoxue.java_147.map;

import static org.junit.Assert.*;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.collection.ComparatorInterfaceTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InitializeTreeMapTest {

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
	public void doubleBrace() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<Fruit, Integer>() {
			private static final long serialVersionUID = -1234223135233714632L;
			{
				put(new Fruit("Grape", 1, 1), 1);
				put(new Fruit("Lemon", 3, 1), 3);
				put(new Fruit("Kiwifruit", 2, 1), 2);
			}
		};
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void putAll() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Map<Fruit, Integer> newMap = new TreeMap<>();
		newMap.putAll(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void constructor() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Map<Fruit, Integer> newMap = new TreeMap<>(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}

	@Test
	public void comparator() {
		int expectedSize = 3;
		Comparator<Fruit> quantityComparator = (o, o2) -> Double.compare(o.quantity, o2.quantity);
		Map<Fruit, Integer> map = new TreeMap<>(quantityComparator);
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		System.out.println(map);
		assertEquals(expectedSize, map.size());
	}

	@Test
	public void sortedMap() {
		int expectedSize = 3;
		SortedMap<Fruit, Integer> map = new ConcurrentSkipListMap<Fruit, Integer>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Map<Fruit, Integer> newMap = new TreeMap<>(map);
		System.out.println(newMap);
		assertEquals(expectedSize, newMap.size());
	}
}
