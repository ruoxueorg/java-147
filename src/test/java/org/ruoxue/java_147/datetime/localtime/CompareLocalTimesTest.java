package org.ruoxue.java_147.datetime.localtime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.junit.Test;

public class CompareLocalTimesTest {

	@Test
	public void compareTo() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(10, 12, 5);
		int result = localTime.compareTo(localTime2);
		System.out.println(result);
		assertThat(result).isLessThan(0);

		localTime = LocalTime.of(9, 12, 5);
		localTime2 = LocalTime.of(9, 12, 5);
		result = localTime.compareTo(localTime2);
		System.out.println(result);
		assertThat(result).isEqualTo(0);

		localTime = LocalTime.of(9, 12, 5);
		localTime2 = LocalTime.of(8, 12, 5);
		result = localTime.compareTo(localTime2);
		System.out.println(result);
		assertThat(result).isGreaterThan(0);
	}

	@Test
	public void isBefore() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(10, 12, 5);
		boolean result = localTime.isBefore(localTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localTime2.isBefore(localTime);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void isAfter() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(8, 12, 5);
		boolean result = localTime.isAfter(localTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localTime2.isAfter(localTime);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void equals() {
		LocalTime localTime = LocalTime.of(9, 12, 5);
		LocalTime localTime2 = LocalTime.of(9, 12, 5);
		boolean result = localTime.equals(localTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localTime2.equals(localTime);
		System.out.println(result);
		assertThat(result).isTrue();
	}
}
