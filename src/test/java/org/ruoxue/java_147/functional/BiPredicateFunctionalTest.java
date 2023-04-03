package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
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

public class BiPredicateFunctionalTest {

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
		int expectedSize = 1;
		List<String> list = Arrays.asList("Bacon", "", "Ham", "Pork", "");
		BiPredicate<String, String> startsWith = String::startsWith;
		list = list.stream().filter(e -> startsWith.test(e, "B")).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("BaconB", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		BiPredicate<Food, String> endsWith = (o, s) -> o.name.endsWith(s);
		foodList = foodList.stream().filter(e -> contains.and(endsWith).test(e, "B")).collect(Collectors.toList());
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	public static List<String> filter(List<String> list, BiPredicate<String, Integer> biPredicate) {
		return list.stream().filter(e -> biPredicate.test(e, 3)).collect(Collectors.toList());
	}

	public static List<Food> foodFilter(List<Food> list, BiPredicate<Food, String> biPredicate) {
		return list.stream().filter(e -> biPredicate.test(e, "B")).collect(Collectors.toList());
	}

	@Test
	public void methodParameter() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		list = filter(list, lengthGreaterThan);
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		BiPredicate<Food, String> startsWith = (o, s) -> o.name.startsWith(s);
		BiPredicate<Food, String> contains = (o, s) -> o.name.contains(s);
		foodList = foodFilter(foodList, startsWith.and(contains));
		System.out.println(foodList);
		assertEquals(1, foodList.size());
	}

	@Test
	public void listOfBiPredicates() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork", "");
		BiPredicate<String, Integer> lengthGreaterThan = (s, i) -> s.length() > i;
		BiPredicate<String, Integer> substring = (s, i) -> s.substring(i).length() > 0;
		List<BiPredicate<String, Integer>> predicateList = Arrays.asList(lengthGreaterThan, substring);
		BiPredicate<String, Integer> biPredicate = predicateList.stream().reduce((s, i) -> true, BiPredicate::and);
		list = list.stream().filter(e -> biPredicate.test(e, 3)).collect(Collectors.toList());
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
		BiPredicate<Integer, Integer> greaterThan = (i, i2) -> i > i2;
		BiPredicate<Integer, Integer> lessThan = (i, i2) -> i < i2 + 3;
		List<BiPredicate<Integer, Integer>> intPredicateList = Arrays.asList(greaterThan, lessThan);
		intList = intList.stream()
				.filter(e -> intPredicateList.stream().reduce((s, i) -> true, BiPredicate::and).test(e, 3))
				.collect(Collectors.toList());
		System.out.println(intList);
		assertEquals(expectedSize, intList.size());

	}
}
