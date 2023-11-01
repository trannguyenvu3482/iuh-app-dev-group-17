package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationItem extends JPanel {
	private static final long serialVersionUID = 1L;

	public NotificationItem(String name, String des, String time) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblTitle = new JLabel("Đã thêm mới một phòng");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblTitle);

		JLabel lblDesc = new JLabel("Phòng 005");
		add(lblDesc);

		lblTitle.setText(name);
		lblDesc.setText(des);
	}

}
