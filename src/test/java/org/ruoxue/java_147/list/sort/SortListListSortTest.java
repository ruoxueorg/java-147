package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SortListListSortTest {

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
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Arrays.asList(banana, apple, cherry);
		System.out.println(list);

		list.sort(Comparator.naturalOrder());
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);
	}

	@Test
	public void sortWithReverseOrder() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Arrays.asList(banana, apple, cherry);
		System.out.println(list);

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly(cherry, banana, apple);
	}

	@Test
	public void sortWithComparator() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Arrays.asList(banana, apple, cherry);
		System.out.println(list);

		list.sort((o1, o2) -> Double.compare(o1.quantity, o2.quantity));
		System.out.println(list);
		assertThat(list).containsExactly(banana, cherry, apple);
	}

	@Test
	public void sortWithComparing() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Arrays.asList(banana, apple, cherry);
		System.out.println(list);

		list.sort(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(list);
		assertThat(list).containsExactly(cherry, apple, banana);
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
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = Arrays.asList(apple, cherry, banana);
		System.out.println(list);

		list.sort(nameComparator.thenComparing(quantityComparator));
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);
	}

	@Test
	public void sortWithNull() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = Arrays.asList(apple, cherry, banana, null);
		System.out.println(list);

		list.sort((s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		});
		System.out.println(list);
		assertThat(list).containsExactly(null, apple, banana, cherry);
	}

	@Test
	public void sortWithNullsFirst() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = Arrays.asList(apple, cherry, banana, null);
		System.out.println(list);

		list.sort(Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(null, apple, banana, cherry);
	}

	@Test
	public void sortWithNullsLast() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		List<Fruit> list = Arrays.asList(apple, cherry, banana, null);
		System.out.println(list);

		list.sort(Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry, null);
	}
}
