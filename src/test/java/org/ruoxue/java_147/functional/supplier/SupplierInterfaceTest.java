package org.ruoxue.java_147.functional.supplier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
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
		Optional<String> opt = Optional.ofNullable(null);
		Supplier<String> defaultValue = () -> "DEFAULT_VALUE";
		String result = opt.orElseGet(defaultValue);
		System.out.println(result);
		assertEquals("DEFAULT_VALUE", result);

		Optional<Integer> intOpt = Optional.ofNullable(null);
		Supplier<Integer> intSupplier = () -> 0;
		int intResult = intOpt.orElseGet(intSupplier);
		System.out.println(intResult);
		assertEquals(0, intResult);

		Optional<List<String>> listOpt = Optional.ofNullable(null);
		Supplier<List<String>> listSupplier = ArrayList::new;
		List<String> listResult = listOpt.orElseGet(listSupplier);
		System.out.println(listResult);
		assertEquals(0, listResult.size());

		Optional<Food> foodOpt = Optional.ofNullable(null);
		Supplier<Food> foodSupplier = () -> new Food("DEFAULT_FOOD", 1, 1);
		Food foodResult = foodOpt.orElseGet(foodSupplier);
		System.out.println(foodResult);
		assertEquals("DEFAULT_FOOD", foodResult.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void Optional_orElseThrow() {
		Optional<String> opt = Optional.ofNullable(null);
		String result = opt.orElseThrow(IllegalArgumentException::new);
		System.out.println(result);
		assertFalse(opt.isPresent());
	}

	@Test
	public void Stream_collect() {
		int expectedSize = 2;
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Supplier<List<String>> supplier = () -> new ArrayList<>();
		BiConsumer<List<String>, String> accumulator = (l, s) -> l.add(s);
		BiConsumer<List<String>, List<String>> combiner = (l, l2) -> l.addAll(l2);
		List<String> result = list.stream().parallel().filter(e -> e.length() > 3).collect(supplier, accumulator,
				combiner);
		System.out.println(result);
		assertEquals(expectedSize, result.size());
	}

	@Test
	public void Stream_generate() {
		Stream.generate(() -> "Bacon").limit(1).forEach(e -> System.out.println(e));

		Supplier<Integer> intSupplier = () -> 0;
		Consumer<Integer> intConsumer = System.out::println;
		Stream.generate(intSupplier).limit(1).forEach(intConsumer);

		Supplier<List<String>> listSupplier = ArrayList::new;
		Stream.generate(listSupplier).limit(1).forEach(System.out::println);

		Supplier<Food> foodSupplier = () -> new Food("Bacon", 1, 1);
		Stream.generate(foodSupplier).limit(1).forEach(System.out::println);
	}
}
