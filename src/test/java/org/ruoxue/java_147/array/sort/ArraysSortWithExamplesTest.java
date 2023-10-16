package org.ruoxue.java_147.array.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ArraysSortWithExamplesTest {

	@Test
	public void sort() {
		int[] array = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithFromTo() {
		int[] array = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, 0, 2);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, Integer.MAX_VALUE, 3);
	}

	@Test
	public void sortWithReverseOrder() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.reverseOrder());
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(Integer.MAX_VALUE, 3, -1);
	}

	@Test
	public void sortWithComparator() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, (i1, i2) -> Integer.compare(i1, i2));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithComparing() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.comparing(Integer::intValue));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithComparingInt() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.comparingInt(i -> i));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	protected static Comparator<Integer> valueComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer i1, Integer i2) {
			return Integer.compare(i1, i2);
		}
	};

	protected static Comparator<Integer> lengthComparator = (i1, i2) -> Integer.compare(String.valueOf(i1).length(),
			String.valueOf(i2).length());

	@Test
	public void sortWithMultipleConditions() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, valueComparator.thenComparing(lengthComparator));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithNull() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3, null };
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
		assertThat(array).containsExactly(null, -1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithNullsFirst() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3, null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(null, -1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithNullsLast() {
		Integer[] array = new Integer[] { Integer.MAX_VALUE, -1, 3, null };
		System.out.println(Arrays.toString(array));

		Arrays.sort(array, Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(-1, 3, Integer.MAX_VALUE, null);
	}
}
