package com.nhom17.quanlykaraoke;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.nhom17.quanlykaraoke.gui.DangNhapGUI;
import com.nhom17.quanlykaraoke.utils.HibernateUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		HibernateUtil.provideSessionFactory();

		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		new DangNhapGUI().setVisible(true);
	}
}
