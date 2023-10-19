package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class URLEncodingDecodingUsingBase64Test {
	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringWhitespaces("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ")
				.hasSize(size);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/?s=base64";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ=");
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		String result = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ=");
	}

	@Test
	public void Base64_encodeBase64URLSafeString() throws Exception {
		String value = "https://www.ruoxue.org/?s=base64";
		System.out.println(value);
		String result = org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafeString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy8_cz1iYXNlNjQ");
	}
}
