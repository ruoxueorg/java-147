package org.ruoxue.java_147.array.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SortArrayArraysSortTest {

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
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { guava, durian, pitaya };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(durian, guava, pitaya);
	}

	@Test
	public void sortWithReverseOrder() {
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { guava, durian, pitaya };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.reverseOrder());
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(pitaya, guava, durian);
	}

	@Test
	public void sortWithComparator() {
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { guava, durian, pitaya };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, (o1, o2) -> Double.compare(o1.quantity, o2.quantity));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(guava, pitaya, durian);
	}

	@Test
	public void sortWithComparing() {
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { guava, durian, pitaya };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.comparing(Fruit::getType).thenComparing(Fruit::getQuantity));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(pitaya, durian, guava);
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
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit[] array = new Fruit[] { guava, durian, pitaya };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, nameComparator.thenComparing(quantityComparator));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(durian, guava, pitaya);
	}

	@Test
	public void sortWithNull() {
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit[] array = new Fruit[] { guava, durian, pitaya, null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, (s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		});
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(null, durian, guava, pitaya);
	}

	@Test
	public void sortWithNullsFirst() {
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit[] array = new Fruit[] { guava, durian, pitaya, null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(null, durian, guava, pitaya);
	}

	@Test
	public void sortWithNullsLast() {
		Fruit durian = new Fruit("Durian", Double.MAX_VALUE, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", -1, 3);
		Fruit[] array = new Fruit[] { guava, durian, pitaya, null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(durian, guava, pitaya, null);
	}
}
