package com.nhom17.quanlykaraoke.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nhom17.quanlykaraoke.entities.ChucVu;
import com.nhom17.quanlykaraoke.entities.NhanVien;

/**
 * @author  Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 13:36:00
 */
public class HibernateUtil {
	private static SessionFactory mySessionFactory = null;

	public static void provideSessionFactory() {
		mySessionFactory = new Configuration().configure().addAnnotatedClass(NhanVien.class).addAnnotatedClass(ChucVu.class).buildSessionFactory();

	}

	public static SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}
}
