package com.nhom17.quanlykaraoke.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nhom17.quanlykaraoke.entities.ChucVu;
import com.nhom17.quanlykaraoke.entities.NhanVien;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author  Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
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
		
		// Build session factory
		mySessionFactory = config.buildSessionFactory();
	}

	public static SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}
}
