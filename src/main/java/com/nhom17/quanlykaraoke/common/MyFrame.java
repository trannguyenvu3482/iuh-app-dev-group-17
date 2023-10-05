package com.nhom17.quanlykaraoke.common;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame() {
		setTitle("Quản lý nhân viên");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}
}
