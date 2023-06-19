package org.ruoxue.java_147.datetime.zoneid;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

public class ZoneIdMethodsTest {

	@Test
	public void getAvailableZoneIds() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		System.out.println("size: " + zoneIds.size());
		assertTrue(zoneIds.size() > 0);
	}

	@Test
	public void systemDefault() {
		ZoneId zone = ZoneId.systemDefault();
		System.out.println(zone);
		System.out.println(zone.getRules());
		LocalDateTime localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
	}

	@Test
	public void getDisplayName() {
		ZoneId zone = ZoneId.of("Australia/Sydney");
		System.out.println(zone);
		assertEquals("Australia/Sydney", zone.getId());

		String result = zone.getDisplayName(TextStyle.FULL, Locale.ROOT);
		System.out.println(result);
		assertEquals("Australian Eastern Time (New South Wales)", result);

		result = zone.getDisplayName(TextStyle.SHORT, Locale.ROOT);
		System.out.println(result);
		assertEquals("AET", result);

		result = zone.getDisplayName(TextStyle.NARROW, Locale.ROOT);
		System.out.println(result);
		assertEquals("Australia/Sydney", result);
	}

	@Test
	public void of() {
		ZoneId zone = ZoneId.of("Australia/Sydney");
		System.out.println(zone);
		assertEquals("Australia/Sydney", zone.getId());
		LocalDateTime localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.of("UTC+10");
		System.out.println(zone);
		assertEquals("UTC+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
		
		zone = ZoneId.of("GMT+10");
		System.out.println(zone);
		assertEquals("GMT+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.of("Etc/GMT-10");
		System.out.println(zone);
		assertEquals("Etc/GMT-10", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
	}

	@Test
	public void ofOffset() {
		ZoneId zone = ZoneId.ofOffset("UTC", ZoneOffset.UTC);
		System.out.println(zone);
		LocalDateTime localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("UTC", ZoneOffset.of("+10:00"));
		System.out.println(zone);
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("GMT", ZoneOffset.ofHours(10));
		System.out.println(zone);
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("GMT", ZoneOffset.MIN);
		System.out.println(zone);
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("GMT", ZoneOffset.MAX);
		System.out.println(zone);
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
	}

	@Test
	public void from() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		ZoneId result = ZoneId.from(zonedDateTime);
		System.out.println(result);

		zonedDateTime = ZonedDateTime.parse("2023-08-03T01:02:03.999+08:00[Australia/Sydney]");
		result = ZoneId.from(zonedDateTime);
		System.out.println(result);
	}
}
