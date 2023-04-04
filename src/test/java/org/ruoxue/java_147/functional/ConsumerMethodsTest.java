package org.ruoxue.java_147.functional;

import java.util.Objects;
import java.util.function.Consumer;

import org.junit.Test;

public class ConsumerMethodsTest {

	@Test
	public void accept() {
		Consumer<String> startsWith = s -> System.out.println(s.startsWith("B"));
		startsWith.accept("Bacon");
		startsWith.accept("Ham");

		Consumer<Integer> greaterThan = i -> System.out.println(i > 3);
		greaterThan.accept(5);
		greaterThan.accept("Ham".length());
	}

	@Test
	public void andThen() {
		Consumer<String> startsWith = s -> System.out.println(s.startsWith("B"));
		Consumer<String> endsWith = s -> System.out.println(s.endsWith("n"));
		startsWith.andThen(endsWith).accept("Bacon");
		startsWith.andThen(endsWith).accept("Ham");

		Consumer<Integer> greaterThan = i -> System.out.println(i > 3);
		Consumer<Integer> lessThan = i -> System.out.println(i < 6);
		greaterThan.andThen(lessThan).accept(5);
		greaterThan.andThen(lessThan).accept(6);
	}

	@Test
	public void chaining() {
		Consumer<String> nonNull = s -> System.out.println(Objects.nonNull(s));
		Consumer<String> startsWith = s -> System.out.println(s.startsWith("B"));
		Consumer<String> endsWith = s -> System.out.println(s.endsWith("n"));
		nonNull.andThen(startsWith).andThen(endsWith).accept("Bacon");
		nonNull.andThen(startsWith).andThen(endsWith).accept("Ham");

		Consumer<Integer> intNonNull = i -> System.out.println(Objects.nonNull(i));
		Consumer<Integer> greaterThan = i -> System.out.println(i > 3);
		Consumer<Integer> lessThan = i -> System.out.println(i < 6);
		intNonNull.andThen(greaterThan).andThen(lessThan).accept(7);
		intNonNull.andThen(greaterThan).andThen(lessThan).accept(2);
	}

	public static class LengthGreaterThan<E> implements Consumer<String> {
		@Override
		public void accept(String t) {
			System.out.println(t.length() > 3);
		}
	}

	@Test
	public void traditional() {
		Consumer<String> lengthGreaterThan = new LengthGreaterThan<String>();
		Consumer<String> contains = s -> System.out.println(s.contains("o"));
		lengthGreaterThan.andThen(contains).accept("Bacon");
		lengthGreaterThan.andThen(contains).accept("Ham");
	}
}
