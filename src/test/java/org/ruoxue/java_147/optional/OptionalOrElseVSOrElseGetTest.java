package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OptionalOrElseVSOrElseGetTest {

	@Rule
	public BenchmarkRule benchmarkRule = new BenchmarkRule();

	private static SecureRandom secureRandom = null;
	public static final String SHA1PRNG = "SHA1PRNG";
	public static List<Food> defaultValues = Arrays.asList(new Food("DEFAULT_Beef", 1, 1),
			new Food("DEFAULT_Chicken", 2, 1), new Food("DEFAULT_Duck", 3, 1));

	static {
		new Static();
	}

	protected static class Static {
		public Static() {
			try {
				secureRandom = SecureRandom.getInstance(SHA1PRNG);
				secureRandom.setSeed(System.nanoTime());
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
		}
	}

	@NoArgsConstructor
	@Getter
	@Setter
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

	public static Food getDefaultValue() {
		System.out.println("Call getDefaultValue()");
		int index = secureRandom.nextInt(defaultValues.size());
		return defaultValues.get(index);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0, concurrency = 1)
	public void orElse() {
		Optional<Food> opt = Optional.ofNullable(new Food("Beef", 1, 1));
		Food result = opt.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result.getName());
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0, concurrency = 1)
	public void orElseGet() {
		Optional<Food> opt = Optional.ofNullable(new Food("Beef", 1, 1));
		Food result = opt.orElseGet(() -> getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result.getName());
	}

	public static Food defaultValue() {
		int index = secureRandom.nextInt(defaultValues.size());
		return defaultValues.get(index);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 1, concurrency = 1)
	public void orElseBenchmark() {
		Optional<Food> opt = Optional.ofNullable(new Food("Beef", 1, 1));
		Food result = opt.orElse(defaultValue());
		assertEquals("Beef", result.getName());
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 1, concurrency = 1)
	public void orElseGetBenchmark() {
		Optional<Food> opt = Optional.ofNullable(new Food("Beef", 1, 1));
		Food result = opt.orElseGet(() -> defaultValue());
		assertEquals("Beef", result.getName());
	}
}
