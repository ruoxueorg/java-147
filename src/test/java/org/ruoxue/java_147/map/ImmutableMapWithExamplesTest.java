package org.ruoxue.java_147.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ImmutableMapWithExamplesTest {

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
	}

	@Test
	public void Collections_unmodifiableMap() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		Fruit lemon = new Fruit("Lemon", 3, 1);
		Map<String, Fruit> map = new HashMap<String, Fruit>();
		map.put(grape.getName(), grape);
		map.put(kiwifruit.getName(), kiwifruit);
		map.put(lemon.getName(), lemon);
		Map<String, Fruit> result = Collections.unmodifiableMap(map);
		Fruit papaya = new Fruit("Papaya", 101, 2);
		assertThatCode(() -> result.put(papaya.getName(), papaya)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void Collections_singletonMap() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Map<String, Fruit> result = Collections.singletonMap(grape.getName(), grape);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		assertThatCode(() -> result.put(kiwifruit.getName(), kiwifruit))
				.isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(1);
	}

	@Test
	public void ImmutableMap_of() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		Fruit lemon = new Fruit("Lemon", 3, 1);
		Map<String, Fruit> map = ImmutableMap.of(grape.getName(), grape, kiwifruit.getName(), kiwifruit,
				lemon.getName(), lemon);
		Fruit papaya = new Fruit("Papaya", 101, 2);
		assertThatCode(() -> map.put(papaya.getName(), papaya)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(map);
		assertThat(map).hasSize(3);
	}

	@Test
	public void ImmutableMap_copyOf() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		Fruit lemon = new Fruit("Lemon", 3, 1);
		Map<String, Fruit> map = new HashMap<String, Fruit>();
		map.put(grape.getName(), grape);
		map.put(kiwifruit.getName(), kiwifruit);
		map.put(lemon.getName(), lemon);
		Map<String, Fruit> result = ImmutableMap.copyOf(map);
		Fruit papaya = new Fruit("Papaya", 101, 2);
		assertThatCode(() -> result.put(papaya.getName(), papaya)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ImmutableMap_builder() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		Fruit lemon = new Fruit("Lemon", 3, 1);
		Map<String, Fruit> map = new HashMap<String, Fruit>();
		map.put(grape.getName(), grape);
		map.put(kiwifruit.getName(), kiwifruit);
		map.put(lemon.getName(), lemon);
		Map<String, Fruit> result = ImmutableMap.<String, Fruit>builder().putAll(map).build();
		Fruit papaya = new Fruit("Papaya", 101, 2);
		assertThatCode(() -> result.put(papaya.getName(), papaya)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void MapUtils_unmodifiableMap() {
		Fruit grape = new Fruit("Grape", Double.MAX_VALUE, 1);
		Fruit kiwifruit = new Fruit("Kiwifruit", -1, 3);
		Fruit lemon = new Fruit("Lemon", 3, 1);
		Map<String, Fruit> map = new HashMap<String, Fruit>();
		map.put(grape.getName(), grape);
		map.put(kiwifruit.getName(), kiwifruit);
		map.put(lemon.getName(), lemon);
		Map<String, Fruit> result = MapUtils.unmodifiableMap(map);
		Fruit papaya = new Fruit("Papaya", 101, 2);
		assertThatCode(() -> result.put(papaya.getName(), papaya)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}
}
