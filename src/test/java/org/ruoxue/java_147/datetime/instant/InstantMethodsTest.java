package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class InstantMethodsTest {

	@Test
	public void now() {
		Instant instant = Instant.now();
		System.out.println(instant);

		ZoneId zone = ZoneId.of("America/New_York");
		Clock clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);

		zone = ZoneId.of("UTC-4");
		clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);
	}

	@Test
	public void ofEpochMilli() {
		Instant instant = Instant.ofEpochMilli(1694491506123L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123Z", instant.toString());
	}

	@Test
	public void ofEpochSecond() {
		Instant instant = Instant.ofEpochSecond(1694491506L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06Z", instant.toString());

		instant = Instant.ofEpochSecond(1694491506L, 123456789L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123456789Z", instant.toString());
	}

	@Test
	public void get() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		int milliOfSecond = instant.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(123, milliOfSecond);

		int nanoOfSecond = instant.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456789, nanoOfSecond);
	}

	@Test
	public void getValue() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		long epochSecond = instant.getEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1694491506, epochSecond);

		int nano = instant.getNano();
		System.out.println(nano);
		assertEquals(123456789, nano);
	}

	@Test
	public void with() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.with(ChronoField.MILLI_OF_SECOND, 223);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223Z", result.toString());

		result = instant.with(ChronoField.NANO_OF_SECOND, 223456789);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223456789Z", result.toString());
	}

	@Test
	public void withTemporalAdjuster() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		TemporalAdjuster temporalAdjuster = t -> t.plus(Duration.ofMillis(100));
		Instant result = instant.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223456789Z", result.toString());

		result = instant.with(t -> t.plus(Duration.ofNanos(100000000)));
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223456789Z", result.toString());
	}

	@Test
	public void toEpochMilli() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1694491506123L, result);
	}
}
