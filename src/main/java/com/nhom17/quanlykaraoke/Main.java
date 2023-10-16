package com.nhom17.quanlykaraoke;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.nhom17.quanlykaraoke.dao.ChucVuDAO;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI;
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

//		File imageFile = new File("src/main/resources/images/logo.png");
//		FileInputStream fis = new FileInputStream(imageFile);
//
//		byte[] data = new byte[(int) imageFile.length()];
//		fis.read(data);
//		fis.close();

//		ChucVu cv = cvDAO.getChucVu("CV001");
//
//		if (nvDAO.addNhanVien(
//				new NhanVien("Nguyen Van A", 1, "$2a$12$ZJY.KwNvPyqf5LItFvfuf.YMD5.E7X1ozqYkb7NXR559GOgwP/9oq",
//						LocalDate.of(2003, 11, 23), cv, "0903252058", "079203027776", data, true))) {
//			System.out.println("ADDED");
//		} else {
//			System.out.println("NOT ADDED");
//		}

		// Hiện giao diện đăng nhập
		DangNhapGUI loginGUI = new DangNhapGUI();
		loginGUI.setVisible(true);

		// Vòng lặp này ngăn chặn cho việc phần code ở dưới được chạy mà đăng nhập chưa
		// hoàn tất
		// Để tối ưu cho CPU thì sẽ cho CPU nghỉ ngơi cách 100ms trong lúc chờ đợi
		// LoginGUI thực hiện xong
		while (loginGUI.getLoggedInEmployeeID() == null) {
			Thread.sleep(100);
		}

		// Lấy mã nhân viên đã đăng nhập
		String maNV = loginGUI.getLoggedInEmployeeID();
		if (maNV != null) {
			QuanLyNhanVienGUI mainGUI = new QuanLyNhanVienGUI();

			System.out.println("Nhân viên đăng nhập: " + maNV);

			mainGUI.setVisible(true); // FIRST visible = true
		}
	}
}
