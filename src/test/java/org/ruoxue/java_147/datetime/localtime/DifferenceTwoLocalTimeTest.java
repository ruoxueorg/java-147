package org.ruoxue.java_147.datetime.localtime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class DifferenceTwoLocalTimeTest {

	@Test
	public void Duration_between() {
		LocalTime localTime = LocalTime.of(9, 12, 5, 123456789);
		LocalTime localTime2 = LocalTime.of(10, 12, 5, 123456789);
		Duration duration = Duration.between(localTime, localTime2);
		System.out.println(duration);

		long hours = duration.toHours();
		System.out.println(hours);
		assertThat(hours).isEqualTo(1);

		long minutes = duration.toMinutes();
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(60);

		long seconds = duration.getSeconds();
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(3600);

		long nanos = duration.toNanos();
		System.out.println(nanos);
		assertThat(nanos).isEqualTo(3600000000000L);
	}

	@Test
	public void ChronoUnit_between() {
		LocalTime localTime = LocalTime.of(9, 12, 5, 123456789);
		LocalTime localTime2 = LocalTime.of(10, 12, 5, 123456789);

		long hours = ChronoUnit.HOURS.between(localTime, localTime2);
		System.out.println(hours);
		assertThat(hours).isEqualTo(1);

		long minutes = ChronoUnit.MINUTES.between(localTime, localTime2);
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(60);

		long seconds = ChronoUnit.SECONDS.between(localTime, localTime2);
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(3600);

		long milliseconds = ChronoUnit.MILLIS.between(localTime, localTime2);
		System.out.println(milliseconds);
		assertThat(milliseconds).isEqualTo(3600000);

		long nano = ChronoUnit.NANOS.between(localTime, localTime2);
		System.out.println(nano);
		assertThat(nano).isEqualTo(3600000000000L);
	}

	@Test
	public void until() {
		LocalTime localTime = LocalTime.of(9, 12, 5, 123456789);
		LocalTime localTime2 = LocalTime.of(10, 12, 5, 987654321);

		long hours = localTime.until(localTime2, ChronoUnit.HOURS);
		System.out.println(hours);
		assertThat(hours).isEqualTo(1);

		long minutes = localTime.until(localTime2, ChronoUnit.MINUTES);
		System.out.println(minutes);
		assertThat(minutes).isEqualTo(60);

		long seconds = localTime.until(localTime2, ChronoUnit.SECONDS);
		System.out.println(seconds);
		assertThat(seconds).isEqualTo(3600);

		long milliseconds = localTime.until(localTime2, ChronoUnit.MILLIS);
		System.out.println(milliseconds);
		assertThat(milliseconds).isEqualTo(3600864);

		long nano = localTime.until(localTime2, ChronoUnit.NANOS);
		System.out.println(nano);
		assertThat(nano).isEqualTo(3600864197532L);
	}
}
