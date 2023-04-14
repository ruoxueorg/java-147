package org.ruoxue.java_147.functional.unaryoperator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
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

public class UnaryOperatorInterfaceTest {

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
		UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
		Optional<String> toUpperCaseOpt = opt.map(toUpperCase);
		String result = toUpperCaseOpt.orElse("");
		System.out.println(result);
		assertEquals("BACON", result);

		Optional<Food> foodOpt = Optional.ofNullable(new Food("Ham", 1, 1));
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		Optional<Food> booleanOpt = foodOpt.map(addition.andThen(multiply));
		Food foodResult = booleanOpt.orElse(null);
		System.out.println(foodResult);
		assertNotNull(foodResult);
	}

//	@Test
//	public void Optional_flatMap() {
//		Optional<Optional<String>> opt = Optional.ofNullable(Optional.ofNullable("Bacon"));
//		UnaryOperator<Optional<Optional<String>>> toUpperCase = s -> s;
//		Optional<Optional<String>> mapOpt = opt.map(toUpperCase);
//		System.out.println(mapOpt);
//		assertEquals(Optional.ofNullable(Optional.ofNullable("BACON")), mapOpt);
//
//		Optional<String> flatMapOpt = opt.flatMap(toUpperCase);
//		System.out.println(flatMapOpt);
//		assertEquals(Optional.ofNullable("BACON"), flatMapOpt);
//	}

	@Test
	public void Stream_map() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
		Stream<String> stream = list.stream().map(toUpperCase);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedSize, count);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		Stream<Food> foodStream = foodList.stream().map(addition.andThen(multiply));
		count = foodStream.peek(System.out::println).count();
		assertEquals(expectedSize, count);
	}

	@Test
	public void Stream_flatMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		UnaryOperator<String> toUpperCase = String::toUpperCase;
		List<String> result = list.stream().map(toUpperCase).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<List<String>> nestedlist = Arrays.asList(Arrays.asList("Bacon"), Arrays.asList("Ham"),
				Arrays.asList("Pork"));
		List<String> nestedResult = nestedlist.stream().flatMap(Collection::stream).collect(Collectors.toList());
		System.out.println(nestedResult);
		assertEquals(expectedSize, nestedResult.size());
	}

	@Test
	public void Map_computeIfAbsent() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Bacon", "Bacon");
		map.put("Ham", "Ham");
		map.put("Pork", "Pork");
		UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
		String result = map.computeIfAbsent("Bread", toUpperCase);
		System.out.println(map);
		assertEquals("BREAD", result);

		Map<Food, Food> foodMap = new HashMap<Food, Food>();
		foodMap.put(new Food("Bacon", 1, 1), new Food("Bacon", 1, 1));
		foodMap.put(new Food("Ham", 2, 1), new Food("Ham", 1, 2));
		foodMap.put(new Food("Pork", 3, 1), new Food("Pork", 1, 3));
		UnaryOperator<Food> addition = o -> {
			o.setQuantity(o.getQuantity() + 3);
			return o;
		};
		UnaryOperator<Food> multiply = o -> {
			o.setQuantity(o.getQuantity() * 2);
			return o;
		};
		Food foodResult = foodMap.computeIfAbsent(new Food("Bread", 1, 1), addition.andThen(multiply));
		System.out.println(foodMap);
		assertEquals(8d, foodResult.getQuantity(), 2);
	}

	@Test
	public void Collectors_toMap() {
		int expectedSize = 3;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		UnaryOperator<String> key = s -> s.toUpperCase();
		UnaryOperator<String> toLowerCase = s -> s.toLowerCase();
		Map<String, String> map = list.stream().collect(Collectors.toMap(key, toLowerCase));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		UnaryOperator<Food> foodKey = o -> o.name;
		UnaryOperator<Food> foodLength = o -> o.name.length();
		UnaryOperator<Integer, Integer> twice = i -> i * i;
		Map<String, Integer> foodMap = foodList.stream().collect(Collectors.toMap(foodKey, foodLength.andThen(twice)));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}
//
//	@Test
//	public void Collectors_groupingBy() {
//		int expectedSize = 3;
//		List<String> list = Arrays.asList("Bacon", "Ham", "Pork", "Bread");
//		UnaryOperator<String, Integer> length = s -> s.length();
//		Map<Integer, List<String>> map = list.stream().collect(Collectors.groupingBy(length));
//		System.out.println(map);
//		assertEquals(expectedSize, map.size());
//
//		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 2),
//				new Food("Bread", 4, 3));
//		Map<Integer, List<Food>> foodMap = foodList.stream().collect(Collectors.groupingBy(Food::getType));
//		System.out.println(foodMap);
//		assertEquals(expectedSize, foodMap.size());
//	}
}
