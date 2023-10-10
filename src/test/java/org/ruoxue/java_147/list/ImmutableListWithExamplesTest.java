package org.ruoxue.java_147.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ImmutableListWithExamplesTest {
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit {

		private String name;
		private double quantity;
		private int type;

		public Fruit(String name, double quantity, int type) {
			this.name = name;
			this.quantity = quantity;
			this.type = type;
		}

		public String toString() {
			ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.JSON_STYLE);
			builder.appendSuper(super.toString());
			builder.append("name", name);
			builder.append("quantity", quantity);
			builder.append("type", type);
			return builder.toString();
		}
	}

	@Test
	public void Arrays_asList() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Arrays.asList(apple, banana, cherry);
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> list.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(list);
		assertThat(list).hasSize(3);
	}

	@Test
	public void Collections_unmodifiableList() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>();
		list.add(apple);
		list.add(banana);
		list.add(cherry);
		List<Fruit> result = Collections.unmodifiableList(list);
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> result.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ImmutableList_of() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = ImmutableList.of(apple, banana, cherry);
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> list.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(list);
		assertThat(list).hasSize(3);
	}

	@Test
	public void ImmutableList_copyOf() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>();
		list.add(apple);
		list.add(banana);
		list.add(cherry);
		List<Fruit> result = ImmutableList.copyOf(list);
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> result.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ImmutableList_builder() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>();
		list.add(apple);
		list.add(banana);
		list.add(cherry);
		List<Fruit> result = ImmutableList.<Fruit>builder().addAll(list).build();
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> result.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ListUtils_unmodifiableList() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = new ArrayList<>();
		list.add(apple);
		list.add(banana);
		list.add(cherry);
		List<Fruit> result = ListUtils.unmodifiableList(list);
		Fruit kiwifruit = new Fruit("Kiwifruit", 101, 2);
		assertThatCode(() -> result.add(kiwifruit)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}
}
