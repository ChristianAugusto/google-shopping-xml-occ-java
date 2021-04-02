package com.christian.googleshoppingxmloccjava.utils;

public class Logger {
	public static void info(String message) {
		System.out.println("[INFO]    - ".concat(message));
	}

	public static void warning(String message) {
		System.out.println("[WARNING] - ".concat(message));
	}

	public static void error(String message) {
		System.out.println("[ERROR]   - ".concat(message));
	}
}
