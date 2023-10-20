package org.ruoxue.java_147.collector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BlueberryMelonFig");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRYMELONFIG");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining());
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("2147483647-13");
	}

	@Test
	public void joiningWithDelimiter() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("Blueberry, Melon, Fig");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRY, MELON, FIG");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining(", "));
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("2147483647, -1, 3");
	}

	@Test
	public void joiningWithPrefixSuffix() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[Blueberry, Melon, Fig]");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[BLUEBERRY, MELON, FIG]");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("[2147483647, -1, 3]");
	}
}
