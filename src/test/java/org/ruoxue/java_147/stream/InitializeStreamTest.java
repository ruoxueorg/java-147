package org.ruoxue.java_147.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class InitializeStreamTest {

	@Test
	public void collection() {
		int expectedCount = 3;
		List<String> list = Arrays.asList("Coconut", "Lichee", "Plum");
		Stream<String> stream = list.stream();
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		List<Integer> intList = Arrays.asList(1, 2, 3);
		Stream<Integer> intStream = intList.stream();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void stream() {
		int expectedCount = 3;
		Stream<String> stream = Stream.of("Coconut", "Lichee", "Plum");
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		String[] array = new String[] { "Coconut", "Lichee", "Plum" };
		stream = Stream.of(array);
		System.out.println(stream);
		count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Stream<Integer> intStream = Stream.of(1, 2, 3);
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		int[] intArray = new int[] { 1, 2, 3 };
		Stream<int[]> streamIntArray = Stream.of(intArray);
		IntStream intArrayStream = streamIntArray.flatMapToInt(e -> Arrays.stream(e));
		System.out.println(intArrayStream);
		count = intArrayStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void array() {
		int expectedCount = 3;
		String[] array = new String[] { "Coconut", "Lichee", "Plum" };
		Stream<String> stream = Arrays.stream(array);
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		stream = Arrays.stream(array, 0, 2);
		System.out.println(stream);
		count = stream.peek(System.out::println).count();
		assertEquals(2, count);

		int[] intArray = new int[] { 1, 2, 3 };
		Stream<Integer> intStream = Arrays.stream(intArray).boxed();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		intStream = Arrays.stream(intArray, 0, 2).boxed();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(2, count);
	}

	@Test
	public void builder() {
		int expectedCount = 3;
		Stream.Builder<String> builder = Stream.builder();
		Stream<String> stream = builder.add("Coconut").add("Lichee").add("Plum").build();
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Stream.Builder<Integer> intBuilder = Stream.builder();
		Stream<Integer> intStream = intBuilder.add(1).add(2).add(3).build();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void generate() {
		int expectedCount = 3;
		Stream<String> stream = Stream.generate(() -> "Lichee").limit(3);
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		stream = Stream.generate(() -> UUID.randomUUID().toString()).limit(3);
		System.out.println(stream);
		count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Stream<Integer> intStream = Stream.generate(() -> (int) (Math.random() * 100)).limit(3);
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void pattern() {
		int expectedCount = 1;
		Pattern pattern = Pattern.compile("^L");
		List<String> list = Arrays.asList("Coconut", "Lichee", "Plum");
		Stream<String> stream = list.stream().filter(pattern.asPredicate());
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}
}
