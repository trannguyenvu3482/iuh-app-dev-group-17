package com.nhom17.quanlykaraoke.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 1:40:49 PM
 */
public class DateTimeFormatUtil {
	public static String formatFullDate(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s - d/M/yyyy ", new Locale("vi", "VN"));
		return time.format(formatter);
	}

	public static String formatTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		System.out.println("Start time: " + startTime.toString());
		System.out.println("End time: " + endTime.toString());

		long millis = Duration.between(startTime, endTime).toMillis();

		long hour = TimeUnit.MILLISECONDS.toHours(millis);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis - TimeUnit.HOURS.toMillis(hour));

		return String.format("%d giờ %d phút", hour, minutes);
	}
}
