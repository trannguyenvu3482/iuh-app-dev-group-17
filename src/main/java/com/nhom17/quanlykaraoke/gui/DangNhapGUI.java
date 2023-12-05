/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created Nov 6, 2023 1:06:43 AM
 */
package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignL;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.DangNhapBUS;
import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.OTPUtil;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;

import raven.toast.Notifications;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 12-Oct-2023 14:23:00
 */

public class DangNhapGUI extends JFrame implements ActionListener {
	// INTERFACE
	public interface LoginListener {
		void onLogin(String employeeId);
	}

	// SERIAL VERSION UID
	private static final long serialVersionUID = 1L;

	// BUS
	private DangNhapBUS dangNhapBUS;

	// COMPONENTS
	private JFormattedTextField txtMaNV = null;
	private JPasswordField txtMatKhau;
	private final JButton btnDangNhap;
	private final JPasswordField txtNewPassword;
	private final JPasswordField txtConfirmNewPassword;
	private final JFormattedTextField txtPhoneNo;
	private final JFormattedTextField txtOTP;
	private final JButton btnQuenMatKhau = new JButton("Quên mật khẩu?");
	private final JPanel panelLogin = new JPanel();
	private final JPanel panelForgot = new JPanel();
	private final JButton btnGetOTP = new JButton("Lấy mã OTP");
	private final JButton btnReturn = new JButton("");
	private final JButton btnResetPassword = new JButton("Tạo mật khẩu mới");

	// VARIABLES
	private final NhanVienBUS nvBUS = new NhanVienBUS();
	private Map<JPasswordField, Boolean> isPasswordShownStates = new HashMap<>();
	private LoginListener listener;
	private long duration = 10000;
	private String loggedInEmployeeID = null;
	private String phoneNo = "";

	public void setLoginListener(LoginListener listener) {
		this.listener = listener;
	}

	// Create UI
	public DangNhapGUI() throws Exception {
		// Init
		Notifications.getInstance().setJFrame(this);

		setTitle("Đăng nhập");
		setSize(1200, 600);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setIconImage(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/logo.png")))
				.getImage());

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
		logoLabel.setIcon(
				new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/logo.png"))));
		logoLabel.setBounds(0, 0, 550, 568);
		leftPanel.add(logoLabel);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 28));

		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(546, 0, 640, 563);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(null);

		panelForgot.setVisible(false);
		panelForgot.setBorder(new EmptyBorder(0, 20, 0, 0));
		panelForgot.setLayout(null);
		panelForgot.setBounds(56, 20, 520, 533);
		rightPanel.add(panelForgot);

		btnReturn.setBounds(479, 5, 40, 40);
		panelForgot.add(btnReturn);
		btnReturn.setIcon(MyIcon.getIcon(MaterialDesignA.ARROW_LEFT_BOLD, MyIcon.DEFAULT_SIZE, null));

		JLabel lblPhoneNoLabel = new JLabel("");
		lblPhoneNoLabel.setIcon(MyIcon.getIcon(MaterialDesignP.PHONE, MyIcon.DEFAULT_SIZE, null));
		lblPhoneNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNoLabel.setBounds(2, 330, 50, 50);
		panelForgot.add(lblPhoneNoLabel);

		btnGetOTP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGetOTP.setBounds(378, 330, 141, 52);
		btnGetOTP.setEnabled(false);
		panelForgot.add(btnGetOTP);

		JLabel lblNewPassWordIcon = new JLabel("", MyIcon.getIcon(MaterialDesignL.LOCK, MyIcon.DEFAULT_SIZE, null),
				SwingConstants.LEFT);
		lblNewPassWordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassWordIcon.setBounds(2, 116, 50, 50);
		panelForgot.add(lblNewPassWordIcon);

		JLabel lblConfirmPasswordIcon = new JLabel("", MyIcon.getIcon(MaterialDesignL.LOCK, MyIcon.DEFAULT_SIZE, null),
				SwingConstants.LEFT);
		lblConfirmPasswordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPasswordIcon.setBounds(2, 217, 50, 50);
		panelForgot.add(lblConfirmPasswordIcon);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setEchoChar('*');
		txtNewPassword.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtNewPassword.setColumns(10);
		txtNewPassword.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		txtNewPassword.setBounds(4, 114, 515, 50);
		panelForgot.add(txtNewPassword);

