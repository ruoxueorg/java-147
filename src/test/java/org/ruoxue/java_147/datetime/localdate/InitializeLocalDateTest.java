package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeLocalDateTest {

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
		assertEquals(19526L, epochDay);

		localDate = LocalDate.of(2023, Month.JUNE, 19);
		System.out.println(localDate);
		assertEquals("2023-06-19", localDate.toString());

		localDate = LocalDate.ofEpochDay(19528L);
		System.out.println(localDate);
		assertEquals("2023-06-20", localDate.toString());
	}

	@Test
	public void parse() {
		LocalDate localDate = LocalDate.parse("2023-06-18");
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());

		localDate = LocalDate.parse("2023/06/18", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}

	@Test
	public void from() {
		LocalDate localDate = LocalDate.parse("2023-06-18");
		LocalDate result = LocalDate.from(localDate);
		System.out.println(result);
		assertEquals("2023-06-18", result.toString());
	}

	@Test
	public void ofEpochDay() {
		LocalDate localDate = LocalDate.ofEpochDay(19526L);
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}
}
