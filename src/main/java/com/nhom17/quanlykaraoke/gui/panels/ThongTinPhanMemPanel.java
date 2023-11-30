package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 01-Dec-2023 2:34:29 AM
 */
public class ThongTinPhanMemPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ThongTinPhanMemPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
	}
}
