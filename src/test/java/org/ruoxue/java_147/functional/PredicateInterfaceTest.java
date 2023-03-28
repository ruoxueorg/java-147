package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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

public class PredicateInterfaceTest {

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
	public void Collection_removeIf() {
		int expectedSize = 1;
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		list.removeIf(lengthGreaterThan);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		foodList.removeIf(lengthLessThan.and(contains));
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	@Test
	public void Collectors_partitioningBy() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		Map<Boolean, List<String>> map = list.stream().collect(Collectors.partitioningBy(lengthGreaterThan));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		Map<Boolean, List<Food>> foodMap = foodList.stream()
				.collect(Collectors.partitioningBy(lengthLessThan.and(contains)));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}

	@Test
	public void Pattern_asPredicate() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> contains = Pattern.compile("\\wo").asPredicate();
		List<String> result = list.stream().filter(contains).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Predicate<String> startsWith = Pattern.compile("^B").asPredicate();
		result = list.stream().filter(startsWith.and(contains)).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(1, result.size());
	}

	@Test
	public void Stream_filter() {
		int expectedSize = 2;
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		List<String> result = list.stream().filter(lengthGreaterThan).collect(Collectors.toList());
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		List<Food> foodResult = foodList.stream().filter(lengthLessThan.and(contains)).collect(Collectors.toList());
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());
	}

	@Test
	public void Stream_allMatch() {
		List<String> list = new ArrayList<>(Arrays.asList("Bacon", "Ham", "Pork"));
		Predicate<String> lengthGreaterThan = s -> s.length() > 2;
		boolean result = list.stream().allMatch(lengthGreaterThan);
		System.out.println(result);
		assertTrue(result);

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		boolean foodResult = foodList.stream().allMatch(lengthLessThan.and(contains));
		System.out.println(foodResult);
		assertFalse(foodResult);
	}

	@Test
	public void listOfPredicates() {

	}

	@Test
	public void methodReference() {

	}

	@Test
	public void methodParameter() {

	}
}
