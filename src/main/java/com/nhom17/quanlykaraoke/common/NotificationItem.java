
package com.nhom17.quanlykaraoke.common;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created Nov 3, 2023 1:34:06 PM
 */
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

		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
	}

}
