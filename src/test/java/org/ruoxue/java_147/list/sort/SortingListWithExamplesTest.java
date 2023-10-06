package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SortingListWithExamplesTest {

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
	}

	protected static Comparator<Fruit> nameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	protected static Comparator<Fruit> quantityComparator = (o1, o2) -> Double.compare(o1.quantity, o2.quantity);

	@Test
	public void Collections_sort() {
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(peach, mango, orange);
	}

	@Test
	public void Collections_sort_useThenComparing() {
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		Collections.sort(list, nameComparator.thenComparing(quantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(mango, orange, peach);
	}

	@Test
	public void List_sort() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(apple, cherry, banana));
		System.out.println(list);

		list.sort(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(cherry, apple, banana);
	}

	@Test
	public void List_sort_useThenComparing() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(apple, cherry, banana));
		System.out.println(list);

		list.sort(nameComparator.thenComparing(quantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);
	}

	@Test
	public void Stream_sorted() {
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		ArrayList<Fruit> result = list.stream()
				.sorted(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(peach, mango, orange);
	}

	@Test
	public void Stream_sorted_useThenComparing() {
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted(nameComparator.thenComparing(quantityComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(mango, orange, peach);
	}
}
