package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.zone.ZoneRulesProvider;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LocalDateMethodsTest {

	@Test
	public void getAvailableZoneIds() {
		List<String> list = new ArrayList<>(ZoneRulesProvider.getAvailableZoneIds());
		System.out.println(list.size());
	}

	@Test
	public void now() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		ZoneId zone = ZoneId.of("America/Chicago");
		localDate = LocalDate.now(zone);
		System.out.println(localDate);

		Clock clock = Clock.system(zone);
		localDate = LocalDate.now(clock);
		System.out.println(localDate);
	}

	@Test
	public void of() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		System.out.println(localDate);
		assertEquals(2023, localDate.getYear());
		assertEquals(6, localDate.getMonthValue());
		assertEquals(18, localDate.getDayOfMonth());

		localDate = LocalDate.of(2023, Month.JUNE, 19);
		System.out.println(localDate);

		localDate = LocalDate.ofYearDay(2023, 171);
		System.out.println(localDate);

		localDate = LocalDate.ofEpochDay(19529);
		System.out.println(localDate);
	}

	@Test
	public void get() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.get(ChronoField.YEAR));
		System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));
		System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
		System.out.println(localDate.get(ChronoField.DAY_OF_WEEK));
		System.out.println(localDate.get(ChronoField.DAY_OF_YEAR));
	}

	@Test
	public void get_2() {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.getYear());
		System.out.println(localDate.getMonth());
		System.out.println(localDate.getMonthValue());
		System.out.println(localDate.getDayOfMonth());
		System.out.println(localDate.getDayOfWeek());
		System.out.println(localDate.getDayOfYear());
	}
}
