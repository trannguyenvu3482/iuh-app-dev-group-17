package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;

public class QuanLyNhanVienGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtMatKhau;
	private JButton btnThem = new JButton("Thêm");
	private JButton btnLay = new JButton("Set dark mode");
	private NhanVienDAO nvDAO = new NhanVienDAO();
	private int mode = 0;

	public QuanLyNhanVienGUI() {
		setTitle("Quản lý nhân viên");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblMaNV = new JLabel("Nhập mã nhân viên:");
		centerPanel.add(lblMaNV);

		txtMaNV = new JTextField();
		centerPanel.add(txtMaNV);
		txtMaNV.setColumns(10);

		JLabel lblNhpHTn = new JLabel("Nhập họ tên:");
		centerPanel.add(lblNhpHTn);

		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		centerPanel.add(txtHoTen);

		JLabel lblMaNV_1_1 = new JLabel("Nhập mật khẩu:");
		centerPanel.add(lblMaNV_1_1);

		txtMatKhau = new JTextField();
		txtMatKhau.setColumns(10);
		centerPanel.add(txtMatKhau);

		centerPanel.add(btnThem);
		centerPanel.add(btnLay);

		btnThem.addActionListener(this);
		btnLay.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			String maNV = txtMaNV.getText().trim();
			String hoTen = txtHoTen.getText().trim();
			String matKhau = PasswordUtil.encrypt(txtMatKhau.getText().trim());

			System.out.println(matKhau);
			if (PasswordUtil.check("23112003", matKhau)) {
				JOptionPane.showMessageDialog(null, "Chuẩn");
			}

			NhanVien nv = new NhanVien(maNV, hoTen, matKhau);
			if (nvDAO.addNhanVien(nv)) {
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}
		} else if (o.equals(btnLay)) {
			try {
				if (mode == 0) {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					SwingUtilities.updateComponentTreeUI(this);
					mode = 1;
				} else {
					UIManager.setLookAndFeel(new FlatLightLaf());
					SwingUtilities.updateComponentTreeUI(this);
					mode = 0;
				}

			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
