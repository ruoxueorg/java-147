package org.ruoxue.java_147.list;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionsSortTest {

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void sortWithReverseOrder() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		Collections.sort(list, Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly("Cherry", "Banana", "Apple");
	}

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void sortWithComparing() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(String::length));
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
	public void sortWithThenComparing() {
		List<String> list = Arrays.asList("Apple", "Cherry", "Banana");
		System.out.println(list);

		Collections.sort(list, nameComparator.thenComparing(lengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}
}
