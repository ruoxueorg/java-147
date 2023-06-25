package org.ruoxue.java_147.datetime.localdatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeLocalDateTimeTest {

	@Test
	public void now() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		ZoneId zone = ZoneId.of("Asia/Tokyo");
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.of("UTC+9");
		Clock clock = Clock.system(zone);
		localDateTime = LocalDateTime.now(clock);
		System.out.println(localDateTime);
		
		zone = ZoneId.of("Etc/GMT-9");
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
	}

	@Test
	public void of() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		long epochSecond = localDateTime.toEpochSecond(ZoneOffset.UTC);
		System.out.println(epochSecond);
		assertEquals(1691024523L, epochSecond);

		localDateTime = LocalDateTime.of(2023, Month.AUGUST, 4, 1, 2, 3);
		System.out.println(localDateTime);
		assertEquals("2023-08-04T01:02:03", localDateTime.toString());

		localDateTime = LocalDateTime.ofEpochSecond(1691197323, 0, ZoneOffset.UTC);
		System.out.println(localDateTime);
		assertEquals("2023-08-05T01:02:03", localDateTime.toString());
	}

	@Test
	public void ofDateTime() {
		LocalDate localDate = LocalDate.of(2023, 8, 3);
		LocalTime localTime = LocalTime.of(1, 2, 3);
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}

	@Test
	public void parse() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		localDateTime = LocalDateTime.parse("20230803 010203", DateTimeFormatter.ofPattern("yyyyMMdd HHmmss"));
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
		
		localDateTime = LocalDateTime.parse("2023/08/03 01:02:03", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}

	@Test
	public void from() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		LocalDateTime result = LocalDateTime.from(localDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());
		
		localDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
		System.out.println(offsetDateTime);
		result = LocalDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-08-03T01:02:03+09:00[Asia/Tokyo]");
		result = LocalDateTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());
	}

	@Test
	public void ofEpochSecond() {
		ZoneOffset zoneOffset = ZoneOffset.of("+08:00");
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1690995723L, 0, zoneOffset);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = Instant.ofEpochSecond(1690995723L, 0);
		localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = Instant.ofEpochMilli(1690995723000L);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}
}
