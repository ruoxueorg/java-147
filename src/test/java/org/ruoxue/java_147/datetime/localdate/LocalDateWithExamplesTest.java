package org.ruoxue.java_147.datetime.localdate;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LocalDateWithExamplesTest {

	@Test
	public void format() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		String result = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(result);
		assertEquals("2023-06-18", result);
		
		result = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(result);
		assertEquals("20230618", result);

		result = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		System.out.println(result);
		assertEquals("2023/06/18", result);
	}

	@Test
	public void parse() {
		LocalDate localDate = LocalDate.parse("2023-06-18");
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());

		localDate = LocalDate.parse("20230618", DateTimeFormatter.ofPattern("yyyyMMdd"));
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

		LocalDateTime localDateTime = LocalDateTime.parse("2023-06-18T09:12:05");
		ZoneOffset zoneOffset = ZoneOffset.of("+05:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
		System.out.println(offsetDateTime);
		result = LocalDate.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-06-18", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-06-18T09:12:05+05:00[America/Chicago]");
		result = LocalDate.from(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-06-18", result.toString());
	}

	@Test
	public void toEpochDay() {
		LocalDate localDate = LocalDate.of(2023, 6, 18);
		long result = localDate.toEpochDay();
		System.out.println(result);
		assertEquals(19526L, result);
	}

	@Test
	public void ofEpochDay() {
		LocalDate localDate = LocalDate.ofEpochDay(19526L);
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}
	
	@Test
	public void ofYearDay() {
		LocalDate localDate = LocalDate.ofYearDay(2023, 169);
		System.out.println(localDate);
		assertEquals("2023-06-18", localDate.toString());
	}
}
