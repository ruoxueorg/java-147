package org.ruoxue.java_147.set;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.set.LinkedHashSetMethodsTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LinkedHashSetClassTest {

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
	public void contains() {
		Set<Fruit> set = new LinkedHashSet<>();
		Fruit fruit = new Fruit("Longan", 1, 1);
		set.add(fruit);
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		boolean contains = set.contains(fruit);
		System.out.println(contains);
		assertTrue(contains);

		contains = set.contains(new Fruit("Grape", 4, 1));
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void containsAll() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Set<Fruit> set2 = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));

		boolean contains = set.containsAll(set2);
		System.out.println(contains);
		assertTrue(contains);

		contains = set2.containsAll(set);
		System.out.println(contains);
		assertFalse(contains);
	}

	@Test
	public void stream() {
		int expectedSize = 1;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		set = set.stream().filter(e -> e.getName().length() < 6).collect(Collectors.toSet());
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void parallelStream() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		set.parallelStream().forEach(System.out::println);
		System.out.println("----------");
		set.parallelStream().forEachOrdered(System.out::println);
	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Set<Fruit> set2 = new LinkedHashSet<>();
		set2.add(new Fruit("Longan", 1, 1));
		set2.add(new Fruit("Lemon", 4, 1));
		set2.add(new Fruit("Mango", 5, 1));

		set.retainAll(set2);
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}
}
