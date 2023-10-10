package org.ruoxue.java_147.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapTest {

	@Test
	public void Collections_unmodifiableMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", Integer.MAX_VALUE);
		map.put("Kiwifruit", -1);
		map.put("Lemon", 3);
		Map<String, Integer> result = Collections.unmodifiableMap(map);
		assertThatCode(() -> result.put("Papaya", 101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void Collections_singletonMap() {
		Map<String, Integer> result = Collections.singletonMap("Grape", Integer.MAX_VALUE);
		assertThatCode(() -> result.put("Kiwifruit", -1)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(1);
	}

	@Test
	public void ImmutableMap_of() {
		Map<String, Integer> map = ImmutableMap.of("Grape", Integer.MAX_VALUE, "Kiwifruit", -1, "Lemon", 3);
		assertThatCode(() -> map.put("Papaya", 101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(map);
		assertThat(map).hasSize(3);
	}

	@Test
	public void ImmutableMap_copyOf() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", Integer.MAX_VALUE);
		map.put("Kiwifruit", -1);
		map.put("Lemon", 3);
		Map<String, Integer> result = ImmutableMap.copyOf(map);
		assertThatCode(() -> result.put("Papaya", 101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void ImmutableMap_builder() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", Integer.MAX_VALUE);
		map.put("Kiwifruit", -1);
		map.put("Lemon", 3);
		Map<String, Integer> result = ImmutableMap.<String, Integer>builder().putAll(map).build();
		assertThatCode(() -> result.put("Papaya", 101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}

	@Test
	public void MapUtils_unmodifiableMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", Integer.MAX_VALUE);
		map.put("Kiwifruit", -1);
		map.put("Lemon", 3);
		Map<String, Integer> result = MapUtils.unmodifiableMap(map);
		assertThatCode(() -> result.put("Papaya", 101)).isInstanceOf(UnsupportedOperationException.class);
		System.out.println(result);
		assertThat(result).hasSize(3);
	}
}
