package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
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

public class CollectorsMappingTest {

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
	public void withMaxBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Optional<Integer> result = list.stream()
				.collect(Collectors.mapping(String::length, Collectors.maxBy(Integer::compareTo)));
		System.out.println(result);
		assertEquals(9, result.get().intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Optional<Double> fruit = fruitList.stream()
				.collect(Collectors.mapping(Fruit::getQuantity, Collectors.maxBy(Double::compareTo)));
		System.out.println(fruit);
		assertEquals(Double.MAX_VALUE, fruit.get(), 0);
	}

	@Test
	public void withMinBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Optional<Integer> result = list.stream()
				.collect(Collectors.mapping(e -> e.length(), Collectors.minBy(Integer::compareTo)));
		System.out.println(result);
		assertEquals(3, result.get().intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Optional<Double> fruit = fruitList.stream()
				.collect(Collectors.mapping(e -> e.quantity, Collectors.minBy(Double::compareTo)));
		System.out.println(fruit);
		assertEquals(3, result.get().intValue());
	}

	@Test
	public void withGroupingBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Integer, List<String>> result = list.stream().collect(
				Collectors.groupingBy(String::length, Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(3).size());
		assertEquals(1, result.get(5).size());
		assertEquals(1, result.get(9).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Integer, List<Double>> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}

	@Test
	public void withPartitioningBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3,
				Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(2, result.get(Boolean.TRUE).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Boolean, List<Double>> fruitResult = fruitList.stream().collect(Collectors
				.partitioningBy(e -> e.name.length() > 3, Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}
}
