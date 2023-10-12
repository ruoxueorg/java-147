package org.ruoxue.java_147.set.linkedhashset;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
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

public class LinkedHashSetMethodsTest {

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
	public void add() {
		int expectedSize = 3;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 6;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Set<Fruit> set2 = new LinkedHashSet<>();
		set.add(new Fruit("Grape", 4, 1));
		set.add(new Fruit("Lemon", 5, 1));
		set.add(new Fruit("Mango", 6, 1));

		set.addAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void remove() {
		int expectedSize = 2;
		Set<Fruit> set = new LinkedHashSet<>();
		Fruit fruit = new Fruit("Longan", 1, 1);
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		set.remove(fruit);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void removeAll() {
		int expectedSize = 1;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Set<Fruit> set2 = new LinkedHashSet<>();
		set2.add(new Fruit("Longan", 1, 1));
		set2.add(new Fruit("Tomato", 2, 1));
		set2.add(new Fruit("Mango", 4, 1));
		set.removeAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void clear() {
		int expectedSize = 0;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		set.clear();
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void size() {
		int expectedSize = 3;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		System.out.println(set.size());
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void isEmpty() {
		Set<Fruit> set = new LinkedHashSet<>();
		System.out.println(set.isEmpty());
		assertTrue(set.isEmpty());
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		System.out.println(set.isEmpty());
		assertFalse(set.isEmpty());
	}
}
