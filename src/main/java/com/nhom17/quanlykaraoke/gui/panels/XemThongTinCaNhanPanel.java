package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:16:05 PM
 */
public class XemThongTinCaNhanPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public XemThongTinCaNhanPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.DARK_GRAY);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));

		JLabel lblThngTinNgi = new JLabel("Thông tin người dùng");
		lblThngTinNgi.setBorder(new EmptyBorder(5, 10, 5, 0));
		lblThngTinNgi.setFont(new Font("Dialog", Font.BOLD, 30));
		lblThngTinNgi.setForeground(Color.WHITE);
		panelTop.add(lblThngTinNgi);

		Component horizontalGlue = Box.createHorizontalGlue();
		panelTop.add(horizontalGlue);

		JButton btnSaThngTin = new JButton("Sửa thông tin");
		btnSaThngTin.setForeground(Color.WHITE);
		btnSaThngTin.setBackground(Color.BLUE);
		btnSaThngTin.setFont(new Font("Dialog", Font.BOLD, 20));
		panelTop.add(btnSaThngTin);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelTop.add(horizontalStrut);

		JButton btniMtKhu = new JButton("Đổi mật khẩu");
		btniMtKhu.setForeground(Color.WHITE);
		btniMtKhu.setBackground(Color.RED);
		btniMtKhu.setFont(new Font("Dialog", Font.BOLD, 20));
		panelTop.add(btniMtKhu);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelTop.add(horizontalStrut_1);

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(40, 0, 40, 0));
		panelBottom.setBackground(Color.DARK_GRAY);
		add(panelBottom, BorderLayout.SOUTH);
	}
}
