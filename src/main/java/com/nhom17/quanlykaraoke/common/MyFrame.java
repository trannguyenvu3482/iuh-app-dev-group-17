package com.nhom17.quanlykaraoke.common;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MyFrame() {
		setTitle("Quản lý nhân viên");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
