package org.ruoxue.java_147.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class ArraysWithExamplesTest {

	@Test
	public void fill() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
		Arrays.fill(array, "Durian");
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "Durian", "Durian");

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));
		Arrays.fill(intArray, -1);
		System.out.println(Arrays.toString(intArray));
		assertThat(intArray).containsExactly(-1, -1, -1);
	}

	@Test
	public void setAll() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
		Arrays.setAll(array, index -> {
			String value = array[index];
			if (value.startsWith("G")) {
				return value.toUpperCase();
			}
			return value;
		});
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "GUAVA", "Pitaya");

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));
		Arrays.setAll(intArray, index -> intArray[index] - 100);
		System.out.println(Arrays.toString(intArray));
		assertThat(intArray).containsExactly(2147483547, -101, -97);
	}

	@Test
	public void paralleSetAll() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
		Arrays.parallelSetAll(array, index -> {
			String value = array[index];
			if (value.contains("a")) {
				return value.toUpperCase();
			}
			return value;
		});
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("DURIAN", "GUAVA", "PITAYA");

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));
		Arrays.setAll(intArray, index -> intArray[index] - 100);
		System.out.println(Arrays.toString(intArray));
		assertThat(intArray).containsExactly(2147483547, -101, -97);
	}

	@Test
	public void stream() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
		Stream<String> stream = Arrays.stream(array);
		List<String> result = stream.map(e -> e.toUpperCase()).collect(Collectors.toList());
		System.out.println(result);
		assertThat(result).containsExactly("DURIAN", "GUAVA", "PITAYA");

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		System.out.println(Arrays.toString(intArray));
		IntStream intStream = Arrays.stream(intArray);
		List<Integer> intResult = intStream.map(e -> e - 100).boxed().collect(Collectors.toList());
		System.out.println(intResult);
		assertThat(intResult).containsExactly(2147483547, -101, -97);
	}

	@Test
	public void streamIntArray() {
		int[] array = new int[] { 1, -1, 3 };
		System.out.println(Arrays.toString(array));
		IntStream stream = Arrays.stream(array);
		List<Integer> result = stream.map(e -> e * 10).boxed().collect(Collectors.toList());
		System.out.println(result);
		assertThat(result).containsExactly(10, -10, 30);

		Stream<int[]> streamIntArray = Stream.of(array);
		result = streamIntArray.flatMapToInt(e -> Arrays.stream(e)).map(e -> e * 10).boxed()
				.collect(Collectors.toList());
		System.out.println(result);
		assertThat(result).containsExactly(10, -10, 30);
	}

	@Test
	public void spliterator() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		Spliterator<String> sit = Arrays.spliterator(array);
		sit.tryAdvance(e -> System.out.println(e));
		System.out.println("----------");
		sit.forEachRemaining(e -> System.out.println(e));

		System.out.println("----------");
		sit = Arrays.spliterator(array);
		while (sit.tryAdvance(e -> System.out.println(e))) {
		}
	}
}
