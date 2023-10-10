package org.ruoxue.java_147.list.arraylist;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class InitializeArrayListTest {

	@Test
	public void add() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = new ArrayList<>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void doubleBrace() {
		int expectedSize = 3;
		List<String> list = new ArrayList<String>() {
			private static final long serialVersionUID = -5948701413773974786L;
			{
				add("Apple");
				add("Banana");
				add("Cherry");
			}
		};
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = new ArrayList<Integer>() {
			private static final long serialVersionUID = -1238701413773974786L;
			{
				add(1);
				add(2);
				add(3);
			}
		};
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
		List<String> result = new ArrayList<>();
		result.addAll(list);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Integer> intList = Arrays.asList(1, 2, 3);
		List<Integer> intResult = new ArrayList<>();
		intResult.addAll(intList);
		System.out.println(intResult);
		assertEquals(expectedSize, intResult.size());
	}

	@Test
	public void Arrays_asList() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
		assertThatCode(() -> list.add("Grape")).isInstanceOf(UnsupportedOperationException.class);

		List<Integer> intList = Arrays.asList(1, 2, 3);
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
		assertThatCode(() -> intList.add(Integer.MAX_VALUE)).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void Arrays_asList_withMutable() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
		System.out.println(list);
		assertEquals(expectedSize, list.size());
		list.add("Grape");
		System.out.println(list);
		assertEquals(4, list.size());

		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
		intList.add(Integer.MAX_VALUE);
		System.out.println(intList);
		assertEquals(4, intList.size());
	}
}
