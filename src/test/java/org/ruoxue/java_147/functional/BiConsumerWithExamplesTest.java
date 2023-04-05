package org.ruoxue.java_147.functional;

import java.util.function.BiConsumer;
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

public class BiConsumerWithExamplesTest {

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
	public void accept() {
		BiConsumer<Food, Integer> lessThan = (o, i) -> System.out.println(o.quantity < i);
		Food food = new Food("Bacon", 1, 1);
		lessThan.accept(food, 3);
		food = new Food("Pork", 3, 1);
		lessThan.accept(food, 3);
	}

	@Test
	public void andThen() {
		BiConsumer<Food, String> startsWith = (o, s) -> {
			Optional<Food> opt = Optional.ofNullable(o);
			opt.ifPresent(e -> System.out.println(e.name.startsWith(s)));
		};
		BiConsumer<Food, String> contains = (o, s) -> {
			Optional<Food> opt = Optional.ofNullable(o);
			opt.ifPresent(e -> System.out.println(e.name.contains(s)));
		};
		Food food = new Food("Bacon", 1, 1);
		startsWith.andThen(contains).accept(food, "B");
		startsWith.andThen(contains).accept(null, "B");
	}

	@Test
	public void chaining() {
		BiConsumer<Food, String> contains = (o, s) -> System.out.println(o.name.contains(s));
		BiConsumer<Food, String> startsWith = (o, s) -> System.out.println(o.name.startsWith(s));
		BiConsumer<Food, String> endsWith = (o, s) -> System.out.println(o.name.endsWith(s));
		Food food = new Food("Bacon", 1, 1);
		contains.andThen(startsWith).andThen(endsWith).accept(food, "B");
		food = new Food("Ham", 2, 1);
		contains.andThen(startsWith).andThen(endsWith).accept(food, "B");
	}

	public static class LengthGreaterThan<E> implements BiConsumer<Food, Integer> {
		@Override
		public void accept(Food t, Integer u) {
			System.out.println(t.name.length() > u);
		}
	}

	@Test
	public void traditional() {
		BiConsumer<Food, Integer> lengthGreaterThan = new LengthGreaterThan<Food>();
		BiConsumer<Food, Integer> mod = (o, i) -> System.out.println(o.name.length() % i == 1);
		Food food = new Food("Bacon", 1, 1);
		lengthGreaterThan.andThen(mod).accept(food, 4);
		food = new Food("Ham", 2, 1);
		lengthGreaterThan.andThen(mod).accept(food, 3);
	}
}
