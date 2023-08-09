package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
	public void mappingWithMaxBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Optional<Double> result = list.stream()
				.collect(Collectors.mapping(Fruit::getQuantity, Collectors.maxBy(Double::compareTo)));
		System.out.println(result);
		assertEquals(Double.MAX_VALUE, result.get(), 0);
	}

	@Test
	public void mappingWithMinBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Optional<Integer> result = list.stream()
				.collect(Collectors.mapping(e -> e.name.length(), Collectors.minBy(Integer::compareTo)));
		System.out.println(result);
		assertEquals(3, result.get().intValue());
	}

	@Test
	public void mappingWithGroupingBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Integer, List<Double>> result = list.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(result);
		assertEquals(2, result.size());
	}

	@Test
	public void mappingWithPartitioningBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Boolean, List<Double>> result = list.stream().collect(Collectors.partitioningBy(e -> e.name.length() > 5,
				Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(result);
		assertEquals(2, result.size());
	}

	@Test
	public void mappingWithCounting() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Integer, Long> result = list.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.mapping(Function.identity(), Collectors.counting())));

		System.out.println(result);
		assertEquals(2, result.size());
	}

	@Test
	public void mappingWithJoining() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String result = list.stream().collect(Collectors.mapping(Fruit::getName, Collectors.joining(", ", "(", ")")));
		System.out.println(result);
		assertEquals("(Blueberry, Melon, Fig)", result);
	}

	@Test
	public void mappingWithSummarizingInt() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		IntSummaryStatistics result = list.stream()
				.collect(Collectors.mapping(e -> e.name.length(), Collectors.summarizingInt(e -> e)));
		System.out.println(result);
		assertEquals(5.66, result.getAverage(), 2);
		assertEquals(17, result.getSum());
	}

	@Test
	public void mappingWithToList() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		List<Double> result = list.stream().collect(Collectors.mapping(Fruit::getQuantity, Collectors.toList()));
		System.out.println(result);
		assertEquals(3, result.size());
	}
}
