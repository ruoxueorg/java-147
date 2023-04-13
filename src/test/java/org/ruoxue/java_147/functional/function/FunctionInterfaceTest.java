package org.ruoxue.java_147.functional.function;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FunctionInterfaceTest {

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
	public void Optional_map() {
		Optional<String> opt = Optional.ofNullable("Bacon");
		Function<String, Integer> length = s -> s.length();
		Optional<Integer> lengthOpt = opt.map(length);
		int result = lengthOpt.orElse(0);
		System.out.println(result);
		assertEquals(5, result);

		Optional<Food> foodOpt = Optional.ofNullable(new Food("Ham", 1, 1));
		Function<Food, Integer> foodLength = o -> o.name.length();
		Function<Integer, Boolean> lengthLessThan = i -> i > 1;
		Optional<Boolean> booleanOpt = foodOpt.map(foodLength.andThen(lengthLessThan));
		boolean booleanResult = booleanOpt.orElse(false);
		System.out.println(booleanResult);
		assertTrue(booleanResult);
	}

	@Test
	public void Optional_flatMap() {

	}

	@Test
	public void Stream_map() {
		int expectedSize = 3;
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Function<String, Integer> length = String::length;
		Stream<Integer> stream = list.stream().map(length);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedSize, count);

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Function<Food, Integer> foodLength = o -> o.name.length();
		Function<Integer, Integer> twice = i -> i * i;
		stream = foodList.stream().map(foodLength.andThen(twice));
		count = stream.peek(System.out::println).count();
		assertEquals(expectedSize, count);
	}

	@Test
	public void Stream_flatMap() {

	}

	@Test
	public void Map_computeIfAbsent() {
		Integer expected = 5;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);
		Function<String, Integer> length = s -> s.length();
		Integer result = map.computeIfAbsent("Bread", length);
		System.out.println(map);
		assertEquals(expected, result);

		Map<Food, Integer> foodMap = new HashMap<Food, Integer>();
		foodMap.put(new Food("Bacon", 1, 1), 1);
		foodMap.put(new Food("Ham", 2, 1), 2);
		foodMap.put(new Food("Pork", 3, 1), 3);
		Function<Food, Integer> foodLength = o -> o.name.length();
		Function<Integer, Integer> twice = i -> i * i;
		result = foodMap.computeIfAbsent(new Food("Bread", 1, 1), foodLength.andThen(twice));
		System.out.println(foodMap);
		assertEquals(new Integer(25), result);
	}

	@Test
	public void Collectors_toMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Function<String, String> key = s -> s.toUpperCase();
		Function<String, Integer> length = s -> s.length();
		Map<String, Integer> map = list.stream().collect(Collectors.toMap(key, length));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Function<Food, String> foodKey = o -> o.name;
		Function<Food, Integer> foodLength = o -> o.name.length();
		Function<Integer, Integer> twice = i -> i * i;
		Map<String, Integer> foodMap = foodList.stream().collect(Collectors.toMap(foodKey, foodLength.andThen(twice)));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}

	@Test
	public void Collectors_groupingBy() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork", "Bread");
		Function<String, Integer> length = s -> s.length();
		Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(length));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = new ArrayList<>(Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1),
				new Food("Pork", 3, 2), new Food("Bread", 4, 3)));
		Map<Integer, List<Food>> foodMap = foodList.stream().collect(Collectors.groupingBy(Food::getType));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}
}
