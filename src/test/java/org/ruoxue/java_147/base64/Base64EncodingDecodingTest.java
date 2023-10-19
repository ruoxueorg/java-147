package org.ruoxue.java_147.base64;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class Base64EncodingDecodingTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		byte[] result = encoder.encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==");
	}

	@Test
	public void decode() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void encodeWithoutPadding() throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		byte[] result = encoder.withoutPadding().encode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA");
	}

	@Test
	public void decodeWithoutPadding() throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA";
		System.out.println(value);
		byte[] result = decoder.decode(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void Base64_encodeBase64() throws Exception {
		String value = "Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.encodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==");
	}

	@Test
	public void Base64_decodeBase64() throws Exception {
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.decodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}
}
