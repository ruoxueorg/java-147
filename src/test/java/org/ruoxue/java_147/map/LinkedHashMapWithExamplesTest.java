package org.ruoxue.java_147.map;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedHashMap;
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

public class LinkedHashMapWithExamplesTest {
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
	public void entrySet() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + ", " + e.getValue());
		}
	}

	@Test
	public void forEach() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	public void keyForEach() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.keySet().forEach(e -> System.out.println(e));
	}

	@Test
	public void keyForEachRemaining() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
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
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void valueForEach() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.values().forEach(System.out::println);
	}

	@Test
	public void valueForEachRemaining() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
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
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		Iterator<Integer> it = map.values().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void keyToArray() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);

		String[] array = new String[map.size()];
		map.keySet().toArray(array);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void keyStreamToArray() {
		int expectedSize = 3;
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);

		String[] array = map.keySet().stream().toArray(String[]::new);
		for (String e : array) {
			System.out.println(e);
		}
		assertEquals(expectedSize, array.length);
	}
}
