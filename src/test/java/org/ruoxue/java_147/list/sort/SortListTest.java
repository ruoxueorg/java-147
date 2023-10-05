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

public class SortListTest {

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

	@Test
	public void Collections_sort() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void Collections_sort_withComparator() {
		List<Double> list = Arrays.asList(1d, -1d, 3d);
		System.out.println(list);

		Collections.sort(list, (i1, i2) -> Double.compare(i1, i2));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);
	}

	@Test
	public void Collections_sort_useComparing() {
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", 1, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(orange, mango, peach));
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(orange, mango, peach);
	}

	@Test
	public void List_sort() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void List_sort_withComparator() {
		List<Double> list = Arrays.asList(1d, -1d, 3d);
		System.out.println(list);

		list.sort((i1, i2) -> Double.compare(i1, i2));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);
	}

	@Test
	public void List_sort_useComparing() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", 1, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(banana, apple, cherry));
		System.out.println(list);

		list.sort(Comparator.comparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(banana, apple, cherry);
	}

	@Test
	public void Stream_sorted() {
		List<String> list = Arrays.asList("Lichee", "Coconut", "Plum");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Coconut", "Lichee", "Plum");
	}

	@Test
	public void Stream_sorted_withComparator() {
		List<Double> list = Arrays.asList(1d, -1d, 3d);
		System.out.println(list);

		ArrayList<Double> result = list.stream().sorted((i1, i2) -> Double.compare(i1, i2))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1d, 1d, 3d);
	}

	@Test
	public void Stream_sorted_useComparing() {
		Fruit lichee = new Fruit("Lichee", -1, 3);
		Fruit coconut = new Fruit("Coconut", 1, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(lichee, coconut, plum));
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted(Comparator.comparing(Fruit::getQuantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(lichee, coconut, plum);
	}
}
