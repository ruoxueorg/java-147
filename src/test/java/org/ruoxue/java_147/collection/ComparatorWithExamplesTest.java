package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ComparatorWithExamplesTest {

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
	public void comparingInt() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 2), new Fruit("Peach", 1.1, 3),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		list.sort(Comparator.comparingInt(Fruit::getType));
		System.out.println(list);
		assertEquals("Orange", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
		assertEquals("Peach", list.get(2).getName());
	}

	@Test
	public void comparingLong() {
		List<Long> list = Arrays.asList(3L, 1L, 5L, 9L, 7L);
		System.out.println(list);

		list.sort(Comparator.comparingLong(e -> e));
		System.out.println(list);
		assertEquals(1L, list.get(0).longValue());
		assertEquals(3L, list.get(1).longValue());
		assertEquals(5L, list.get(2).longValue());
	}

	@Test
	public void comparingDouble() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 2), new Fruit("Peach", 1.1, 3),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		list.sort(Comparator.comparingDouble(Fruit::getQuantity));
		System.out.println(list);
		assertEquals("Peach", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
		assertEquals("Orange", list.get(2).getName());
	}

	@Test
	public void naturalOrder() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		list.sort(Comparator.naturalOrder());
		System.out.println(list);
		assertEquals("Mango", list.get(0));
		assertEquals("Orange", list.get(1));
		assertEquals("Peach", list.get(2));
	}

	@Test
	public void reverseOrder() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertEquals("Peach", list.get(0));
		assertEquals("Orange", list.get(1));
		assertEquals("Mango", list.get(2));
	}
}
