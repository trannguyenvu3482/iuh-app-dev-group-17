package com.nhom17.quanlykaraoke;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.nhom17.quanlykaraoke.dao.ChucVuDAO;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
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

		// Set look and feel to be FlatLaf
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		ChucVuDAO cvDAO = new ChucVuDAO();
		NhanVienDAO nvDAO = new NhanVienDAO();

		// Hiện giao diện đăng nhập
		DangNhapGUI loginGUI = new DangNhapGUI();

		// Kiểm tra đăng nhập thành công hay chưa
		loginGUI.setLoginListener(new LoginListener() {
			public void onLogin(String id) {
				if (id != null) {
					QuanLyNhanVienGUI main = new QuanLyNhanVienGUI(id);
					main.setVisible(true);
					System.out.println("Nhân viên hiện tại: " + id);
				}
			}
		});

		loginGUI.setVisible(true);

	}
}
