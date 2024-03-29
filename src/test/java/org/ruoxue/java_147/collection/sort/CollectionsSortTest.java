package org.ruoxue.java_147.collection.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionsSortTest {

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithReverseOrder() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		Collections.sort(list, Comparator.reverseOrder());
		System.out.println(list);
		assertThat(list).containsExactly("Peach", "Orange", "Mango");
	}

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Peach", "Orange");
	}

	@Test
	public void sortWithComparing() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Peach", "Orange");
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
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		Collections.sort(list, nameComparator.thenComparing(lengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithNull() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange", null);
		System.out.println(list);

		Collections.sort(list, (s1, s2) -> {
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else if (s2 == null) {
				return 1;
			}
			return s1.compareTo(s2);
		});
		System.out.println(list);
		assertThat(list).containsExactly(null, "Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithNullsFirst() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange", null);
		System.out.println(list);

		Collections.sort(list, Comparator.nullsFirst(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly(null, "Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithNullsLast() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange", null);
		System.out.println(list);

		Collections.sort(list, Comparator.nullsLast(Comparator.comparing(s -> s)));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach", null);
	}
}
