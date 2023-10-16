package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class DifferenceCollectionCollectionsTest {

	@Test
	public void addAll() {
		int expectedSize = 3;
		List<String> list = Lists.newArrayList("Apple", "Banana", "Cherry");
		List<String> result = new ArrayList<>();
		result.addAll(list);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Collections.addAll(result, "Mango", "Orange", "Peach");
		System.out.println(result);
		assertEquals(6, result.size());
	}

	@Test
	public void addAllWithInteger() {
		int expectedSize = 3;
		List<Integer> list = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		List<Integer> result = new ArrayList<>();
		result.addAll(list);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Collections.addAll(result, 10, 20, 30);
		System.out.println(result);
		assertEquals(6, result.size());
	}

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Banana", "Apple", "Cherry");
		System.out.println(list);
		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly("Apple", "Banana", "Cherry");

		list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");
	}

	@Test
	public void sortWithDouble() {
		List<Double> list = Lists.newArrayList(Double.MAX_VALUE, -1d, 3d);
		System.out.println(list);
		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);

		list = Lists.newArrayList(Double.MAX_VALUE, -1d, 3d);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly(-1d, 3d, Double.MAX_VALUE);
	}

	@Test
	public void min() {
		List<String> list = Lists.newArrayList("Apple", "Banana", "Cherry");
		String result = Collections.min(list);
		System.out.println(result);
		assertThat(result).isEqualTo("Apple");

		List<Integer> intList = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		Integer intResult = Collections.min(intList);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(-1);
	}

	@Test
	public void max() {
		List<String> list = Lists.newArrayList("Apple", "Banana", "Cherry");
		String result = Collections.max(list);
		System.out.println(result);
		assertThat(result).isEqualTo("Cherry");

		List<Integer> intList = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		Integer intResult = Collections.max(intList);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(Integer.MAX_VALUE);
	}
}
