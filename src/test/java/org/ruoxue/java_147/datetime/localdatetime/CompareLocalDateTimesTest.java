package org.ruoxue.java_147.datetime.localdatetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class CompareLocalDateTimesTest {

	@Test
	public void compareTo() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3, 123456789);
		int result = localDateTime.compareTo(localDateTime2);
		System.out.println(result);
		assertThat(result).isLessThan(0);

		localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		localDateTime2 = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		result = localDateTime.compareTo(localDateTime2);
		System.out.println(result);
		assertThat(result).isEqualTo(0);

		localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3, 123456789);
		localDateTime2 = LocalDateTime.of(2022, 8, 3, 1, 2, 3, 123456789);
		result = localDateTime.compareTo(localDateTime2);
		System.out.println(result);
		assertThat(result).isGreaterThan(0);
	}

	@Test
	public void isBefore() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2024, 8, 3, 1, 2, 3);
		boolean result = localDateTime.isBefore(localDateTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDateTime2.isBefore(localDateTime);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void isAfter() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2022, 8, 3, 1, 2, 3);
		boolean result = localDateTime.isAfter(localDateTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDateTime2.isAfter(localDateTime);
		System.out.println(result);
		assertThat(result).isFalse();
	}

	@Test
	public void equals() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		boolean result = localDateTime.equals(localDateTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDateTime2.equals(localDateTime);
		System.out.println(result);
		assertThat(result).isTrue();
	}
	
	@Test
	public void isEqual() {
		LocalDateTime localDateTime = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		LocalDateTime localDateTime2 = LocalDateTime.of(2023, 8, 3, 1, 2, 3);
		boolean result = localDateTime.isEqual(localDateTime2);
		System.out.println(result);
		assertThat(result).isTrue();

		result = localDateTime2.isEqual(localDateTime);
		System.out.println(result);
		assertThat(result).isTrue();
	}
}
