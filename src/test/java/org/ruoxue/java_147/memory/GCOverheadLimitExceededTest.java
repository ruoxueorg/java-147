package org.ruoxue.java_147.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GCOverheadLimitExceededTest {

	public static class Key {
		private Integer id;

		public Key(Integer id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}
	}

	public static class KeyEquals {
		Integer id;

		public KeyEquals(Integer id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}

		public boolean equals(Object object) {
			if (!(object instanceof KeyEquals)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			KeyEquals other = (KeyEquals) object;
			return id.equals(other.id);
		}
	}

	@Test
	public void list() {
		List<Key> list = new ArrayList<Key>();
		int counter = 1;
		while (true) {
			for (int i = 0; i < 1000; i++) {
				list.add(new Key(i));
			}
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
			System.out.println(list.size());
		}
	}

	@Test
	public void map() {
		Map<Key, Integer> map = new HashMap<Key, Integer>();
		int counter = 1;
		while (true) {
			for (int i = 0; i < 1000; i++) {
				map.put(new Key(i), i);
			}
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
			System.out.println(map.size());
		}
	}

	@Test
	public void byteArray() {
		Map<Byte[], Integer> map = new HashMap<Byte[], Integer>();
		int counter = 1;
		while (true) {
			for (int i = 0; i < 1000; i++) {
				map.put(new Byte[10], i);
			}
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
			System.out.println(map.size());
		}
	}

	@Test
	public void normal() {
		Map<KeyEquals, Integer> map = new HashMap<KeyEquals, Integer>();
		int counter = 1;
		while (true) {
			for (int i = 0; i < 1000; i++) {
				map.put(new KeyEquals(i), i);
			}
			Runtime rt = Runtime.getRuntime();
			System.out.printf("[%d] free memory: %s%n", counter++, rt.freeMemory());
			System.out.println(map.size());
		}
	}
}
