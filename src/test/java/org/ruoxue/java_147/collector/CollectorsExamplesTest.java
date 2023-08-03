package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsExamplesTest {

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
	public void counting() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Long result = list.stream().filter(e -> e.length() > 3).collect(Collectors.counting());
		System.out.println(result);
		assertEquals(2, result.longValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Long fruitResult = fruitList.stream().filter(e -> e.getQuantity() > 0).collect(Collectors.counting());
		System.out.println(fruitResult);
		assertEquals(2, fruitResult.longValue());
	}

	@Test
	public void collectingAndThen() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Set<String> result = list.stream()
				.collect(Collectors.collectingAndThen(Collectors.toSet(), ImmutableSet::copyOf));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Set<Fruit> fruitResult = fruitList.stream()
				.collect(Collectors.collectingAndThen(Collectors.toSet(), ImmutableSet::copyOf));
		System.out.println(fruitResult);
		assertEquals(3, fruitResult.size());
	}

	@Test
	public void joining() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining(", "));
		System.out.println(result);
		assertEquals("Blueberry, Melon, Fig", result);

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String fruitResult = fruitList.stream().map(e -> e.getName())
				.collect(Collectors.joining(", ", "PRE-", "-POST"));
		System.out.println(fruitResult);
		assertEquals("PRE-Blueberry, Melon, Fig-POST", fruitResult);
	}

	@Test
	public void mapping() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		List<Integer> result = list.stream().collect(Collectors.mapping(e -> e.length(), Collectors.toList()));
		System.out.println(result);
		assertEquals(3, result.size());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String fruitResult = fruitList.stream()
				.collect(Collectors.mapping(Fruit::getName, Collectors.joining(", ", "PRE-", "-POST")));
		System.out.println(fruitResult);
		assertEquals("PRE-Blueberry, Melon, Fig-POST", fruitResult);
	}

	@Test
	public void reducing() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Integer result = list.stream().collect(Collectors.reducing(0, String::length, Integer::sum));
		System.out.println(result);
		assertEquals(17, result.intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		BigDecimal fruitResult = fruitList.stream()
				.collect(Collectors.reducing(BigDecimal.ZERO, e -> new BigDecimal(e.getQuantity()), BigDecimal::add));
		System.out.println(fruitResult);
		assertEquals(6, fruitResult.doubleValue(), 0);
	}

}
