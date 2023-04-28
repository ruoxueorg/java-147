package org.ruoxue.java_147.functional.binaryoperator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
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
		foodMap.put(new Food("Bacon", 1, 1), null);
		foodMap.put(new Food("Ham", 2, 1), null);
		foodMap.put(new Food("Pork", 3, 1), null);
		BinaryOperator<Food> multiply = (k, v) -> {
			Food food = new Food();
			food.setName(k.name + v);
			food.setQuantity(k.name.length());
			return food;
		};
		Food foodKey = new Food("Bacon", 1, 1);
		Food foodResult = foodMap.compute(foodKey, multiply);
		System.out.println(foodResult);
		assertNotNull(foodResult);
		System.out.println(foodMap);
	}

	@Test
	public void Map_computeIfPresent() {
		Integer expected = 2;
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
xxx
		Map<Food, Integer> foodMap = new HashMap<Food, Integer>();
		foodMap.put(new Food("Bacon", 1, 1), 1);
		foodMap.put(new Food("Ham", 2, 1), 2);
		foodMap.put(new Food("Pork", 3, 1), 3);
		BiFunction<Food, Integer, Integer> multiply = (o, i) -> (int) o.quantity * 2;
		Food foodKey = new Food("Bacon", 1, 1);
		Integer foodResult = foodMap.computeIfPresent(foodKey, multiply);
		System.out.println(foodResult);
		assertEquals(expected, foodResult);
		foodKey = new Food("Bread", 1, 1);
		foodResult = foodMap.computeIfPresent(foodKey, multiply);
		System.out.println(foodResult);
		assertNull(foodResult);
		System.out.println(foodMap);
	}

	@Test
	public void Map_merge() {
		Integer expected = 11;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);
		String key = "Bacon";
		BiFunction<Integer, Integer, Integer> addition = (i, i2) -> i + i2;
		Integer result = map.merge(key, 10, addition);
		System.out.println(result);
		assertEquals(expected, result);

		key = "Bread";
		result = map.merge(key, 11, addition);
		System.out.println(result);
		assertEquals(expected, result);
		System.out.println(map);

		Map<Food, Integer> foodMap = new HashMap<Food, Integer>();
		foodMap.put(new Food("Bacon", 1, 1), 1);
		foodMap.put(new Food("Ham", 2, 1), 2);
		foodMap.put(new Food("Pork", 3, 1), 3);
		BiFunction<Integer, Integer, Integer> multiply = (i, i2) -> (int) i * i2;
		Food foodKey = new Food("Bacon", 1, 1);
		Integer foodResult = foodMap.merge(foodKey, 11, multiply);
		System.out.println(foodMap);
		assertEquals(expected, foodResult);
	}

	@Test
	public void Map_replaceAll() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);
		BiFunction<String, Integer, Integer> length = (s, i) -> s.length() + i;
		map.replaceAll(length);
		System.out.println(map);

		Map<Food, Integer> foodMap = new HashMap<Food, Integer>();
		foodMap.put(new Food("Bacon", 1, 1), 1);
		foodMap.put(new Food("Ham", 2, 1), 2);
		foodMap.put(new Food("Pork", 3, 1), 3);
		BiFunction<Food, Integer, Integer> multiply = (o, i) -> (int) o.name.length() * i;
		foodMap.replaceAll(multiply);
		System.out.println(foodMap);
	}
}
