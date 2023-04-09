package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.ruoxue.java_147.functional.BiConsumerInterfaceTest.Food;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SupplierInterfaceTest {

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
	public void Optional_orElseGet() {
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
	public void Optional_orElseThrow() {
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
	public void Stream_collect() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		list = list.stream().parallel().filter(e -> e.contains("o")).collect(() -> new ArrayList<>(),
				(c, e) -> c.add(e), (c1, c2) -> c1.addAll(c2));
		System.out.println(list);
		assertEquals(expectedSize, list.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		foodList = foodList.stream().parallel().filter(e -> e.quantity > 1).collect(() -> new ArrayList<>(),
				(c, e) -> c.add(e), (c1, c2) -> c1.addAll(c2));
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());

		foodList = foodList.stream().parallel().filter(e -> e.quantity > 1).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		System.out.println(foodList);
		assertEquals(expectedSize, foodList.size());
	}

	@Test
	public void Stream_generate() {

	}
}
