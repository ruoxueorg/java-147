package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<String> newOpt = opt.filter(e -> e.contains("B"));
		System.out.println(newOpt);
		boolean result = newOpt.isPresent();
		System.out.println(result);
		assertTrue(result);

		Optional<Integer> intOpt = Optional.of(147);
		System.out.println(intOpt);
		assertTrue(intOpt.isPresent());
		System.out.println(intOpt.get());

		Food food = new Food("Chicken", 2, 1);
		Optional<Food> foodOpt = Optional.of(food);
		System.out.println(foodOpt);
		assertTrue(foodOpt.isPresent());
		System.out.println(foodOpt.get());

		List<Food> list = new ArrayList<>();
		list.add(food);
		list.add(new Food("Duck", 3, 1));
		Optional<List<Food>> listOpt = Optional.of(list);
		System.out.println(listOpt);
		assertTrue(listOpt.isPresent());
		System.out.println(listOpt.get());
	}

	@Test
	public void map() {
		Optional<String> opt = Optional.ofNullable("Beef");
		Optional<Integer> newOpt = opt.map(e -> e.length());
		System.out.println(newOpt);
		int result = newOpt.orElse(0);
		System.out.println(result);
		assertEquals(4, result);

		opt = Optional.ofNullable(null);
		newOpt = opt.map(String::length);
		System.out.println(newOpt);
		assertEquals(Optional.empty(), newOpt);
		result = newOpt.orElse(0);
		System.out.println(result);
		assertEquals(0, result);
	}

	public static String findByIdWithTraditional(String id) {
		if (id != null) {
			return id;
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByIdWithTraditional() {
		String result = findByIdWithTraditional("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findByIdWithTraditional(null);
		System.out.println(result);
		assertNull(result);
	}

	public static String findByIdWithIsPresent(String id) {
		Optional<String> opt = Optional.ofNullable(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException(id);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByIdWithIsPresent() {
		String result = findByIdWithIsPresent("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findByIdWithIsPresent(null);
		System.out.println(result);
		assertNull(result);
	}

	public static String findByIdWithOrElseThrow(String id) {
		Optional<String> opt = Optional.ofNullable(id);
		return opt.orElseThrow(() -> new IllegalArgumentException(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void findByIdWithOrElseThrow() {
		String result = findByIdWithOrElseThrow("Beef");
		System.out.println(result);
		assertEquals("Beef", result);

		result = findByIdWithOrElseThrow(null);
		System.out.println(result);
		assertNull(result);
	}
}
