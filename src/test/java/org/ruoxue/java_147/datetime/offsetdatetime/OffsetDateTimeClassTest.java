package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetDateTimeClassTest {

	@Test
	public void plus() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.plusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = offsetDateTime.plusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(1, month);

		result = offsetDateTime.plusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(26, dayOfMonth);

		result = offsetDateTime.plusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		result = offsetDateTime.plusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(5, minute);

		result = offsetDateTime.plusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void plusTemporalAmount() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.plus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = offsetDateTime.plus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(1, month);

		result = offsetDateTime.plus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(26, dayOfMonth);

		result = offsetDateTime.plus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(6, hour);

		result = offsetDateTime.plus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(5, minute);

		result = offsetDateTime.plus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(4, second);
	}

	@Test
	public void minus() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.minusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = offsetDateTime.minusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(11, month);

		result = offsetDateTime.minusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(24, dayOfMonth);

		result = offsetDateTime.minusHours(1);
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(4, hour);

		result = offsetDateTime.minusMinutes(1);
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = offsetDateTime.minusSeconds(1);
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void minusTemporalAmount() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = offsetDateTime.minus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = offsetDateTime.minus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(11, month);

		result = offsetDateTime.minus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(24, dayOfMonth);

		result = offsetDateTime.minus(Duration.ofHours(1));
		int hour = result.getHour();
		System.out.println(hour);
		assertEquals(4, hour);

		result = offsetDateTime.minus(Duration.ofMinutes(1));
		int minute = result.getMinute();
		System.out.println(minute);
		assertEquals(3, minute);

		result = offsetDateTime.minus(Duration.ofSeconds(1));
		int second = result.getSecond();
		System.out.println(second);
		assertEquals(2, second);
	}

	@Test
	public void isBefore() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime offsetDateTime2 = OffsetDateTime.of(2024, 12, 25, 5, 4, 3, 0, offset);
		boolean result = offsetDateTime.isBefore(offsetDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetDateTime2.isBefore(offsetDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime offsetDateTime2 = OffsetDateTime.of(2022, 12, 25, 5, 4, 3, 0, offset);
		boolean result = offsetDateTime.isAfter(offsetDateTime2);
		System.out.println(result);
		assertTrue(result);

		result = offsetDateTime2.isAfter(offsetDateTime);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime offsetDateTime2 = OffsetDateTime.of(2023, 12, 26, 5, 4, 3, 0, offset);
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
}
