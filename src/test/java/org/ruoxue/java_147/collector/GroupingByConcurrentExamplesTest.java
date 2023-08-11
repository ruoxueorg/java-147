package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class GroupingByConcurrentExamplesTest {

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
	public void withClassification() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Map<Integer, List<Integer>> result = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Integer, List<Fruit>> fruitResult = fruitList.stream()
				.collect(Collectors.groupingByConcurrent(Fruit::getType));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}

	@Test
	public void withDownstream() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Map<Integer, Long> result = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3,
				Collectors.mapping(Function.identity(), Collectors.counting())));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Integer, Long> fruitResult = fruitList.stream().collect(Collectors.groupingByConcurrent(Fruit::getType,
				Collectors.mapping(Function.identity(), Collectors.counting())));

		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}

	@Test
	public void withSupplier() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Map<Integer, List<Integer>> result = list.stream().collect(Collectors.groupingByConcurrent(e -> e % 3,
				ConcurrentHashMap::new, Collectors.mapping(e -> e * e, Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(0).size());
		assertEquals(2, result.get(1).size());
		assertEquals(2, result.get(2).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Integer, List<Double>> fruitResult = fruitList.stream().collect(Collectors.groupingByConcurrent(
				Fruit::getType, ConcurrentHashMap::new, Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}
}
