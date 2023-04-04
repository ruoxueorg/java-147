package org.ruoxue.java_147.functional;

import java.util.function.Consumer;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ConsumerWithExamplesTest {

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
		Consumer<Food> lessThan = o -> System.out.println(o.quantity < 3);
		Food food = new Food("Bacon", 1, 1);
		lessThan.accept(food);
		food = new Food("Pork", 3, 1);
		lessThan.accept(food);
	}

	@Test
	public void andThen() {
		Consumer<Food> nonNull = o -> System.out.println(Objects.nonNull(o));
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		Food food = new Food("Bacon", 1, 1);
		nonNull.andThen(contains).accept(food);
		food = new Food("Ham", 2, 1);
		nonNull.andThen(contains).accept(food);
	}

	@Test
	public void chaining() {
		Consumer<Food> nonNull = o -> System.out.println(Objects.nonNull(o));
		Consumer<Food> startsWith = o -> System.out.println(o.name.startsWith("B"));
		Consumer<Food> endsWith = o -> System.out.println(o.name.endsWith("n"));
		Food food = new Food("Bacon", 1, 1);
		nonNull.andThen(startsWith).andThen(endsWith).accept(food);
		food = new Food("Ham", 2, 1);
		nonNull.andThen(startsWith).andThen(endsWith).accept(food);
	}

	public static class LengthGreaterThan<E> implements Consumer<Food> {
		@Override
		public void accept(Food t) {
			System.out.println(t.name.length() > 3);
		}
	}

	@Test
	public void traditional() {
		Consumer<Food> lengthGreaterThan = new LengthGreaterThan<Food>();
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		Food food = new Food("Bacon", 1, 1);
		lengthGreaterThan.andThen(contains).accept(food);
		food = new Food("Ham", 2, 1);
		lengthGreaterThan.andThen(contains).accept(food);
	}
}
