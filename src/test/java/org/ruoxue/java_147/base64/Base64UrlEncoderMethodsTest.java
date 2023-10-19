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

public class Base64UrlEncoderMethodsTest {

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
	public void wrap() throws Exception {
		Path src = Paths.get("./", "README.md");
		System.out.println(src);
		Path dst = Paths.get("./", "README.log");
		System.out.println(dst);
		Base64.Encoder encoder = Base64.getUrlEncoder();
		try (OutputStream output = Files.newOutputStream(dst)) {
			Files.copy(src, encoder.wrap(output));
			// OutputStream encodedStrem = encoder.wrap(output);
		}
	}
}
