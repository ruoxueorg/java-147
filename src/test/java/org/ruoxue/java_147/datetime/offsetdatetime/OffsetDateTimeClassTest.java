package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetDateTimeClassTest {

	@Test
	public void plus() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = offsetDateTime.plusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = offsetDateTime.plusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(9, month);

		result = offsetDateTime.plusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(4, dayOfMonth);

		result = offsetDateTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = offsetDateTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = offsetDateTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void plusTemporalAmount() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = offsetDateTime.plus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = offsetDateTime.plus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(9, month);

		result = offsetDateTime.plus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(4, dayOfMonth);

		result = offsetDateTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = offsetDateTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = offsetDateTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void minus() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = offsetDateTime.minusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = offsetDateTime.minusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = offsetDateTime.minusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(2, dayOfMonth);

		result = offsetDateTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(0, hour);

		result = offsetDateTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(1, minute);

		result = offsetDateTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void minusTemporalAmount() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = offsetDateTime.minus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = offsetDateTime.minus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = offsetDateTime.minus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(2, dayOfMonth);

		result = offsetDateTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(0, hour);

		result = offsetDateTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(1, minute);

		result = offsetDateTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void isBefore() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime offsetDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3);
		boolean result = offsetDateTime.isBefore(offsetDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetDateTime2.isBefore(offsetDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime offsetDateTime2 = LocalDateTime.of(2022, 8, 3, 1, 2, 3);
		boolean result = offsetDateTime.isAfter(offsetDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetDateTime2.isAfter(offsetDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime offsetDateTime2 = LocalDateTime.of(2023, 8, 4, 1, 2, 3);
		long result = offsetDateTime.until(offsetDateTime2, ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals(1, result);

		result = offsetDateTime.until(offsetDateTime2, ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals(24, result);

		result = offsetDateTime.until(offsetDateTime2, ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals(1440, result);
	}

	@Test
	public void atZone() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneId zone = ZoneId.of("UTC+8");
		ZonedDateTime result = offsetDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03+08:00[UTC+08:00]", result.toString());
	}

	@Test
	public void atOffset() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		OffsetDateTime result = offsetDateTime.atOffset(ZoneOffset.ofHours(-4));
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03-04:00", result.toString());
	}
}
