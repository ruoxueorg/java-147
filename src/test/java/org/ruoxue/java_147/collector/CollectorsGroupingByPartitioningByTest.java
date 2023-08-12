package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsGroupingByPartitioningByTest {

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
	public void groupingBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, List<Fruit>> result = list.stream().collect(Collectors.groupingBy(Fruit::getType));
		System.out.println(result);
		assertEquals(3, result.size());
	}

	@Test
	public void partitioningBy() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Boolean, List<Fruit>> result = list.stream().collect(Collectors.partitioningBy(e -> e.type > 1));
		System.out.println(result);
		assertEquals(2, result.get(Boolean.FALSE).size());
		assertEquals(3, result.get(Boolean.TRUE).size());
	}

	@Test
	public void groupingByWithMapping() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, Long> result = list.stream().collect(
				Collectors.groupingBy(Fruit::getType, Collectors.mapping(Function.identity(), Collectors.counting())));

		System.out.println(result);
		assertEquals(3, result.size());
	}

	@Test
	public void partitioningByWithMapping() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Boolean, List<Double>> result = list.stream().collect(Collectors.partitioningBy(e -> e.type > 1,
				Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(result);
		assertEquals(2, result.size());
	}

	@Test
	public void groupingByWithSupplier() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1), new Fruit("Guava", 4, 2), new Fruit("Kiwifruit", 5, 3));
		Map<Integer, List<Double>> result = list.stream().collect(Collectors.groupingBy(Fruit::getType, LinkedMap::new,
				Collectors.mapping(Fruit::getQuantity, Collectors.toList())));
		System.out.println(result);
		assertEquals(3, result.size());
	}
}
