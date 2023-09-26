package com.nhom17.quanlykaraoke;

import com.nhom17.quanlykaraoke.gui.QuanLyNhanVienGUI;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		HibernateUtil.provideSessionFactory();
		new QuanLyNhanVienGUI().setVisible(true);
	}
}
