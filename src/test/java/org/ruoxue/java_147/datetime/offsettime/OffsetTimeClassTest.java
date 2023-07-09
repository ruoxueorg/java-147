package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetTimeClassTest {

	@Test
	public void plus() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime result = offsetTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(7, hour);

		result = offsetTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(2, minute);

		result = offsetTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(5, second);
	}

	@Test
	public void plusTemporalAmount() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime result = offsetTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(7, hour);

		result = offsetTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(2, minute);

		result = offsetTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(5, second);
	}

	@Test
	public void minus() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime result = offsetTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(5, hour);

		result = offsetTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(0, minute);

		result = offsetTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(3, second);
	}

	@Test
	public void minusTemporalAmount() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime result = offsetTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(5, hour);

		result = offsetTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(0, minute);

		result = offsetTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(3, second);
	}

	@Test
	public void isBefore() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime offsetTime2 = OffsetTime.of(7, 1, 4, 0, offset);
		boolean result = offsetTime.isBefore(offsetTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetTime2.isBefore(offsetTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime offsetTime2 = OffsetTime.of(5, 0, 3, 0, offset);
		boolean result = offsetTime.isAfter(offsetTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetTime2.isAfter(offsetTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		OffsetTime offsetTime2 = OffsetTime.of(7, 1, 4, 0, offset);
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
}
