package org.ruoxue.java_147.datetime.datetimeformatter;

import static org.junit.Assert.*;

import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeFormatterClassTest {
	
	@Test
	public void toFormat() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		System.out.println(formatter.toFormat());
	}

	@Test
	public void get() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}

	@Test
	public void with() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}

}
