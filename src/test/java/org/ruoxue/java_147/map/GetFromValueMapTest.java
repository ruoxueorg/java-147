package org.ruoxue.java_147.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class GetFromValueMapTest {

	@Test
	public void keySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.put("Mango", null);
		map.put(null, 5);
		for (String key : map.keySet()) {
			System.out.println(key);
		}
	}

	public Set<String> getKeys(Map<String, Integer> map, Integer value) {
		Set<String> result = new HashSet<>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (Objects.equals(entry.getValue(), value)) {
				result.add(entry.getKey());
			}
		}
		return result;
	}

	@Test
	public void getKeysFromValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.put("Mango", null);
		map.put(null, 3);
		for (String key : getKeys(map, 3)) {
			System.out.println(key);
		}
	}

	@Test
	public void getKeysFromNullValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.put("Mango", null);
		map.put(null, 3);
		for (String key : getKeys(map, null)) {
			System.out.println(key);
		}
	}

	private Set<String> getKeysUseStream(Map<String, Integer> map, Integer value) {
		return map
				.entrySet()
				.stream()
				.filter(e -> Objects.equals(e.getValue(), value))
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());

	}

	@Test
	public void getKeysUseStreamFromValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.put("Mango", null);
		map.put(null, 3);
		for (String key : getKeysUseStream(map, 3)) {
			System.out.println(key);
		}
	}

	public Optional<String> getKeysOptional(Map<String, Integer> map, Integer value) {
		return map
				.entrySet()
				.stream()
				.filter(e -> Objects.equals(e.getValue(), value))
				.map(Map.Entry::getKey)
				.findFirst();

	}

	@Test(expected = NullPointerException.class)
	public void getKeysOptionalFromValue() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Grape", 1);
		map.put("Kiwifruit", 2);
		map.put("Lemon", 3);
		map.put("Mango", null);
		map.put(null, 3);
		Optional<String> optional = getKeysOptional(map, 3);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}
}
