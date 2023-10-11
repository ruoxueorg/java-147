package org.ruoxue.java_147.comparator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import com.google.common.collect.ComparisonChain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ComparableInterfaceTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Fruit implements Comparable<Fruit> {
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

		@Override
		public int compareTo(Fruit o) {
			int ret = this.name.compareTo(o.name);
			if (ret == 0)
				ret = Double.compare(this.quantity, o.quantity);
			return ret;
		}
	}

	@Test
	public void compareTo() {
		List<Fruit> list = Arrays.asList(new Fruit("Mango", Double.MAX_VALUE, 1), new Fruit("Mango", -1, 3),
				new Fruit("Peach", 3, 1), new Fruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertEquals(-1d, list.get(0).getQuantity(), 2);
		assertEquals(Double.MAX_VALUE, list.get(1).getQuantity(), 2);
	}

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class GuavaFruit implements Comparable<GuavaFruit> {
		private String name;
		private double quantity;
		private int type;

		public GuavaFruit(String name, double quantity, int type) {
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

		@Override
		public int compareTo(GuavaFruit o) {
			return ComparisonChain.start().compare(this.name, o.name).compare(this.quantity, o.quantity).result();
		}
	}

	@Test
	public void ComparisonChain_compare() {
		List<GuavaFruit> list = Arrays.asList(new GuavaFruit("Mango", Double.MAX_VALUE, 1),
				new GuavaFruit("Mango", -1, 3), new GuavaFruit("Peach", 3, 1), new GuavaFruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertEquals(-1d, list.get(0).getQuantity(), 2);
		assertEquals(Double.MAX_VALUE, list.get(1).getQuantity(), 2);
	}

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class ApacheFruit implements Comparable<ApacheFruit> {
		private String name;
		private double quantity;
		private int type;

		public ApacheFruit(String name, double quantity, int type) {
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

		@Override
		public int compareTo(ApacheFruit o) {
			return new CompareToBuilder().append(this.name, o.name).append(this.quantity, o.quantity).toComparison();
		}
	}

	@Test
	public void CompareToBuilder_toComparison() {
		List<ApacheFruit> list = Arrays.asList(new ApacheFruit("Mango", Double.MAX_VALUE, 1),
				new ApacheFruit("Mango", -1, 3), new ApacheFruit("Peach", 3, 1), new ApacheFruit("Orange", 2, 1));
		System.out.println(list);

		Collections.sort(list);
		System.out.println(list);
		assertEquals(-1d, list.get(0).getQuantity(), 2);
		assertEquals(Double.MAX_VALUE, list.get(1).getQuantity(), 2);
	}
}
