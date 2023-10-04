package org.ruoxue.java_147.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArraysMethodsTest {

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
	public void asList() {
		int expectedSize = 3;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		List<String> list = Arrays.asList(array);
		System.out.println(list);
		assertThat(list).hasSize(expectedSize);

		Integer[] intArray = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		List<Integer> intList = Arrays.asList(intArray);
		System.out.println(intList);
		assertThat(list).hasSize(expectedSize);
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		int result = Arrays.binarySearch(array, "Guava");
		System.out.println(result);
		assertThat(result).isEqualTo(expectedIndex);

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		int intResult = Arrays.binarySearch(intArray, -1);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(expectedIndex);
	}

	@Test
	public void copyOf() {
		int expectedSize = 5;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		String[] array2 = null;
		array2 = Arrays.copyOf(array, 5);
		System.out.println(Arrays.toString(array2));
		assertThat(array2).hasSize(expectedSize);

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));

		int[] intArray2 = null;
		intArray2 = Arrays.copyOf(intArray, 5);
		System.out.println(Arrays.toString(intArray2));
		assertThat(intArray2).hasSize(expectedSize);
	}

	@Test
	public void copyOfRange() {
		int expectedSize = 1;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		String[] array2 = null;
		array2 = Arrays.copyOfRange(array, 1, 2);
		System.out.println(Arrays.toString(array2));
		assertThat(array2).hasSize(expectedSize);

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));

		int[] intArray2 = null;
		intArray2 = Arrays.copyOfRange(intArray, 1, 2);
		System.out.println(Arrays.toString(intArray2));
		assertThat(intArray2).hasSize(expectedSize);
	}

	@Test
	public void parallelSort() {
		int size = 1_000_000;
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = UUID.randomUUID().toString();
		}
		long beg = System.currentTimeMillis();
		Arrays.parallelSort(array);
		long end = System.currentTimeMillis();
		// parallelSort waste: 374 mills.
		System.out.println("parallelSort waste: " + (end - beg) + " mills.");

		beg = System.currentTimeMillis();
		Arrays.sort(array);
		end = System.currentTimeMillis();
		// sort waste: 34 mills.
		System.out.println("sort waste: " + (end - beg) + " mills.");
	}

	public static Comparator<Fruit> quantityComparator = new Comparator<Fruit>() {
		@Override
		public int compare(Fruit o1, Fruit o2) {
			return Double.compare(o1.quantity, o2.quantity);
		}
	};

	@Test
	public void parallelSortWithComparator() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", 2, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		System.out.println(Arrays.toString(array));

		Arrays.parallelSort(array, quantityComparator);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(durian, guava, pitaya);
	}

	@Test
	public void parallelSortWithLambda() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Pitaya", 3, 1);
		Fruit guava = new Fruit("Guava", 2, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		System.out.println(Arrays.toString(array));

		Arrays.parallelSort(array, (o1, o2) -> Double.compare(o1.quantity, o2.quantity));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(durian, guava, pitaya);

		Arrays.parallelSort(array, (o1, o2) -> {
			return Double.compare(o2.quantity, o1.quantity);
		});
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(pitaya, guava, durian);
	}
}
