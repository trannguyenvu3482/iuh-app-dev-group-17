package com.nhom17.quanlykaraoke.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 1:40:49 PM
 */
public class DateTimeFormatUtil {
	public static String formatFullDate(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		formatter = formatter.withLocale(new Locale("vi", "VN"));
		return time.format(formatter);
	}

	public static String formatTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		long millis = Duration.between(startTime, endTime).toMillis();

		return String.format("%d giờ %d phút", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis)
						- TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
	}
}
