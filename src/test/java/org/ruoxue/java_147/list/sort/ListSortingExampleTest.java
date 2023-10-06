package org.ruoxue.java_147.list.sort;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ListSortingExampleTest {

	protected static Comparator<String> stringNameComparator = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};

	protected static Comparator<String> stringLengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());

	@Test
	public void Collections_sort() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		Collections.sort(list, Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Peach", "Orange");

		Collections.sort(list, stringNameComparator.thenComparing(stringLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	protected static Comparator<Double> doubleValueComparator = new Comparator<Double>() {
		@Override
		public int compare(Double i1, Double i2) {
			return Double.compare(i1, i2);
		}
	};

	protected static Comparator<Double> doubleLengthComparator = (i1, i2) -> Double.compare(String.valueOf(i1).length(),
			String.valueOf(i2).length());

	@Test
	public void Collections_sort_useThenComparing() {
		List<Double> list = Arrays.asList(Double.MAX_VALUE, 3d, -1d);
		System.out.println(list);

		Collections.sort(list, Comparator.comparingDouble(d -> d));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);

		Collections.sort(list, doubleValueComparator.thenComparing(doubleLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);
	}

	@Test
	public void List_sort() {
		List<String> list = Arrays.asList("Apple", "Cherry", "Banana");
		System.out.println(list);

		list.sort(Comparator.comparing(String::length));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Cherry", "Banana");

		list.sort(stringNameComparator.thenComparing(stringLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");
	}

	@Test
	public void List_sort_useThenComparing() {
		List<Double> list = Arrays.asList(Double.MAX_VALUE, 3d, -1d);
		System.out.println(list);

		list.sort(Comparator.comparingDouble(d -> d));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);

		list.sort(doubleValueComparator.thenComparing(doubleLengthComparator));
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);
	}

	@Test
	public void Stream_sorted() {
		List<String> list = Arrays.asList("Mango", "Peach", "Orange");
		System.out.println(list);

		ArrayList<String> result = list.stream().sorted(Comparator.comparing(String::length))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Peach", "Orange");

		result = list.stream().sorted(stringNameComparator.thenComparing(stringLengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void Stream_sorted_useThenComparing() {
		List<Double> list = Arrays.asList(Double.MAX_VALUE, 3d, -1d);
		System.out.println(list);

		ArrayList<Double> result = list.stream().sorted(Comparator.comparingDouble(d -> d))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1d, 3d, Double.MAX_VALUE);

		result = list.stream().sorted(doubleValueComparator.thenComparing(doubleLengthComparator))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(result);
		assertThat(result).containsExactly(-1d, 3d, Double.MAX_VALUE);
	}
}
