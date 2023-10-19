package com.nhom17.quanlykaraoke;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI.LoginListener;
import com.nhom17.quanlykaraoke.gui.QuanLyNhanVienGUI;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 16:36:00
 */

public class Main {

	public static void main(String[] args) throws Exception {
		// Start Hibernate
		HibernateUtil.provideSessionFactory();

		// Setup Flatlaf
		FlatLaf.registerCustomDefaultsSource("com.nhom17.quanlykaraoke.themes");
		FlatIntelliJLaf.setup();

		// Show loginGUI
		DangNhapGUI loginGUI = new DangNhapGUI();

		// Check if login is complete
		loginGUI.setLoginListener(new LoginListener() {
			public void onLogin(String id) {
				if (id != null) {
					QuanLyNhanVienGUI main = new QuanLyNhanVienGUI(id);
					main.setLogoutListener(() -> showLoginScreen());
					main.setVisible(true);
					System.out.println("Nhân viên hiện tại: " + id);
				}
			}
		});

		loginGUI.setVisible(true);
	}

	private static void showLoginScreen() {
		DangNhapGUI login = null;
		try {
			login = new DangNhapGUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		login.setLoginListener(id -> {
			if (id != null) {
				QuanLyNhanVienGUI main = new QuanLyNhanVienGUI(id);

				main.setLogoutListener(() -> showLoginScreen());

				main.setVisible(true);
			}
		});

		login.setVisible(true);
	}
}
