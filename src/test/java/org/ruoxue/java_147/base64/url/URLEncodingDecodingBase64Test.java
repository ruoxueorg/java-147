package org.ruoxue.java_147.base64.url;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class URLEncodingDecodingBase64Test {

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		System.out.println(new String(dst));
		assertThat(new String(dst)).containsIgnoringWhitespaces("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==")
				.hasSize(size);
	}

	@Test
	public void decodeWithDst() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		System.out.println(new String(dst));
		assertThat(new String(dst)).containsIgnoringWhitespaces("https://www.ruoxue.org/java-learn/java-base64/")
				.hasSize(size);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==");
	}

	@Test
	public void decodeWithByteBuffer() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = decoder.decode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		String result = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==");
	}

	@Test
	public void decodeWithString() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		byte[] result = decoder.decode(value);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}

	@Test
	public void Base64_encodeBase64URLSafeString() throws Exception {
		String value = "https://www.ruoxue.org/java-learn/java-base64/";
		System.out.println(value);
		String result = org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafeString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw");
	}

	@Test
	public void Base64_decodeBase64WithString() throws Exception {
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9qYXZhLWxlYXJuL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(value);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/java-learn/java-base64/");
	}
}
