package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetDateTimeWithExamplesTest {

	@Test
	public void format() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		String result = offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result);

		offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		result = offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03.123456789", result);

		result = offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
		System.out.println(result);
		assertEquals("2023/08/03 01:02:03.123", result);
	}

	@Test
	public void parse() {
		LocalDateTime offsetDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		offsetDateTime = LocalDateTime.parse("2023-08-03T01:02:03.123456789");
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", offsetDateTime.toString());

		offsetDateTime = LocalDateTime.parse("2023/08/03 01:02:03.123",
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123", offsetDateTime.toString());
	}

	@Test
	public void from() {
		LocalDateTime offsetDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		LocalDateTime result = LocalDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result.toString());
		
		offsetDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(offsetDateTime, zoneOffset);
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
	public void ofInstant() {
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = Instant.ofEpochSecond(1690992123L);
		LocalDateTime offsetDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		instant = Instant.ofEpochSecond(1690992123L, 123456789);
		offsetDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", offsetDateTime.toString());
	}

	@Test
	public void toEpochSecond() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		long result = offsetDateTime.toEpochSecond(zoneOffset);
		System.out.println(result);
		assertEquals(1690992123L, result);

		offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		zoneOffset = ZoneOffset.of("+09:00");
		result = offsetDateTime.toEpochSecond(zoneOffset);
		System.out.println(result);
		assertEquals(1690992123L, result);
	}

	@Test
	public void toEpochMilli() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = offsetDateTime.atZone(zone).toInstant();
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1690992123000L, result);

		offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		zone = ZoneId.of("UTC+9");
		instant = offsetDateTime.atZone(zone).toInstant();
		result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1690992123123L, result);
	}

	@Test
	public void ofEpochSecond() {
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		LocalDateTime offsetDateTime = LocalDateTime.ofEpochSecond(1690992123L, 0, zoneOffset);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		offsetDateTime = LocalDateTime.ofEpochSecond(1690992123L, 123456789, zoneOffset);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", offsetDateTime.toString());
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = Instant.ofEpochMilli(1690992123000L);
		LocalDateTime offsetDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		instant = Instant.ofEpochMilli(1690992123123L);
		offsetDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123", offsetDateTime.toString());
	}

	@Test
	public void timestamp() {
		LocalDateTime offsetDateTime = new Timestamp(1690995723000L).toLocalDateTime();
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03", offsetDateTime.toString());

		offsetDateTime = new Timestamp(1690995723123L).toLocalDateTime();
		System.out.println(offsetDateTime);
		assertEquals("2023-08-03T01:02:03.123", offsetDateTime.toString());
	}

	@Test
	public void truncatedTo() {
		LocalDateTime offsetDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime result = offsetDateTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-08-03T01:00", result.toString());

		result = offsetDateTime.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-08-03T00:00", result.toString());
		
		result = offsetDateTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03.123", result.toString());
	}
}
