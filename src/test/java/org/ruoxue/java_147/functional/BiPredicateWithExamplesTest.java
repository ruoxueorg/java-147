package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.function.BiPredicate;
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
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
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
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
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
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		Food food = new Food("Bacon", 1, 1);
		boolean result = startsWith.and(contains).test(food, "B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 3, 1);
		result = startsWith.and(contains).test(food, "B");
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void or() {
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		Food food = new Food("Pork", 3, 1);
		boolean result = startsWith.or(contains).test(food, "o");
		System.out.println(result);
		assertTrue(result);
		result = startsWith.or(contains).test(food, "k");
		System.out.println(result);
		assertTrue(result);
	}

	@Test
	public void chaining() {
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> endsWith = (o, s) -> o.name.endsWith(s);
		Food food = new Food("BaconB", 1, 1);
		boolean result = contains.and(startsWith).or(endsWith).test(food, "B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 2, 1);
		result = contains.and(startsWith).or(endsWith).test(food, "B");
		System.out.println(result);
		assertFalse(result);
	}

	public static class StartsWith<E> implements BiPredicate<Food, String> {
		@Override
		public boolean test(Food t, String u) {
			return t.name.startsWith(u);
		}
	}

	@Test
	public void traditional() {
		BiPredicate<Food, String> startsWith = new StartsWith<Food>();
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		Food food = new Food("Bacon", 1, 1);
		boolean result = startsWith.and(contains).test(food, "B");
		System.out.println(result);
		assertTrue(result);
		food = new Food("Ham", 2, 1);
		result = startsWith.and(contains).test(food, "B");
		System.out.println(result);
		assertFalse(result);
	}
}
