package org.ruoxue.java_147.net;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UriVSUrlTest {

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
		Collection<Fruit> collection = Lists.newArrayList(apple, banana, cherry);
		Collection<Fruit> result = new ArrayList<>();
		result.addAll(collection);
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
		Collection<Fruit> collection = Lists.newArrayList(apple, banana, cherry);
		Fruit result = Collections.min(collection, Comparator.comparing(Fruit::getQuantity));
		System.out.println(result);
		assertThat(result).isEqualTo(banana);
	}

	@Test
	public void max() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Collection<Fruit> collection = Lists.newArrayList(apple, banana, cherry);
		Fruit result = Collections.max(collection, Comparator.comparing(Fruit::getQuantity));
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

	@Test
	public void enumeration() {
		Fruit apple = new Fruit("Apple", Double.MAX_VALUE, 1);
		Fruit banana = new Fruit("Banana", -1, 3);
		Fruit cherry = new Fruit("Cherry", 3, 1);
		Collection<Fruit> collection = Lists.newArrayList(apple, banana, cherry);
		Iterator<Fruit> it = collection.iterator();
		while (it.hasNext()) {
			Fruit e = it.next();
			System.out.println(e);
		}

		Enumeration<Fruit> en = Collections.enumeration(collection);
		while (en.hasMoreElements()) {
			Fruit e = en.nextElement();
			System.out.println(e);
		}
	}

	@Test
	public void list() {
		int expectedSize = 3;
		Vector<Fruit> vector = new Vector<>();
		vector.add(new Fruit("Apple", Double.MAX_VALUE, 1));
		vector.add(new Fruit("Banana", -1, 3));
		vector.add(new Fruit("Cherry", 3, 1));
		List<Fruit> result = Collections.list(vector.elements());
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}
}
