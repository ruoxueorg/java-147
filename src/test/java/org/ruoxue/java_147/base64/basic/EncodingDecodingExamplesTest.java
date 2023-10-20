package org.ruoxue.java_147.base64.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class EncodingDecodingExamplesTest {

	@Test
	public void encodeToString() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		String result = encoder.encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==");
	}

	@Test
	public void decodeWithString() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = decoder.decode(value);
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void encodeWithDst() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		System.out.println(new String(dst));
		assertThat(new String(dst))
				.containsIgnoringWhitespaces(
						"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==")
				.hasSize(size);
	}

	@Test
	public void decodeWithDst() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		System.out.println(value);
		int size = 100;
		byte[] dst = new byte[size];
		int result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()), dst);
		System.out.println(result);
		System.out.println(new String(dst));
		assertThat(new String(dst))
				.containsIgnoringWhitespaces("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484")
				.hasSize(size);
	}

	@Test
	public void encodeWithByteBuffer() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = encoder.encode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==");
	}

	@Test
	public void decodeWithByteBuffer() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(byteBuffer);
		ByteBuffer result = decoder.decode(byteBuffer);
		System.out.println(result);
		String stringResult = new String(result.array(), StandardCharsets.UTF_8.toString());
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void Base64_encodeBase64String() throws Exception {
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		String result = org.apache.commons.codec.binary.Base64
				.encodeBase64String(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		assertThat(result).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==");
	}

	@Test
	public void Base64_decodeBase64WithString() throws Exception {
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(value);
		System.out.println(result);
		String stringResult = new String(result);
		System.out.println(stringResult);
		assertThat(stringResult).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}
}
