package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;

public class DoiMatKhauDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JLabel lblTenNhanVien = new JLabel("");
	private JLabel lblCurrentPass = new JLabel("Nhập mật khẩu hiện tại: ");
	private JPasswordField txtCurrentPass = new JPasswordField();
	private JLabel lblNewPass = new JLabel("Nhập mật khẩu mới: ");
	private JPasswordField txtNewPass = new JPasswordField();
	private JLabel lblComfirmPass = new JLabel("Nhập lại mật khẩu mới: ");
	private JPasswordField txtComfirmPass = new JPasswordField();
	private final JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
	private final JButton btnHuy = new JButton("Hủy");

	private final NhanVienBUS nvBUS = new NhanVienBUS();
	private final NhanVien nhanVienCurrent;

	public DoiMatKhauDialog(NhanVien nhanVien) {
		this.nhanVienCurrent = nhanVien;
		getUI();
		btnDoiMatKhau.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.dispose();
		} else if (o.equals(btnDoiMatKhau)) {
			if (txtCurrentPass.toString().trim().length() == 0 || txtNewPass.toString().trim().length() == 0
					|| txtComfirmPass.toString().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!", "Thông báo",
						JOptionPane.ERROR_MESSAGE);
			} else {
				NhanVien nhanVien = nvBUS.getNhanVien(ConstantUtil.currentNhanVien.getMaNhanVien());
				if (!PasswordUtil.check(txtCurrentPass.getText(), nhanVien.getMatKhau())) {
					JOptionPane.showMessageDialog(null, "Mật khẩu hiện tại không chính xác!", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else if (!txtNewPass.getText().equalsIgnoreCase(txtComfirmPass.getText())) {
					JOptionPane.showMessageDialog(null, "Mật khẩu mới không đồng nhất!", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {	
					nhanVien.setMatKhau(PasswordUtil.encrypt(txtNewPass.getText()));
					nvBUS.updateNV(nhanVien);
					JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "Thông báo",
							JOptionPane.DEFAULT_OPTION);
					this.dispose();
				}
			}
		}
	}

	public void getUI() {
		setSize(800, 500);
		setTitle("Đổi mật khẩu");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		FlowLayout fl_panelTop = (FlowLayout) panelTop.getLayout();
		fl_panelTop.setVgap(20);
		fl_panelTop.setHgap(20);
		fl_panelTop.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);

		lblTenNhanVien.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTenNhanVien.setText("Nhân viên: " + nhanVienCurrent.getHoTen());
		panelTop.add(lblTenNhanVien);

		JPanel panelBot = new JPanel();
		getContentPane().add(panelBot, BorderLayout.SOUTH);
		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panelBot.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);

		btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuy.setBackground(Color.RED);
		btnHuy.setFont(new Font("Dialog", Font.BOLD, 26));
		panel_2.add(btnHuy);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue);

		btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoiMatKhau.setBackground(Color.GREEN);
		btnDoiMatKhau.setFont(new Font("Dialog", Font.BOLD, 26));
		panel_2.add(btnDoiMatKhau);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_1);

		Component verticalStrut = Box.createVerticalStrut(20);
		panelBot.add(verticalStrut);

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(50);
		panelCenter.add(verticalStrut_1);

		Box verticalBox = Box.createVerticalBox();
		panelCenter.add(verticalBox);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1.setAlignmentY(Component.CENTER_ALIGNMENT);
		verticalBox.add(horizontalBox_1_1);

		Component horizontalStrut_3 = Box.createHorizontalStrut(50);
		horizontalBox_1_1.add(horizontalStrut_3);

		lblCurrentPass.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_1_1.add(lblCurrentPass);

		txtCurrentPass.setFont(new Font("Dialog", Font.PLAIN, 24));
		horizontalBox_1_1.add(lblCurrentPass);
		txtCurrentPass.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_4);
		txtCurrentPass.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtCurrentPass.setColumns(10);
		horizontalBox_1_1.add(txtCurrentPass);

		Component horizontalStrut_3_3 = Box.createHorizontalStrut(50);
		horizontalBox_1_1.add(horizontalStrut_3_3);

		Component verticalStrut_1_2 = Box.createVerticalStrut(40);
		verticalBox.add(verticalStrut_1_2);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_3_1);

		lblNewPass.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox.add(lblNewPass);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(60);
		horizontalBox.add(horizontalStrut_2_1);
		txtNewPass.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtNewPass.setColumns(10);
		horizontalBox.add(txtNewPass);

		Component horizontalStrut_3_3_1 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_3_3_1);

		Component verticalStrut_1_2_1 = Box.createVerticalStrut(30);
		verticalBox.add(verticalStrut_1_2_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component horizontalStrut_3_2 = Box.createHorizontalStrut(50);
		horizontalBox_1.add(horizontalStrut_3_2);

		lblComfirmPass.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_1.add(lblComfirmPass);

		Component horizontalStrut_4_1 = Box.createHorizontalStrut(25);
		horizontalBox_1.add(horizontalStrut_4_1);
		txtComfirmPass.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtComfirmPass.setColumns(10);
		horizontalBox_1.add(txtComfirmPass);

		Component horizontalStrut_3_3_1_1 = Box.createHorizontalStrut(50);
		horizontalBox_1.add(horizontalStrut_3_3_1_1);

		Component verticalStrut_1_1 = Box.createVerticalStrut(40);
		verticalBox.add(verticalStrut_1_1);
	}
}
