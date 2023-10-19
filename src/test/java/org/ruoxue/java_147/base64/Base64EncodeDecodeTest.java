package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class Base64EncodeDecodeTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result))
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODktXw==");
	}

	@Test
	public void decode() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODktXw==";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void mimeEncode() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
		System.out.println(value);
		byte[] result = encoder.encode(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n" + "NTY3ODktXw==");
	}

	@Test
	public void mimeDecode() throws Exception {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n"
				+ "NTY3ODktXw==";
		System.out.println(value);
		byte[] result = decoder.decode(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
	}

	@Test
	public void urlEncode() throws Exception {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		String value = "https://www.ruoxue.org/category/rd/java/java-base64/";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result))
				.isEqualTo("aHR0cHM6Ly93d3cucnVveHVlLm9yZy9jYXRlZ29yeS9yZC9qYXZhL2phdmEtYmFzZTY0Lw==");
	}

	@Test
	public void urlDecode() throws Exception {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String value = "aHR0cHM6Ly93d3cucnVveHVlLm9yZy9jYXRlZ29yeS9yZC9qYXZhL2phdmEtYmFzZTY0Lw==";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("https://www.ruoxue.org/category/rd/java/java-base64/");
	}
}
