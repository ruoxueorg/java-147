package org.ruoxue.java_147.comparator;

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

public class ComparableVSComparatorTest {

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
		assertEquals(Double.MAX_VALUE, list.get(3).getQuantity(), 2);
	}

	@Test
	public void quantityCompare() {
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

	public static Comparator<Fruit> nameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	@Test
	public void nameCompare() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Peach", -1d, 1),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list, nameComparator);
		System.out.println(list);
		assertEquals("Mango", list.get(0).getName());
		assertEquals("Orange", list.get(1).getName());
		assertEquals("Peach", list.get(2).getName());
	}

	@Test
	public void typeCompare() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", 1.2, 2), new Fruit("Peach", 1.1, 3),
				new Fruit("Orange", 2, 1));
		System.out.println(list);

		list.sort(Comparator.comparing(Fruit::getType));
		System.out.println(list);
		assertEquals("Orange", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
		assertEquals("Peach", list.get(2).getName());
	}
}
