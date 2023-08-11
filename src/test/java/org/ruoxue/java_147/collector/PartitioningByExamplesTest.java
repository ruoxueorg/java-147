package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.collector.MappingExamplesTest.Fruit;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PartitioningByExamplesTest {

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
	public void withPredicate() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(4, result.get(Boolean.TRUE).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Boolean, List<Fruit>> fruitResult = fruitList.stream().collect(Collectors.partitioningBy(e -> e.type > 1));
		System.out.println(fruitResult);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(4, result.get(Boolean.TRUE).size());
	}

	@Test
	public void withDownstream() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3,
				Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(4, result.get(Boolean.TRUE).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Fig", 5, 3));
		Map<Boolean, List<Double>> fruitResult = fruitList.stream().collect(Collectors.partitioningBy(e -> e.type > 1,
				Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}

	@Test
	public void withCounting() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, Long> result = list.stream()
				.collect(Collectors.partitioningBy(e -> e.length() > 3, Collectors.counting()));
		System.out.println(result);
		assertEquals(2, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Boolean, Long> fruitResult = fruitList.stream()
				.collect(Collectors.partitioningBy(e -> e.type > 1, Collectors.counting()));

		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}
}
