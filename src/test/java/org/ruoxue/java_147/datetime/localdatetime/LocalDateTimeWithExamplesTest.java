package org.ruoxue.java_147.datetime.localdatetime;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class LocalDateTimeWithExamplesTest {

	@Test
	public void format() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		String result = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result);

		result = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		System.out.println(result);
		assertEquals("2023/08/03 01:02:03", result);
	}

	@Test
	public void parse() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
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
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = Instant.ofEpochSecond(1690995723L, 0);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}

	@Test
	public void toEpochSecond() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneOffset zoneOffset = ZoneOffset.of("+08:00");
		long result = localDateTime.toEpochSecond(zoneOffset);
		System.out.println(result);
		assertEquals(1690995723L, result);
	}

	@Test
	public void toEpochMilli() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneId zone = ZoneId.of("UTC+8");
		Instant instant = localDateTime.atZone(zone).toInstant();
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1690995723000L, result);
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

	@Test
	public void timestamp() {
		LocalDateTime localDateTime = new Timestamp(1690995723000L).toLocalDateTime();
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());
	}

	@Test
	public void truncatedTo() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime result = localDateTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-08-03T01:00", result.toString());

		result = localDateTime.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-08-03T00:00", result.toString());
	}
}
