package com.nhom17.quanlykaraoke;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignE;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI;
import com.nhom17.quanlykaraoke.gui.NhanVienGUI;
import com.nhom17.quanlykaraoke.gui.QuanLyGUI;
import com.nhom17.quanlykaraoke.gui.dialogs.SplashScreenDialog;
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
		setupFlatLaf();

		// Show splash screen
		SplashScreenDialog splashGUI = new SplashScreenDialog();
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
						if (userId.equals("NV001")) {
							QuanLyGUI main = new QuanLyGUI(userId);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						} else {
							NhanVienGUI main = new NhanVienGUI(userId);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						}
					}
				});

				loginGUI.setVisible(true);

			}
		});
	}

	/**
	 * 
	 */
	private static void setupFlatLaf() {
		// TODO Auto-generated method stub
		FlatLaf.registerCustomDefaultsSource("com.theme");
		FlatIntelliJLaf.setup();

		UIManager.put("Table.showHorizontalLines", true);
		UIManager.put("Table.showVerticalLines", true);
		UIManager.put("JTabbedPane.showTabSeparators", true);
		UIManager.put("PasswordField.showRevealButton", true);
		UIManager.put("PasswordField.revealIcon ", MyIcon.getIcon(MaterialDesignE.EYE, 12, null));
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
				if (id.equals("NV001")) {
					QuanLyGUI main = new QuanLyGUI(id);
					main.setLogoutListener(() -> showLoginScreen());
					main.setVisible(true);
				} else {
					NhanVienGUI main = new NhanVienGUI(id);
					main.setLogoutListener(() -> showLoginScreen());
					main.setVisible(true);
				}
			}
		});

		login.setVisible(true);
	}
}
