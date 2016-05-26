package com.github.rapidcron.webservice.util;

public class ThreadUtil {

	public static void sleep(int mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}
	
	public static void sleepSeconds(int seconds) {
		sleep(seconds * 1000);
	}
}
