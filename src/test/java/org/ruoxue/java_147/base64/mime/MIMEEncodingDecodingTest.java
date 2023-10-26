package org.ruoxue.java_147.base64.mime;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

public class MIMEEncodingDecodingTest {

	@Test
	public void encode() throws Exception {
		Base64.Encoder encoder = Base64.getMimeEncoder();
		StringBuilder value = new StringBuilder();
		value.append("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
		System.out.println(value);
		byte[] result = encoder.encode(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).containsIgnoringNewLines(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n" + "NjAsIElUIDQ4NA==");
	}

	@Test
	public void decode() throws Exception {
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = decoder.decode(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void Base64_encodeBase64Chunked() throws Exception {
		StringBuilder value = new StringBuilder();
		value.append("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.encodeBase64Chunked(value.toString().getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).containsIgnoringNewLines(
				"SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n" + "NjAsIElUIDQ4NA==");
	}

	@Test
	public void Base64_decodeBase64() throws Exception {
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64
				.decodeBase64(value.getBytes(StandardCharsets.UTF_8.toString()));
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}

	@Test
	public void Base64_decodeBase64WithString() throws Exception {
		String value = "SmF2YSAxNDcsIFNwcmluZyBib290IDE2OCwgSnVuaXQgMTUxLCBBc3NlcnRKIDE1NSwgQmFzaCA0\n"
				+ "NjAsIElUIDQ4NA==";
		System.out.println(value);
		byte[] result = org.apache.commons.codec.binary.Base64.decodeBase64(value);
		System.out.println(result);
		System.out.println(new String(result));
		assertThat(new String(result)).isEqualTo("Java 147, Spring boot 168, Junit 151, AssertJ 155, Bash 460, IT 484");
	}
}
