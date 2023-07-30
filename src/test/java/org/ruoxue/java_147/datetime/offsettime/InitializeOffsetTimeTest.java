package org.ruoxue.java_147.datetime.offsettime;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class InitializeOffsetTimeTest {

	@Test
	public void now() {
		OffsetTime offsetTime = OffsetTime.now();
		System.out.println(offsetTime);

		ZoneId zone = ZoneId.of("Brazil/Acre");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);

		zone = ZoneId.of("UTC-5");
		Clock clock = Clock.system(zone);
		offsetTime = OffsetTime.now(clock);
		System.out.println(offsetTime);

		zone = ZoneId.of("Etc/GMT+5");
		offsetTime = OffsetTime.now(zone);
		System.out.println(offsetTime);
	}

	@Test
	public void of() {
		ZoneOffset offset = ZoneOffset.ofHours(-5);
		OffsetTime offsetTime = OffsetTime.of(6, 1, 4, 0, offset);
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());

		offsetTime = OffsetTime.of(LocalTime.of(6, 1, 4, 0), offset);
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());
	}

	@Test
	public void parse() {
		OffsetTime offsetTime = OffsetTime.parse("06:01:04-05:00");
		System.out.println(offsetTime);
		assertEquals("06:01:04-05:00", offsetTime.toString());

		offsetTime = OffsetTime.parse("06:01:04.123456789-05:00");
		System.out.println(offsetTime);
		assertEquals("06:01:04.123456789-05:00", offsetTime.toString());
	}

	@Test
	public void from() {
		OffsetTime offsetTime = OffsetTime.parse("06:01:04.123456789-05:00");
		OffsetTime result = OffsetTime.from(offsetTime);
		System.out.println(result);
		assertEquals("06:01:04.123456789-05:00", result.toString());

		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2023-11-11T06:01:04-05:00[Brazil/Acre]");
		result = OffsetTime.from(zonedDateTime);
		System.out.println(result);
		assertEquals("06:01:04-05:00", result.toString());
	}
}
