package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CollectionsMethodsTest {

	@Test
	public void addAll() {
		int expectedSize = 6;
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.addAll(list, "Papaya", "Strawberry", "Watermelon");
		System.out.println(list);
		assertThat(list).hasSize(expectedSize);

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.addAll(intList, 10, 20, 30);
		System.out.println(intList);
		assertThat(intList).hasSize(expectedSize);
	}

	@Test
	public void binarySearch() {
		int expectedIndex = 1;
		List<String> list = Arrays.asList("Mango", "Orange", "Peach");
		int result = Collections.binarySearch(list, "Orange");
		System.out.println(result);
		assertThat(result).isEqualTo(expectedIndex);

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		result = Collections.binarySearch(intList, -1);
		System.out.println(result);
		assertThat(result).isEqualTo(expectedIndex);
	}

	@Test
	public void copy() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		List<String> list2 = new ArrayList<>(Arrays.asList("Papaya", "Strawberry"));
		Collections.copy(list, list2);
		System.out.println(list);
		assertThat(list).hasSize(expectedSize);
		assertThat(list).containsExactly("Papaya", "Strawberry", "Peach");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		List<Integer> intList2 = new ArrayList<>(Arrays.asList(10, 20));
		Collections.copy(intList, intList2);
		System.out.println(intList);
		assertThat(intList).hasSize(expectedSize);
		assertThat(intList).containsExactly(10, 20, 3);
	}

	@Test
	public void disjoint() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		List<String> list2 = new ArrayList<>(Arrays.asList("Papaya", "Strawberry"));
		boolean result = Collections.disjoint(list, list2);
		System.out.println(result);
		assertThat(result).isTrue();

		list2 = new ArrayList<>(Arrays.asList("Mango", "Strawberry"));
		result = Collections.disjoint(list, list2);
		System.out.println(result);
		assertThat(result).isFalse();

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		List<Integer> intList2 = new ArrayList<>(Arrays.asList(10, 20));
		result = Collections.disjoint(intList, intList2);
		System.out.println(result);
		assertThat(result).isTrue();

		intList2 = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, 20));
		result = Collections.disjoint(intList, intList2);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void shuffle() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.shuffle(list);
		System.out.println(list);
		assertThat(list).contains("Mango", "Orange", "Peach");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.shuffle(intList);
		System.out.println(intList);
		assertThat(intList).contains(Integer.MAX_VALUE, -1, 3);
	}

	@Test
	public void reverse() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.reverse(list);
		System.out.println(list);
		assertThat(list).containsExactly("Peach", "Orange", "Mango");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.reverse(intList);
		System.out.println(intList);
		assertThat(intList).containsExactly(3, -1, Integer.MAX_VALUE);
	}

	@Test
	public void swap() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.swap(list, 0, 2);
		System.out.println(list);
		assertThat(list).containsExactly("Peach", "Orange", "Mango");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.swap(intList, 0, 2);
		System.out.println(intList);
		assertThat(intList).containsExactly(3, -1, Integer.MAX_VALUE);
	}
}
