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

public class Base64MimeEncoderMethodsTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
		}
		System.out.println(value);
		byte[] result = encoder.encode(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n"
						+ "NTY3ODkrL0FCQ0RFRkdISUpLTE1OT1BRUlNUVVZXWFlaYWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4\n"
						+ "eXowMTIzNDU2Nzg5Ky9BQkNERUZHSElKS0xNTk9QUVJTVFVWV1hZWmFiY2RlZmdoaWprbG1ub3Bx\n"
						+ "cnN0dXZ3eHl6MDEyMzQ1Njc4OSsv");
	}

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
		}
		System.out.println(value);
		byte[] dst = new byte[512];
		int result = encoder.encode(value.toString().getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringWhitespaces(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n"
						+ "NTY3ODkrL0FCQ0RFRkdISUpLTE1OT1BRUlNUVVZXWFlaYWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4\n"
						+ "eXowMTIzNDU2Nzg5Ky9BQkNERUZHSElKS0xNTk9QUVJTVFVWV1hZWmFiY2RlZmdoaWprbG1ub3Bx\n"
						+ "cnN0dXZ3eHl6MDEyMzQ1Njc4OSsv")
				.hasSize(512);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
		}
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n"
						+ "NTY3ODkrL0FCQ0RFRkdISUpLTE1OT1BRUlNUVVZXWFlaYWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4\n"
						+ "eXowMTIzNDU2Nzg5Ky9BQkNERUZHSElKS0xNTk9QUVJTVFVWV1hZWmFiY2RlZmdoaWprbG1ub3Bx\n"
						+ "cnN0dXZ3eHl6MDEyMzQ1Njc4OSsv");
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		StringBuilder value = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			value.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
		}
		System.out.println(value);
		String result = encoder.encodeToString(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).containsIgnoringNewLines(
				"QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0\n"
						+ "NTY3ODkrL0FCQ0RFRkdISUpLTE1OT1BRUlNUVVZXWFlaYWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4\n"
						+ "eXowMTIzNDU2Nzg5Ky9BQkNERUZHSElKS0xNTk9QUVJTVFVWV1hZWmFiY2RlZmdoaWprbG1ub3Bx\n"
						+ "cnN0dXZ3eHl6MDEyMzQ1Njc4OSsv");
	}

	@Test
	public void wrap() throws Exception {
		Path src = Paths.get("./", "README.md");
		System.out.println(src);
		Path dst = Paths.get("./", "README.log");
		System.out.println(dst);
		Base64.Encoder encoder = Base64.getMimeEncoder();
		try (OutputStream output = Files.newOutputStream(dst)) {
			Files.copy(src, encoder.wrap(output));
			// OutputStream encodedStrem = encoder.wrap(output);
		}
	}
}
