package com.nhom17.quanlykaraoke.common;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MyMessageDialog extends JOptionPane {
	private static final long serialVersionUID = 1L;

	public static void showMessage(Component parentComponent, String title, String message) {
		JOptionPane.showMessageDialog(parentComponent, message, title, ERROR_MESSAGE);
	}
}
