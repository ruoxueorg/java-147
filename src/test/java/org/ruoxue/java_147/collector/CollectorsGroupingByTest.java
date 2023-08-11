package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.collector.CollectorsMappingTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsGroupingByTest {

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
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, Optional<String>> result = list.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.maxBy(String::compareTo)));
		System.out.println(result);
		assertEquals("Fig", result.get(3).get());
		assertEquals("Melon", result.get(5).get());
		assertEquals("Kiwifruit", result.get(9).get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Integer, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.maxBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Blueberry", fruitResult.get(1).get().getName());
		assertEquals("Guava", fruitResult.get(2).get().getName());
		assertEquals("Fig", fruitResult.get(3).get().getName());
	}

	@Test
	public void withMinBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, Optional<String>> result = list.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.minBy(String::compareTo)));
		System.out.println(result);
		assertEquals("Fig", result.get(3).get());
		assertEquals("Guava", result.get(5).get());
		assertEquals("Blueberry", result.get(9).get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Integer, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.minBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Fig", fruitResult.get(1).get().getName());
		assertEquals("Guava", fruitResult.get(2).get().getName());
		assertEquals("Melon", fruitResult.get(3).get().getName());
	}

	@Test
	public void withGroupingBy() {
		List<String> list = Arrays.asList("Blueberry", "Guava", "Melon", "Fig");
		Comparator<String> comparator = Comparator.comparing(String::length);
		BinaryOperator<String> binaryOperator = BinaryOperator.maxBy(comparator);
		Map<Integer, String> result = list.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.reducing("", binaryOperator)));
		System.out.println(result);
		assertEquals("Blueberry", result.get(9));

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Comparator<Fruit> fruitComparator = Comparator.comparing(Fruit::getQuantity);
		BinaryOperator<Fruit> fruitBinaryOperator = BinaryOperator.maxBy(fruitComparator);
		Map<Integer, Fruit> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.reducing(new Fruit("", 0d, 0), fruitBinaryOperator)));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}

	@Test
	public void withPartitioningBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava");
		Comparator<Integer> comparator = Comparator.comparingInt(i -> i);
		BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy(comparator);
		Map<Boolean, Integer> result = list.stream().collect(
				Collectors.partitioningBy(e -> e.length() > 3, Collectors.reducing(0, String::length, binaryOperator)));
		System.out.println(result);
		assertEquals(9, result.get(Boolean.TRUE).intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Comparator<Double> fruitComparator = Comparator.comparingDouble(d -> d);
		BinaryOperator<Double> fruitBinaryOperator = BinaryOperator.maxBy(fruitComparator);
		Map<Boolean, Double> fruitResult = fruitList.stream().collect(Collectors.partitioningBy(
				e -> e.name.length() > 3, Collectors.reducing(0d, Fruit::getQuantity, fruitBinaryOperator)));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}
}
