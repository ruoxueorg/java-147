package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

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

		@Override
		public int compareTo(Fruit o) {
			int ret = name.compareTo(o.name);
			if (ret == 0)
				ret = Double.compare(quantity, o.quantity);
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
		assertEquals("Mango", list.get(0).getName());
		assertEquals("Mango", list.get(1).getName());
		assertEquals("Orange", list.get(2).getName());
		assertEquals("Peach", list.get(3).getName());
	}
}
