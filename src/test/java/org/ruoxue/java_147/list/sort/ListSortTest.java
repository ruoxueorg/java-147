package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class ListSortTest {

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void sortWithReverseOrder() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		list.sort(Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly("Cherry", "Banana", "Apple");
	}

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		list.sort((s1, s2) -> s1.length() - s2.length());
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void sortUseComparing() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		list.sort(Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
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
		List<String> list = Arrays.asList("Apple", "Cherry", "Banana");
		System.out.println(list);

		list.sort(nameComparator.thenComparing(lengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}
}
