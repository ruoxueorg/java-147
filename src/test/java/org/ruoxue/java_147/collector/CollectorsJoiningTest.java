package org.ruoxue.java_147.collector;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class CollectorsJoiningTest {

	@Test
	public void joining() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BlueberryMelonFig");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining());
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRYMELONFIG");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining());
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("2147483647-13");
	}

	@Test
	public void joiningWithDelimiter() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("Blueberry, Melon, Fig");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
		System.out.println(result);
		assertThat(result).isEqualTo("BLUEBERRY, MELON, FIG");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining(", "));
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("2147483647, -1, 3");
	}

	@Test
	public void joiningWithPrefixSuffix() {
		List<String> list = Arrays.asList("Blueberry", "Melon", "Fig");
		String result = list.stream().collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[Blueberry, Melon, Fig]");

		result = list.stream().map(String::toUpperCase).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(result);
		assertThat(result).isEqualTo("[BLUEBERRY, MELON, FIG]");

		List<Integer> intList = Arrays.asList(Integer.MAX_VALUE, -1, 3);
		String intResult = intList.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(intResult);
		assertThat(intResult).isEqualTo("[2147483647, -1, 3]");
	}

	@Test
	public void encodeURL() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("user", "user");
		paramMap.put("name", "name %");
		paramMap.put("email", "email@!$");
		paramMap.put("amount", "101");
		paramMap.put("timestamp", "1470926696715");
		System.out.println(paramMap);
		// sort
		Map<String, String> sortedMap = paramMap.entrySet().stream().sorted(Map.Entry.<String, String>comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
		System.out.println(sortedMap);

		String result = sortedMap.keySet().stream().map(key -> {
			try {
				String value = URLEncoder.encode(paramMap.get(key), StandardCharsets.UTF_8.toString());
				return key + "=" + value;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}).collect(Collectors.joining("&", "https://www.ruoxue.org?", ""));
		System.out.println(result);
		assertThat(result).isEqualTo(
				"https://www.ruoxue.org?amount=101&email=email%40%21%24&name=name+%25&timestamp=1470926696715&user=user");
	}

	@Test
	public void decodeURL() throws Exception {
		String value = "https://www.ruoxue.org?amount=101&email=email%40%21%24&name=name+%25&timestamp=1470926696715&user=user";
		URI uri = new URI(value);
		String scheme = uri.getScheme();
		String host = uri.getHost();
		String query = uri.getRawQuery();
		System.out.println(query);
		String decodedString = Arrays.stream(query.split("&")).map(e -> {
			try {
				String[] array = e.split("=");
				String param = array[0] + "=" + URLDecoder.decode(array[1], StandardCharsets.UTF_8.toString());
				// System.out.println("param: " + param);
				return param;
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}).collect(Collectors.joining("&"));
		System.out.println(decodedString);
		String result = scheme + "://" + host + "?" + decodedString;
		System.out.println(result);
		assertThat(result).isEqualTo(
				"https://www.ruoxue.org?amount=101&email=email@!$&name=name %&timestamp=1470926696715&user=user");
	}
}
