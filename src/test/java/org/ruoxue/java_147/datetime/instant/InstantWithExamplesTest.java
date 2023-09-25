package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalQueries;
import java.time.temporal.ValueRange;

import org.junit.Test;

public class InstantWithExamplesTest {

	@Test
	public void format() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		String result = formatter.format(instant);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.123456789Z", result);

		ZoneId zone = ZoneId.of("UTC+8");
		formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS").withZone(zone);
		result = formatter.format(instant);
		System.out.println(result);
		assertEquals("2023/09/12 12:05:06.123", result);
	}

	@Test
	public void parse() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1694491506123L, result);

		instant = Instant.parse("2023-09-12T04:05:06Z");
		result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1694491506000L, result);
	}

	@Test
	public void from() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = Instant.from(instant);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.123456789Z", result.toString());

		instant = Instant.parse("2023-09-12T04:05:06Z");
		result = Instant.from(instant);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06Z", result.toString());
	}

	@Test
	public void truncatedTo() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-09-12T04:00:00Z", result.toString());

		result = instant.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-09-12T00:00:00Z", result.toString());
	}

	@Test
	public void compareTo() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant instant2 = Instant.parse("2024-09-12T04:05:06.123456789Z");
		int result = instant.compareTo(instant2);
		System.out.println(result);
		assertTrue(result < 0);

		instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		instant2 = Instant.parse("2023-09-12T04:05:06.123456789Z");
		result = instant.compareTo(instant2);
		System.out.println(result);
		assertTrue(result == 0);

		instant = Instant.parse("2024-09-12T04:05:06.123456789Z");
		instant2 = Instant.parse("2023-09-12T04:05:06.123456789Z");
		result = instant.compareTo(instant2);
		System.out.println(result);
		assertTrue(result > 0);
	}

	@Test
	public void query() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		String result = instant.query(TemporalQueries.precision()).toString();
		System.out.println(result);
		assertEquals("Nanos", result);

		instant = Instant.now();
		ZoneOffset offset = ZoneOffset.ofHours(-4);
		OffsetDateTime offsetDateTime = instant.atOffset(offset);
		System.out.println(offsetDateTime);
		ZoneOffset zoneOffset = offsetDateTime.query(TemporalQueries.offset());
		System.out.println(zoneOffset);
		assertEquals("-04:00", zoneOffset.toString());

		ZoneId zone = ZoneId.of("America/New_York");
		ZonedDateTime zonedDateTime = instant.atZone(zone);
		System.out.println(zonedDateTime);
		ZoneId zoneId = zonedDateTime.query(TemporalQueries.zoneId());
		System.out.println(zoneId);
		assertEquals("America/New_York", zoneId.toString());
	}

	@Test
	public void range() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		ValueRange result = instant.range(ChronoField.MILLI_OF_SECOND);
		System.out.println(result);
		assertEquals("0 - 999", result.toString());

		instant.range(ChronoField.NANO_OF_SECOND);
		System.out.println(result);
		assertEquals("0 - 999", result.toString());
	}
}
