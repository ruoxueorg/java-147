package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalDateClassTest {

	@Test
	public void plus() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.plusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDate.plusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = localDate.plusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(19, dayOfMonth);
	}

	@Test
	public void plusTemporalAmount() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.plus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDate.plus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(7, month);

		result = localDate.plus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(19, dayOfMonth);
	}

	@Test
	public void minus() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.minusYears(1);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = localDate.minusMonths(1);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(5, month);

		result = localDate.minusDays(1);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(17, dayOfMonth);
	}

	@Test
	public void minusTemporalAmount() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.minus(Period.ofYears(1));
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2022, year);

		result = localDate.minus(Period.ofMonths(1));
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(5, month);

		result = localDate.minus(Period.ofDays(1));
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(17, dayOfMonth);
	}

	@Test
	public void isBefore() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2024, 6, 18);
		boolean result = localDate.isBefore(localDate2);
		System.out.println(result);
		assertTrue(result);

		result = localDate2.isBefore(localDate);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void isAfter() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2022, 6, 18);
		boolean result = localDate.isAfter(localDate2);
		System.out.println(result);
		assertTrue(result);
		
		result = localDate2.isAfter(localDate);
		System.out.println(result);
		assertFalse(result);
	}

	@Test
	public void until() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate localDate2 = LocalDate.of(2024, 6, 18);
		long result = localDate.until(localDate2, ChronoUnit.YEARS);
		System.out.println(result);
		assertEquals(1, result);

		result = localDate.until(localDate2, ChronoUnit.MONTHS);
		System.out.println(result);
		assertEquals(12, result);

		result = localDate.until(localDate2, ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals(366, result);
	}

	@Test
	public void atStartOfDay() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		ZoneId zone = ZoneId.of("UTC+8");
		ZonedDateTime result = localDate.atStartOfDay(zone);
		System.out.println(result);
		assertEquals("2023-06-18T00:00+08:00[UTC+08:00]", result.toString());
	}
}
