package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class ListSortWithExamplesTest {

	@Test
	public void sort() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		list.sort(Comparator.naturalOrder());
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithReverseOrder() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly(Integer.MAX_VALUE, 3, -1);
	}

	@Test
	public void sortWithComparator() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		list.sort((i1, i2) -> Integer.compare(i1, i2));
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortUseComparing() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		list.sort(Comparator.comparing(Integer::intValue));
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortUseComparingInt() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		list.sort(Comparator.comparingInt(i -> i));
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
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
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, 3, -1);
		System.out.println(list);

		list.sort(valueComparator.thenComparing(lengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1, 3, Integer.MAX_VALUE);
	}
}
