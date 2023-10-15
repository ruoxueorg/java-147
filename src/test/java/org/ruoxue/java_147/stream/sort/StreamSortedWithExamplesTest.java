package org.ruoxue.java_147.stream.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

public class StreamSortedWithExamplesTest {

	@Test
	public void sort() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithReverseOrder() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(Long.MAX_VALUE, 33L, -22L);
	}

	@Test
	public void sortWithComparator() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted((i1, i2) -> Long.compare(i1, i2))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithComparing() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(Comparator.comparing(Long::longValue))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithComparingLong() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(Comparator.comparingLong(l -> l))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE);
	}

	protected static Comparator<Long> valueComparator = new Comparator<Long>() {
		@Override
		public int compare(Long i1, Long i2) {
			return Long.compare(i1, i2);
		}
	};

	protected static Comparator<Long> lengthComparator = (i1, i2) -> Long.compare(String.valueOf(i1).length(),
			String.valueOf(i2).length());

	@Test
	public void sortWithMultipleConditions() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(valueComparator.thenComparing(lengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithNull() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L, null);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted((s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		}).collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(null, -22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithNullsFirst() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L, null);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(Comparator.nullsFirst(Comparator.comparing(s -> s)))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(null, -22L, 33L, Long.MAX_VALUE);
	}

	@Test
	public void sortWithNullsLast() {
		List<Long> list = Lists.newArrayList(Long.MAX_VALUE, -22L, 33L, null);
		System.out.println(list);

		ArrayList<Long> result = list.stream().sorted(Comparator.nullsLast(Comparator.comparing(s -> s)))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-22L, 33L, Long.MAX_VALUE, null);
	}
}
