package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class Base64EncoderMethodsTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}

	@Test
	public void withoutPadding() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		byte[] result = encoder.withoutPadding().encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw");
	}

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		byte[] dst = new byte[128];
		int result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==                                        ");
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		String result = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}
}
