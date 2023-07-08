package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class OffsetDateTimeWithExamplesTest {

	@Test
	public void format() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		String result = offsetDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-12-25T05:04:03+02:00", result);

		offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 123456789, offset);
		result = offsetDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-12-25T05:04:03.123456789+02:00", result);

		result = offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSZ"));
		System.out.println(result);
		assertEquals("2023/12/25 05:04:03.123+0200", result);
	}

	@Test
	public void parse() {
		OffsetDateTime offsetDateTime = OffsetDateTime.parse("2023-12-25T05:04:03+02:00");
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.parse("2023-12-25T05:04:03.123456789+02:00");
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03.123456789+02:00", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.parse("2023/12/25 05:04:03.123+0200",
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSZ"));
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03.123+02:00", offsetDateTime.toString());
	}

	@Test
	public void from() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetDateTime result = OffsetDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-12-25T05:04:03+02:00", result.toString());
	}

	@Test
	public void toEpochSecond() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		long result = offsetDateTime.toEpochSecond();
		System.out.println(result);
		assertEquals(1703473443L, result);
	}

	@Test
	public void toEpochMilli() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		Instant instant = offsetDateTime.toInstant();
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1703473443000L, result);

		offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 123456789, offset);
		instant = offsetDateTime.toInstant();
		result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1703473443123L, result);
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+2");
		Instant instant = Instant.ofEpochMilli(1703473443000L);
		OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		instant = Instant.ofEpochMilli(1703473443123L);
		offsetDateTime = OffsetDateTime.ofInstant(instant, zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03.123+02:00", offsetDateTime.toString());
	}

	@Test
	public void truncatedTo() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 123456789, offset);
		OffsetDateTime result = offsetDateTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-12-25T05:00+02:00", result.toString());

		result = offsetDateTime.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-12-25T00:00+02:00", result.toString());

		result = offsetDateTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("2023-12-25T05:04:03.123+02:00", result.toString());
	}

	@Test
	public void toOffsetTime() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		OffsetTime result = offsetDateTime.toOffsetTime();
		System.out.println(result);
		assertEquals("05:04:03+02:00", result.toString());
	}

	@Test
	public void toInstant() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		Instant result = offsetDateTime.toInstant();
		System.out.println(result);
		assertEquals("2023-12-25T03:04:03Z", result.toString());
	}
}
