package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

public class LocalDateMethodsTest {

	@Test
	public void now() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		ZoneId zone = ZoneId.of("America/Chicago");
		localDate = LocalDate.now(zone);
		System.out.println(localDate);

		zone = ZoneId.of("UTC-4");
		Clock clock = Clock.system(zone);
		localDate = LocalDate.now(clock);
		System.out.println(localDate);
	}

	@Test
	public void of() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());

		long epochDay = localDate.toEpochDay();
		System.out.println(epochDay);
		assertEquals(19526, epochDay);

		localDate = LocalDate.of(2023, Month.JUNE, 19);
		System.out.println(localDate);
		assertEquals("2023-06-19", localDate.toString());

		localDate = LocalDate.ofYearDay(2023, 171);
		System.out.println(localDate);
		assertEquals("2023-06-20", localDate.toString());

		localDate = LocalDate.ofEpochDay(19529);
		System.out.println(localDate);
		assertEquals("2023-06-21", localDate.toString());
	}

	@Test
	public void get() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		int year = localDate.get(ChronoField.YEAR);
		System.out.println(year);
		assertEquals(2023, year);

		int month = localDate.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(month);
		assertEquals(6, month);

		int dayOfMonth = localDate.get(ChronoField.DAY_OF_MONTH);
		System.out.println(dayOfMonth);
		assertEquals(18, dayOfMonth);

		int dayOfWeek = localDate.get(ChronoField.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		assertEquals(7, dayOfWeek);

		int dayOfYear = localDate.get(ChronoField.DAY_OF_YEAR);
		System.out.println(dayOfYear);
		assertEquals(169, dayOfYear);
	}

	@Test
	public void getValue() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		int year = localDate.getYear();
		System.out.println(year);
		assertEquals(2023, year);

		int month = localDate.getMonthValue();
		System.out.println(month);
		assertEquals(6, month);

		int dayOfMonth = localDate.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(18, dayOfMonth);

		int dayOfWeek = localDate.getDayOfWeek().getValue();
		System.out.println(dayOfWeek);
		assertEquals(7, dayOfWeek);

		int dayOfYear = localDate.getDayOfYear();
		System.out.println(dayOfYear);
		assertEquals(169, dayOfYear);
	}

	@Test
	public void withYearMonthDay() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.withYear(2024);
		int year = result.getYear();
		System.out.println(year);
		assertEquals(2024, year);

		result = localDate.withMonth(8);
		int month = result.getMonthValue();
		System.out.println(month);
		assertEquals(8, month);

		result = localDate.withDayOfMonth(3);
		int dayOfMonth = result.getDayOfMonth();
		System.out.println(dayOfMonth);
		assertEquals(3, dayOfMonth);
	}

	@Test
	public void with() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		LocalDate result = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println(result);
		assertEquals("2023-06-24", result.toString());

		result = localDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(result);
		assertEquals("2023-06-30", result.toString());

		TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
		result = localDate.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-07-02", result.toString());
	}
}
