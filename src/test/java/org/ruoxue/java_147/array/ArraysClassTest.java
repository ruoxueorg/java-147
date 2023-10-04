package org.ruoxue.java_147.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArraysClassTest {

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
	public void equals() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		String[] array2 = new String[] { "Durian", "Guava", "Pitaya" };
		boolean result = Arrays.equals(array, array2);
		System.out.println(result);
		assertThat(result).isTrue();

		String[] array3 = new String[] { "Mango" };
		result = Arrays.equals(array, array3);
		System.out.println(result);
		assertThat(result).isFalse();
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
		assertThat(result).isTrue();

		Fruit[] array3 = new Fruit[] { new Fruit("Durian", 1, 1), new Fruit("Guava", 2, 1), new Fruit("Pitaya", 3, 1) };
		result = Arrays.equals(array, array3);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void toStringz() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		String result = Arrays.toString(array);
		System.out.println(result);
		assertThat(result).isEqualTo("[Durian, Guava, Pitaya]");

		int[] intArray = new int[] { Integer.MAX_VALUE, -1, 3 };
		String intResult = Arrays.toString(intArray);
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("[2147483647, -1, 3]");
	}

	@Test
	public void deepToString() {
		Fruit durian = new Fruit("Durian", 1, 1);
		Fruit pitaya = new Fruit("Guava", 2, 1);
		Fruit guava = new Fruit("Pitaya", 3, 1);
		Fruit[] array = new Fruit[] { durian, pitaya, guava };
		String result = Arrays.deepToString(array);
		System.out.println(result);
		assertThat(result).isEqualTo(
				"[{\"name\":\"Durian\",\"quantity\":1.0,\"type\":1}, {\"name\":\"Guava\",\"quantity\":2.0,\"type\":1}, {\"name\":\"Pitaya\",\"quantity\":3.0,\"type\":1}]");
	}

	@Test
	public void parallelPrefix() {
		String[] array = new String[] { "Durian", "Guava", "Pitaya" };
		Arrays.parallelPrefix(array, (e1, e2) -> {
			return e1.toUpperCase() + "_" + e2;
		});
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly("Durian", "DURIAN_Guava", "DURIAN_GUAVA_Pitaya");
	}

	@Test
	public void parallelPrefixInt() {
		int[] array = new int[] { 1, 2, 3, 4, 5 };
		Arrays.parallelPrefix(array, (e1, e2) -> e1 * e2);
		System.out.println(Arrays.toString(array));
		assertThat(array).containsExactly(1, 2, 6, 24, 120);
	}
}
