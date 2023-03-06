package org.ruoxue.java_147.conversion;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnumToStringTest {

	public enum Tutorial {
		JAVA("Java 147"), SPRING_BOOT("Spring boot 168"), JUNIT("Junit 151"), BASH("Bash 460"), IT("IT 484");

		private String name;

		private Tutorial(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	@Test
	public void name() {
		String result = Tutorial.JAVA.name();
		System.out.println(result);
		assertEquals("JAVA", result);

		for (Tutorial e : Tutorial.values()) {
			System.out.println(e.name());
		}
	}

	@Test
	public void toStringz() {
		String result = Tutorial.JAVA.toString();
		System.out.println(result);
		assertEquals("Java 147", result);

		for (Tutorial e : Tutorial.values()) {
			System.out.println(e.toString());
		}
	}

	public class Course {
		public static final String JAVA = "Java 147";
		public static final String SPRING_BOOT = "Spring boot 168";
		public static final String JUNIT = "Junit 151";
		public static final String BASH = "Bash 460";
		public static final String IT = "IT 484";
	}

	@Test
	public void constant() {
		String result = Course.JAVA;
		System.out.println(result);
		assertEquals("Java 147", result);

		result = Course.SPRING_BOOT;
		System.out.println(result);
		assertEquals("Spring boot 168", result);

		result = Course.JUNIT;
		System.out.println(result);
		assertEquals("Junit 151", result);
	}

	public interface Learning {
		String JAVA = "Java 147";
		String SPRING_BOOT = "Spring boot 168";
		String JUNIT = "Junit 151";
		String BASH = "Bash 460";
		String IT = "IT 484";
	}
	
	@Test
	public void interfacez() {
		String result = Learning.JAVA;
		System.out.println(result);
		assertEquals("Java 147", result);

		result = Learning.SPRING_BOOT;
		System.out.println(result);
		assertEquals("Spring boot 168", result);

		result = Learning.JUNIT;
		System.out.println(result);
		assertEquals("Junit 151", result);
	}

}
