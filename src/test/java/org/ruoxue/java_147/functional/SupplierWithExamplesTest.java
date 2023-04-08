package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.util.function.Supplier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SupplierWithExamplesTest {

	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class Food {
		private String name;
		private double quantity;
		private int type;

		public Food(String name, double quantity, int type) {
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
			if (!(object instanceof Food)) {
				return false;
			}
			if (this == object) {
				return true;
			}
			Food other = (Food) object;
			return new EqualsBuilder().append(getName(), other.getName()).isEquals();
		}

		public int hashCode() {
			return new HashCodeBuilder().append(getName()).toHashCode();
		}
	}

	@Test
	public void get() {
		Supplier<Food> value = () -> new Food("Bacon", 1, 1);
		Food result = value.get();
		System.out.println(result);
		assertNotNull(result);
		value = () -> new Food("Pork", 3, 1);
		result = value.get();
		System.out.println(result);
		assertNotNull(result);
	}

	public static class DefaultValue<E> implements Supplier<Food> {
		@Override
		public Food get() {
			return new Food("DEFAULT_Bacon", 1, 1);
		}
	}

	@Test
	public void traditional() {
		DefaultValue<Food> defaultValue = new DefaultValue<Food>();
		Food result = defaultValue.get();
		System.out.println(result);
		assertNotNull(result);
	}
}