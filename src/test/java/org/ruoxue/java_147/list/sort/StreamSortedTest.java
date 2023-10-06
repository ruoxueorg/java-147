package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamSortedTest {

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Lichee", "Coconut", "Plum");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Coconut", "Lichee", "Plum");
	}

	@Test
	public void sortWithReverseOrder() {
		List<String> list = Arrays.asList("Lichee", "Coconut", "Plum");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Plum", "Lichee", "Coconut");
	}

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Lichee", "Coconut", "Plum");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted((s1, s2) -> s1.length() - s2.length())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Plum", "Lichee", "Coconut");
	}

	@Test
	public void sortWithComparing() {
		List<String> list = Arrays.asList("Lichee", "Coconut", "Plum");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.comparing(String::length))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Plum", "Lichee", "Coconut");
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
		List<String> list = Arrays.asList("Coconut", "Plum", "Lichee");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(nameComparator.thenComparing(lengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Coconut", "Lichee", "Plum");
	}

	@Test
	public void sortWithNull() {
		List<String> list = Arrays.asList("Coconut", "Plum", "Lichee", null);
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted((s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		}).collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(null, "Coconut", "Lichee", "Plum");
	}

	@Test
	public void sortWithNullsFirst() {
		List<String> list = Arrays.asList("Coconut", "Plum", "Lichee", null);
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.nullsFirst(Comparator.comparing(s -> s)))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(null, "Coconut", "Lichee", "Plum");
	}

	@Test
	public void sortWithNullsLast() {
		List<String> list = Arrays.asList("Coconut", "Plum", "Lichee", null);
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.nullsLast(Comparator.comparing(s -> s)))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Coconut", "Lichee", "Plum", null);
	}
}
