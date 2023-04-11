package org.ruoxue.java_147.functional.biconsumer;

import java.util.function.BiConsumer;

import org.junit.Test;

public class BiConsumerMethodsTest {

	@Test
	public void accept() {
		BiConsumer<String, String> startsWith = (s, s2) -> System.out.println(s.startsWith(s2));
		startsWith.accept("Bacon", "B");
		startsWith.accept("Ham", "B");

		BiConsumer<String, Integer> lengthGreaterThan = (s, i) -> System.out.println(s.length() > i);
		lengthGreaterThan.accept("Bacon", 3);
		lengthGreaterThan.accept("Ham", 3);
	}

	@Test
	public void andThen() {
		BiConsumer<String, String> startsWith = (s, s2) -> System.out.println(s.startsWith(s2));
		BiConsumer<String, String> endsWith = (s, s2) -> System.out.println(s.endsWith(s2));
		startsWith.andThen(endsWith).accept("BaconB", "B");
		startsWith.andThen(endsWith).accept("Ham", "B");

		BiConsumer<String, Integer> lengthGreaterThan = (s, i) -> System.out.println(s.length() > i);
		BiConsumer<String, Integer> mod = (s, i) -> System.out.println(s.length() % i == 1);
		lengthGreaterThan.andThen(mod).accept("BaconB", 5);
		lengthGreaterThan.andThen(mod).accept("Ham", 6);
	}

	@Test
	public void chaining() {
		BiConsumer<String, String> contains = (s, s2) -> System.out.println(s.contains(s2));
		BiConsumer<String, String> startsWith = (s, s2) -> System.out.println(s.startsWith(s2));
		BiConsumer<String, String> endsWith = (s, s2) -> System.out.println(s.endsWith(s2));
		contains.andThen(startsWith).andThen(endsWith).accept("Bacon", "B");
		contains.andThen(startsWith).andThen(endsWith).accept("Ham", "B");

		BiConsumer<String, Integer> parseInt = (s, i) -> {
			if (i < Integer.parseInt(s))
				System.out.println(true);
			System.out.println(false);
		};
		BiConsumer<String, Integer> lengthGreaterThan = (s, i) -> System.out.println(s.length() > i);
		BiConsumer<String, Integer> mod = (s, i) -> System.out.println(s.length() % i == 1);
		parseInt.andThen(lengthGreaterThan).andThen(mod).accept("777", 2);
		parseInt.andThen(lengthGreaterThan).andThen(mod).accept("7", 2);
	}

	public static class LengthGreaterThan<E> implements BiConsumer<String, Integer> {
		@Override
		public void accept(String t, Integer u) {
			System.out.println(t.length() > u);
		}
	}

	@Test
	public void traditional() {
		BiConsumer<String, Integer> lengthGreaterThan = new LengthGreaterThan<String>();
		BiConsumer<String, Integer> mod = (s, i) -> System.out.println(s.length() % i == 1);
		lengthGreaterThan.andThen(mod).accept("Bacon", 4);
		lengthGreaterThan.andThen(mod).accept("Ham", 3);
	}
}
