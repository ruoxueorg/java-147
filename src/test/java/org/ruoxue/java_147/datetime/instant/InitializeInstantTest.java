package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

public class InitializeInstantTest {

	@Test
	public void now() {
		Instant instant = Instant.now();
		System.out.println(instant);

		ZoneId zone = ZoneId.of("America/New_York");
		Clock clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);

		zone = ZoneId.of("UTC-4");
		clock = Clock.system(zone);
		instant = Instant.now(clock);
		System.out.println(instant);
	}

	@Test
	public void parse() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		long result = instant.toEpochMilli();
		System.out.println(instant.toEpochMilli());
		assertEquals(1694491506123L, result);

		instant = Instant.parse("2023-09-12T04:05:06Z");
		result = instant.toEpochMilli();
		System.out.println(instant.toEpochMilli());
		assertEquals(1694491506000L, result);
	}

	@Test
	public void from() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = Instant.from(instant);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.123456789Z", result.toString());
	}

	@Test
	public void ofEpochMilli() {
		Instant instant = Instant.ofEpochMilli(1694491506123L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123Z", instant.toString());
	}

	@Test
	public void ofEpochSecond() {
		Instant instant = Instant.ofEpochSecond(1694491506L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06Z", instant.toString());

		instant = Instant.ofEpochSecond(1694491506L, 123456789L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123456789Z", instant.toString());
	}
}
