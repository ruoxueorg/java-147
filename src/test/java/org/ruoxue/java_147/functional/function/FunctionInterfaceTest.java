package org.ruoxue.java_147.functional.function;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
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

//	Optional.flatMap
//	Stream.map
//	Stream.flatMap
//	Map.computeIfAbsent 
//	Collectors.toMap  
//	Collectors.groupingBy

	@Test
	public void Optional_map() {
		String value = "Bacon";
		Optional<String> opt = Optional.ofNullable(value);
		Function<String, Integer> length = s -> s.length();
		Optional<Integer> intOpt = opt.map(length);
		int result = intOpt.orElse(0);
		System.out.println(result);
		assertEquals(value.length(), result);

		Optional<Food> foodOpt = Optional.ofNullable(new Food("Ham", 1, 1));
		Function<Food, Boolean> lengthMod = o -> o.name.length() % 2 == 1;
		Optional<Boolean> booleanOpt = foodOpt.map(lengthMod);
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
		Function<String, Integer> length = s -> s.length();
		Stream<Integer> stream = list.stream().map(length);
		long count = stream.peek(System.out::println).count();
		assertEquals(expectedSize, count);

//		List<Food> foodList = new ArrayList<>(
//				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
//		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
//		Predicate<Food> contains = o -> o.name.contains("o");
//		foodList.removeIf(lengthLessThan.and(contains));
//		System.out.println(foodList);
//		assertEquals(expectedSize, foodList.size());
	}

	@Test
	public void Collectors_partitioningBy() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(lengthGreaterThan));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		Map<Boolean, List<Food>> foodMap = foodList.stream()
				.collect(Collectors.partitioningBy(lengthLessThan.and(contains)));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}

	@Test
	public void Stream_filter() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		list = list.stream().filter(lengthGreaterThan).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		foodList = foodList.stream().filter(lengthLessThan.and(contains)).collect(Collectors.toList());
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	@Test
	public void Stream_allMatch() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> lengthGreaterThan = s -> s.length() > 2;
		boolean result = list.stream().allMatch(lengthGreaterThan);
		System.out.println(result);
		assertTrue(result);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		result = foodList.stream().allMatch(lengthLessThan.and(contains));
		System.out.println(result);
		assertFalse(result);
	}
}
