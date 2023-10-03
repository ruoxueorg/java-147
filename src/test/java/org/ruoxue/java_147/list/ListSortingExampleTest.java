package org.ruoxue.java_147.list;

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

public class ListSortingExampleTest {

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

	protected static Comparator<String> stringNameComparator = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};

	protected static Comparator<String> stringLengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());

	@Test
	public void Collections_sort() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Peach", "Orange");

		Collections.sort(list, stringNameComparator.thenComparing(stringLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	protected static Comparator<Double> doubleValueComparator = new Comparator<Double>() {
		@Override
		public int compare(Double i1, Double i2) {
			return Double.compare(i1, i2);
		}
	};

	protected static Comparator<Double> doubleLengthComparator = (i1, i2) -> Double.compare(String.valueOf(i1).length(),
			String.valueOf(i2).length());

	@Test
	public void Collections_sort_Double() {
		List<Double> list = Arrays.asList(1d, 3d, -1d);
		System.out.println(list);

		Collections.sort(list, Comparator.comparingDouble(d -> d));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);

		Collections.sort(list, doubleValueComparator.thenComparing(doubleLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);
	}

	protected static Comparator<Fruit> objectNameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	protected static Comparator<Fruit> objectQuantityComparator = (o1, o2) -> Double.compare(o1.quantity, o2.quantity);

	@Test
	public void Collections_sort_Object() {
		Fruit mango = new Fruit("Mango", 1, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(mango, peach, orange));
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(mango, peach, orange);

		Collections.sort(list, objectNameComparator.thenComparing(objectQuantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(mango, orange, peach);
	}

	@Test
	public void List_sort() {
		List<String> list = Arrays.asList("Apple", "Cherry", "Banana");
		System.out.println(list);

		list.sort(Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Cherry", "Banana");

		list.sort(stringNameComparator.thenComparing(stringLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void List_sort_Double() {
		List<Double> list = Arrays.asList(1d, 3d, -1d);
		System.out.println(list);

		list.sort(Comparator.comparingDouble(d -> d));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);

		list.sort(doubleValueComparator.thenComparing(doubleLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 1d, 3d);
	}

	@Test
	public void List_sort_Object() {
		Fruit apple = new Fruit("Apple", 1, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(apple, cherry, banana));
		System.out.println(list);

		list.sort(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(apple, cherry, banana);

		list.sort(objectNameComparator.thenComparing(objectQuantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);
	}

	@Test
	public void Stream_sorted() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.comparing(String::length))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Peach", "Orange");

		result = list.stream().sorted(stringNameComparator.thenComparing(stringLengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void Stream_sorted_Double() {
		List<Double> list = Arrays.asList(1d, 3d, -1d);
		System.out.println(list);

		ArrayList<Double> result = list.stream().sorted(Comparator.comparingDouble(d -> d))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1d, 1d, 3d);

		result = list.stream().sorted(doubleValueComparator.thenComparing(doubleLengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1d, 1d, 3d);
	}

	@Test
	public void Stream_sorted_Object() {
		Fruit apple = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		List<Fruit> list = new ArrayList<>(Arrays.asList(apple, peach, orange));
		System.out.println(list);

		ArrayList<Fruit> result = list.stream()
				.sorted(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(peach, apple, orange);

		result = list.stream().sorted(objectNameComparator.thenComparing(objectQuantityComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(apple, orange, peach);
	}
}
