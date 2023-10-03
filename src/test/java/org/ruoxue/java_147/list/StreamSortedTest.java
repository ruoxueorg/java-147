package org.ruoxue.java_147.list;

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
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithReverseOrder() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Peach", "Orange", "Mango");
	}

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted((s1, s2) -> s1.length() - s2.length())
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Peach", "Orange");
	}

	@Test
	public void sortWithComparing() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.comparing(String::length))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Peach", "Orange");
	}

	protected static Comparator<String> nameComparator = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};

	protected static Comparator<String> lengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());

	@Test
	public void sortWithThenComparing() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(nameComparator.thenComparing(lengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Orange", "Peach");
	}
}
