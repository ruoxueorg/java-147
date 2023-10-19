package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class Base64DecoderMethodsTest {

	@Test
	public void decode() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}

	@Test
	public void decodeWithoutPadding() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}

	@Test
	public void decodeWithDst() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult)
				.containsIgnoringWhitespaces("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/")
				.hasSize(size);
	}

	@Test
	public void decodeWithByteBuffer() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = decoder.decode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}

	@Test
	public void decodeWithString() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		System.out.println(value);
		byte[] result = decoder.decode(value);
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}

	@Test
	public void Base64_decodeBase64() throws Exception {
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.decodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}

	@Test
	public void Base64_decodeBase64WithString() throws Exception {
		String value = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(value);
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	}
}
