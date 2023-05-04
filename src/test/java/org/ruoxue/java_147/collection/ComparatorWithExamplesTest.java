package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	public void comparing() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1, 1), new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1));
		System.out.println(list);

		List<Fruit> result = list.stream().sorted(Comparator.comparing(Fruit::getName)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Mango", result.get(0).getName());
		assertEquals("Orange", result.get(1).getName());
	}

	@Test
	public void comparingWithKeyComparator() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 1), new Fruit("Peach", 1.1, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		List<Fruit> result = list.stream().sorted(Comparator.comparing(Fruit::getQuantity, (o, o2) -> o.compareTo(o2)))
				.collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Peach", result.get(0).getName());
		assertEquals("Mango", result.get(1).getName());
	}

	@Test
	public void thenComparing() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 1), new Fruit("Peach", 1.1, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		List<Fruit> result = list.stream()
				.sorted(Comparator.comparing(Fruit::getName).thenComparing(Fruit::getQuantity))
				.collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Mango", result.get(0).getName());
		assertEquals("Orange", result.get(1).getName());
	}

	@Test
	public void comparingInt() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 2), new Fruit("Peach", 1.1, 3),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		List<Fruit> result = list.stream().sorted(Comparator.comparingInt(Fruit::getType)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Orange", result.get(0).getName());
		assertEquals("Mango", result.get(1).getName());
	}

	@Test
	public void comparingLong() {
		List<Long> list = Arrays.asList(3L, 1L, 5L, 9L, 7L);
		System.out.println(list);

		List<Long> result = list.stream().sorted(Comparator.comparingLong(e -> e)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(1L, result.get(0).longValue());
		assertEquals(3L, result.get(1).longValue());
	}

	@Test
	public void comparingDouble() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 2), new Fruit("Peach", 1.1, 3),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		List<Fruit> result = list.stream().sorted(Comparator.comparingDouble(Fruit::getQuantity))
				.collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Peach", result.get(0).getName());
		assertEquals("Mango", result.get(1).getName());
	}

	@Test
	public void nullsFirst() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1, 1), new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1),
				null);
		System.out.println(list);

		Comparator<Fruit> nameComparator = Comparator.comparing(Fruit::getName);
		List<Fruit> result = list.stream().sorted(Comparator.nullsFirst(nameComparator)).collect(Collectors.toList());
		System.out.println(result);
		assertNull(result.get(0));
		assertEquals("Mango", result.get(1).getName());
	}

	@Test
	public void nullsLast() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1, 1), new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1),
				null);
		System.out.println(list);

		Comparator<Fruit> nameComparator = Comparator.comparing(Fruit::getName);
		List<Fruit> result = list.stream().sorted(Comparator.nullsLast(nameComparator)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Mango", result.get(0).getName());
		assertNull(result.get(list.size() - 1));
	}

	@Test
	public void naturalOrder() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		List<String> result = list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Mango", result.get(0));
		assertEquals("Orange", result.get(1));
	}

	@Test
	public void reverseOrder() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		List<String> result = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(result);
		assertEquals("Peach", result.get(0));
		assertEquals("Orange", result.get(1));
	}
}