		txtConfirmNewPassword = new JPasswordField();
		txtConfirmNewPassword.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtConfirmNewPassword.setEchoChar('*');
		txtConfirmNewPassword.setColumns(10);
		txtConfirmNewPassword.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		txtConfirmNewPassword.setBounds(6, 217, 518, 50);
		panelForgot.add(txtConfirmNewPassword);

		JLabel lblMaNV_1 = new JLabel("Nhập mật khẩu mới:");
		lblMaNV_1.setForeground(Color.BLACK);
		lblMaNV_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		lblMaNV_1.setBounds(4, 85, 206, 28);
		panelForgot.add(lblMaNV_1);

		JLabel lblMatKhau_1 = new JLabel("Xác nhận mật khẩu mới:");
		lblMatKhau_1.setForeground(Color.BLACK);
		lblMatKhau_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		lblMatKhau_1.setBounds(4, 187, 243, 28);
		panelForgot.add(lblMatKhau_1);

		btnResetPassword.setEnabled(false);
		btnResetPassword.addActionListener(this);
		btnResetPassword.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		btnResetPassword.setBounds(2, 412, 517, 87);
		panelForgot.add(btnResetPassword);

		JLabel lblNhnVinng_1 = new JLabel("Quên mật khẩu");
		lblNhnVinng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVinng_1.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
		lblNhnVinng_1.setBounds(2, 0, 520, 50);
		panelForgot.add(lblNhnVinng_1);

		JLabel lblMatKhau_1_1 = new JLabel("Nhập số điện thoại:");
		lblMatKhau_1_1.setForeground(Color.BLACK);
		lblMatKhau_1_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		lblMatKhau_1_1.setBounds(4, 300, 229, 26);
		panelForgot.add(lblMatKhau_1_1);

		MaskFormatter txtphoneNoFormatter = new MaskFormatter("0#########");
		txtPhoneNo = new JFormattedTextField(txtphoneNoFormatter);

