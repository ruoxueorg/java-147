package org.ruoxue.java_147.map.treemap;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TreeMapWithExamplesTest {
	
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
	public void entrySet() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		for (Map.Entry<Fruit, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void forEach() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	public void keyForEach() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.keySet().forEach(e -> System.out.println(e));
	}

	@Test
	public void keyForEachRemaining() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Set<Fruit> set = map.keySet();
		Iterator<Fruit> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void keyIterator() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Iterator<Fruit> it = map.keySet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void valueForEach() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		map.values().forEach(System.out::println);
	}

	@Test
	public void valueForEachRemaining() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Collection<Integer> collection = map.values();
		Iterator<Integer> it = collection.iterator();
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (i == 1) {
				break;
			}
			i++;
		}
		System.out.println("----------");
		it.forEachRemaining(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void valueIterator() {
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);
		Iterator<Integer> it = map.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void keyToArray() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);

		Fruit[] array = new Fruit[map.size()];
		map.keySet().toArray(array);
		for (Fruit e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void keyStreamToArray() {
		int expectedSize = 3;
		Map<Fruit, Integer> map = new TreeMap<>();
		map.put(new Fruit("Grape", 1, 1), 1);
		map.put(new Fruit("Lemon", 3, 1), 3);
		map.put(new Fruit("Kiwifruit", 2, 1), 2);

		Fruit[] array = map.keySet().stream().toArray(Fruit[]::new);
		for (Fruit e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
