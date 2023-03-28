package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OptionalClassTest {

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
	public void filter() {
		Optional<String> opt = Optional.ofNullable("Beef");
		opt = opt.filter(e -> e.startsWith("B"));
		System.out.println(opt);
		assertTrue(opt.isPresent());

		Food food = new Food("Chicken", 2, 1);
		Optional<Food> foodOpt = Optional.ofNullable(food);
		foodOpt = foodOpt.filter(o -> o.name.startsWith("B"));
		System.out.println(foodOpt);
		assertFalse(foodOpt.isPresent());

		List<Food> foodList = Arrays.asList(new Food("Beef", 1, 1), new Food("Chicken", 2, 1), new Food("Duck", 3, 1));
		Optional<List<Food>> foodListOpt = Optional.ofNullable(foodList);
		Predicate<List<Food>> predicate = list -> list.stream().filter(e -> e.name.startsWith("E")).count() > 0;
		foodListOpt = foodListOpt.filter(predicate);
		System.out.println(foodListOpt);
		assertFalse(foodListOpt.isPresent());
	}

	@Test
	public void map() {
		Optional<String> opt = Optional.ofNullable("Beef");
		Optional<Integer> newOpt = opt.map(e -> e.length());
		System.out.println(newOpt);
		int result = newOpt.orElse(0);
		System.out.println(result);
		assertEquals(4, result);

//		opt = Optional.ofNullable(null);
//		newOpt = opt.map(String::length);
//		System.out.println(newOpt);
//		assertEquals(Optional.empty(), newOpt);
//		result = newOpt.orElse(0);
//		System.out.println(result);
//		assertEquals(0, result);
	}

	public static String findWithTraditional(String id) {
		if (id != null) {
			return id;
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void findWithTraditional() {
		String result = findWithTraditional("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findWithTraditional(null);
		System.out.println(result);
		assertNull(result);
	}

	public static String findWithIsPresent(String id) {
		Optional<String> opt = Optional.ofNullable(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void findWithIsPresent() {
		String result = findWithIsPresent("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findWithIsPresent(null);
		System.out.println(result);
		assertNull(result);
	}

	public static String findWithOrElseThrow(String id) {
		Optional<String> opt = Optional.ofNullable(id);
		return opt.orElseThrow(() -> new IllegalArgumentException(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void findWithOrElseThrow() {
		String result = findWithOrElseThrow("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findWithOrElseThrow(null);
		System.out.println(result);
		assertNull(result);
	}
}
