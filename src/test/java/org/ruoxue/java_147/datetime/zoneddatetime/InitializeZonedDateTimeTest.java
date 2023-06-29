package org.ruoxue.java_147.datetime.zoneddatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeZonedDateTimeTest {

	@Test
	public void now() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);

		ZoneId zone = ZoneId.of("Europe/Athens");
		zonedDateTime = ZonedDateTime.now(zone);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("UTC+3");
		Clock clock = Clock.system(zone);
		zonedDateTime = ZonedDateTime.now(clock);
		System.out.println(zonedDateTime);

		zone = ZoneId.of("Etc/GMT-3");
		zonedDateTime = ZonedDateTime.now(zone);
		System.out.println(zonedDateTime);
	}

	@Test
	public void of() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		long epochSecond = zonedDateTime.toEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1686024489L, epochSecond);

		zonedDateTime = ZonedDateTime.of(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.of(LocalDate.of(2023, 6, 6), LocalTime.of(7, 8, 9, 0), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.parse("2023-06-06T04:08:09Z"), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.ofInstant(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), ZoneOffset.ofHours(3), zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void ofLocal() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(LocalDateTime.of(2023, 6, 6, 7, 8, 9, 0), zone,
				ZoneOffset.ofHours(3));
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());
	}

	@Test
	public void parse() {
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-06-06T07:08:09+03:00[Europe/Athens]");
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.parse("2023-06-06T07:08:09.123456789+03:00[Europe/Athens]");
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09.123456789+03:00[Europe/Athens]", zonedDateTime.toString());

		zonedDateTime = ZonedDateTime.parse("2023/06/06 07:08:09.123+0300",
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSZ"));
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09.123+03:00", zonedDateTime.toString());
	}

	@Test
	public void from() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		ZonedDateTime result = ZonedDateTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", result.toString());

		LocalDateTime localDateTime = LocalDateTime.parse("2023-06-06T07:08:09");
		ZoneOffset zoneOffset = ZoneOffset.of("+03:00");
		OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
		System.out.println(offsetDateTime);
		result = ZonedDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09+03:00", result.toString());

		offsetDateTime = OffsetDateTime.of(2023, 6, 6, 7, 8, 9, 0, zoneOffset);
		result = ZonedDateTime.from(offsetDateTime);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09+03:00", result.toString());
	}

	@Test
	public void ofEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+3");
		Instant instant = Instant.ofEpochMilli(1686024489000L);
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09+03:00[UTC+03:00]", zonedDateTime.toString());

		instant = Instant.ofEpochMilli(1686024489123L);
		zonedDateTime = ZonedDateTime.ofInstant(instant, zone);
		System.out.println(zonedDateTime);
		assertEquals("2023-06-06T07:08:09.123+03:00[UTC+03:00]", zonedDateTime.toString());
	}
}
