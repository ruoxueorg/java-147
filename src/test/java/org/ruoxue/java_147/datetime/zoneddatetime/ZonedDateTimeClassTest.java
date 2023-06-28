package org.ruoxue.java_147.datetime.zoneddatetime;

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

public class ZonedDateTimeClassTest {

	@Test
	public void plus() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.plusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = zonedDateTime.plusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = zonedDateTime.plusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(7, dayOfMonth);

		result = zonedDateTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = zonedDateTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(9, minute);

		result = zonedDateTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(10, second);
	}

	@Test
	public void plusTemporalAmount() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.plus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = zonedDateTime.plus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = zonedDateTime.plus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(7, dayOfMonth);

		result = zonedDateTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(8, hour);

		result = zonedDateTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(9, minute);

		result = zonedDateTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(10, second);
	}

	@Test
	public void minus() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.minusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = zonedDateTime.minusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(5, month);

		result = zonedDateTime.minusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(5, dayOfMonth);

		result = zonedDateTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		result = zonedDateTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(7, minute);

		result = zonedDateTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(8, second);
	}

	@Test
	public void minusTemporalAmount() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = zonedDateTime.minus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = zonedDateTime.minus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(5, month);

		result = zonedDateTime.minus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(5, dayOfMonth);

		result = zonedDateTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		result = zonedDateTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(7, minute);

		result = zonedDateTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(8, second);
	}

	@Test
	public void isBefore() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2024, 6, 6, 7, 8, 9, 0, zone);
		boolean result = zonedDateTime.isBefore(zonedDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = zonedDateTime2.isBefore(zonedDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2022, 6, 6, 7, 8, 9, 0, zone);
		boolean result = zonedDateTime.isAfter(zonedDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = zonedDateTime2.isAfter(zonedDateTime);
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
