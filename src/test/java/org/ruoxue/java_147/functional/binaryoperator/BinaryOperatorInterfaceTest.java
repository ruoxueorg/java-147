package org.ruoxue.java_147.functional.binaryoperator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
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

public class BinaryOperatorInterfaceTest {

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
	public void Map_compute() {
		Map<String, String> map = new HashMap<>();
		map.put("Bacon", "1");
		map.put("Ham", "2");
		map.put("Pork", "3");
		String key = "Bacon";
		BinaryOperator<String> addition = (k, v) -> k.length() + v + 1;
		String result = map.compute(key, addition);
		System.out.println(result);
		System.out.println(map);
		assertEquals("511", result);

		Map<Food, Food> foodMap = new HashMap<>();
		foodMap.put(new Food("Bacon", 1, 1), new Food("Bacon", 1, 1));
		foodMap.put(new Food("Ham", 2, 1), new Food("Ham", 2, 1));
		foodMap.put(new Food("Pork", 3, 1), new Food("Pork", 3, 1));
		BinaryOperator<Food> multiply = (k, v) -> {
			Food food = new Food();
			food.setName(k.name + v);
			food.setQuantity(k.name.length() * 2);
			return food;
		};
		Food foodKey = new Food("Bacon", 1, 1);
		Food foodResult = foodMap.compute(foodKey, multiply);
		System.out.println(foodResult);
		System.out.println(foodMap);
		assertEquals(10d, foodResult.getQuantity(), 2);
	}

	@Test
	public void Map_computeIfPresent() {
		Map<String, String> map = new HashMap<>();
		map.put("Bacon", "1");
		map.put("Ham", "2");
		map.put("Pork", "3");
		String key = "Bacon";
		BinaryOperator<String> addition = (k, v) -> k.length() + v + 1;
		String result = map.computeIfPresent(key, addition);
		System.out.println(result);
		assertEquals("511", result);
		key = "Bread";
		result = map.computeIfPresent(key, addition);
		System.out.println(result);
		assertNull(result);
		System.out.println(map);

		Map<Food, Food> foodMap = new HashMap<>();
		foodMap.put(new Food("Bacon", 1, 1), new Food("Bacon", 1, 1));
		foodMap.put(new Food("Ham", 2, 1), new Food("Ham", 2, 1));
		foodMap.put(new Food("Pork", 3, 1), new Food("Pork", 3, 1));
		BinaryOperator<Food> multiply = (k, v) -> {
			Food food = new Food();
			food.setName(k.name + v);
			food.setQuantity(k.name.length() * 2);
			return food;
		};
		Food foodKey = new Food("Bacon", 1, 1);
		Food foodResult = foodMap.computeIfPresent(foodKey, multiply);
		System.out.println(foodResult);
		assertEquals(10d, foodResult.getQuantity(), 2);
		foodKey = new Food("Bread", 1, 1);
		foodResult = foodMap.computeIfPresent(foodKey, multiply);
		System.out.println(foodResult);
		System.out.println(foodMap);
		assertNull(foodResult);
	}

	@Test
	public void Map_merge() {
		Map<String, String> map = new HashMap<>();
		map.put("Bacon", "1");
		map.put("Ham", "2");
		map.put("Pork", "3");
		String key = "Bacon";
		BinaryOperator<String> addition = (k, v) -> k.length() + v + 1;
		String result = map.merge(key, "10", addition);
		System.out.println(result);
		assertEquals("1101", result);

		key = "Bread";
		result = map.merge(key, "11", addition);
		System.out.println(result);
		assertEquals("11", result);
		System.out.println(map);

		Map<Food, Food> foodMap = new HashMap<>();
		foodMap.put(new Food("Bacon", 1, 1), new Food("Bacon", 1, 1));
		foodMap.put(new Food("Ham", 2, 1), new Food("Ham", 2, 1));
		foodMap.put(new Food("Pork", 3, 1), new Food("Pork", 3, 1));
		BinaryOperator<Food> multiply = (k, v) -> {
			Food food = new Food();
			food.setName(k.name + v);
			food.setQuantity(k.name.length() * 2);
			return food;
		};
		Food foodKey = new Food("Bacon", 1, 1);
		Food foodResult = foodMap.merge(foodKey, new Food("Bread", 4, 1), multiply);
		System.out.println(foodMap);
		assertNotNull(foodResult);
	}

	@Test
	public void Map_replaceAll() {
		Map<String, String> map = new HashMap<>();
		map.put("Bacon", "1");
		map.put("Ham", "2");
		map.put("Pork", "3");
		BinaryOperator<String> addition = (k, v) -> k.length() + v + 1;
		map.replaceAll(addition);
		System.out.println(map);

		Map<Food, Food> foodMap = new HashMap<>();
		foodMap.put(new Food("Bacon", 1, 1), new Food("Bacon", 1, 1));
		foodMap.put(new Food("Ham", 2, 1), new Food("Ham", 2, 1));
		foodMap.put(new Food("Pork", 3, 1), new Food("Pork", 3, 1));
		BinaryOperator<Food> multiply = (k, v) -> {
			Food food = new Food();
			food.setName(k.name + v);
			food.setQuantity(k.name.length() * 2);
			return food;
		};
		foodMap.replaceAll(multiply);
		System.out.println(foodMap);
	}
}
