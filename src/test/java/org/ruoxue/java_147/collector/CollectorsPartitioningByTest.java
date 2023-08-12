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

public class CollectorsPartitioningByTest {

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
		Map<Boolean, Optional<String>> result = list.stream()
				.collect(Collectors.partitioningBy(e -> e.length() > 3, Collectors.maxBy(String::compareTo)));
		System.out.println(result);
		assertEquals("Fig", result.get(Boolean.FALSE).get());
		assertEquals("Melon", result.get(Boolean.TRUE).get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Boolean, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.partitioningBy(e -> e.type > 1, Collectors.maxBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Blueberry", fruitResult.get(Boolean.FALSE).get().getName());
		assertEquals("Kiwifruit", fruitResult.get(Boolean.TRUE).get().getName());
	}

	@Test
	public void withMinBy() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, Optional<String>> result = list.stream()
				.collect(Collectors.partitioningBy(e -> e.length() > 3, Collectors.minBy(String::compareTo)));
		System.out.println(result);
		assertEquals("Fig", result.get(Boolean.FALSE).get());
		assertEquals("Blueberry", result.get(Boolean.TRUE).get());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Boolean, Optional<Fruit>> fruitResult = fruitList.stream().collect(
				Collectors.partitioningBy(e -> e.type > 1, Collectors.minBy(Comparator.comparing(Fruit::getQuantity))));
		System.out.println(fruitResult);
		assertEquals("Fig", fruitResult.get(Boolean.FALSE).get().getName());
		assertEquals("Melon", fruitResult.get(Boolean.TRUE).get().getName());
	}

	@Test
	public void withMapping() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(e -> e.length() > 3,
				Collectors.mapping(e -> e.toUpperCase(), Collectors.toList())));
		System.out.println(result);
		assertEquals(1, result.get(Boolean.FALSE).size());
		assertEquals(4, result.get(Boolean.TRUE).size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Boolean, List<Double>> fruitResult = fruitList.stream().collect(Collectors.partitioningBy(e -> e.type > 1,
				Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}

	@Test
	public void withJoining() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig", "Guava", "Kiwifruit");
		Map<Boolean, String> result = list.stream()
				.collect(Collectors.partitioningBy(e -> e.length() > 3, Collectors.joining(", ", "(", ")")));
		System.out.println(result);
		assertEquals(2, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Boolean, String> fruitResult = fruitList.stream().collect(Collectors.partitioningBy(e -> e.type > 1,
				Collectors.mapping(Fruit::getName, Collectors.joining(", ", "(", ")"))));
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}
}
