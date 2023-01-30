package org.ruoxue.java_147.list;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayListClassTest {

	public ArrayListClassTest() {

	}

	@Test
	public void retainAll() {
		int expectedSize = 1;
		List<String> list = new ArrayList<String>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");

		List<String> list2 = new ArrayList<String>();
		list2.add("Apple");
		list2.add("Lemon");
		list2.add("Mango");

		list.retainAll(list2);
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
