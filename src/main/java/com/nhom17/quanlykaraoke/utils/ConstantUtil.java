package com.nhom17.quanlykaraoke.utils;

import java.awt.Color;
import java.time.LocalDateTime;

import com.nhom17.quanlykaraoke.entities.NhanVien;

public class ConstantUtil {
	public static final Color MAIN_COLOR = new Color(20, 20, 20);
	public static final int MAXIMUM_PAGE_SIZE = 8;
	public static NhanVien currentNhanVien = null;
	public static final double DAYTIME_HOUR_PRICE = 45000;
	public static final double NIGHTTIME_HOUR_PRICE = 60000;

	public static final double getStandardHourPrice(LocalDateTime time) {
		if (time.getHour() < 12) {
			return DAYTIME_HOUR_PRICE;
		} else {
			return NIGHTTIME_HOUR_PRICE;
		}
	}
}
