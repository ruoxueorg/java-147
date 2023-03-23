package org.ruoxue.java_147.stream;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	public void of() {
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
		IntStream iStream = streamIntArray.flatMapToInt(e -> Arrays.stream(e));
		System.out.println(iStream);
		count = iStream.peek(System.out::println).count();
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

		int[] intArray = new int[] { 1, 2, 3 };
		Stream<Integer> intStream = Arrays.stream(intArray).boxed();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
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

	@Test
	public void empty() {
		int expectedCount = 0;
		Stream<String> stream = Stream.empty();
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Stream<Integer> intStream = Stream.empty();
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void iterate() {
		int expectedCount = 3;
		Stream<String> stream = Stream.iterate("Coconut", n -> n + 1).limit(3);
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Stream<Integer> intStream = Stream.iterate(2, n -> n * n).limit(3);
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void iterator() {
		int expectedCount = 3;
		List<String> list = Arrays.asList("Coconut", "Lichee", "Plum");
		Spliterator<String> spliterator = Spliterators.spliteratorUnknownSize(list.iterator(), Spliterator.NONNULL);
		Stream<String> stream = StreamSupport.stream(spliterator, false);
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		List<Integer> intList = Arrays.asList(1, 2, 3);
		Spliterator<Integer> intSpliterator = Spliterators.spliteratorUnknownSize(intList.iterator(),
				Spliterator.NONNULL);
		Stream<Integer> intStream = StreamSupport.stream(intSpliterator, false);
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void iterable() {
		int expectedCount = 3;
		Iterable<String> iterable = Arrays.asList("Coconut", "Lichee", "Plum");
		Stream<String> stream = StreamSupport.stream(iterable.spliterator(), false);
		System.out.println(stream);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);

		Iterable<Integer> intIterable = Arrays.asList(1, 2, 3);
		Stream<Integer> intStream = StreamSupport.stream(intIterable.spliterator(), false);
		System.out.println(intStream);
		count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void intStream() {
		int expectedCount = 3;
		IntStream intStream = IntStream.of(10, 20, 30);
		System.out.println(intStream);
		long count = intStream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void longStream() {
		int expectedCount = 3;
		LongStream stream = LongStream.of(40, 50, 60);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}

	@Test
	public void doubleStream() {
		int expectedCount = 3;
		DoubleStream stream = DoubleStream.of(70.0, 80.0, 90.0);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedCount, count);
	}
}
