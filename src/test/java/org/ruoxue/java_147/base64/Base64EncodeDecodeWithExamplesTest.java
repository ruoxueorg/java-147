package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class Base64EncodeDecodeWithExamplesTest {

	@Test
	public void encodeDecode() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
		System.out.println(value);
		String encodedString = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedString);
		assertThat(encodedString)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODktXw==");

		Base64.Decoder decoder = Base64.getDecoder();
		byte[] result = decoder.decode(encodedString);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void Base64_encodeDecode() throws Exception {
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
		System.out.println(value);
		byte[] encodedByteArray = org.apache.commons.codec.binary.Base64
				.encodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedByteArray);
		System.out.println(new String(encodedByteArray));
		assertThat(new String(encodedByteArray))
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODktXw==");

		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(encodedByteArray);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void mimeEncodeDecode() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
		System.out.println(value);
		String encodedString = encoder.encodeToString(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedString);
		assertThat(encodedString).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n" + "NTY3ODktXw==");

		Base64.Decoder decoder = Base64.getMimeDecoder();
		byte[] result = decoder.decode(encodedString);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void Base64_mimeEncodeDecode() throws Exception {
		StringBuilder value = new StringBuilder();
		value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
		System.out.println(value);
		byte[] encodedByteArray = org.apache.commons.codec.binary.Base64
				.encodeBase64Chunked(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedByteArray);
		System.out.println(new String(encodedByteArray));
		assertThat(new String(encodedByteArray)).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n" + "NTY3ODktXw==");

		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(encodedByteArray);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void urlEncodeDecode() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/category/rd/java/java-base64/";
		System.out.println(value);
		String encodedString = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedString);
		assertThat(encodedString).isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9jYXRlZ29yeS9yZC9qYXZhL2phdmEtYmFzZTY0Lw==");

		Base64.Decoder decoder = Base64.getUrlDecoder();
		byte[] result = decoder.decode(encodedString);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/category/rd/java/java-base64/");
	}

	@Test
	public void Base64_urlEncodeDecode() throws Exception {
		String value = "https://www.ruoxue.org/category/rd/java/java-base64/";
		System.out.println(value);
		byte[] encodedByteArray = org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafe(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(encodedByteArray);
		System.out.println(new String(encodedByteArray));
		assertThat(new String(encodedByteArray))
				.isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9jYXRlZ29yeS9yZC9qYXZhL2phdmEtYmFzZTY0Lw");

		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(encodedByteArray);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/category/rd/java/java-base64/");
	}
}
