package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
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

public class ComparatorInterfaceTest {

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

	public static Comparator<Fruit> nameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	@Test
	public void compare() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1, 1), new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list, nameComparator);
		System.out.println(list);
		assertEquals("Mango", list.get(0).getName());
		assertEquals("Orange", list.get(1).getName());
	}

	@Test
	public void compareWithLambda() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1, 1), new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1));
		System.out.println(list);

		Comparator<Fruit> lengthComparator = (o, o2) -> Integer.compare(o.name.length(), o2.name.length());
		Collections.sort(list, lengthComparator);
		System.out.println(list);
		assertEquals("Mango", list.get(0).getName());
		assertEquals("Peach", list.get(1).getName());
	}

	@Test
	public void thenComparing() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 1), new Fruit("Peach", 1.1, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		Comparator<Fruit> lengthComparator = (o, o2) -> Integer.compare(o.name.length(), o2.name.length());
		Comparator<Fruit> quantityComparator = (o, o2) -> Double.compare(o.quantity, o2.quantity);
		Collections.sort(list, lengthComparator.thenComparing(quantityComparator));
		System.out.println(list);
		assertEquals("Peach", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
	}

	@Test
	public void thenComparingWithKey() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 1), new Fruit("Peach", 1.1, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		Comparator<Fruit> lengthComparator = (o, o2) -> Integer.compare(o.name.length(), o2.name.length());
		Collections.sort(list, lengthComparator.thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertEquals("Peach", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
	}

	@Test
	public void thenComparingWithKeyComparator() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 1), new Fruit("Peach", 1.1, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		Comparator<Fruit> lengthComparator = (o, o2) -> Integer.compare(o.name.length(), o2.name.length());
		Comparator<Double> doubleCmparator = (o, o2) -> o.compareTo(o2);
		Collections.sort(list, lengthComparator.thenComparing(Fruit::getQuantity, doubleCmparator));
		System.out.println(list);
		assertEquals("Peach", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
	}
}
