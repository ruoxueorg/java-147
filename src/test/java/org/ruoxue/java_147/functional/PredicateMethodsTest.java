package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PredicateMethodsTest {

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
		Predicate<String> startsWith = s -> s.startsWith("B");
		boolean result = startsWith.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		result = greaterThan.test(5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.test("Ham".length());
		System.out.println(result);
		assertFalse(result);

		Predicate<Food> quantityLessThan = o -> o.quantity < 6;
		Food food = new Food("Pork", 3, 1);
		result = quantityLessThan.test(food);
		System.out.println(result);
		assertTrue(result);
		food = new Food("Pork", 6, 1);
		result = quantityLessThan.test(food);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void negate() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		boolean result = startsWith.negate().test("Bacon");
		System.out.println(result);
		assertFalse(result);
		result = startsWith.negate().test("Ham");
		System.out.println(result);
		assertTrue(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		result = greaterThan.negate().test(5);
		System.out.println(result);
		assertFalse(result);
		result = greaterThan.negate().test("Ham".length());
		System.out.println(result);
		assertTrue(result);

		Predicate<Food> quantityLessThan = o -> o.quantity < 6;
		Food food = new Food("Pork", 3, 1);
		result = quantityLessThan.negate().test(food);
		System.out.println(result);
		assertFalse(result);
		food = new Food("Pork", 6, 1);
		result = quantityLessThan.negate().test(food);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void and() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = startsWith.and(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.and(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = greaterThan.and(lessThan).test(5);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.and(lessThan).test(6);
		System.out.println(result);
		assertFalse(result);

		Predicate<Food> nonNull = Objects::nonNull;
		Predicate<Food> contains = o -> o.name.contains("o");
		Food food = new Food("Pork", 3, 1);
		result = nonNull.and(contains).test(food);
		System.out.println(result);
		assertTrue(result);
		result = nonNull.and(contains).test(null);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void or() {
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = startsWith.or(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.or(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = greaterThan.or(lessThan).test(7);
		System.out.println(result);
		assertTrue(result);
		result = greaterThan.or(lessThan).test(2);
		System.out.println(result);
		assertTrue(result);

		Predicate<Food> isNull = Objects::isNull;
		Predicate<Food> contains = o -> o.name.contains("o");
		Food food = new Food("Pork", 3, 1);
		result = isNull.or(contains).test(food);
		System.out.println(result);
		assertTrue(result);
		result = isNull.or(contains).test(null);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void chaining() {
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> startsWith = s -> s.startsWith("B");
		Predicate<String> endsWith = s -> s.endsWith("n");
		boolean result = nonNull.and(startsWith).or(endsWith).test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = nonNull.and(startsWith).or(endsWith).test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> intNonNull = Objects::nonNull;
		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		result = intNonNull.and(greaterThan).or(lessThan).test(7);
		System.out.println(result);
		assertTrue(result);
		result = intNonNull.and(greaterThan).or(lessThan).test(2);
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void isEqual() {
		Predicate<String> isEqual = Predicate.isEqual("Bacon");
		boolean result = isEqual.test("Bacon");
		System.out.println(result);
		assertTrue(result);
		result = isEqual.test("Ham");
		System.out.println(result);
		assertFalse(result);

		Predicate<Integer> intIsEqual = Predicate.isEqual(6);
		result = intIsEqual.test(6);
		System.out.println(result);
		assertTrue(result);
		result = intIsEqual.test(3);
		System.out.println(result);
		assertFalse(result);
	}
}
