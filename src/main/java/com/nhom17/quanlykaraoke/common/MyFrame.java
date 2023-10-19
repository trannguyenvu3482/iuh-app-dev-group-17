package com.nhom17.quanlykaraoke.common;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MyFrame() {
		setTitle("Hệ thống quản lý karaoke Nnice");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(new ImageIcon("src/main/resources/images/logo-icon.png").getImage());
	}
}
