package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class URLEncodingDecodingTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==");
	}

	@Test
	public void decode() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}

	@Test
	public void encodeWithoutPadding() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		byte[] result = encoder.withoutPadding().encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw");
	}

	@Test
	public void decodeWithoutPadding() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}

	@Test
	public void Base64_encodeBase64URLSafe() throws Exception {
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafe(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw");
	}

	@Test
	public void Base64_decodeBase64() throws Exception {
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.decodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}

}
