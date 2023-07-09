package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.OffsetTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetTimeClassTest {

	@Test
	public void plus() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime result = offsetTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(10, hour);

		result = offsetTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(13, minute);

		result = offsetTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(6, second);
	}

	@Test
	public void plusTemporalAmount() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime result = offsetTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(10, hour);

		result = offsetTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(13, minute);

		result = offsetTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(6, second);
	}

	@Test
	public void minus() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime result = offsetTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = offsetTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(11, minute);

		result = offsetTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void minusTemporalAmount() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime result = offsetTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = offsetTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(11, minute);

		result = offsetTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void isBefore() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime offsetTime2 = OffsetTime.of(10, 12, 5);
		boolean result = offsetTime.isBefore(offsetTime2);
		System.out.println(result);
		assertTrue(result);
		
		result = offsetTime2.isBefore(offsetTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime offsetTime2 = OffsetTime.of(8, 12, 5);
		boolean result = offsetTime.isAfter(offsetTime2);
		System.out.println(result);
		assertTrue(result);
		
		result = offsetTime2.isAfter(offsetTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime offsetTime2 = OffsetTime.of(10, 12, 5);
		long result = offsetTime.until(offsetTime2, ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals(1, result);

		result = offsetTime.until(offsetTime2, ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals(60, result);

		result = offsetTime.until(offsetTime2, ChronoUnit.SECONDS);
		System.out.println(result);
		assertEquals(3600, result);
	}

	@Test
	public void atOffset() {
		OffsetTime offsetTime = OffsetTime.of(9, 12, 5);
		OffsetTime result = offsetTime.atOffset(ZoneOffset.of("+01:00"));
		System.out.println(result);
		assertEquals("09:12:05+01:00", result.toString());

		offsetTime = OffsetTime.of(9, 12, 5, 123456789);
		result = offsetTime.atOffset(ZoneOffset.ofHours(1));
		System.out.println(result);
		assertEquals("09:12:05.123456789+01:00", result.toString());
	}
}
