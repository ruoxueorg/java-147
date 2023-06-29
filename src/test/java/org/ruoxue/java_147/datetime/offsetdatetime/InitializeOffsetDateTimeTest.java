package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeOffsetDateTimeTest {

	@Test
	public void now() {
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.println(offsetDateTime);

		ZoneId zone = ZoneId.of("Asia/Tokyo");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);

		zone = ZoneId.of("UTC+9");
		Clock clock = Clock.system(zone);
		offsetDateTime = OffsetDateTime.now(clock);
		System.out.println(offsetDateTime);
		
		zone = ZoneId.of("Etc/GMT-9");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);
	}

	@Test
	public void of() {
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 8, 3, 1, 2, 3);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		long epochSecond = offsetDateTime.toEpochSecond(ZoneOffset.UTC);
		System.out.println(epochSecond);
		assertEquals(1691024523L, epochSecond);

		offsetDateTime = OffsetDateTime.of(2023, Month.AUGUST, 4, 1, 2, 3);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-04T01:02:03", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.ofEpochSecond(1691197323, 0, ZoneOffset.UTC);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-05T01:02:03", offsetDateTime.toString());
	}

	@Test
	public void ofDateTime() {
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalTime localTime = LocalTime.of(1, 2, 3);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(localDate, localTime);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());
	}

	@Test
	public void parse() {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2023-08-03T01:02:03");
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.parse("20230803 010203", DateTimeFormatter.ofPattern("yyyyMMdd HHmmss"));
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());
		
		offsetDateTime = OffsetDateTime.parse("2023/08/03 01:02:03", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());
	}

	@Test
	public void from() {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2023-08-03T01:02:03");
		OffsetDateTime result = OffsetDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());
		
		offsetDateTime = OffsetDateTime.parse("2023-08-03T01:02:03");
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(offsetDateTime, zoneOffset);
		System.out.println(offsetDateTime);
		result = OffsetDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-08-03T01:02:03+09:00[Asia/Tokyo]");
		result = OffsetDateTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());
	}

	@Test
	public void ofEpochSecond() {
		ZoneOffset zoneOffset = ZoneOffset.of("+08:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.ofEpochSecond(1690995723L, 0, zoneOffset);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = Instant.ofEpochSecond(1690995723L, 0);
		offsetDateTime = OffsetDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = Instant.ofEpochMilli(1690995723000L);
		OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());
	}
}
