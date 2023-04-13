package org.ruoxue.java_147.functional.unaryoperator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UnaryOperatorFunctionalTest {

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
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		UnaryOperator<String> toUpperCase = String::toUpperCase;
		List<String> result = list.stream().map(toUpperCase).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		List<Food> foodResult = foodList.stream().map(addition.andThen(multiply)).collect(Collectors.toList());
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());
	}

	public static List<String> map(List<String> list, UnaryOperator<String> unaryOperator) {
		return list.stream().map(unaryOperator).collect(Collectors.toList());
	}

	public static List<Food> foodMap(List<Food> list, Function<Food, Food> function) {
		return list.stream().map(function).collect(Collectors.toList());
	}

	@Test
	public void methodParameter() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
		List<String> result = map(list, toUpperCase);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		List<Food> foodResult = foodMap(foodList, addition.andThen(multiply));
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());
	}
}
