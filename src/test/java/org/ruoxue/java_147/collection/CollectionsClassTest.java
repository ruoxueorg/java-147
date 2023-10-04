package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class CollectionsClassTest {

	@Test
	public void sort() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(intList);
		Collections.sort(intList);
		System.out.println(intList);
		assertThat(intList).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	protected static Comparator<String> nameComparator = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	protected static Comparator<Integer> valueComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer i1, Integer i2) {
			return Integer.compare(i1, i2);
		}
	};

	@Test
	public void sortWithComparator() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		Collections.sort(list, nameComparator);
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, 3, -1);
		System.out.println(intList);
		Collections.sort(intList, valueComparator);
		System.out.println(intList);
		assertThat(intList).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void sortWithLambda() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		System.out.println(list);
		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
		System.out.println(list);
		assertThat(list).containsExactly("Mango", "Orange", "Peach");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		System.out.println(intList);
		Collections.sort(intList, (i1, i2) -> Integer.compare(i1, i2));
		System.out.println(intList);
		assertThat(intList).containsExactly(-1, 3, Integer.MAX_VALUE);
	}

	@Test
	public void max() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		String result = Collections.max(list);
		System.out.println(result);
		assertThat(result).isEqualTo("Peach");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, 3, -1);
		int intResult = Collections.max(intList);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void min() {
		List<String> list = Arrays.asList("Orange", "Mango", "Peach");
		String result = Collections.min(list);
		System.out.println(result);
		assertThat(result).isEqualTo("Mango");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, 3, -1);
		int intResult = Collections.min(intList);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo(-1);
	}

	@Test
	public void singletonList() {
		List<String> list = Collections.singletonList("Orange");
		System.out.println(list);
		assertThat(list).hasSize(1);
		assertThatCode(() -> list.add("Mango")).isInstanceOf(UnsupportedOperationException.class);

		List<Integer> intList = Collections.singletonList(Integer.MAX_VALUE);
		System.out.println(intList);
		assertThat(intList).hasSize(1);
		assertThatCode(() -> intList.add(3)).isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void emptyList() {
		List<String> list = Collections.emptyList();
		System.out.println(list);
		assertThat(list).hasSize(0);
		assertThatCode(() -> list.add("Mango")).isInstanceOf(UnsupportedOperationException.class);

		List<Integer> intList = Collections.emptyList();
		System.out.println(intList);
		assertThat(intList).hasSize(0);
		assertThatCode(() -> intList.add(3)).isInstanceOf(UnsupportedOperationException.class);
	}
}
