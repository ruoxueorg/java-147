package org.ruoxue.java_147.functional.function;

import static org.junit.Assert.*;

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

public class FunctionWithExamplesTest {

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
		Function<Food, Boolean> startsWith = o -> o.name.startsWith("B");
		boolean result = startsWith.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertTrue(result);
		result = startsWith.apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertFalse(result);

		Function<Food, Boolean> greaterThan = o -> o.name.length() > 3;
		result = greaterThan.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void andThen() {
		Function<Food, Double> half = d -> d.quantity / 2;
		Function<Double, Double> twice = d -> d * d;
		double result = half.andThen(twice).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(0.25d, result, 2);

		Function<Food, Integer> length = s -> s.name.length();
		Function<Integer, Integer> multiply = i -> i * 2;
		int intResult = length.andThen(multiply).apply(new Food("Ham", 3, 1));
		System.out.println(intResult);
		assertEquals(6, intResult);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		Function<Food, Double> half = d -> d.quantity / 2;
		half = half.andThen(null);
	}

	@Test
	public void compose() {
		Function<Double, Double> half = d -> d / 2;
		Function<Food, Double> twice = d -> d.quantity * d.quantity;
		double result = half.compose(twice).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(0.5, result, 2);
	}

	@Test(expected = NullPointerException.class)
	public void composeThrowException() {
		Function<Double, Double> half = d -> d / 2;
		half = half.compose(null);
	}

	@Test
	public void identity() {
		Function<Food, Food> identity = Function.identity();
		Food result = identity.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals("Bacon", result.getName());

		Object objResult = Function.identity().apply(new Food("Ham", 2, 1));
		System.out.println(objResult);
		assertEquals("Ham", ((Food) objResult).getName());

		Function<Integer, Integer> intIdentity = i -> i;
		int intResult = intIdentity.apply(7);
		System.out.println(intResult);
		assertEquals(7, intResult);
	}

	public static class Length<E, F> implements Function<Food, Integer> {
		@Override
		public Integer apply(Food t) {
			return t.getName().length();
		}
	}

	@Test
	public void traditional() {
		Function<Food, Integer> length = new Length<Food, Integer>();
		Function<Integer, Integer> multiply = i -> i * 2;
		int result = length.andThen(multiply).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(10, result);
		result = length.andThen(multiply).apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertEquals(6, result);
	}
}
