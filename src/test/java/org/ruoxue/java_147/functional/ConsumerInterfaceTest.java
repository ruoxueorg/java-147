package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
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

public class ConsumerInterfaceTest {

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
	public void Stream_forEach() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Consumer<String> println = System.out::println;
		list.stream().forEach(println);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Consumer<Food> lengthLessThan = o -> System.out.println(o.name.length() < 6);
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		foodList.stream().forEach(lengthLessThan.andThen(contains));
	}

	@Test
	public void Stream_forEachOrdered() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Consumer<String> println = System.out::println;
		list.parallelStream().forEachOrdered(println);

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Consumer<Food> lengthLessThan = o -> System.out.println(o.name.length() < 6);
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		foodList.parallelStream().forEachOrdered(lengthLessThan.andThen(contains));
	}

	@Test
	public void Stream_peek() {
		List<String> list = Arrays.asList("Bacon", "Ham", "Pork");
		Consumer<String> println = System.out::println;
		list.stream().peek(println).count();

		List<Food> foodList = Arrays.asList(new Food("Bacon", 1, 1), new Food("Ham", 2, 1), new Food("Pork", 3, 1));
		Consumer<Food> lengthLessThan = o -> System.out.println(o.name.length() < 6);
		Consumer<Food> contains = o -> System.out.println(o.name.contains("o"));
		foodList.stream().peek(lengthLessThan.andThen(contains)).count();
	}
}
