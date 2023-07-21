package org.ruoxue.java_147.datetime.instant;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class InstantMethodsTest {

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
	public void ofEpochMilli() {
		Instant instant = Instant.ofEpochMilli(1694491506123L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06.123Z", instant.toString());

		instant = Instant.ofEpochMilli(1694491506000L);
		System.out.println(instant);
		assertEquals("2023-09-12T04:05:06Z", instant.toString());
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

	@Test
	public void get() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		int milliOfSecond = instant.get(ChronoField.MILLI_OF_SECOND);
		System.out.println(milliOfSecond);
		assertEquals(123, milliOfSecond);

		int microOfSecond = instant.get(ChronoField.MICRO_OF_SECOND);
		System.out.println(microOfSecond);
		assertEquals(123456, microOfSecond);

		int nanoOfSecond = instant.get(ChronoField.NANO_OF_SECOND);
		System.out.println(nanoOfSecond);
		assertEquals(123456789, nanoOfSecond);
	}

	@Test
	public void getValue() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		long epochSecond = instant.getEpochSecond();
		System.out.println(epochSecond);
		assertEquals(1694491506, epochSecond);

		int nano = instant.getNano();
		System.out.println(nano);
		assertEquals(123456789, nano);
	}

	@Test
	public void with() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		Instant result = instant.with(ChronoField.MILLI_OF_SECOND, 223);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223Z", result.toString());

		result = instant.with(ChronoField.MICRO_OF_SECOND, 323456);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.323456Z", result.toString());

		result = instant.with(ChronoField.NANO_OF_SECOND, 423456789);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.423456789Z", result.toString());
	}

	@Test
	public void withTemporalAdjuster() {
		Instant instant = Instant.parse("2023-09-12T04:05:06.123456789Z");
		TemporalAdjuster temporalAdjuster = t -> t.plus(Duration.ofMillis(100));
		Instant result = instant.with(temporalAdjuster);
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.223456789Z", result.toString());

		result = instant.with(t -> t.plus(Duration.ofNanos(300000000)));
		System.out.println(result);
		assertEquals("2023-09-12T04:05:06.423456789Z", result.toString());
	}

	@Test
	public void toEpochMilli() {
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
	public void nano() {
//		LocalDateTime localDateTime = LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0);
//		long e = localDateTime.toEpochSecond(ZoneOffset.of("+00:00"));
//		System.out.println(e);
//		Instant instant = Instant.now();
//		System.out.println(instant.getEpochSecond());
		// System.out.println(instant.getNano());

		for (int i = 0; i < 100000; i++) {
			Instant instant = Instant.now();
			//long mills = System.currentTimeMillis();
			// System.out.println(mills);
			// long EPOCH_NANOS = mills * 1_000_000;

			// 1689946842000000000
			long EPOCH_NANOS = instant.getEpochSecond() * 1_000_000_000;
			//System.out.println(EPOCH_NANOS);

			// 約為 1/1000000 秒
			// 返回的值是當前時間與 UTC 時間 1970 年 1 月 1 日午夜之間的差值（以納秒為單位）。
			long NANO_START = System.nanoTime();
			// 3097711466352414
			System.out.println(NANO_START);
			//System.out.println(NANO_START / 1_000_000_000);

			// EPOCH_NANOS*1_000_000-System.nanoTime()
			long OFFSET_NANOS = EPOCH_NANOS - NANO_START;
			System.out.println(OFFSET_NANOS);

			//long nano = System.nanoTime() + OFFSET_NANOS;
			// System.out.println(nano);
		}
	}

}
