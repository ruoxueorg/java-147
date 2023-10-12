package org.ruoxue.java_147.set.treeset;

import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InitializeTreeSetTest {

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
	public void add() {
		int expectedSize = 3;
		Set<Fruit> set = new TreeSet<Fruit>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 3, 1));
		set.add(new Fruit("Pear", 2, 1));
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void doubleBrace() {
		int expectedSize = 3;
		Set<Fruit> set = new TreeSet<Fruit>() {
			private static final long serialVersionUID = -1234223135233714632L;
			{
				add(new Fruit("Longan", 1, 1));
				add(new Fruit("Tomato", 3, 1));
				add(new Fruit("Pear", 2, 1));
			}
		};
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 3;
		Set<Fruit> set = new TreeSet<Fruit>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 3, 1));
		set.add(new Fruit("Pear", 2, 1));
		Set<Fruit> newSet = new TreeSet<Fruit>();
		newSet.addAll(set);
		System.out.println(newSet);
		assertEquals(expectedSize, newSet.size());
	}

	@Test
	public void constructor() {
		int expectedSize = 3;
		Set<Fruit> set = new TreeSet<Fruit>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 3, 1));
		set.add(new Fruit("Pear", 2, 1));
		Set<Fruit> newSet = new TreeSet<Fruit>(set);
		System.out.println(newSet);
		assertEquals(expectedSize, newSet.size());
	}

	@Test
	public void comparator() {
		int expectedSize = 3;
		Comparator<Fruit> quantityComparator = (o, o2) -> Double.compare(o.quantity, o2.quantity);
		Set<Fruit> set = new TreeSet<Fruit>(quantityComparator);
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 3, 1));
		set.add(new Fruit("Pear", 2, 1));
		System.out.println(set);
		assertEquals(expectedSize, set.size());
	}

	@Test
	public void sortedSet() {
		int expectedSize = 3;
		SortedSet<Fruit> set = new ConcurrentSkipListSet<Fruit>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 3, 1));
		set.add(new Fruit("Pear", 2, 1));
		Set<Fruit> newSet = new TreeSet<>(set);
		System.out.println(newSet);
		assertEquals(expectedSize, newSet.size());
	}
}
