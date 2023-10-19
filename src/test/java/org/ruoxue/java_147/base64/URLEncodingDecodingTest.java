package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class URLEncodingDecodingTest {
	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ=");
	}

	@Test
	public void encodeWithoutPadding() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		byte[] result = encoder.withoutPadding().encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ");
	}
	
	@Test
	public void Base64_encodeBase64URLSafe() throws Exception {
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafe(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ");
	}
}
