package org.ruoxue.java_147.base64.mime;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class MIMEEncodingDecodingBase64Test {

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
		System.out.println(value);
		int size = 256;
		byte[] dst = new byte[size];
		int result = encoder.encode(value.toString().getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringWhitespaces(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n" + "NjAsIElUIDQ4NA==")
				.hasSize(size);
	}

	@Test
	public void decodeWithDst() throws Exception {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		System.out.println(value);
		int size = 512;
		byte[] dst = new byte[size];
		int result = decoder.decode(value.toString().getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		String stringResult = new String(dst);
		System.out.println(stringResult);
		assertThat(stringResult)
				.containsIgnoringWhitespaces("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484")
				.hasSize(size);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).containsIgnoringNewLines(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n" + "NjAsIElUIDQ4NA==");
	}

	@Test
	public void decodeWithByteBuffer() throws Exception {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = decoder.decode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
		System.out.println(value);
		String result = encoder.encodeToString(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).containsIgnoringNewLines(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n" + "NjAsIElUIDQ4NA==");
	}

	@Test
	public void decodeWithString() throws Exception {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = decoder.decode(value);
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}
}
