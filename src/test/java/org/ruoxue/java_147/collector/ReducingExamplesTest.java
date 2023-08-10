package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReducingExamplesTest {

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
	public void withCounting() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Integer, Long> result = list.stream().collect(
				Collectors.groupingBy(String::length, Collectors.mapping(Function.identity(), Collectors.counting())));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Map<Integer, Long> fruitResult = fruitList.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.mapping(Function.identity(), Collectors.counting())));

		System.out.println(fruitResult);
		assertEquals(2, fruitResult.size());
	}

	@Test
	public void withJoining() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Map<Integer, String> result = list.stream().collect(Collectors.groupingBy(String::length,
				Collectors.mapping(Function.identity(), Collectors.joining("", "(", ")"))));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String fruitResult = fruitList.stream()
				.collect(Collectors.mapping(Fruit::getName, Collectors.joining(", ", "(", ")")));
		System.out.println(fruitResult);
		assertEquals("(Blueberry, Melon, Fig)", fruitResult);
	}

	@Test
	public void withSummarizingInt() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		IntSummaryStatistics result = list.stream()
				.collect(Collectors.mapping(e -> e.length() * e.length(), Collectors.summarizingInt(e -> e)));
		System.out.println(result);
		assertEquals(38.33, result.getAverage(), 2);
		assertEquals(115, result.getSum());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		IntSummaryStatistics fruitResult = fruitList.stream()
				.collect(Collectors.mapping(e -> e.name.length(), Collectors.summarizingInt(e -> e)));
		System.out.println(fruitResult);
		assertEquals(5.66, fruitResult.getAverage(), 2);
		assertEquals(17, fruitResult.getSum());
	}

	@Test
	public void withToList() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		List<Integer> result = list.stream().collect(Collectors.mapping(String::length, Collectors.toList()));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		List<Double> fruitResult = fruitList.stream()
				.collect(Collectors.mapping(Fruit::getQuantity, Collectors.toList()));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}
}
