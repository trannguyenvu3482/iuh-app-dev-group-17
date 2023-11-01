package com.nhom17.quanlykaraoke.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormatUtil {
	public static String format(double d) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return formatter.format(d);
	}
}
