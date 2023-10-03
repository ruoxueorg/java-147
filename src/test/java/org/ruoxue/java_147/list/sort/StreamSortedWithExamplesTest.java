package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamSortedWithExamplesTest {

	@Test
	public void sort() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithReverseOrder() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(Integer.MAX_VALUE, 3, -1);
	}

	@Test
	public void sortWithComparator() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted((i1, i2) -> Integer.compare(i1, i2))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithComparing() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted(Comparator.comparing(Integer::intValue))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithComparingInt() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted(Comparator.comparingInt(i -> i))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1, 3, Integer.MAX_VALUE);
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
	public void sortWithThenComparing() {
		List<Integer> list = Arrays.asList(Integer.MAX_VALUE, 3, -1);
		System.out.println(list);

		ArrayList<Integer> result = list.stream().sorted(valueComparator.thenComparing(lengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1, 3, Integer.MAX_VALUE);
	}
}
