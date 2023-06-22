package org.ruoxue.java_147.datetime.localdatetime;

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

public class LocalDateTimeClassTest {

	@Test
	public void plus() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.plusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDateTime.plusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(9, month);

		result = localDateTime.plusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(4, dayOfMonth);

		result = localDateTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = localDateTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = localDateTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void plusTemporalAmount() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.plus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDateTime.plus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(9, month);

		result = localDateTime.plus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(4, dayOfMonth);

		result = localDateTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(2, hour);

		result = localDateTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = localDateTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void minus() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.minusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = localDateTime.minusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = localDateTime.minusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(2, dayOfMonth);

		result = localDateTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(0, hour);

		result = localDateTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(1, minute);

		result = localDateTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void minusTemporalAmount() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.minus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = localDateTime.minus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = localDateTime.minus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(2, dayOfMonth);

		result = localDateTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(0, hour);

		result = localDateTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(1, minute);

		result = localDateTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void isBefore() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3);
		boolean result = localDateTime.isBefore(localDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = localDateTime2.isBefore(localDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2022, 8, 3, 1, 2, 3);
		boolean result = localDateTime.isAfter(localDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = localDateTime2.isAfter(localDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2023, 8, 4, 1, 2, 3);
		long result = localDateTime.until(localDateTime2, ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals(1, result);

		result = localDateTime.until(localDateTime2, ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals(24, result);

		result = localDateTime.until(localDateTime2, ChronoUnit.MINUTES);
		System.out.println(result);
		assertEquals(1440, result);
	}

	@Test
	public void atZone() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneId zone = ZoneId.of("UTC+8");
		ZonedDateTime result = localDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03+08:00[UTC+08:00]", result.toString());
	}

	@Test
	public void atOffset() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		OffsetDateTime result = localDateTime.atOffset(ZoneOffset.ofHours(-4));
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03-04:00", result.toString());
	}
}
