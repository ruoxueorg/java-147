package org.ruoxue.java_147.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectionVSCollectionsTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit implements Comparable<Fruit> {

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

		@Override
		public int compareTo(Fruit o) {
			int result = this.name.compareTo(o.name);
			if (result == 0)
				result = Double.compare(this.quantity, o.quantity);
			return result;
		}
	}

	@Test
	public void addAll() {
		int expectedSize = 3;
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Lists.newArrayList(apple, banana, cherry);
		List<Fruit> result = new ArrayList<>();
		result.addAll(list);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit peach = new Fruit("Peach", 3, 1);
		Collections.addAll(result, mango, orange, peach);
		System.out.println(result);
		assertEquals(6, result.size());
	}

	@Test
	public void sort() {
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Lists.newArrayList(banana, apple, cherry);
		System.out.println(list);
		list.sort(null);
		System.out.println(list);
		assertThat(list).containsExactly(apple, banana, cherry);

		Fruit orange = new Fruit("Orange", -1, 3);
		Fruit mango = new Fruit("Mango", Double.MAX_VALUE, 1);
		Fruit peach = new Fruit("Peach", 3, 1);
		list = Lists.newArrayList(orange, mango, peach);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		assertThat(list).containsExactly(mango, orange, peach);
	}

	@Test
	public void min() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Lists.newArrayList(apple, banana, cherry);
		Fruit result = Collections.min(list, Comparator.comparing(Fruit::getQuantity));
		System.out.println(result);
		assertThat(result).isEqualTo(banana);
	}

	@Test
	public void max() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Lists.newArrayList(apple, banana, cherry);
		Fruit result = Collections.max(list, Comparator.comparing(Fruit::getQuantity));
		System.out.println(result);
		assertThat(result).isEqualTo(apple);
	}

	@Test
	public void replaceAll() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		List<Fruit> list = Lists.newArrayList(apple, banana, cherry, apple);
		System.out.println(list);
		Fruit orange = new Fruit("Orange", -1, 3);
		boolean result = Collections.replaceAll(list, apple, orange);
		System.out.println(list);
		System.out.println(result);
		assertThat(result).isTrue();
	}
}
