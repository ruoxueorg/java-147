package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.Test;

public class Base64EncoderMethodsTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}

	@Test
	public void encodeWithoutPadding() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		byte[] result = encoder.withoutPadding().encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw");
	}

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult)
				.containsIgnoringWhitespaces(
						"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==")
				.hasSize(size);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		String result = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}

	@Test
	public void wrap() throws Exception {
		Path src = Paths.get("./", "README.md");
		System.out.println(src);
		Path dst = Paths.get("./", "README.log");
		System.out.println(dst);
		Base64.Encoder encoder = Base64.getEncoder();
		try (OutputStream output = Files.newOutputStream(dst)) {
			Files.copy(src, encoder.wrap(output));
			// OutputStream encodedStrem = encoder.wrap(output);
		}
	}

	@Test
	public void Base64_encodeBase64() throws Exception {
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.encodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}

	@Test
	public void Base64_encodeBase64String() throws Exception {
		String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		System.out.println(value);
		String result = org.apache.commons.codec.binary.Base64
				.encodeBase64String(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result)
				.isEqualTo("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==");
	}
}
