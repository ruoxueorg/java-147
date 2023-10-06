package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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

public class ListCollectionsSortTest {

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
			int result = this.name.compareTo(o.name);
			if (result == 0)
				result = Double.compare(this.quantity, o.quantity);
			return result;
		}
	}

	@Test
	public void sort() {
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(orange, mango, peach));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly(mango, orange, peach);
	}

	@Test
	public void sortWithReverseOrder() {
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(orange, mango, peach));
		System.out.println(list);

		Collections.sort(list, Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly(peach, orange, mango);
	}

	@Test
	public void sortWithComparator() {
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(orange, mango, peach));
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> Double.compare(o1.quantity, o2.quantity));
		System.out.println(list);
		assertThat(list).containsExactly(orange, peach, mango);
	}

	@Test
	public void sortWithComparing() {
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(orange, mango, peach));
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(peach, mango, orange);
	}

	protected static Comparator<Fruit> nameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	protected static Comparator<Fruit> quantityComparator = (o1, o2) -> Double.compare(o1.quantity, o2.quantity);

	@Test
	public void sortWithMultipleConditions() {
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		Collections.sort(list, nameComparator.thenComparing(quantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(mango, orange, peach);
	}
}
