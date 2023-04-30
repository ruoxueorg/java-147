package org.ruoxue.java_147.functional.binaryoperator;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.function.BinaryOperator;
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

public class BinaryOperatorWithExamplesTest {

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
		BinaryOperator<Food> merge = (o, o1) -> {
			Food result = new Food();
			result.setName(o.name.concat(o1.name));
			result.setQuantity(o.quantity + o1.quantity);
			result.setType(o.type + o1.type);
			return result;
		};
		Food food = new Food("Bacon", 1, 1);
		Food food2 = new Food("Ham", 2, 1);
		Food result = merge.apply(food, food2);
		System.out.println(result);
		assertEquals("BaconHam", result.getName());

		Food food3 = new Food("Pork", 3, 1);
		result = merge.apply(food2, food3);
		System.out.println(result);
		assertEquals(5d, result.getQuantity(), 2);
	}

	@Test
	public void andThen() {
		BinaryOperator<Food> concat = (o, o1) -> {
			o.setName(o.name.concat(o1.name));
			return o;
		};
		Function<Food, Integer> multiply = o -> o.name.length() * 2;
		Food food = new Food("Bacon", 1, 1);
		Food food2 = new Food("Ham", 2, 1);
		int result = concat.andThen(multiply).apply(food, food2);
		System.out.println(result);
		assertEquals(16, result);

		Food food3 = new Food("Pork", 3, 1);
		result = concat.andThen(multiply).apply(food2, food3);
		System.out.println(result);
		assertEquals(14, result);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		BinaryOperator<Food> ret = (o, o2) -> o2;
		ret.andThen(null);
	}

	@Test
	public void maxBy() {
		BinaryOperator<Food> maxBy = BinaryOperator.maxBy(Comparator.comparingDouble(Food::getQuantity));
		Food food = new Food("Bacon", 1, 1);
		Food food2 = new Food("Ham", 2, 1);
		Food result = maxBy.apply(food, food2);
		System.out.println(result);
		assertEquals(food2, result);
		Food food3 = new Food("Port", 3, 1);
		result = maxBy.apply(food2, food3);
		System.out.println(result);
		assertEquals(food3, result);
	}

	@Test
	public void minBy() {
		BinaryOperator<Food> minBy = BinaryOperator.minBy(Comparator.comparing(Food::getQuantity));
		Food food = new Food("Bacon", 1, 1);
		Food food2 = new Food("Ham", 2, 1);
		Food result = minBy.apply(food, food2);
		System.out.println(result);
		assertEquals(food, result);
		Food food3 = new Food("Port", 3, 1);
		result = minBy.apply(food2, food3);
		System.out.println(result);
		assertEquals(food2, result);
	}

	public static class Addition<E> implements BinaryOperator<Double> {
		@Override
		public Double apply(Double t, Double u) {
			return t + u;
		}
	}

	public static class Merge<E> implements BinaryOperator<Food> {
		@Override
		public Food apply(Food t, Food u) {
			Food result = new Food();
			result.setName(t.name.concat(u.name));
			result.setQuantity(t.quantity + u.quantity);
			result.setType(t.type + u.type);
			return result;
		}
	}

	@Test
	public void traditional() {
		BinaryOperator<Food> merge = new Merge<>();
		Function<Food, Integer> multiply = s -> s.name.length() * 2;
		Food food = new Food("Bacon", 1, 1);
		Food food2 = new Food("Ham", 2, 1);
		int result = merge.andThen(multiply).apply(food, food2);
		System.out.println(result);
		assertEquals(16, result);

		Food food3 = new Food("Pork", 3, 1);
		result = merge.andThen(multiply).apply(food2, food3);
		System.out.println(result);
		assertEquals(14, result);
	}
}
