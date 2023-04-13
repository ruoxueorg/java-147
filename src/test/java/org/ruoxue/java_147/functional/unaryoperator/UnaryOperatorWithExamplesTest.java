package org.ruoxue.java_147.functional.unaryoperator;

import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.functional.unaryoperator.UnaryOperatorFunctionalTest.Food;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UnaryOperatorWithExamplesTest {

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
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		Food result = addition.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(4d, result.getQuantity(), 2);
		result = addition.apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertEquals(5d, result.getQuantity(), 2);

		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		result = multiply.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(2d, result.getQuantity(), 2);
		result = multiply.apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertEquals(6d, result.getQuantity(), 2);
	}

	@Test
	public void andThen() {
		UnaryOperator<Food> half = o -> {
			o.setQuantity(o.getQuantity() / 2);
			return o;
		};
		UnaryOperator<Food> twice = o -> {
			o.setQuantity(o.getQuantity() * o.getQuantity());
			return o;
		};
		Food result = half.andThen(twice).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(0.25d, result.getQuantity(), 2);

		result = half.andThen(twice).apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertEquals(2.25d, result.getQuantity(), 2);
	}

	@Test(expected = NullPointerException.class)
	public void andThenThrowException() {
		UnaryOperator<Double> half = d -> d / 2;
		Function<Double, Double> function = half.andThen(null);
		System.out.println(function);
	}

	@Test
	public void compose() {
		UnaryOperator<Food> half = o -> {
			o.setQuantity(o.getQuantity() / 2);
			return o;
		};
		UnaryOperator<Food> twice = o -> {
			o.setQuantity(o.getQuantity() * o.getQuantity());
			return o;
		};
		Food result = half.compose(twice).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals(1d, result.getQuantity(), 2);
	}

	@Test(expected = NullPointerException.class)
	public void composeThrowException() {
		UnaryOperator<Double> half = d -> d / 2;
		Function<Double, Double> function = half.compose(null);
		System.out.println(function);
	}

	@Test
	public void identity() {
		UnaryOperator<Food> identity = UnaryOperator.identity();
		Food result = identity.apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals("Bacon", result.getName());

		Object objResult = UnaryOperator.identity().apply(new Food("Ham", 2, 1));
		System.out.println(objResult);
		assertEquals("Ham", ((Food) objResult).getName());

		UnaryOperator<Integer> intIdentity = i -> i;
		int intResult = intIdentity.apply(7);
		System.out.println(intResult);
		assertEquals(7, intResult);
	}

	public static class ToUpperCase<E> implements UnaryOperator<Food> {
		@Override
		public Food apply(Food t) {
			t.setName(t.getName().toUpperCase());
			return t;
		}
	}

	@Test
	public void traditional() {
		UnaryOperator<Food> toUpperCase = new ToUpperCase<Food>();
		UnaryOperator<Food> toString = o -> {
			o.getName().toString();
			return o;
		};
		Food result = toUpperCase.andThen(toString).apply(new Food("Bacon", 1, 1));
		System.out.println(result);
		assertEquals("BACON", result.getName());
		result = toUpperCase.andThen(toString).apply(new Food("Ham", 3, 1));
		System.out.println(result);
		assertEquals("HAM", result.getName());
	}
}
