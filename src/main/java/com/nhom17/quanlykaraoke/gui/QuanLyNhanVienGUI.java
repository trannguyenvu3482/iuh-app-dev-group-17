package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.nhom17.quanlykaraoke.common.MyFrame;
import com.nhom17.quanlykaraoke.utils.ClockUtil;
import com.nhom17.quanlykaraoke.utils.OTPUtil;

public class QuanLyNhanVienGUI extends MyFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JLabel lbl = new JLabel("Time");
	private final JButton btnSend = new JButton("Gửi tin nhắn");

	public QuanLyNhanVienGUI() {

		lbl.setBounds(0, 21, 118, 64);

		lbl.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		lbl.setVerticalAlignment((int) CENTER_ALIGNMENT);
		lbl.setFont(new Font("Dialog", Font.BOLD, 50));
		getContentPane().add(lbl);

		ClockUtil clock = new ClockUtil(lbl);

		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnSend.setBounds(313, 285, 400, 60);
		getContentPane().add(btnSend, BorderLayout.SOUTH);
		clock.startClock();
		btnSend.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnSend)) {
			OTPUtil.sendSMS("+84832296849");
		}
	}

}
