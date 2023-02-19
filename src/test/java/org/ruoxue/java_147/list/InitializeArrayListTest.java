package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class InitializeArrayListTest {

	@Test
	public void add() {
		int expectedSize = 3;
		ArrayList<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void extend() {
		int expectedSize = 3;
		ArrayList<String> list = new ArrayList<String>() {

			private static final long serialVersionUID = -5948701413773974786L;

			{
				add("Apple");
				add("Banana");
				add("Cherry");
			}
		};
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void addAll() {
		int expectedSize = 3;
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("Apple", "Banana", "Cherry"));
		ArrayList<String> list2 = new ArrayList<String>();
		list2.addAll(list);
		System.out.println(list2);
		assertEquals(expectedSize, list2.size());
	}

	@Test
	public void asList() {
		int expectedSize = 3;
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("Apple", "Banana", "Cherry"));
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void constructor() {
		int expectedSize = 3;
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("Apple", "Banana", "Cherry"));
		ArrayList<String> list2 = new ArrayList<String>(list);
		System.out.println(list2);
		assertEquals(expectedSize, list2.size());
	}

	@Test
	public void of() {
		int expectedSize = 3;
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeThrowException() {
		int expectedSize = 3;
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		System.out.println(list);
		assertEquals(expectedSize, list.size());
		list.remove(0);
	}
}
