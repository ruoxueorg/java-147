package org.ruoxue.java_147.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class URLEncoderMethodsTest {

	@Test
	public void encode() {
		try {
			String value = "https://www.ruoxue.org";
			System.out.println(value);
			String result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
	
	@Test
	public void encodeParams() {
		try {
			String value = "https://www.ruoxue.org";
			System.out.println(value);
			String result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
	@Test
	public void encodeThrowException() {
		try {
			String value = "https://www.ruoxue.org";
			System.out.println(value);
			String result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			System.out.println(result);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}
}
