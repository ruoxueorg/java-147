package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
}
