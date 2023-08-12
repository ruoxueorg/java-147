package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

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
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.maxBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Blueberry", fruitResult.get(1).get().getName());
		assertEquals("Guava", fruitResult.get(2).get().getName());
		assertEquals("Kiwifruit", fruitResult.get(3).get().getName());
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
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.minBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Fig", fruitResult.get(1).get().getName());
		assertEquals("Guava", fruitResult.get(2).get().getName());
		assertEquals("Melon", fruitResult.get(3).get().getName());
	}

	@Test
	public void withMapping() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, List<String>> result = list.stream().collect(Collectors.groupingBy(String::length, TreeMap::new,
				Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(3).size());
		assertEquals(2, result.get(5).size());
		assertEquals(2, result.get(9).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, List<Double>> fruitResult = fruitList.stream().collect(Collectors.groupingBy(Fruit::getType,
				TreeMap::new, Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}

	@Test
	public void withJoining() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Integer, String> result = list.stream()
				.collect(Collectors.groupingBy(String::length, Collectors.joining(", ", "(", ")")));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, String> fruitResult = fruitList.stream().collect(Collectors.groupingBy(Fruit::getType,
				Collectors.mapping(Fruit::getName, Collectors.joining(", ", "(", ")"))));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}
}
