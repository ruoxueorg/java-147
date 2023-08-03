package org.ruoxue.java_147.collector;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CollectorsClassTest {

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
	public void averagingInt() {
		Collectors.averagingInt(null);
	}

	@Test
	public void averagingLong() {
	}

	@Test
	public void averagingDouble() {
	}

	@Test
	public void summarizingInt() {
		Collectors.summarizingInt(null);
	}

	@Test
	public void summarizingLong() {
		Collectors.summarizingLong(null);
	}

	@Test
	public void summarizingDouble() {
		Collectors.summarizingDouble(null);
	}

}
