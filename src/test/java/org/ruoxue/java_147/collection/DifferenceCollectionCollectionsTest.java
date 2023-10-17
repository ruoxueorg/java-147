package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import com.google.common.collect.Lists;

public class DifferenceCollectionCollectionsTest {

	@Test
	public void addAll() {
		int expectedSize = 3;
		Collection<String> collection = Lists.newArrayList("Apple", "Banana", "Cherry");
		Collection<String> result = new ArrayList<>();
		result.addAll(collection);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Collections.addAll(result, "Mango", "Orange", "Peach");
		System.out.println(result);
		assertEquals(6, result.size());
	}

	@Test
	public void addAllWithInteger() {
		int expectedSize = 3;
		Collection<Integer> collection = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		Collection<Integer> result = new ArrayList<>();
		result.addAll(collection);
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
		Collection<String> collection = Lists.newArrayList("Apple", "Banana", "Cherry");
		String result = Collections.min(collection);
		System.out.println(result);
		assertThat(result).isEqualTo("Apple");

		Collection<Integer> intCollection = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		Integer intResult = Collections.min(intCollection);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(-1);
	}

	@Test
	public void max() {
		Collection<String> collection = Lists.newArrayList("Apple", "Banana", "Cherry");
		String result = Collections.max(collection);
		System.out.println(result);
		assertThat(result).isEqualTo("Cherry");

		Collection<Integer> intCollection = Lists.newArrayList(Integer.MAX_VALUE, -1, 3);
		Integer intResult = Collections.max(intCollection);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void replaceAll() {
		List<String> list = Lists.newArrayList("Apple", "Banana", "Cherry", "Apple");
		System.out.println(list);
		boolean result = Collections.replaceAll(list, "Apple", "Mango");
		System.out.println(list);
		System.out.println(result);
		assertThat(result).isTrue();

		List<Integer> intList = Lists.newArrayList(Integer.MAX_VALUE, -1, 3, Integer.MAX_VALUE);
		System.out.println(intList);
		boolean intResult = Collections.replaceAll(intList, Integer.MAX_VALUE, Integer.MIN_VALUE);
		System.out.println(intList);
		System.out.println(intResult);
		assertThat(intResult).isTrue();
	}

	@Test
	public void enumeration() {
		Collection<String> collection = Lists.newArrayList("Apple", "Banana", "Cherry");
		Iterator<String> it = collection.iterator();
		while (it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}

		Enumeration<String> en = Collections.enumeration(collection);
		while (en.hasMoreElements()) {
			String e = en.nextElement();
			System.out.println(e);
		}
	}

	@Test
	public void list() {
		int expectedSize = 3;
		Vector<String> vector = new Vector<>();
		vector.add("Apple");
		vector.add("Banana");
		vector.add("Cherry");
		List<String> result = Collections.list(vector.elements());
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Vector<Integer> intVector = new Vector<>();
		intVector.add(Integer.MAX_VALUE);
		intVector.add(-1);
		intVector.add(3);
		List<Integer> intResult = Collections.list(intVector.elements());
		System.out.println(intResult);
		assertEquals(expectedSize, intResult.size());
	}
}
