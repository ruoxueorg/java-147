package org.ruoxue.java_147.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArraysWithExamplesTest {

	// fill
	// parallelPrefix
	// spliterator
	// stream

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
	public void setAll() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.setAll(array, i -> {
			String value = array[i];
			if (value.startsWith("G")) {
				return value.toUpperCase();
			}
			return value;
		});
		System.out.println(Arrays.toString(array));
	}

	@Test
	public void paralleSetAll() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));

		Arrays.parallelSetAll(array, i -> {
			String value = array[i];
			if (value.contains("a")) {
				return value.toUpperCase();
			}
			return value;
		});
		System.out.println(Arrays.toString(array));
	}

	@Test
	public void equals() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		String[] array2 = new String[] { "Durian", "Guava", "Pitaya" };
		boolean result = Arrays.equals(array, array2);
		System.out.println(result);
		assertTrue(result);

		String[] array3 = new String[] { "Mango" };
		result = Arrays.equals(array, array3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void deepToEquals() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Guava", 2, 1);
		Fruit guava = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		Fruit[] array2 = new Fruit[] { durian, pitaya, guava };
		boolean result = Arrays.equals(array, array2);
		System.out.println(result);
		assertTrue(result);

		Fruit[] array3 = new Fruit[] { new Fruit("Durian", 1, 1), new Fruit("Guava", 2, 1), new Fruit("Pitaya", 3, 1) };
		result = Arrays.equals(array, array3);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void toStringz() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		System.out.println(Arrays.toString(array));
	}

	@Test
	public void deepToString() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Guava", 2, 1);
		Fruit guava = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		System.out.println(Arrays.deepToString(array));
	}
}
