package org.ruoxue.java_147.functional.biconsumer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BiConsumerFunctionalTest {

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
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		List<String> result = list.stream().parallel().filter(e -> e.contains("o")).collect(() -> new ArrayList<>(),
				(l, e) -> l.add(e), (l1, l2) -> l1.addAll(l2));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		result = list.stream().parallel().filter(e -> e.contains("o")).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		List<Food> foodResult = foodList.stream().parallel().filter(e -> e.quantity > 1)
				.collect(() -> new ArrayList<>(), (l, e) -> l.add(e), (l1, l2) -> l1.addAll(l2));
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());

		foodResult = foodList.stream().parallel().filter(e -> e.quantity > 1).collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());
	}

	public static void forEach(Map<String, Integer> map, BiConsumer<String, Integer> biConsumer) {
		map.forEach(biConsumer);
	}

	public static void foodForEach(Map<Food, Integer> map, BiConsumer<Food, Integer> biConsumer) {
		map.forEach(biConsumer);
	}

	@Test
	public void methodParameter() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bacon", 1);
		map.put("Ham", 2);
		map.put("Pork", 3);
		BiConsumer<String, Integer> println = (s, i) -> System.out.println(s + ", " + i);
		forEach(map, println);

		Map<Food, Integer> foodMap = new HashMap<Food, Integer>();
		foodMap.put(new Food("Bacon", 1, 1), 1);
		foodMap.put(new Food("Ham", 2, 1), 2);
		foodMap.put(new Food("Pork", 3, 1), 3);
		BiConsumer<Food, Integer> lengthGreaterThan = (o, i) -> System.out.println(o.name.length() > i);
		BiConsumer<Food, Integer> lengthMod = (o, i) -> System.out.println(o.name.length() % i == 1);
		foodForEach(foodMap, lengthGreaterThan.andThen(lengthMod));
	}
}
