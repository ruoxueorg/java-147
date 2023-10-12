package org.ruoxue.java_147.set.linkedhashset;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LinkedHashSetWithExamplesTest {

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
	public void forEach() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		set.forEach(System.out::println);
	}

	@Test
	public void forEachRemaining() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
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
	public void iterator() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		Iterator<Fruit> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	@Test
	public void spliterator() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		Spliterator<Fruit> sit = set.spliterator();
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = set.spliterator();
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}

	@Test
	public void trySplit() {
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));
		Spliterator<Fruit> sit = set.spliterator();
		Spliterator<Fruit> sit2 = sit.trySplit();
		System.out.println(sit.getExactSizeIfKnown());
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		System.out.println(sit2.getExactSizeIfKnown());
		sit2.forEachRemaining(e -> System.out.println(e));
	}

	@Test
	public void toArray() {
		int expectedSize = 3;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Fruit[] array = set.toArray(new Fruit[0]);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}

	@Test
	public void streamToArray() {
		int expectedSize = 3;
		Set<Fruit> set = new LinkedHashSet<>();
		set.add(new Fruit("Longan", 1, 1));
		set.add(new Fruit("Tomato", 2, 1));
		set.add(new Fruit("Pear", 3, 1));

		Fruit[] array = set.stream().toArray(Fruit[]::new);
		System.out.println(Arrays.toString(array));
		assertEquals(expectedSize, array.length);
	}
}
