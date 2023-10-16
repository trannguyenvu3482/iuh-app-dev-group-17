package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.materialdesign2.MaterialDesignL;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.materialdesign2.MaterialDesignR;

import com.nhom17.quanlykaraoke.bus.DangNhapBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.common.MyMessageDialog;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 12-Oct-2023 14:23:00
 */
public class DangNhapGUI extends JFrame implements ActionListener {
	// SERIAL VERSION UID
	private static final long serialVersionUID = 1L;

	// BUS
	private DangNhapBUS dangNhapBUS;

	// COMPONENTS
	private final JTextField txtMaNV;
	private final JPasswordField txtMatKhau;
	private final JButton btnDangNhap;
	private final JButton btnShow = new JButton("");
	private final JPasswordField txtNewPassword;
	private final JPasswordField txtConfirmNewPassword;
	private final JFormattedTextField txtPhoneNo;
	private final JTextField txtOTP;
	private final JButton btnQuenMatKhau = new JButton("Quên mật khẩu?");
	private final JPanel panelLogin = new JPanel();
	private final JPanel panelForgot = new JPanel();
	private final JButton btnShow2 = new JButton("",
			MyIcon.getIcon(MaterialDesignE.EYE_OFF, MyIcon.DEFAULT_SIZE, null));
	private final JButton btnGetOTP = new JButton("Lấy mã OTP");
	private final JButton btnReturn = new JButton("");

	// LOCAL VARIABLES
	private Map<JPasswordField, Boolean> isPasswordShownStates = new HashMap<>();
	private long duration = 10000;

	private String loggedInEmployeeID = null;

	// Create UI
	public DangNhapGUI() throws Exception {
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
		btnReturn.setIcon(MyIcon.getIcon(MaterialDesignR.RESTORE, MyIcon.DEFAULT_SIZE, null));

		btnShow2.setPreferredSize(new Dimension(40, 30));
		btnShow2.setBorder(BorderFactory.createEmptyBorder());
		btnShow2.setBounds(474, 114, 50, 52);
		panelForgot.add(btnShow2);

		JLabel lblPhoneNoLabel = new JLabel("");
		lblPhoneNoLabel.setIcon(MyIcon.getIcon(MaterialDesignP.PHONE, MyIcon.DEFAULT_SIZE, null));
		lblPhoneNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNoLabel.setBounds(2, 330, 50, 50);
		panelForgot.add(lblPhoneNoLabel);

		btnGetOTP.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGetOTP.setBounds(378, 330, 141, 52);
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
		txtNewPassword.setBounds(4, 114, 470, 50);
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
		lblMaNV_1.setBounds(4, 85, 190, 26);
		panelForgot.add(lblMaNV_1);

		JLabel lblMatKhau_1 = new JLabel("Xác nhận mật khẩu mới:");
		lblMatKhau_1.setForeground(Color.BLACK);
		lblMatKhau_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		lblMatKhau_1.setBounds(4, 187, 229, 26);
		panelForgot.add(lblMatKhau_1);

		JButton btnResetPassword = new JButton("Cấp lại mật khẩu mới");
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

		txtPhoneNo = new JFormattedTextField();
		MaskFormatter formatter = new MaskFormatter("0###########");
		formatter.setValidCharacters("0123456789");

		txtPhoneNo.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
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

		txtMaNV = new JTextField();
		txtMaNV.setBounds(0, 116, 520, 50);
		panelLogin.add(txtMaNV);
		txtMaNV.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtMaNV.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

		txtMaNV.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(0, 228, 472, 50);
		panelLogin.add(txtMatKhau);
		txtMatKhau.setEchoChar('*');
		txtMatKhau.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		btnShow.setBounds(470, 228, 50, 50);
		panelLogin.add(btnShow);

		btnShow.setPreferredSize(new Dimension(40, 30));
		btnShow.setBorder(BorderFactory.createEmptyBorder());
		btnShow.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, MyIcon.DEFAULT_SIZE, null));
		btnShow.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
		btnShow.addActionListener(this);
		btnShow2.addActionListener(this);
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

		// Add key listener for enter key
		txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
					btnDangNhap.doClick();
				}
			}
		});

		// Add phone-number listener
		txtPhoneNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();

				// Not allow user to enter different characters except for digit
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		txtPhoneNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// Auto add 0 to text field
				if (txtPhoneNo.getText().startsWith("0")) {
					return;
				}
				txtPhoneNo.setValue("0" + txtPhoneNo.getText());
			}
		});

		// Add otp-listener
		txtOTP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();

				// Not allow user to enter different characters except for digit
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDangNhap)) {
			handleLogin();
		} else if (o.equals(btnShow)) {
			toggleVisibility(btnShow, txtMatKhau);
		} else if (o.equals(btnShow2)) {
			toggleVisibility(btnShow2, txtNewPassword);
		} else if (o.equals(btnQuenMatKhau)) {
			setTitle("Quên mật khẩu");
			panelLogin.setVisible(false);
			panelForgot.setVisible(true);
		} else if (o.equals(btnGetOTP)) {
			MyMessageDialog.showMessage(null, "Nhận OTP", "Lấy OTP thành công");
			timeoutBtnGetOTP();
		} else if (o.equals(btnReturn)) {
			setTitle("Đăng nhập");
			panelLogin.setVisible(true);
			panelForgot.setVisible(false);
		}
	}

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

	private void handleLogin() {
		String maNV = txtMaNV.getText().trim();

		String password = txtMatKhau.getText().trim();

		if (isPasswordValid(maNV, password)) {
			boolean ketQua = dangNhapBUS.checkDangNhap(maNV, password);

			if (ketQua) {
				setLoggedInEmployeeID(maNV);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
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

	private void toggleVisibility(JButton btn, JPasswordField txt) {
		boolean visible = isPasswordShownStates.get(txt);
		visible = !visible;

		isPasswordShownStates.put(txt, visible);

		if (visible) {
			btn.setIcon(MyIcon.getIcon(MaterialDesignE.EYE, MyIcon.DEFAULT_SIZE, null));
			txt.setEchoChar((char) 0);
		} else {
			btn.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, MyIcon.DEFAULT_SIZE, null));
			txt.setEchoChar('*');
		}

	}

	public String getLoggedInEmployeeID() {
		return loggedInEmployeeID;
	}

	public void setLoggedInEmployeeID(String loggedInEmployeeID) {
		this.loggedInEmployeeID = loggedInEmployeeID;
	}
}