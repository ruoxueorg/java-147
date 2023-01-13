package org.ruoxue.java_147.stream;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;

public class PrimitiveTypeStreamsTest {

	@Test
	public void intStreamOf() {
		int expectedCount = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void intStreamRange() {
		int expectedCount = 3;
		IntStream stream = IntStream.range(1, 4);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void intStreamRangeClosed() {
		int expectedCount = 4;
		IntStream stream = IntStream.rangeClosed(1, 4);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void longStreamOf() {
		int expectedCount = 3;
		LongStream stream = LongStream.of(4, 5, 6);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void longStreamRange() {
		int expectedCount = 3;
		LongStream stream = LongStream.range(4, 7);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void longStreamRangeClosed() {
		int expectedCount = 4;
		LongStream stream = LongStream.rangeClosed(4, 7);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void doubleStreamOf() {
		int expectedCount = 3;
		DoubleStream stream = DoubleStream.of(7.0, 8.0, 9.0);
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void arraysIntStream() {
		int expectedCount = 3;
		IntStream stream = Arrays.stream(new int[] { 1, 2, 3 });
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void arraysLongStream() {
		int expectedCount = 3;
		LongStream stream = Arrays.stream(new long[] { 4, 5, 6 });
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void arraysDoubleStream() {
		int expectedCount = 3;
		DoubleStream stream = Arrays.stream(new double[] { 7.0, 8.0, 9.0 });
		// stream.forEach(System.out::println);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}
}
