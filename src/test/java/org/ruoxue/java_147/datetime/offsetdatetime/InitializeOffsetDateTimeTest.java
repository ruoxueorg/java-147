package org.ruoxue.java_147.datetime.offsetdatetime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class InitializeOffsetDateTimeTest {

	@Test
	public void now() {
		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.println(offsetDateTime);

		ZoneId zone = ZoneId.of("Egypt");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);

		zone = ZoneId.of("UTC+2");
		Clock clock = Clock.system(zone);
		offsetDateTime = OffsetDateTime.now(clock);
		System.out.println(offsetDateTime);

		zone = ZoneId.of("Etc/GMT-2");
		offsetDateTime = OffsetDateTime.now(zone);
		System.out.println(offsetDateTime);
	}

	@Test
	public void of() {
		ZoneOffset offset = ZoneOffset.ofHours(2);
		OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 12, 25, 5, 4, 3, 0, offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		long epochSecond = offsetDateTime.toEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1703473443L, epochSecond);

		offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 12, 25, 5, 4, 3, 0), offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());

		offsetDateTime = OffsetDateTime.of(LocalDate.of(2023, 12, 25), LocalTime.of(5, 4, 3, 0), offset);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());
	}

	@Test
	public void ofInstant() {
		ZoneId zone = ZoneId.of("Egypt");
		OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(Instant.parse("2023-12-25T03:04:03Z"), zone);
		System.out.println(offsetDateTime);
		assertEquals("2023-12-25T05:04:03+02:00", offsetDateTime.toString());
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
}
