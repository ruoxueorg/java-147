package org.ruoxue.java_147.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class ImmutableListTest {

	@Test
	public void Arrays_asList() {
		List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
		assertThatCode(() -> list.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(list);
		assertThat(list).hasSize(3);

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		assertThatCode(() -> intList.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intList);
		assertThat(intList).hasSize(3);
	}

	@Test
	public void Collections_unmodifiableList() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = Collections.unmodifiableList(list);
		assertThatCode(() -> result.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);

		List<Integer> intList = new ArrayList<>();
		intList.add(Integer.MAX_VALUE);
		intList.add(-1);
		intList.add(3);
		List<Integer> intResult = Collections.unmodifiableList(intList);
		assertThatCode(() -> intResult.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intResult);
		assertThat(result).hasSize(3);
	}

	@Test
	public void Collections_singletonList() {
		List<String> result = Collections.singletonList("Apple");
		assertThatCode(() -> result.add("Banana")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(1);

		List<Integer> intResult = Collections.singletonList(Integer.MAX_VALUE);
		assertThatCode(() -> intResult.add(-3)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intResult);
		assertThat(result).hasSize(1);
	}

	@Test
	public void ImmutableList_of() {
		List<String> list = ImmutableList.of("Apple", "Banana", "Cherry");
		assertThatCode(() -> list.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(list);
		assertThat(list).hasSize(3);

		List<Integer> intList = ImmutableList.of(Integer.MAX_VALUE, -1, 3);
		assertThatCode(() -> intList.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intList);
		assertThat(intList).hasSize(3);
	}

	@Test
	public void ImmutableList_copyOf() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = ImmutableList.copyOf(list);
		assertThatCode(() -> result.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);

		List<Integer> intList = new ArrayList<>();
		intList.add(Integer.MAX_VALUE);
		intList.add(-1);
		intList.add(3);
		List<Integer> intResult = ImmutableList.copyOf(intList);
		assertThatCode(() -> intResult.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intResult);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ImmutableList_builder() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = ImmutableList.<String>builder().addAll(list).build();
		assertThatCode(() -> result.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);

		List<Integer> intList = new ArrayList<>();
		intList.add(Integer.MAX_VALUE);
		intList.add(-1);
		intList.add(3);
		List<Integer> intResult = ImmutableList.<Integer>builder().addAll(intList).build();
		assertThatCode(() -> intResult.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intResult);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ListUtils_unmodifiableList() {
		List<String> list = new ArrayList<>();
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		List<String> result = ListUtils.unmodifiableList(list);
		assertThatCode(() -> result.add("Kiwifruit")).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);

		List<Integer> intList = new ArrayList<>();
		intList.add(Integer.MAX_VALUE);
		intList.add(-1);
		intList.add(3);
		List<Integer> intResult = ListUtils.unmodifiableList(intList);
		assertThatCode(() -> intResult.add(101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(intResult);
		assertThat(result).hasSize(3);
	}
}
