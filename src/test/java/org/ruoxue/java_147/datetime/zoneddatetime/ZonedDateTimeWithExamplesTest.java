package org.ruoxue.java_147.datetime.zoneddatetime;

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

public class ZonedDateTimeWithExamplesTest {

	@Test
	public void format() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		String result = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03", result);

		localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		result = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03.123456789", result);

		result = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
		System.out.println(result);
		assertEquals("2023/08/03 01:02:03.123", result);
	}

	@Test
	public void parse() {
		LocalDateTime localDateTime = LocalDateTime.parse("2023-08-03T01:02:03");
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		localDateTime = LocalDateTime.parse("2023-08-03T01:02:03.123456789");
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", localDateTime.toString());

		localDateTime = LocalDateTime.parse("2023/08/03 01:02:03.123",
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123", localDateTime.toString());
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
	public void ofInstant() {
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = Instant.ofEpochSecond(1690992123L);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		instant = Instant.ofEpochSecond(1690992123L, 123456789);
		localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", localDateTime.toString());
	}

	@Test
	public void toEpochSecond() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		long result = localDateTime.toEpochSecond(zoneOffset);
		System.out.println(result);
		assertEquals(1690992123L, result);

		localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		zoneOffset = ZoneOffset.of("+09:00");
		result = localDateTime.toEpochSecond(zoneOffset);
		System.out.println(result);
		assertEquals(1690992123L, result);
	}

	@Test
	public void toEpochMilli() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = localDateTime.atZone(zone).toInstant();
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1690992123000L, result);

		localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		zone = ZoneId.of("UTC+9");
		instant = localDateTime.atZone(zone).toInstant();
		result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1690992123123L, result);
	}

	@Test
	public void ofEpochSecond() {
		ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1690992123L, 0, zoneOffset);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		localDateTime = LocalDateTime.ofEpochSecond(1690992123L, 123456789, zoneOffset);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123456789", localDateTime.toString());
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+9");
		Instant instant = Instant.ofEpochMilli(1690992123000L);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		instant = Instant.ofEpochMilli(1690992123123L);
		localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123", localDateTime.toString());
	}

	@Test
	public void timestamp() {
		LocalDateTime localDateTime = new Timestamp(1690995723000L).toLocalDateTime();
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03", localDateTime.toString());

		localDateTime = new Timestamp(1690995723123L).toLocalDateTime();
		System.out.println(localDateTime);
		assertEquals("2023-08-03T01:02:03.123", localDateTime.toString());
	}

	@Test
	public void truncatedTo() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime result = localDateTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-08-03T01:00", result.toString());

		result = localDateTime.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-08-03T00:00", result.toString());
		
		result = localDateTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("2023-08-03T01:02:03.123", result.toString());
	}
}
