package com.nhom17.quanlykaraoke;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI;
import com.nhom17.quanlykaraoke.gui.QuanLyNhanVienGUI;
import com.nhom17.quanlykaraoke.gui.SplashScreen;
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
		FlatLaf.registerCustomDefaultsSource("com.theme");
		FlatIntelliJLaf.setup();

		// Show splash screen
		SplashScreen splashGUI = new SplashScreen();
		splashGUI.setVisible(true);

		// Add window listener to detect when splash screen close
		splashGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

				// Splash closed, show login screen
				DangNhapGUI loginGUI = null;
				try {
					loginGUI = new DangNhapGUI();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Add listener for login
				loginGUI.setLoginListener(userId -> {
					if (userId != null) {

						// Login success, show main window
						QuanLyNhanVienGUI main = new QuanLyNhanVienGUI(userId);
						main.setLogoutListener(() -> showLoginScreen());
						main.setVisible(true);

					}
				});

				loginGUI.setVisible(true);

			}
		});
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
