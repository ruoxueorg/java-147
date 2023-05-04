package org.ruoxue.java_147.collection;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ComparatorWithExamplesTest {

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
	public void comparing() {
		Comparator.comparing(null);
	}

	@Test
	public void comparingWithKeyComparator() {
		Comparator.comparing(null, null);
	}

	@Test
	public void comparingInt() {
		Comparator.comparingInt(null);
	}

	@Test
	public void comparingLong() {
		Comparator.comparingLong(null);
	}

	@Test
	public void comparingDouble() {
		Comparator.comparingDouble(null);
	}

	@Test
	public void nullsFirst() {
		Comparator.nullsFirst(null);
	}

	@Test
	public void nullsLast() {
		Comparator.nullsLast(null);
	}

	@Test
	public void naturalOrder() {
		Comparator.naturalOrder();
	}

	@Test
	public void reverseOrder() {
		Comparator.reverseOrder();
	}
}
