package org.ruoxue.java_147.optional;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

public class DifferenceOptionalOrElseOrElseGetTest {

	@Rule
	public BenchmarkRule benchmarkRule = new BenchmarkRule();

	private static SecureRandom secureRandom = null;
	public static final String SHA1PRNG = "SHA1PRNG";
	public static List<String> defaultValues = Arrays.asList("DEFAULT_Beef", "DEFAULT_Chicken", "DEFAULT_Duck");

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

	public static String getDefaultValue() {
		System.out.println("Call getDefaultValue()");
		int index = secureRandom.nextInt(defaultValues.size());
		return defaultValues.get(index);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0, concurrency = 1)
	public void orElse() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElse(getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0, concurrency = 1)
	public void orElseGet() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElseGet(() -> getDefaultValue());
		System.out.println(result);
		assertEquals("Beef", result);
	}

	public static String defaultValue() {
		int index = secureRandom.nextInt(defaultValues.size());
		return defaultValues.get(index);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 1, concurrency = 1)
	public void orElseBenchmark() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElse(defaultValue());
		assertEquals("Beef", result);
	}

	@Test
	@BenchmarkOptions(benchmarkRounds = 1000000, warmupRounds = 1, concurrency = 1)
	public void orElseGetBenchmark() {
		Optional<String> opt = Optional.ofNullable("Beef");
		String result = opt.orElseGet(() -> defaultValue());
		assertEquals("Beef", result);
	}
}
