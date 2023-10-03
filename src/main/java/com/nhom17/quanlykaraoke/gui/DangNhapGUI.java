package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nhom17.quanlykaraoke.bus.DangNhapBUS;

public class DangNhapGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnShow = new JButton("");
	private boolean isPasswordShown = false;

	private DangNhapBUS dangNhapBUS;
	public String maNVDangNhap;

	public String getMaNVDangNhap() {
		return maNVDangNhap;
	}

	public DangNhapGUI() {
		setTitle("Đăng nhập");
		setSize(1200, 600);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon("src/main/resources/images/logo-icon.png").getImage());

		dangNhapBUS = new DangNhapBUS();

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 550, 567);
		mainPanel.add(panel);
		panel.setLayout(null);

		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("src/main/resources/images/logo.png"));
		logoLabel.setBounds(0, 0, 550, 568);
		panel.add(logoLabel);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("Dialog", Font.BOLD, 28));

		JLabel lblMaNVIcon = new JLabel("");
		lblMaNVIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaNVIcon.setIcon(new ImageIcon("src/main/resources/images/icon-user.png"));
		lblMaNVIcon.setBounds(630, 174, 50, 50);
		mainPanel.add(lblMaNVIcon);

		JLabel lblMatKhauIcon = new JLabel("");
		lblMatKhauIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauIcon.setIcon(new ImageIcon("src/main/resources/images/icon-lock.png"));
		lblMatKhauIcon.setBounds(630, 286, 50, 50);
		mainPanel.add(lblMatKhauIcon);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMaNV.setBounds(628, 174, 520, 50);
		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		mainPanel.add(txtMaNV);

		txtMaNV.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setEchoChar('*');
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		txtMatKhau.setBounds(628, 286, 472, 50);
		mainPanel.add(txtMatKhau);

		btnShow.setPreferredSize(new Dimension(40, 30));
		btnShow.setBorder(BorderFactory.createEmptyBorder());
		btnShow.setIcon(new ImageIcon("src/main/resources/images/icon-hide.png"));
		btnShow.setBounds(1098, 286, 50, 50);
		mainPanel.add(btnShow);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMaNV.setBounds(628, 145, 160, 25);
		mainPanel.add(lblMaNV);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMatKhau.setBounds(628, 256, 160, 25);
		mainPanel.add(lblMatKhau);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Dialog", Font.BOLD, 30));
		btnDangNhap.setBounds(628, 372, 520, 87);
		mainPanel.add(btnDangNhap);

		JLabel lblNhnVinng = new JLabel("Nhân viên đăng nhập");
		lblNhnVinng.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVinng.setFont(new Font("Dialog", Font.BOLD, 36));
		lblNhnVinng.setBounds(628, 58, 520, 50);
		mainPanel.add(lblNhnVinng);

		btnDangNhap.addActionListener(this);
		btnShow.addActionListener(this);

		// Add key listener for enter key
		txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});

		txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});
	}

	private boolean isPasswordValid(String maNV, String password) {
		if (!maNV.matches("NV\\d{3}")) {
			JOptionPane.showMessageDialog(null, "Mã nhân viên phải có định dạng NVXXX");
			txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
					BorderFactory.createEmptyBorder(0, 50, 0, 0)));
			return false;
		}

		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		if (password.equals("")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không được rỗng");
			txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
					BorderFactory.createEmptyBorder(0, 50, 0, 0)));
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDangNhap)) {
			String maNV = txtMaNV.getText().trim();
			String password = txtMatKhau.getText().trim();

			if (isPasswordValid(maNV, password)) {
				boolean ketQua = dangNhapBUS.checkDangNhap(maNV, password);

				if (ketQua) {
					JOptionPane.showMessageDialog(null, "Đúng mật khẩu");
				} else {
					JOptionPane.showMessageDialog(null, "Sai mật khẩu");
					txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
					txtMatKhau.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
			}

		} else if (o.equals(btnShow)) {
			isPasswordShown = !isPasswordShown;
			if (isPasswordShown) {
				btnShow.setIcon(new ImageIcon("src/main/resources/images/icon-show.png"));
				txtMatKhau.setEchoChar((char) 0);

			} else {
				btnShow.setIcon(new ImageIcon("src/main/resources/images/icon-hide.png"));
				txtMatKhau.setEchoChar('*');
			}
		}

	}
}