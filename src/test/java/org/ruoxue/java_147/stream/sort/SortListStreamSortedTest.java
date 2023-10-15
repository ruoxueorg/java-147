package org.ruoxue.java_147.stream.sort;

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

public class SortListStreamSortedTest {

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
		Fruit lichee = new Fruit("Lichee", -1, 3);
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		List<Fruit> list = Arrays.asList(lichee, coconut, plum);
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(coconut, lichee, plum);
	}

	@Test
	public void sortWithReverseOrder() {
		Fruit lichee = new Fruit("Lichee", -1, 3);
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		List<Fruit> list = Arrays.asList(lichee, coconut, plum);
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(plum, lichee, coconut);
	}

	@Test
	public void sortWithComparator() {
		Fruit lichee = new Fruit("Lichee", -1, 3);
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		List<Fruit> list = Arrays.asList(lichee, coconut, plum);
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted((o1, o2) -> Double.compare(o1.quantity, o2.quantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(lichee, plum, coconut);
	}

	@Test
	public void sortWithComparing() {
		Fruit lichee = new Fruit("Lichee", -1, 3);
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		List<Fruit> list = Arrays.asList(lichee, coconut, plum);
		System.out.println(list);

		ArrayList<Fruit> result = list.stream()
				.sorted(Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(plum, coconut, lichee);
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
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		Fruit lichee = new Fruit("Lichee", -1, 3);
		List<Fruit> list = Arrays.asList(coconut, plum, lichee);
		System.out.println(list);

		ArrayList<Fruit> result = list.stream().sorted(nameComparator.thenComparing(quantityComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(coconut, lichee, plum);
	}

	@Test
	public void sortWithNull() {
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		Fruit lichee = new Fruit("Lichee", -1, 3);
		List<Fruit> list = Arrays.asList(coconut, plum, lichee, null);
		System.out.println(list);

		Collections.sort(list, (s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		});
		System.out.println(list);
		assertThat(list).containsExactly(null, coconut, lichee, plum);
	}

	@Test
	public void sortWithNullsFirst() {
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		Fruit lichee = new Fruit("Lichee", -1, 3);
		List<Fruit> list = Arrays.asList(coconut, plum, lichee, null);
		System.out.println(list);

		Collections.sort(list, Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(null, coconut, lichee, plum);
	}

	@Test
	public void sortWithNullsLast() {
		Fruit coconut = new Fruit("Coconut", Double.MAX_VALUE, 1);
		Fruit plum = new Fruit("Plum", 3, 1);
		Fruit lichee = new Fruit("Lichee", -1, 3);
		List<Fruit> list = Arrays.asList(coconut, plum, lichee, null);
		System.out.println(list);

		Collections.sort(list, Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(coconut, lichee, plum, null);
	}
}
