package com.nhom17.quanlykaraoke.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nhom17.quanlykaraoke.entities.NhanVien;

public class HibernateUtil {
	private static SessionFactory mySessionFactory = null;

	public static void provideSessionFactory() {
		mySessionFactory = new Configuration().configure().addAnnotatedClass(NhanVien.class).buildSessionFactory();

	}

	public static SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}
}