		txtPhoneNo.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				if (txtPhoneNo.getText().trim().length() == 10) {
					btnGetOTP.setEnabled(true);
				} else {
					btnGetOTP.setEnabled(false);
				}
			}
		});

		txtPhoneNo.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtPhoneNo.setColumns(10);
		txtPhoneNo.setBorder(new EmptyBorder(0, 50, 0, 0));
		txtPhoneNo.setBounds(4, 330, 242, 50);
		panelForgot.add(txtPhoneNo);

		MaskFormatter txtOTPFormatter = new MaskFormatter("######");
		txtOTP = new JFormattedTextField(txtOTPFormatter);

		txtOTP.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				if (txtOTP.getText().trim().length() == 6) {
					btnResetPassword.setEnabled(true);
				} else {
					btnResetPassword.setEnabled(false);
				}
			}
		});
		txtOTP.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
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
		lblMaNVIcon.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT, MyIcon.DEFAULT_SIZE, null));

		JLabel lblMatKhauIcon = new JLabel("");
		lblMatKhauIcon.setBounds(2, 228, 50, 50);
		panelLogin.add(lblMatKhauIcon);
		lblMatKhauIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhauIcon.setIcon(MyIcon.getIcon(MaterialDesignL.LOCK, MyIcon.DEFAULT_SIZE, null));

		MaskFormatter txtMaNVFormatter = new MaskFormatter("NV###");
		txtMaNV = new JFormattedTextField(txtMaNVFormatter);
		txtMaNV.setBounds(0, 116, 520, 50);
		panelLogin.add(txtMaNV);
		txtMaNV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		txtMaNV.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(0, 228, 520, 50);
		panelLogin.add(txtMatKhau);
		txtMatKhau.setEchoChar('*');
		txtMatKhau.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(0, 87, 160, 25);
		panelLogin.add(lblMaNV);
		lblMaNV.setForeground(Color.BLACK);
		lblMaNV.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setBounds(0, 198, 160, 25);
		panelLogin.add(lblMatKhau);
		lblMatKhau.setForeground(Color.BLACK);
		lblMatKhau.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBounds(0, 354, 520, 87);
		panelLogin.add(btnDangNhap);
		btnDangNhap.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		btnDangNhap.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblNhnVinng = new JLabel("Nhân viên đăng nhập");
		lblNhnVinng.setBounds(0, 0, 520, 50);
		panelLogin.add(lblNhnVinng);
		lblNhnVinng.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhnVinng.setFont(new Font(Font.DIALOG, Font.BOLD, 36));

		btnQuenMatKhau.setBounds(367, 288, 153, 32);
		panelLogin.add(btnQuenMatKhau);
		btnQuenMatKhau.setBackground(new Color(240, 240, 240));
		btnQuenMatKhau.setForeground(Color.BLUE);
		btnQuenMatKhau.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		btnQuenMatKhau.setBorder(BorderFactory.createEmptyBorder());
		btnQuenMatKhau.setOpaque(false);
		btnQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnQuenMatKhau.addActionListener(this);

		btnDangNhap.addActionListener(this);
		btnGetOTP.addActionListener(this);
		btnReturn.addActionListener(this);

		isPasswordShownStates.put(txtMatKhau, false);
		isPasswordShownStates.put(txtNewPassword, false);

		this.

				txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
					@Override
					public void keyPressed(java.awt.event.KeyEvent evt) {
						if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
							btnDangNhap.doClick();
						}
					}
				});
	}

	/**
	 * Action performed.
	 *
	 * @param e the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDangNhap)) {
			handleLogin();
		} else if (o.equals(btnQuenMatKhau)) {
			setTitle("Quên mật khẩu");
			panelLogin.setVisible(false);
			panelForgot.setVisible(true);
		} else if (o.equals(btnGetOTP)) {
			phoneNo = "+84" + txtPhoneNo.getText().substring(1);
			OTPUtil.sendSMS("+" + phoneNo);
			System.out.println("Send SMS to: " + phoneNo);
			timeoutBtnGetOTP();
			Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT,
					"Đã gửi OTP thành công");

		} else if (o.equals(btnResetPassword)) {
			if (OTPUtil.checkOTP(phoneNo, txtOTP.getText())) {
				// TODO: Thêm logic reset password tại đây
				NhanVien nv = nvBUS.getNhanVien(loggedInEmployeeID);
				nv.setMatKhau(PasswordUtil.encrypt(txtNewPassword.getText().trim()));
				Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT,
						"Đã thay đổi mật khẩu");
				btnReturn.doClick();
			} else {
				JOptionPane.showMessageDialog(null, "OTP không đúng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		} else if (o.equals(btnReturn)) {
			setTitle("Đăng nhập");
			panelLogin.setVisible(true);
			panelForgot.setVisible(false);
		}
	}

	/**
	 * Timeout btn get OTP, timeout will increase: 10secs, 30secs, 1min, 5mins
	 */
	private void timeoutBtnGetOTP() {
		btnGetOTP.setEnabled(false);

		Timer timer = new Timer((int) duration, e -> btnGetOTP.setEnabled(true));

		timer.setRepeats(false);
		timer.start();

		// Timeout is 10 secs, 30secs, 1min, 5mins
		if (duration < 5000000) {
			duration += 20000;
		}
	}

	/**
	 * Handle login action
	 */
	private void handleLogin() {
		String maNV = txtMaNV.getText().trim();

		String password = txtMatKhau.getText().trim();

		if (isPasswordValid(maNV, password)) {
			boolean ketQua = dangNhapBUS.checkDangNhap(maNV, password);

			if (ketQua) {
				setLoggedInEmployeeID(maNV);
				if (listener != null) {
					listener.onLogin(maNV);
				}
				Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT,
						"Đăng nhập thành công");
				dispose();
			} else {
				Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT,
						"Sai tên đăng nhập hoặc mật khẩu");
				txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
						BorderFactory.createEmptyBorder(0, 50, 0, 0)));
				txtMatKhau.setBorder(
						BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
								BorderFactory.createEmptyBorder(0, 50, 0, 0)));
			}
		}
	}

	private boolean isPasswordValid(String maNV, String password) {
		if (!maNV.matches("NV\\d{3}")) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT,
					"Mã nhân viên phải có dạng NVXXX");
			txtMaNV.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
					BorderFactory.createEmptyBorder(0, 50, 0, 0)));
			return false;
		}

		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		if (password.equals("")) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT,
					"Mật khẩu không được rỗng");
			txtMatKhau.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 3, true),
					BorderFactory.createEmptyBorder(0, 50, 0, 0)));
			return false;
		}

		return true;
	}

	public String getLoggedInEmployeeID() {
		return loggedInEmployeeID;
	}

	public void setLoggedInEmployeeID(String loggedInEmployeeID) {
		this.loggedInEmployeeID = loggedInEmployeeID;
	}
}