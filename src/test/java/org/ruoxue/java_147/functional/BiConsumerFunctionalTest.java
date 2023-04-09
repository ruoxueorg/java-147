package org.ruoxue.java_147.functional;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
				(c, e) -> c.add(e), (c1, c2) -> c1.addAll(c2));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Supplier<List<String>> supplier = () -> new ArrayList<>();
		BiConsumer<List<String>, String> accumulator = (l, i) -> l.add(i);
		BiConsumer<List<String>, List<String>> combiner = (l, l2) -> l.addAll(l2);

		result = list.stream().parallel().filter(e -> e.contains("o")).collect(supplier, accumulator, combiner);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

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
	public void aaa() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		List<String> result = list.stream().parallel().filter(e -> e.contains("o")).collect(() -> new ArrayList<>(),
				(c, e) -> c.add(e), (c1, c2) -> c1.addAll(c2));
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		Supplier<List<String>> supplier = () -> new ArrayList<>();
		BiConsumer<List<String>, String> accumulator = (l, i) -> l.add(i);
		BiConsumer<List<String>, List<String>> combiner = (l, l2) -> l.addAll(l2);
		result = list.stream().parallel().filter(e -> e.contains("o")).collect(supplier, accumulator, combiner);
		System.out.println(result);
		assertEquals(expectedSize, result.size());

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		List<Food> foodResult = foodList.stream().parallel().filter(e -> e.quantity > 1)
				.collect(() -> new ArrayList<>(), (c, e) -> c.add(e), (c1, c2) -> c1.addAll(c2));
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());

		Supplier<List<Food>> foodSupplier = () -> new ArrayList<>();
		BiConsumer<List<Food>, Food> foodAccumulator = (l, i) -> l.add(i);
		BiConsumer<List<Food>, List<Food>> foodCombiner = (l, l2) -> l.addAll(l2);

		foodResult = foodList.stream().parallel().filter(e -> e.quantity > 1).collect(foodSupplier, foodAccumulator,
				foodCombiner);
		System.out.println(foodResult);
		assertEquals(expectedSize, foodResult.size());
	}
	public static void forEach(List<String> list, Consumer<String> consumer) {
		list.stream().forEach(consumer);
	}

	public static void foodForEach(List<Food> list, Consumer<Food> consumer) {
		list.stream().forEach(consumer);
	}

	@Test
	public void methodParameter() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Consumer<String> lengthGreaterThan = s -> System.out.println(s.length() > 3);
		forEach(list, lengthGreaterThan);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Consumer<Food> lengthLessThan = o -> System.out.println(o.name.length() < 6);
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		foodForEach(foodList, lengthLessThan.andThen(contains));
	}
}
