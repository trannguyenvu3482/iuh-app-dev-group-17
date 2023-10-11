package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.SystemColor;
import javax.swing.JFormattedTextField;
import javax.swing.border.EmptyBorder;

public class DangNhapGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private JPasswordField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnShow = new JButton("");
	private boolean isPasswordShown = false;

	private DangNhapBUS dangNhapBUS;
	public String maNVDangNhap;
	private JPasswordField txtNewPassword;
	private JPasswordField txtConfirmNewPassword;
	private JTextField txtPhoneNo;
	private JTextField txtOTP;
	private final JButton btnQuenMatKhau = new JButton("Quên mật khẩu?");
	private final JPanel panelLogin = new JPanel();
	private final JPanel panelForgot = new JPanel();

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

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.BLACK);
		leftPanel.setBounds(0, 0, 550, 567);
		mainPanel.add(leftPanel);
		leftPanel.setLayout(null);

		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("src/main/resources/images/logo.png"));
		logoLabel.setBounds(0, 0, 550, 568);
		leftPanel.add(logoLabel);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font("Dialog", Font.BOLD, 28));

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(546, 0, 640, 563);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(null);

		
		panelForgot.setVisible(false);
		panelForgot.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelForgot.setLayout(null);
		panelForgot.setBounds(56, 20, 520, 533);
		rightPanel.add(panelForgot);

		JButton btnGetOTP = new JButton("Lấy mã OTP");
		btnGetOTP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGetOTP.setBounds(378, 330, 141, 52);
		panelForgot.add(btnGetOTP);

		JButton btnShow2 = new JButton("");
		btnShow2.setPreferredSize(new Dimension(40, 30));
		btnShow2.setBorder(BorderFactory.createEmptyBorder());
		btnShow2.setBounds(474, 114, 50, 52);
		panelForgot.add(btnShow2);

		JLabel lblMaNVIcon_1 = new JLabel("");
		lblMaNVIcon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaNVIcon_1.setBounds(2, 116, 50, 50);
		panelForgot.add(lblMaNVIcon_1);

		JLabel lblMatKhauIcon_1 = new JLabel("");
		lblMatKhauIcon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauIcon_1.setBounds(2, 228, 50, 50);
		panelForgot.add(lblMatKhauIcon_1);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNewPassword.setColumns(10);
		txtNewPassword.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		txtNewPassword.setBounds(4, 114, 470, 50);
		panelForgot.add(txtNewPassword);

		txtConfirmNewPassword = new JPasswordField();
		txtConfirmNewPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtConfirmNewPassword.setEchoChar('*');
		txtConfirmNewPassword.setColumns(10);
		txtConfirmNewPassword.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		txtConfirmNewPassword.setBounds(6, 217, 518, 50);
		panelForgot.add(txtConfirmNewPassword);

		JLabel lblMaNV_1 = new JLabel("Nhập mật khẩu mới:");
		lblMaNV_1.setForeground(Color.BLACK);
		lblMaNV_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMaNV_1.setBounds(4, 85, 190, 26);
		panelForgot.add(lblMaNV_1);

		JLabel lblMatKhau_1 = new JLabel("Xác nhận mật khẩu mới:");
		lblMatKhau_1.setForeground(Color.BLACK);
		lblMatKhau_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMatKhau_1.setBounds(4, 187, 229, 26);
		panelForgot.add(lblMatKhau_1);

		JButton btnResetPassword = new JButton("Cấp lại mật khẩu mới");
		btnResetPassword.addActionListener(this);
		btnResetPassword.setFont(new Font("Dialog", Font.BOLD, 30));
		btnResetPassword.setBounds(2, 412, 517, 87);
		panelForgot.add(btnResetPassword);

		JLabel lblNhnVinng_1 = new JLabel("Quên mật khẩu");
		lblNhnVinng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVinng_1.setFont(new Font("Dialog", Font.BOLD, 36));
		lblNhnVinng_1.setBounds(2, 0, 520, 50);
		panelForgot.add(lblNhnVinng_1);

		JLabel lblMatKhau_1_1 = new JLabel("Nhập số điện thoại:");
		lblMatKhau_1_1.setForeground(Color.BLACK);
		lblMatKhau_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMatKhau_1_1.setBounds(4, 300, 229, 26);
		panelForgot.add(lblMatKhau_1_1);

		txtPhoneNo = new JTextField();
		txtPhoneNo.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBorder(new EmptyBorder(0, 50, 0, 0));
		txtPhoneNo.setBounds(4, 330, 242, 50);
		panelForgot.add(txtPhoneNo);

		JButton btnShow_1_1 = new JButton("");
		btnShow_1_1.setPreferredSize(new Dimension(40, 30));
		btnShow_1_1.setBorder(BorderFactory.createEmptyBorder());
		btnShow_1_1.setBounds(474, 116, 50, 50);
		panelForgot.add(btnShow_1_1);

		txtOTP = new JTextField();
		txtOTP.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtOTP.setColumns(10);
		txtOTP.setBorder(new EmptyBorder(0, 20, 0, 0));
		txtOTP.setBounds(271, 330, 111, 50);
		panelForgot.add(txtOTP);

		
		panelLogin.setBounds(56, 20, 520, 441);
		rightPanel.add(panelLogin);
		panelLogin.setLayout(null);

		JLabel lblMaNVIcon = new JLabel("");
		lblMaNVIcon.setBounds(2, 116, 50, 50);
		panelLogin.add(lblMaNVIcon);
		lblMaNVIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaNVIcon.setIcon(new ImageIcon("src/main/resources/images/icon-user.png"));

		JLabel lblMatKhauIcon = new JLabel("");
		lblMatKhauIcon.setBounds(2, 228, 50, 50);
		panelLogin.add(lblMatKhauIcon);
		lblMatKhauIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauIcon.setIcon(new ImageIcon("src/main/resources/images/icon-lock.png"));

		txtMaNV = new JTextField();
		txtMaNV.setBounds(0, 116, 520, 50);
		panelLogin.add(txtMaNV);
		txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		txtMaNV.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(0, 228, 472, 50);
		panelLogin.add(txtMatKhau);
		txtMatKhau.setEchoChar('*');
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		btnShow.setBounds(470, 228, 50, 50);
		panelLogin.add(btnShow);

		btnShow.setPreferredSize(new Dimension(40, 30));
		btnShow.setBorder(BorderFactory.createEmptyBorder());
		btnShow.setIcon(new ImageIcon("src/main/resources/images/icon-hide.png"));
		btnShow.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(0, 87, 160, 25);
		panelLogin.add(lblMaNV);
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setBounds(0, 198, 160, 25);
		panelLogin.add(lblMatKhau);
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBounds(0, 354, 520, 87);
		panelLogin.add(btnDangNhap);
		btnDangNhap.setFont(new Font("Dialog", Font.BOLD, 30));
		btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblNhnVinng = new JLabel("Nhân viên đăng nhập");
		lblNhnVinng.setBounds(0, 0, 520, 50);
		panelLogin.add(lblNhnVinng);
		lblNhnVinng.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVinng.setFont(new Font("Dialog", Font.BOLD, 36));

		
		btnQuenMatKhau.setBounds(367, 288, 153, 32);
		panelLogin.add(btnQuenMatKhau);
		btnQuenMatKhau.setBackground(new Color(240, 240, 240));
		btnQuenMatKhau.setForeground(Color.BLUE);
		btnQuenMatKhau.setFont(new Font("Dialog", Font.BOLD, 14));
		btnQuenMatKhau.setBorder(BorderFactory.createEmptyBorder());
		btnQuenMatKhau.setOpaque(false);
		btnQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnQuenMatKhau.addActionListener(this);

		btnDangNhap.addActionListener(this);
		btnShow.addActionListener(this);

		txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});

		// Add key listener for enter key
		txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
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
					JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
					txtMaNV.setBorder(
							BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
									BorderFactory.createEmptyBorder(0, 50, 0, 0)));
					txtMatKhau.setBorder(
							BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
									BorderFactory.createEmptyBorder(0, 50, 0, 0)));
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
		} else if (o.equals(btnQuenMatKhau)) {
			panelLogin.setVisible(false);
			panelForgot.setVisible(true);
		}

	}
}