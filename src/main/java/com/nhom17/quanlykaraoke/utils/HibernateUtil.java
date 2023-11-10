package com.nhom17.quanlykaraoke.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.ChucVu;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiPhong;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 13:36:00
 */
public class HibernateUtil {
	private static SessionFactory mySessionFactory = null;

	public static void provideSessionFactory() {
		// Load configuration from hibernate.cfg.xml
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");

		// Get env vars
		Dotenv dotenv = Dotenv.configure().directory(".").load();
		String url = dotenv.get("DB_URL");
		String username = dotenv.get("DB_USERNAME");
		String password = dotenv.get("DB_PASSWORD");

		// Override properties
		config.setProperty("hibernate.connection.url", url);
		config.setProperty("hibernate.connection.username", username);
		config.setProperty("hibernate.connection.password", password);

		// Eager loading
		config.setProperty("hibernate.enable_lazy_load_no_trans", "true");

		// Add annotated classes
		config.addAnnotatedClass(ChiTietPhieuDatPhong.class);
		config.addAnnotatedClass(ChiTietDichVu.class);
		config.addAnnotatedClass(ChucVu.class);
		config.addAnnotatedClass(HangHoa.class);
		config.addAnnotatedClass(KhachHang.class);
		config.addAnnotatedClass(LoaiHangHoa.class);
		config.addAnnotatedClass(LoaiPhong.class);
		config.addAnnotatedClass(NhanVien.class);
		config.addAnnotatedClass(PhieuDatPhong.class);
		config.addAnnotatedClass(Phong.class);

		// Build session factory
		mySessionFactory = config.buildSessionFactory();
	}

	public static SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}
}
