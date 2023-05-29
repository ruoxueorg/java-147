package org.ruoxue.java_147.conversion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.Maps;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ListToMapTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit {
		private String name;
		private double quantity;
		private int type;

		public Fruit(String name, double quantity, int type) {
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
			if (!(object instanceof Fruit)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Fruit other = (Fruit) object;
			return new EqualsBuilder().append(getName(), other.getName()).isEquals();
		}

		public int hashCode() {
			return new HashCodeBuilder().append(getName()).toHashCode();
		}
	}

	@Test
	public void traditional() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = new HashMap<>();
		for (Fruit e : list) {
			result.put(e.getName(), e);
		}
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}

	@Test
	public void toMap() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = list.stream().collect(Collectors.toMap(Fruit::getName, Function.identity()));
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}

	@Test(expected = IllegalStateException.class)
	public void toMapThrowException() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = list.stream().collect(Collectors.toMap(Fruit::getName, Function.identity()));
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}

	@Test
	public void toMapWithDuplicateKey() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = list.stream()
				.collect(Collectors.toMap(Fruit::getName, Function.identity(), (oldValue, newValue) -> oldValue));
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}

	@Test
	public void MapUtils_populateMap() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = new HashMap<>();
		MapUtils.populateMap(result, list, Fruit::getName);
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}

	@Test
	public void Maps_uniqueIndex() {
		List<Fruit> list = Arrays.asList(new Fruit("Apple", 1, 1), new Fruit("Banana", 2, 1),
				new Fruit("Cherry", 3, 1));
		Map<String, Fruit> result = Maps.uniqueIndex(list, Fruit::getName);
		System.out.println(result);
		assertThat(result.values()).containsAll(list);
	}
}
