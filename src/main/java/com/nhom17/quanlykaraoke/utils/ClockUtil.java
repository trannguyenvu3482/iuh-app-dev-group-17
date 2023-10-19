/**
 * 
 */
package com.nhom17.quanlykaraoke.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 16:36:00
 */
public class ClockUtil {
	private ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Date date = new Date();
			DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			String time = timeFormat.format(date);
			lbl.setText(time);
		}
	};

	private static JLabel lbl;

	public ClockUtil(JLabel label) {
		ClockUtil.lbl = label;
	}

	public static void updateClock(JLabel label) {
		Date date = new Date();
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String time = timeFormat.format(date);
		label.setText(time);
	}

	public void startClock() {
		Timer timer = new Timer(0, al);
		timer.setInitialDelay(0);
		timer.start();
	}

	public ActionListener getAl() {
		return al;
	}

}
