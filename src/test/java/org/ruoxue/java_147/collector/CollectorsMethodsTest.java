package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsMethodsTest {

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
	public void maxBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Optional<String> result = list.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
		System.out.println(result);
		assertEquals("Melon", result.get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Optional<Fruit> fruit = fruitList.stream().collect(Collectors.maxBy(Comparator.comparing(Fruit::getName)));
		System.out.println(fruit);
		assertEquals("Melon", fruit.get().getName());
	}

	@Test
	public void minBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Optional<String> result = list.stream().collect(Collectors.minBy(Comparator.naturalOrder()));
		System.out.println(result);
		assertEquals("Blueberry", result.get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", 3, 3),
				new Fruit("Fig", 2, 1));
		Optional<Fruit> fruit = fruitList.stream()
				.collect(Collectors.minBy(Comparator.comparingDouble(Fruit::getQuantity)));
		System.out.println(fruit);
		assertEquals("Fig", fruit.get().getName());
	}

	@Test
	public void groupingBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Integer, List<String>> result = list.stream().collect(Collectors.groupingBy(String::length));
		System.out.println(result);
		assertEquals(1, result.get(3).size());
		assertEquals(1, result.get(5).size());
		assertEquals(1, result.get(9).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", 3, 3),
				new Fruit("Fig", 2, 1));
		Map<Integer, List<Fruit>> fruitResult = fruitList.stream().collect(Collectors.groupingBy(Fruit::getType));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.get(1).size());
		assertEquals(1, fruitResult.get(3).size());
	}

	@Test
	public void partitioningBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(2, result.get(Boolean.TRUE).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", 3, 3),
				new Fruit("Fig", 2, 1));
		Map<Boolean, List<Fruit>> fruitResult = fruitList.stream()
				.collect(Collectors.partitioningBy(e -> e.getName().length() > 3));
		System.out.println(fruitResult);
		assertEquals(1, fruitResult.get(Boolean.FALSE).size());
		assertEquals(2, fruitResult.get(Boolean.TRUE).size());
	}

}
