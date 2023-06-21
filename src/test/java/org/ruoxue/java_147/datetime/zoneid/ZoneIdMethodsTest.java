package org.ruoxue.java_147.datetime.zoneid;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ZoneIdMethodsTest {

	@Test
	public void getAvailableZoneIds() {
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		Map<String, String> result = sortZone(zoneIds);

		result.forEach((k, v) -> {
			String out = String.format("%-35s (UTC%s)", k, v);
			System.out.println(out);
		});
		System.out.println("----------");
		System.out.println("size: " + zoneIds.size());
		assertTrue(zoneIds.size() > 0);
	}

	public Map<String, String> sortZone(Set<String> zoneIds) {
		Map<String, String> result = new LinkedHashMap<>();
		Map<String, List<String>> map = new HashMap<>();
		LocalDateTime localDateTime = LocalDateTime.now();
		zoneIds.forEach(e -> {
			ZoneId zone = ZoneId.of(e);
			ZonedDateTime zonedDateTime = localDateTime.atZone(zone);
			ZoneOffset zoneOffset = zonedDateTime.getOffset();
			String offset = zoneOffset.getId().replaceAll("Z", "+00:00");
			List<String> regionList = map.computeIfAbsent(offset, k -> new ArrayList<>());
			regionList.add(e);
		});
		for (List<String> list : map.values()) {
			list.sort(Comparator.comparing(e -> e, (o, o2) -> o.compareTo(o2)));
		}
		map.entrySet().stream().sorted(Map.Entry.<String, List<String>>comparingByKey().reversed()).forEach((e) -> {
			String offset = e.getKey();
			List<String> ids = e.getValue();
			ids.forEach(id -> result.put(id, offset));
		});
		return result;
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

		zone = ZoneId.of("+10");
		System.out.println(zone);
		assertEquals("+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
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
		assertEquals("UTC", zone.getId());
		LocalDateTime localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("UTC", ZoneOffset.of("+10"));
		System.out.println(zone);
		assertEquals("UTC+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
		
		zone = ZoneId.ofOffset("UTC", ZoneOffset.of("+10:00"));
		System.out.println(zone);
		assertEquals("UTC+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);

		zone = ZoneId.ofOffset("GMT", ZoneOffset.ofHours(10));
		System.out.println(zone);
		assertEquals("GMT+10:00", zone.getId());
		localDateTime = LocalDateTime.now(zone);
		System.out.println(localDateTime);
	}

	@Test
	public void from() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		ZoneId zone = ZoneId.from(zonedDateTime);
		System.out.println(zone);

		zonedDateTime = ZonedDateTime.parse("2023-12-25T00:00:00.000+10:00[Australia/Sydney]");
		zone = ZoneId.from(zonedDateTime);
		System.out.println(zone);
		assertEquals("Australia/Sydney", zone.getId());
	}
}
