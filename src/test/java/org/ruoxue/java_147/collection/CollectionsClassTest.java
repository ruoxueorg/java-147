package org.ruoxue.java_147.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionsClassTest {

	public static Comparator<String> nameComparator = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	@Test
	public void sort() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
	}

	@Test
	public void sortWithComparator() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list, nameComparator);
		System.out.println(list);
	}

	@Test
	public void sortWithLambda() {
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Peach");
		list.add("Orange");
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
		System.out.println(list);

		Collections.sort(list, (o1, o2) -> {
			return o2.compareTo(o1);
		});
		System.out.println(list);
	}

	@Test
	public void max() {
		String expected = "Peach";
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		String value = Collections.max(list);
		System.out.println(value);
		assertEquals(expected, value);
	}

	@Test
	public void min() {
		String expected = "Mango";
		List<String> list = new ArrayList<String>();
		list.add("Mango");
		list.add("Orange");
		list.add("Peach");
		System.out.println(list);
		String value = Collections.min(list);
		System.out.println(value);
		assertEquals(expected, value);
	}
}
