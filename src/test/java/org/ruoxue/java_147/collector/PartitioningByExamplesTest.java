package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

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
	public void withBinaryOperator() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Optional<Integer> result = list.stream().collect(Collectors.reducing(Integer::sum));
		System.out.println(result);
		assertEquals(6, result.get().intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		Optional<Fruit> fruitResult = fruitList.stream().collect(Collectors.reducing((p, c) -> {
			c.setQuantity(p.getQuantity() + c.getQuantity());
			return c;
		}));
		System.out.println(fruitResult);
		assertEquals(6, fruitResult.get().getQuantity(), 0);
	}

	@Test
	public void withIdentity() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Integer result = list.stream().collect(Collectors.reducing(100, (p, c) -> p + c));
		System.out.println(result);
		assertEquals(106, result.intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		Fruit fruitResult = fruitList.stream().collect(Collectors.reducing(new Fruit("", 100d, 1), (p, c) -> {
			c.setQuantity(p.getQuantity() + c.getQuantity());
			return c;
		}));
		System.out.println(fruitResult);
		assertEquals(106, fruitResult.getQuantity(), 0);
	}

	@Test
	public void withMapper() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		Integer result = list.stream().collect(Collectors.reducing(0, String::length, Integer::sum));
		System.out.println(result);
		assertEquals(17, result.intValue());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		Double fruitResult = fruitList.stream().collect(Collectors.reducing(0d, Fruit::getQuantity, Double::sum));
		System.out.println(fruitResult);
		assertEquals(6, fruitResult, 0);
	}
}
