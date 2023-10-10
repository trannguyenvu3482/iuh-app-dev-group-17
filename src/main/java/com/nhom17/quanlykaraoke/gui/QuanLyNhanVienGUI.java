package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.internal.build.AllowSysOut;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.ClockUtil;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;

public class QuanLyNhanVienGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JLabel lbl = new JLabel("Time");

	public QuanLyNhanVienGUI() {
		setTitle("Quản lý nhân viên");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		lbl.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		lbl.setVerticalAlignment((int) CENTER_ALIGNMENT);
		lbl.setFont(new Font("Dialog", Font.BOLD, 50));
		add(lbl);

		ClockUtil clock = new ClockUtil(lbl);
		clock.startClock();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

	}

}
