package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ListSortWithExamplesTest {

	@Test
	public void sort() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithReverseOrder() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly(Double.MAX_VALUE, 0d, -1d);
	}

	@Test
	public void sortWithComparator() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort((i1, i2) -> Double.compare(i1, i2));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithComparing() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort(Comparator.comparing(Double::intValue));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithComparingInt() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort(Comparator.comparingDouble(d -> d));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE);
	}

	protected static Comparator<Double> valueComparator = new Comparator<Double>() {
		@Override
		public int compare(Double i1, Double i2) {
			return Double.compare(i1, i2);
		}
	};

	protected static Comparator<Double> lengthComparator = (i1, i2) -> Double.compare(String.valueOf(i1).length(),
			String.valueOf(i2).length());

	@Test
	public void sortWithMultipleConditions() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d);
		System.out.println(list);

		list.sort(valueComparator.thenComparing(lengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithNull() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d, null);
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
		assertThat(list).containsExactly(null, -1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithNullsFirst() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d, null);
		System.out.println(list);

		list.sort(Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(null, -1d, 0d, Double.MAX_VALUE);
	}

	@Test
	public void sortWithNullsLast() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 0d, null);
		System.out.println(list);

		list.sort(Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 0d, Double.MAX_VALUE, null);
	}
}
