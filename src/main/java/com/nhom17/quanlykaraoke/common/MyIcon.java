/**
 * 
 */
package com.nhom17.quanlykaraoke.common;

import java.awt.Color;

import javax.swing.Icon;

import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 13:36:00
 */
public class MyIcon {
	public static final int DEFAULT_SIZE = 24;

	public static Icon getIcon(Ikon icon, int iconSize, Color color) {
		FontIcon fontIcon = FontIcon.of(icon);
		fontIcon.setIconSize(iconSize);

		if (color != null) {
			fontIcon.setIconColor(color);
		}

		return fontIcon;
	}
}
