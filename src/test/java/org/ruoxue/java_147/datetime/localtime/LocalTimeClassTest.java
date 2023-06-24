package org.ruoxue.java_147.datetime.localtime;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalTimeClassTest {

	@Test
	public void plus() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(10, hour);

		result = localTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(13, minute);

		result = localTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(6, second);
	}

	@Test
	public void plusTemporalAmount() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(10, hour);

		result = localTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(13, minute);

		result = localTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(6, second);
	}

	@Test
	public void minus() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = localTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(11, minute);

		result = localTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void minusTemporalAmount() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime result = localTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = localTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(11, minute);

		result = localTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void isBefore() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(10, 12, 5);
		boolean result = localTime.isBefore(localTime2);
		System.out.println(result);
		assertTrue(result);
		
		result = localTime2.isBefore(localTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(8, 12, 5);
		boolean result = localTime.isAfter(localTime2);
		System.out.println(result);
		assertTrue(result);
		
		result = localTime2.isAfter(localTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(10, 12, 5);
		long result = localTime.until(localTime2, ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals(1, result);

		result = localTime.until(localTime2, ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals(60, result);

		result = localTime.until(localTime2, ChronoUnit.SECONDS);
		System.out.println(result);
		assertEquals(3600, result);
	}

	@Test
	public void atOffset() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		OffsetTime result = localTime.atOffset(ZoneOffset.of("+01:00"));
		System.out.println(result);
		assertEquals("09:12:05+01:00", result.toString());

		localTime = LocalTime.of(9, 12, 5, 123456789);
		result = localTime.atOffset(ZoneOffset.ofHours(1));
		System.out.println(result);
		assertEquals("09:12:05.123456789+01:00", result.toString());
	}
}
