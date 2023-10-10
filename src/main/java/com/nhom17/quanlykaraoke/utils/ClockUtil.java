/**
 * 
 */
package com.nhom17.quanlykaraoke.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 16:36:00
 */
public class ClockUtil {
	private static ClockUtil instance = new ClockUtil();

	private static Clock clock;
	private static Timer timer;

	private ClockUtil() {
		clock = Clock.systemDefaultZone();
	}

	public static void startUpdating() {

		timer = new Timer(1000, e -> {
			// just update clock
		});

		timer.start();

	}

	public static String getCurrentTime() {

		Instant instant = clock.instant();

		return DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("GMT+7")).format(instant);
	}

}
