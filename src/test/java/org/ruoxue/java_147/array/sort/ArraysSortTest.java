package org.ruoxue.java_147.array.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ArraysSortTest {

	@Test
	public void sort() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "Guava", "Pitaya");
	}

	@Test
	public void sortWithReverseOrder() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.reverseOrder());
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Pitaya", "Guava", "Durian");
	}

	@Test
	public void sortWithComparator() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, (s1, s2) -> s1.length() - s2.length());
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Guava", "Durian", "Pitaya");
	}

	@Test
	public void sortWithComparing() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.comparing(String::length));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Guava", "Durian", "Pitaya");
	}

	protected static Comparator<String> nameComparator = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};

	protected static Comparator<String> lengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());

	@Test
	public void sortWithMultipleConditions() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, nameComparator.thenComparing(lengthComparator));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "Guava", "Pitaya");
	}

	@Test
	public void sortWithNull() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya", null };
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
		assertThat(array).containsExactly(null, "Durian", "Guava", "Pitaya");
	}

	@Test
	public void sortWithNullsFirst() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya", null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(null, "Durian", "Guava", "Pitaya");
	}

	@Test
	public void sortWithNullsLast() {
		String[] array = new String[] { "Guava", "Durian", "Pitaya", null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "Guava", "Pitaya", null);
	}
}
