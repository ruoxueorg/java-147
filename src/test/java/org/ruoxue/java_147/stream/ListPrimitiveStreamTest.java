package org.ruoxue.java_147.stream;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;

public class ListPrimitiveStreamTest {

	@Test
	public void mapToInt() {
		int expectedCount = 3;
		List<Integer> list = Arrays.asList(1, 2, 3);
		IntStream stream = list.stream().mapToInt(e -> e);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void mapToLong() {
		int expectedCount = 3;
		List<Long> list = Arrays.asList(4L, 5L, 6L);
		LongStream stream = list.stream().mapToLong(e -> e);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void mapToDouble() {
		int expectedCount = 3;
		List<Double> list = Arrays.asList(7.0, 8.0, 9.0);
		DoubleStream stream = list.stream().mapToDouble(e -> e);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void sum() {
		int expectedSum = 6;
		IntStream stream = IntStream.of(1, 2, 3);
		int sum = stream.sum();
		System.out.println(sum);
		assertEquals(expectedSum, sum);
	}

	@Test
	public void average() {
		int expectedAverage = 2;
		IntStream stream = IntStream.of(1, 2, 3);
		double average = stream.average().getAsDouble();
		System.out.println(average);
		assertEquals(expectedAverage, average, 0);
	}

	@Test
	public void max() {
		int expectedMax = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		int max = stream.max().getAsInt();
		System.out.println(max);
		assertEquals(expectedMax, max);
	}

	@Test
	public void min() {
		int expectedMin = 1;
		IntStream stream = IntStream.of(1, 2, 3);
		int min = stream.min().getAsInt();
		System.out.println(min);
		assertEquals(expectedMin, min);
	}

	@Test
	public void count() {
		int expectedCount = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		long count = stream.count();
		System.out.println(count);
		assertEquals(expectedCount, count);
	}

	@Test(expected = IllegalStateException.class)
	public void intermediate() {
		int expectedMin = 1;
		int expectedMax = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		int max = stream.max().getAsInt();
		System.out.println(max);
		assertEquals(expectedMax, max);

		int min = stream.min().getAsInt();
		System.out.println(min);
		assertEquals(expectedMin, min);
	}

	@Test
	public void supplier() {
		int expectedMin = 1;
		int expectedMax = 3;
		Supplier<IntStream> supplier = () -> IntStream.of(1, 2, 3);
		int max = supplier.get().max().getAsInt();
		System.out.println(max);
		assertEquals(expectedMax, max);

		int min = supplier.get().min().getAsInt();
		System.out.println(min);
		assertEquals(expectedMin, min);
	}
}
