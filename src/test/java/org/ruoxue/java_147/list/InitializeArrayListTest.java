package org.ruoxue.java_147.list;

import static org.junit.Assert.*;
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
		List<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
		List<String> newList = new ArrayList<>();
		newList.addAll(list);
		System.out.println(newList);
		assertEquals(expectedSize, newList.size());

		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> newIntList = new ArrayList<>();
		newIntList.addAll(intList);
		System.out.println(newIntList);
		assertEquals(expectedSize, newIntList.size());
	}

	@Test
	public void asList() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}
}
