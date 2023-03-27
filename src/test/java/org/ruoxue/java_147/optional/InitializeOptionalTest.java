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

public class InitializeOptionalTest {

	@Test
	public void empty() {
		Optional<String> emptyOpt = Optional.empty();
		System.out.println(emptyOpt);
		assertFalse(emptyOpt.isPresent());

		System.out.println(emptyOpt);
		emptyOpt = Optional.ofNullable(null);
		assertFalse(emptyOpt.isPresent());
	}

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
	public void of() {
		Optional<String> opt = Optional.of("Beef");
		System.out.println(opt);
		assertTrue(opt.isPresent());
		System.out.println(opt.get());

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

	@Test(expected = NullPointerException.class)
	public void ofThrowException() {
		Optional<String> opt = Optional.of(null);
		assertFalse(opt.isPresent());
	}

	@Test
	public void ofNullable() {
		Optional<String> opt = Optional.ofNullable("Beef");
		System.out.println(opt);
		assertTrue(opt.isPresent());
		System.out.println(opt.get());

		Optional<Integer> intOpt = Optional.ofNullable(147);
		System.out.println(intOpt);
		assertTrue(intOpt.isPresent());
		System.out.println(intOpt.get());

		Food food = new Food("Chicken", 2, 1);
		Optional<Food> foodOpt = Optional.ofNullable(food);
		System.out.println(foodOpt);
		assertTrue(foodOpt.isPresent());
		System.out.println(foodOpt.get());

		List<Food> list = new ArrayList<>();
		list.add(food);
		list.add(new Food("Duck", 3, 1));
		Optional<List<Food>> listOpt = Optional.ofNullable(list);
		System.out.println(listOpt);
		assertTrue(listOpt.isPresent());
		System.out.println(listOpt.get());
	}

	@Test
	public void ofNullableWithNull() {
		Optional<String> opt = Optional.ofNullable(null);
		System.out.println(opt);
		assertFalse(opt.isPresent());
		System.out.println(opt.orElse("DEFAULT_VALUE"));

		Optional<Integer> intOpt = Optional.ofNullable(null);
		System.out.println(intOpt);
		assertFalse(intOpt.isPresent());
		System.out.println(intOpt.orElse(0));

		Optional<Food> foodOpt = Optional.ofNullable(null);
		System.out.println(foodOpt);
		assertFalse(foodOpt.isPresent());
		System.out.println(foodOpt.orElse(null));

		Optional<List<Food>> listOpt = Optional.ofNullable(null);
		System.out.println(listOpt);
		assertFalse(listOpt.isPresent());
		System.out.println(listOpt.orElseGet(() -> new ArrayList<Food>()));
	}

	public static class FoodList {
		List<Food> list = null;

		public List<Food> getListWithTraditional() {
			if (null == this.list) {
				this.list = new ArrayList<Food>();
			}
			return this.list;
		}

		public List<Food> getListWithOptional() {
			return Optional.ofNullable(this.list).orElseGet(ArrayList::new);
		}
	}

	@Test
	public void getListWithTraditional() {
		FoodList foodList = new FoodList();
		List<Food> list = foodList.getListWithTraditional();
		System.out.println(list);
		assertNotNull(list);
	}

	@Test
	public void getListWithOptional() {
		FoodList foodList = new FoodList();
		List<Food> list = foodList.getListWithOptional();
		System.out.println(list);
		assertNotNull(list);
	}
}
