package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
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
	public void Pattern_asPredicate() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> contains = Pattern.compile("\\wo").asPredicate();
		list = list.stream().filter(contains).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		Predicate<String> startsWith = Pattern.compile("^B").asPredicate();
		list = list.stream().filter(startsWith.and(contains)).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(1, list.size());
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

	@Test
	public void methodReference() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "", "Ham", "Pork", "");
		Predicate<String> isEmpty = String::isEmpty;
		list = list.stream().filter(isEmpty).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), null, new Food("Ham", 2, 1),
				new Food("Pork", 3, 1), null);
		Predicate<Food> nonNull = Objects::nonNull;
		Predicate<Food> contains = o -> o.name.contains("o");
		foodList = foodList.stream().filter(nonNull.and(contains)).collect(Collectors.toList());
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	public static List<String> filter(List<String> list, Predicate<String> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
	}

	public static List<Food> foodFilter(List<Food> list, Predicate<Food> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
	}

	@Test
	public void methodParameter() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		list = filter(list, lengthGreaterThan);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Predicate<Food> lengthLessThan = o -> o.name.length() < 6;
		Predicate<Food> contains = o -> o.name.contains("o");
		foodList = foodFilter(foodList, lengthLessThan.and(contains));
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	@Test
	public void listOfPredicates() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", null, "Ham", "Pork", "");
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> lengthGreaterThan = s -> s.length() > 3;
		Predicate<String> contains = s -> s.contains("o");
		List<Predicate<String>> predicateList = Arrays.asList(nonNull, lengthGreaterThan, contains);
		list = list.stream().filter(predicateList.stream().reduce(x -> true, Predicate::and))
				.collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = Arrays.asList(1, null, 2, 3, 4, 5, 6, null);
		Predicate<Integer> intNonNull = Objects::nonNull;
		Predicate<Integer> greaterThan = i -> i > 3;
		Predicate<Integer> lessThan = i -> i < 6;
		List<Predicate<Integer>> intPredicateList = Arrays.asList(intNonNull, greaterThan, lessThan);
		intList = intList.stream().filter(intPredicateList.stream().reduce(x -> true, Predicate::and))
				.collect(Collectors.toList());
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());
	}
}
