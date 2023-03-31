package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BiPredicateWithExamplesTest {

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
	public void test() {
		BiPredicate<Food, String> startsWith = (s, s2) -> s.name.startsWith(s2);
		Food food = new Food("Bacon", 1, 1);
		boolean result = startsWith.test(food, "B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 3, 1);
		result = startsWith.test(food, "B");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void negate() {
		BiPredicate<Food, String> startsWith = (s, s2) -> s.name.startsWith(s2);
		Food food = new Food("Bacon", 1, 1);
		boolean result = startsWith.negate().test(food, "B");
		System.out.println(result);
		assertFalse(result);
		food = new Food("Ham", 3, 1);
		result = startsWith.negate().test(food, "B");
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void and() {
		BiPredicate<Food, String> startsWith = (s, s2) -> s.name.startsWith(s2);
		BiPredicate<Food, String> endsWith = (s, s2) -> s.name.endsWith(s2);
		Food food = new Food("BaconB", 1, 1);
		boolean result = startsWith.and(endsWith).test(food,"B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 3, 1);
		result = startsWith.and(endsWith).test(food, "B");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void or() {
		Predicate<Food> isNull = Objects::isNull;
		Predicate<Food> contains = o -> o.name.contains("o");
		Food food = new Food("Pork", 3, 1);
		boolean result = isNull.or(contains).test(food);
		System.out.println(result);
		assertTrue(result);
		result = isNull.or(contains).test(null);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void chaining() {
		Predicate<Food> nonNull = Objects::nonNull;
		Predicate<Food> startsWith = o -> o.name.startsWith("B");
		Predicate<Food> endsWith = o -> o.name.endsWith("n");
		Food food = new Food("Bacon", 1, 1);
		boolean result = nonNull.and(startsWith).or(endsWith).test(food);
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 2, 1);
		result = nonNull.and(startsWith).or(endsWith).test(food);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isEqual() {
		Food food = new Food("Bacon", 1, 1);
		Predicate<Food> isEqual = Predicate.isEqual(food);
		boolean result = isEqual.test(food);
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 2, 1);
		result = isEqual.test(food);
		System.out.println(result);
		assertFalse(result);
	}

	public static class LengthGreaterThan<E> implements Predicate<Food> {
		@Override
		public boolean test(Food t) {
			return t.name.length() > 3;
		}
	}

	@Test
	public void traditional() {
		Predicate<Food> lengthGreaterThan = new LengthGreaterThan<Food>();
		Predicate<Food> contains = o -> o.name.contains("o");
		Food food = new Food("Bacon", 1, 1);
		boolean result = lengthGreaterThan.and(contains).test(food);
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 2, 1);
		result = lengthGreaterThan.and(contains).test(food);
		System.out.println(result);
		assertFalse(result);
	}
}
