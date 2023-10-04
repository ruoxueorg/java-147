package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CollectionsWithExamplesTest {

	@Test
	public void fill() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.fill(list, "Orange");
		System.out.println(list);
		assertThat(list).containsExactly("Orange", "Orange", "Orange");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.fill(intList, -1);
		System.out.println(intList);
		assertThat(intList).containsExactly(-1, -1, -1);
	}

	@Test
	public void replaceAll() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.replaceAll(list, "Peach", "Mango");
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Mango");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.replaceAll(intList, 3, Integer.MAX_VALUE);
		System.out.println(intList);
		assertThat(intList).containsExactly(Integer.MAX_VALUE, -1, Integer.MAX_VALUE);
	}

	@Test
	public void indexOfSubList() {
		int expected = 1;
		List<String> list = Arrays.asList("Mango", "Orange", "Peach");
		List<String> list2 = Arrays.asList("Orange", "Peach");
		int index = Collections.indexOfSubList(list, list2);
		System.out.println(index);
		assertThat(index).isEqualTo(expected);

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		List<Integer> intList2 = Arrays.asList(-1, 3);
		int intIndex = Collections.indexOfSubList(intList, intList2);
		System.out.println(intIndex);
		assertThat(intIndex).isEqualTo(expected);
	}

	@Test
	public void lastIndexOfSubList() {
		int expected = 0;
		List<String> list = Arrays.asList("Mango", "Orange", "Peach");
		List<String> list2 = Arrays.asList("Mango", "Orange");
		int index = Collections.lastIndexOfSubList(list, list2);
		System.out.println(index);
		assertThat(index).isEqualTo(expected);

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		List<Integer> intList2 = Arrays.asList(Integer.MAX_VALUE, -1);
		int intIndex = Collections.lastIndexOfSubList(intList, intList2);
		System.out.println(intIndex);
		assertThat(intIndex).isEqualTo(expected);
	}

	@Test
	public void nCopies() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Mango", "Orange", "Peach");
		System.out.println(list);
		List<List<String>> result = Collections.nCopies(2, list);
		System.out.println(result);
		assertThat(result).hasSize(expectedSize);

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(intList);
		List<List<Integer>> intResult = Collections.nCopies(2, intList);
		System.out.println(intResult);
		assertThat(intResult).hasSize(expectedSize);
	}

	@Test
	public void rotate() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach"));
		System.out.println(list);
		Collections.rotate(list, 2);
		System.out.println(list);
		assertThat(list).containsExactly("Orange", "Peach", "Mango");

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3));
		System.out.println(intList);
		Collections.rotate(intList, 2);
		System.out.println(intList);
		assertThat(intList).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void frequency() {
		List<String> list = new ArrayList<>(Arrays.asList("Mango", "Orange", "Peach", "Orange"));
		int result = Collections.frequency(list, "Orange");
		System.out.println(result);
		assertThat(result).isEqualTo(2);

		List<Integer> intList = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, -1, 3, -1, 3));
		int intResult = Collections.frequency(intList, 3);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(2);
	}
}
