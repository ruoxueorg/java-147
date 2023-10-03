package org.ruoxue.java_147.array;

import static org.junit.Assert.*;

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

public class ArraysClassTest {

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
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		int result = Arrays.binarySearch(array, "Guava");
		System.out.println(result);
		assertEquals(expectedIndex, result);
	}

	@Test
	public void copyOf() {
		int expectedSize = 5;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		String[] array2 = null;
		array2 = Arrays.copyOf(array, 5);
		System.out.println(Arrays.toString(array2));
		assertEquals(expectedSize, array2.length);
	}

	@Test
	public void copyOfRange() {
		int expectedSize = 1;
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		String[] array2 = null;
		array2 = Arrays.copyOfRange(array, 1, 2);
		System.out.println(Arrays.toString(array2));
		assertEquals(expectedSize, array2.length);
	}

	@Test
	public void parallelSort() {
		String[] array = new String[] { "Durian", "Pitaya", "Guava" };
		System.out.println(Arrays.toString(array));

		Arrays.parallelSort(array);
		System.out.println(Arrays.toString(array));
		assertEquals("Durian", array[0]);
		assertEquals("Guava", array[1]);
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
		assertEquals("Durian", array[0].getName());
		assertEquals("Guava", array[1].getName());
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
		assertEquals("Durian", array[0].getName());
		assertEquals("Guava", array[1].getName());

		Arrays.parallelSort(array, (o1, o2) -> {
			return Double.compare(o2.quantity, o1.quantity);
		});
		System.out.println(Arrays.toString(array));
		assertEquals("Pitaya", array[0].getName());
		assertEquals("Guava", array[1].getName());
	}

	@Test
	public void toStringz() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
	}

	@Test
	public void deepToString() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Guava", 2, 1);
		Fruit guava = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		System.out.println(Arrays.deepToString(array));
	}
}
