package org.ruoxue.java_147.datetime.zoneddatetime;

import static org.junit.Assert.*;

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
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		String result = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09+03:00[Europe/Athens]", result);

		zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 123456789, zone);
		result = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09.123456789+03:00[Europe/Athens]", result);

		result = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSZ"));
		System.out.println(result);
		assertEquals("2023/06/06 07:08:09.123+0300", result);
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
	public void toEpochSecond() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		long result = zonedDateTime.toEpochSecond();
		System.out.println(result);
		assertEquals(1686024489L, result);
	}

	@Test
	public void toEpochMilli() {
		ZoneId zone = ZoneId.of("UTC+3");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		Instant instant = zonedDateTime.toInstant();
		long result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1686024489000L, result);

		zone = ZoneId.of("UTC+3");
		zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 123456789, zone);
		instant = zonedDateTime.toInstant();
		result = instant.toEpochMilli();
		System.out.println(result);
		assertEquals(1686024489123L, result);
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

	@Test
	public void truncatedTo() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 123456789, zone);
		ZonedDateTime result = zonedDateTime.truncatedTo(ChronoUnit.HOURS);
		System.out.println(result);
		assertEquals("2023-06-06T07:00+03:00[Europe/Athens]", result.toString());

		result = zonedDateTime.truncatedTo(ChronoUnit.DAYS);
		System.out.println(result);
		assertEquals("2023-06-06T00:00+03:00[Europe/Athens]", result.toString());

		result = zonedDateTime.truncatedTo(ChronoUnit.MILLIS);
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09.123+03:00[Europe/Athens]", result.toString());
	}
	

	@Test
	public void toOffsetDateTime() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		OffsetDateTime result = zonedDateTime.toOffsetDateTime();
		System.out.println(result);
		assertEquals("2023-06-06T07:08:09+03:00", result.toString());
	}

	@Test
	public void toInstant() {
		ZoneId zone = ZoneId.of("Europe/Athens");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 6, 6, 7, 8, 9, 0, zone);
		Instant result = zonedDateTime.toInstant();
		System.out.println(result);
		assertEquals("2023-06-06T04:08:09Z", result.toString());
	}	
}
