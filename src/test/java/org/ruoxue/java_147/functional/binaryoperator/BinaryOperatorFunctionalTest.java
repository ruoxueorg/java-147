package org.ruoxue.java_147.functional.binaryoperator;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.function.BinaryOperator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BinaryOperatorFunctionalTest {

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
	public void methodReference() {
		BinaryOperator<String> concat = String::concat;
		String stringResult = concat.apply("Bacon", "Ham");
		System.out.println(stringResult);
		assertNotNull(stringResult);

		BinaryOperator<Integer> max = Integer::max;
		int intResult = max.apply(1, 10);
		System.out.println(intResult);
		assertEquals(10, intResult);

		BinaryOperator<Boolean> equals = Objects::equals;
		boolean booleanResult = equals.apply(Boolean.TRUE, Boolean.FALSE);
		System.out.println(booleanResult);
		assertFalse(booleanResult);
	}

	public static double calc(double d1, double d2, BinaryOperator<Double> binaryOperator) {
		return binaryOperator.apply(d1, d2);
	}

	public static Food foodCalc(Food o1, Food o2, BinaryOperator<Food> binaryOperator) {
		return binaryOperator.apply(o1, o2);
	}

	@Test
	public void methodParameter() {
		double result = calc(1d, 10d, (d1, d2) -> d1 + d2);
		System.out.println(result);
		assertEquals(11d, result, 2);

		Food foodResult = foodCalc(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), (o1, o2) -> {
			Food food = new Food("Pork", 3, 1);
			food.setQuantity(o1.quantity + o2.quantity);
			return food;
		});
		System.out.println(foodResult);
		assertEquals(3d, foodResult.getQuantity(), 2);
	}
}
