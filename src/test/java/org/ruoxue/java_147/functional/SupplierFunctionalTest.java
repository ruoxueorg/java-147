package org.ruoxue.java_147.functional;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

public class SupplierFunctionalTest {

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
	public void methodReference() {
		Supplier<Optional<String>> emptySupplier = Optional::empty;
		Optional<String> opt = emptySupplier.get();
		System.out.println(opt);
		assertFalse(opt.isPresent());

		Supplier<List<Food>> listSupplier = Collections::emptyList;
		List<Food> list = listSupplier.get();
		System.out.println(list);
		assertEquals(0, list.size());

		Supplier<LocalDateTime> localDateTimeSupplier = LocalDateTime::now;
		LocalDateTime localDateTime = localDateTimeSupplier.get();
		System.out.println(localDateTime);
		assertNotNull(localDateTime);
	}

	public static Optional<String> createOptional(Supplier<Optional<String>> supplier) {
		return supplier.get();
	}

	public static List<Food> createList(Supplier<List<Food>> supplier) {
		return supplier.get();
	}

	public static Map<Food, Integer> createMap(Supplier<Map<Food, Integer>> supplier) {
		return supplier.get();
	}

	@Test
	public void methodParameter() {
		Optional<String> opt = createOptional(Optional::empty);
		System.out.println(opt);
		assertFalse(opt.isPresent());

		List<Food> list = createList(() -> new ArrayList<>());
		System.out.println(list);
		assertEquals(0, list.size());

		Map<Food, Integer> map = createMap(() -> new HashMap<>());
		System.out.println(map);
		assertEquals(0, map.size());
	}
}
