package org.ruoxue.java_147.stream;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class PrimitiveStreamBoxedTest {

	@Test
	public void intStreamBoxed() {
		int expectedCount = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		Stream<Integer> boxedStream = stream.boxed();
//		 boxedStream.forEach(System.out::println);
		long count = boxedStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void intStreamToList() {
		int expectedSize = 3;
		IntStream stream = IntStream.of(1, 2, 3);
		List<Integer> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void longStreamBoxed() {
		int expectedCount = 3;
		LongStream stream = LongStream.of(4, 5, 6);
		Stream<Long> boxedStream = stream.boxed();
//		 boxedStream.forEach(System.out::println);
		long count = boxedStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void longStreamToList() {
		int expectedSize = 3;
		LongStream stream = LongStream.of(4, 5, 6);
		List<Long> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}

	@Test
	public void doubleStreamBoxed() {
		int expectedCount = 3;
		DoubleStream stream = DoubleStream.of(7.0, 8.0, 9.0);
		Stream<Double> boxedStream = stream.boxed();
//		 boxedStream.forEach(System.out::println);
		long count = boxedStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void doubleStreamToList() {
		int expectedSize = 3;
		DoubleStream stream = DoubleStream.of(7.0, 8.0, 9.0);
		List<Double> list = stream.boxed().collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());
	}
}
