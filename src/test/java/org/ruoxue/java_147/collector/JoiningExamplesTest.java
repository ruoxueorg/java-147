package org.ruoxue.java_147.collector;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JoiningExamplesTest {

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
	public void joining() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String result = list.stream().map(Fruit::getName).collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BlueberryMelonFig");

		result = list.stream().map(e -> e.getName().toUpperCase()).collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRYMELONFIG");
	}

	@Test
	public void joiningWithDelimiter() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String result = list.stream().map(Fruit::getName).collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("Blueberry, Melon, Fig");

		result = list.stream().map(e -> e.getName().toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRY, MELON, FIG");
	}

	@Test
	public void joiningWithPrefixSuffix() {
		List<Fruit> list = Arrays.asList(new Fruit("Blueberry", Double.MAX_VALUE, 1), new Fruit("Melon", -1, 3),
				new Fruit("Fig", 3, 1));
		String result = list.stream().map(Fruit::getName).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[Blueberry, Melon, Fig]");

		result = list.stream().map(e -> e.getName().toUpperCase()).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[BLUEBERRY, MELON, FIG]");
	}
}
