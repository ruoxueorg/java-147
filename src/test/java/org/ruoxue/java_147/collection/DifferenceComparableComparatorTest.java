package org.ruoxue.java_147.collection;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DifferenceComparableComparatorTest {

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

		@Override
		public int compareTo(Fruit o) {
			return Double.compare(this.quantity, o.quantity);
		}
	}

	@Test
	public void compareTo() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Mango", -1, 3),
				new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertEquals(-1d, list.get(0).getQuantity(), 2);
		assertEquals(Double.MAX_VALUE, list.get(1).getQuantity(), 2);
	}

	@Test
	public void arrayCompareTo() {
		Fruit[] array = new Fruit[] { new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Mango", -1, 3),
				new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1) };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		assertEquals(-1d, array[0].getQuantity(), 2);
		assertEquals(Double.MAX_VALUE, array[1].getQuantity(), 2);
	}

	@Test
	public void compare() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Peach", -1d, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		Comparator<Fruit> quantityComparator = (o, o2) -> Double.compare(o.quantity, o2.quantity);
		Collections.sort(list, quantityComparator);
		System.out.println(list);
		assertEquals("Peach", list.get(0).getName());
		assertEquals("Orange", list.get(1).getName());
		assertEquals("Mango", list.get(2).getName());
	}

	@Test
	public void arrayCompare() {
		Fruit[] array = new Fruit[] { new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Peach", -1d, 1),
				new Fruit("Orange", 2, 1) };
		System.out.println(Arrays.toString(array));

		Comparator<Fruit> quantityComparator = (o, o2) -> Double.compare(o.quantity, o2.quantity);
		Arrays.sort(array, quantityComparator);
		System.out.println(Arrays.toString(array));
		assertEquals("Peach", array[0].getName());
		assertEquals("Orange", array[1].getName());
		assertEquals("Mango", array[2].getName());
	}
}
