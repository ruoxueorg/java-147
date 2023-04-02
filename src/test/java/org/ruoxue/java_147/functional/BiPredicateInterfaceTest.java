package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
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

public class BiPredicateInterfaceTest {

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
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		list.removeIf(e -> lengthGreaterThan.test(e, 3));
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = new ArrayList<>(
				Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1)));
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		foodList.removeIf(e -> startsWith.and(contains).test(e, "B"));
		System.out.println(foodList);
		assertEquals(2, foodList.size());
	}

	@Test
	public void Collectors_partitioningBy() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		Map<Boolean, List<String>> map = list.stream()
				.collect(Collectors.partitioningBy(e -> lengthGreaterThan.test(e, 3)));
		System.out.println(map);
		assertEquals(expectedSize, map.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		Map<Boolean, List<Food>> foodMap = foodList.stream()
				.collect(Collectors.partitioningBy(e -> startsWith.and(contains).test(e, "B")));
		System.out.println(foodMap);
		assertEquals(expectedSize, foodMap.size());
	}

	@Test
	public void Stream_filter() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		list = list.stream().filter(e -> lengthGreaterThan.test(e, 3)).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		foodList = foodList.stream().filter(e -> startsWith.and(contains).test(e, "B")).collect(Collectors.toList());
		System.out.println(foodList);
		assertEquals(1, foodList.size());
	}

	@Test
	public void Stream_allMatch() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		boolean result = list.stream().allMatch(e -> lengthGreaterThan.test(e, 2));
		System.out.println(result);
		assertTrue(result);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		result = foodList.stream().allMatch(e -> startsWith.and(contains).test(e, "B"));
		System.out.println(result);
		assertFalse(result);
	}
}
