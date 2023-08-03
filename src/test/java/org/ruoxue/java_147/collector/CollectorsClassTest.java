package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsClassTest {

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
	public void averagingInt() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Double result = list.stream().collect(Collectors.averagingInt(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.doubleValue(), 2);

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Double fruitResult = fruitList.stream().collect(Collectors.averagingInt(e -> e.getName().length()));
		System.out.println(fruitResult);
		assertEquals(5.66, fruitResult.doubleValue(), 2);
	}

	@Test
	public void averagingLong() {
		List<Long> list = Arrays.asList(1L, 2L, 3L);
		Double result = list.stream().collect(Collectors.averagingLong(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.doubleValue(), 2);

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Double fruitResult = fruitList.stream().collect(Collectors.averagingLong(e -> e.getType()));
		System.out.println(fruitResult);
		assertEquals(1.66, fruitResult.doubleValue(), 2);
	}

	@Test
	public void averagingDouble() {
		List<Double> list = Arrays.asList(1d, 2d, 3d);
		Double result = list.stream().collect(Collectors.averagingDouble(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.doubleValue(), 2);

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		Double fruitResult = fruitList.stream().collect(Collectors.averagingDouble(e -> e.getQuantity()));
		System.out.println(fruitResult);
		assertEquals(5.992310449541053E307, fruitResult.doubleValue(), 2);
	}

	@Test
	public void summarizingInt() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		IntSummaryStatistics result = list.stream().collect(Collectors.summarizingInt(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.getAverage(), 2);
		assertEquals(6, result.getSum());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		IntSummaryStatistics fruitResult = fruitList.stream()
				.collect(Collectors.summarizingInt(e -> e.getName().length()));
		System.out.println(fruitResult);
		assertEquals(5.66, fruitResult.getAverage(), 2);
		assertEquals(17, fruitResult.getSum());
	}

	@Test
	public void summarizingLong() {
		List<Long> list = Arrays.asList(1L, 2L, 3L);
		LongSummaryStatistics result = list.stream().collect(Collectors.summarizingLong(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.getAverage(), 2);
		assertEquals(6, result.getSum());

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		LongSummaryStatistics fruitResult = fruitList.stream().collect(Collectors.summarizingLong(e -> e.getType()));
		System.out.println(fruitResult);
		assertEquals(1.66, fruitResult.getAverage(), 2);
		assertEquals(5, fruitResult.getSum());
	}

	@Test
	public void summarizingDouble() {
		List<Double> list = Arrays.asList(1d, 2d, 3d);
		DoubleSummaryStatistics result = list.stream().collect(Collectors.summarizingDouble(e -> e));
		System.out.println(result);
		assertEquals(2.00, result.getAverage(), 2);
		assertEquals(6.00, result.getSum(), 2);

		List<Fruit> fruitList = Arrays.asList(new Fruit("Blueberry", 1, 1), new Fruit("Melon", 2, 3),
				new Fruit("Fig", 3, 1));
		DoubleSummaryStatistics fruitResult = fruitList.stream()
				.collect(Collectors.summarizingDouble(e -> e.getQuantity()));
		System.out.println(fruitResult);
		assertEquals(2.00d, fruitResult.getAverage(), 2);
		assertEquals(6.00d, fruitResult.getSum(), 2);
	}

}
