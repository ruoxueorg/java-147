package org.ruoxue.java_147.conversion.datetime;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class LocalDateTimeToZonedDateTimeTest {

	@Test
	public void atZone() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		System.out.println(localDateTime);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime result = localDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		System.out.println(localDateTime);
		result = localDateTime.atZone(zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());
	}

	@Test
	public void of() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		System.out.println(localDateTime);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZonedDateTime result = ZonedDateTime.of(localDateTime, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		System.out.println(localDateTime);
		result = ZonedDateTime.of(localDateTime, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());
	}

	@Test
	public void ofInstant() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		System.out.println(localDateTime);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZoneOffset zoneOffset = zone.getRules().getOffset(localDateTime);
		ZonedDateTime result = ZonedDateTime.ofInstant(localDateTime, zoneOffset, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		System.out.println(localDateTime);
		zoneOffset = zone.getRules().getOffset(localDateTime);
		result = ZonedDateTime.ofInstant(localDateTime, zoneOffset, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());
	}

	@Test
	public void ofLocal() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		System.out.println(localDateTime);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZoneOffset zoneOffset = zone.getRules().getOffset(localDateTime);
		ZonedDateTime result = ZonedDateTime.ofLocal(localDateTime, zone, zoneOffset);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		System.out.println(localDateTime);
		zoneOffset = zone.getRules().getOffset(localDateTime);
		result = ZonedDateTime.ofLocal(localDateTime, zone, zoneOffset);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());
	}

	@Test
	public void ofStrict() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 4, 5, 4, 3);
		System.out.println(localDateTime);
		ZoneId zone = ZoneId.of("Asia/Sakhalin");
		ZoneOffset zoneOffset = zone.getRules().getOffset(localDateTime);
		ZonedDateTime result = ZonedDateTime.ofStrict(localDateTime, zoneOffset, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());

		localDateTime = LocalDateTime.parse("2023-04-04T05:04:03");
		System.out.println(localDateTime);
		zoneOffset = zone.getRules().getOffset(localDateTime);
		result = ZonedDateTime.ofStrict(localDateTime, zoneOffset, zone);
		System.out.println(result);
		assertEquals("2023-04-04T05:04:03+11:00[Asia/Sakhalin]", result.toString());
	}
}
