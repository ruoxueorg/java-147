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

	protected static Comparator<Fruit> nameComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return o1.name.compareTo(o2.name);
		}
	};

	protected static Comparator<Fruit> quantityComparator = (o1, o2) -> Double.compare(o1.quantity, o2.quantity);

	@Test
	public void Collections_sort() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		Collections.sort(list, (i1, i2) -> Integer.compare(i1, i2));
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void Collections_sort_Object() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(banana, apple, cherry));
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(cherry, apple, banana);
	}

	@Test
	public void List_sort() {
		List<String> list = new ArrayList<String>();
		list.add("Banana");
		list.add("Apple");
		list.add("Cherry");
		System.out.println(list);
		list.sort(Comparator.naturalOrder());
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly("Cherry", "Banana", "Apple");
	}

	@Test
	public void List_sort_Object() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(banana, apple, cherry));
		System.out.println(list);
		list.sort(nameComparator);
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);

		Fruit blueberry = new Fruit("BLUEBERRY", 4, 2);
		list.add(blueberry);
		list.sort((o1, o2) -> 0 - o1.name.compareTo(o2.name));
		System.out.println(list);
		assertThat(list).containsExactly(cherry, banana, blueberry, apple);
	}

	@Test
	public void Stream_sorted() {
		List<String> list = new ArrayList<String>();
		list.add("Banana");
		list.add("Apple");
		list.add("Cherry");
		System.out.println(list);
		ArrayList<String> sortedList = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(sortedList);
		assertThat(sortedList).containsExactly("Apple", "Banana", "Cherry");

		sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
		System.out.println(sortedList);
		assertThat(sortedList).containsExactly("Cherry", "Banana", "Apple");
	}

	@Test
	public void Stream_sorted_Object() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>(Arrays.asList(banana, apple, cherry));
		System.out.println(list);
		ArrayList<Fruit> sortedList = list.stream().sorted(Comparator.comparing(Fruit::getName))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(sortedList);
		assertThat(sortedList).containsExactly(apple, banana, cherry);

		sortedList = list.stream().sorted(quantityComparator).collect(Collectors.toCollection(ArrayList::new));
		System.out.println(sortedList);
		assertThat(sortedList).containsExactly(banana, cherry, apple);

		Fruit blueberry = new Fruit("BLUEBERRY", 4, 2);
		list.add(blueberry);
		sortedList = list.stream().sorted((o1, o2) -> 0 - Double.compare(o1.quantity, o2.quantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(sortedList);
		assertThat(sortedList).containsExactly(apple, blueberry, cherry, banana);
	}
}
