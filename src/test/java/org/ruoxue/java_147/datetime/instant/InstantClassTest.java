package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

import org.junit.Test;

public class InstantClassTest {

	@Test
	public void plus() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.plusSeconds(1);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:07.123456789Z", result.toString());
		
		result = instant.plusMillis(1);
		int milliOfSecond = result.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(124, milliOfSecond);
		
		result = instant.plusNanos(1);
		int nanoOfSecond = result.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456790, nanoOfSecond);
	}

	@Test
	public void plusTemporalAmount() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.plus(Duration.ofSeconds(1));
		System.out.println(result);
		assertEquals("2023-09-12T04:05:07.123456789Z", result.toString());
		
		result = instant.plus(Duration.ofMillis(1));
		int milliOfSecond = result.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(124, milliOfSecond);
		
		result = instant.plus(Duration.ofNanos(1));
		int nanoOfSecond = result.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456790, nanoOfSecond);
	}

	@Test
	public void minus() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.minusSeconds(1);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:05.123456789Z", result.toString());
		
		result = instant.minusMillis(1);
		int milliOfSecond = result.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(122, milliOfSecond);
		
		result = instant.minusNanos(1);
		int nanoOfSecond = result.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456788, nanoOfSecond);
	}

	@Test
	public void minusTemporalAmount() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.minus(Duration.ofSeconds(1));
		System.out.println(result);
		assertEquals("2023-09-12T04:05:05.123456789Z", result.toString());
		
		result = instant.minus(Duration.ofMillis(1));
		int milliOfSecond = result.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(122, milliOfSecond);
		
		result = instant.minus(Duration.ofNanos(1));
		int nanoOfSecond = result.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456788, nanoOfSecond);
	}

	@Test
	public void isBefore() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant instant2 = Instant.parse("2024-09-12T04:05:06.123456789Z");
		boolean result = instant.isBefore(instant2);
		System.out.println(result);
		assertTrue(result);
		
		result = instant2.isBefore(instant);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant instant2 = Instant.parse("2022-09-12T04:05:06.123456789Z");
		boolean result = instant.isAfter(instant2);
		System.out.println(result);
		assertTrue(result);
		
		result = instant2.isAfter(instant);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void atZone() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		ZoneId zone = ZoneId.of("UTC+8");
		ZonedDateTime result = instant.atZone(zone);
		System.out.println(result);
		assertEquals("2023-09-12T12:05:06.123456789+08:00[UTC+08:00]", result.toString());
	}

	@Test
	public void atOffset() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		OffsetDateTime result = instant.atOffset(ZoneOffset.ofHours(-4));
		System.out.println(result);
		assertEquals("2023-09-12T00:05:06.123456789-04:00", result.toString());
	}
}
