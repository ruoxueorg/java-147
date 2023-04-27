package org.ruoxue.java_147.functional.bifunction;

import static org.junit.Assert.*;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BiFunctionWithExamplesTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Food {
		private String name;
		private double quantity;
		private int type;

		public Food(String name, double quantity, int type) {
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

		public boolean equals(Object object) {
			if (!(object instanceof Food)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Food other = (Food) object;
			return new EqualsBuilder().append(getName(), other.getName()).isEquals();
		}

		public int hashCode() {
			return new HashCodeBuilder().append(getName()).toHashCode();
		}
	}

	@Test
	public void apply() {
		BiFunction<Food, String, Boolean> startsWith = (o, s) -> o.name.startsWith(s);
		Food food = new Food("Bacon", 1, 1);
		boolean result = startsWith.apply(food, "B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 3, 1);
		result = startsWith.apply(food, "B");
		System.out.println(result);
		assertFalse(result);

		BiFunction<Food, Integer, Boolean> greaterThan = (o, i) -> o.name.length() > i;
		result = greaterThan.apply(new Food("Bacon", 1, 1), 3);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.apply(new Food("Ham", 3, 1), 3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void andThen() {
		BiFunction<Food, String, String> concat = (o, s) -> o.name.concat(s);
		Function<String, Integer> multiply = s -> s.length() * 2;
		Food food = new Food("Bacon", 1, 1);
		int result = concat.andThen(multiply).apply(food, "B");
		System.out.println(result);
		assertEquals(12, result);

		BiFunction<Food, Integer, Integer> length = (o, i) -> o.name.length() + i;
		Function<Integer, Integer> twice = i -> i * i;
		int intResult = length.andThen(twice).apply(new Food("Ham", 3, 1), 1);
		System.out.println(intResult);
		assertEquals(16, intResult);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		BiFunction<Food, String, String> concat = (o, s) -> o.name.concat(s);
		concat = concat.andThen(null);
	}

	public static class Concat<E> implements BiFunction<Food, String, String> {
		@Override
		public String apply(Food t, String u) {
			return t.name.concat(u);
		}
	}

	@Test
	public void traditional() {
		BiFunction<Food, String, String> concat = new Concat<>();
		Function<String, Integer> multiply = s -> s.length() * 2;
		Food food = new Food("Bacon", 1, 1);
		int result = concat.andThen(multiply).apply(food, "B");
		System.out.println(result);
		assertEquals(12, result);
		food = new Food("Ham", 2, 1);
		result = concat.andThen(multiply).apply(food, "B");
		System.out.println(result);
		assertEquals(8, result);
	}
}
