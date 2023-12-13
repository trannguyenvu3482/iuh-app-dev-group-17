package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.nhom17.quanlykaraoke.bus.KhachHangBUS;
import com.nhom17.quanlykaraoke.bus.PhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;

import raven.toast.Notifications;
import raven.toast.Notifications.Location;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 05-Nov-2023 11:50:53 PM
 */
public class TaoPhieuDatPhongDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JFormattedTextField txtSDT = null;
	private JTextField txtHoTen = null;
	private JFormattedTextField txtCCCD = null;
	private final JButton btnLapPhieu = new JButton("Lập phiếu");
	private final JButton btnHuy = new JButton("Hủy");
	private final JLabel lblTenPhong = new JLabel("Tên phòng: Phòng ");

	// VARIABLES
	private final KhachHangBUS khBUS = new KhachHangBUS();
	private final PhieuDatPhongBUS pdpBUS = new PhieuDatPhongBUS();
	private Phong phong = null;
	private boolean isChecked = false;
	private KhachHang kh = null;

	/**
	* 
	*/
	public TaoPhieuDatPhongDialog(Phong phong) {
		// Init UI
		initUI();

		// Load data
		this.phong = phong;
		lblTenPhong.setText(lblTenPhong.getText().concat(phong.getMaPhong()));

		// Action listeners
		btnLapPhieu.addActionListener(this);
		btnHuy.addActionListener(this);

	}

	public boolean checkTonTai(KhachHang kh) {
		boolean ketQua = true;
		if (kh == null)
			ketQua = false;
		return ketQua;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnHuy)) {
			this.dispose();
		} else if (o.equals(btnLapPhieu)) {
			handleLapPhieu();
		}
	}

	/**
	 * 
	 */
	private void handleLapPhieu() {
		kh = khBUS.getKhachHangBySDT(txtSDT.getText().trim());

		// Khách hàng cũ
		if (!isChecked && kh != null) {
			txtHoTen.setText(kh.getHoTen());
			txtCCCD.setText(kh.getCCCD());

			Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
					"Đã tìm thấy khách hàng có số điện thoại " + kh.getSoDienThoai() + "!");

			isChecked = true;
			return;
		} else if (kh == null) {
			if (txtSDT.getText().trim().matches("0\\d{9}") && txtCCCD.getText().trim().matches("0\\d{11}")) {
				kh = new KhachHang(txtHoTen.getText().trim(), txtSDT.getText().trim(), txtCCCD.getText().trim());

				khBUS.addKhachHang(kh);
				Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
						"Đã thêm khách hàng " + kh.getHoTen() + "!");
			} else {
				Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
						"Số điện thoại hoặc CCCD không đúng định dạng");
			}

		}

		PhieuDatPhong pdp = new PhieuDatPhong("", ConstantUtil.currentNhanVien, false, kh);
		if (pdpBUS.addPhieuDatPhong(pdp, phong)) {
			Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
					"Đã thêm phiếu đặt phòng thành công");

			dispose();
		} else {
			Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
					"Đã có lỗi khi thêm phiếu đặt phòng");
		}
	}

	/**
	 * 
	 */
	private void initUI() {
		// TODO Auto-generated method stub
		setSize(700, 500);
		setTitle("Tạo phiếu đặt phòng");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		FlowLayout fl_panelTop = (FlowLayout) panelTop.getLayout();
		fl_panelTop.setVgap(20);
		fl_panelTop.setHgap(20);
		fl_panelTop.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panelTop, BorderLayout.NORTH);

		lblTenPhong.setFont(new Font("Dialog", Font.BOLD, 26));
		panelTop.add(lblTenPhong);

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

		btnLapPhieu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLapPhieu.setBackground(Color.GREEN);
		btnLapPhieu.setFont(new Font("Dialog", Font.BOLD, 26));
		panel_2.add(btnLapPhieu);

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

		JLabel lblSDT = new JLabel("SĐT khách hàng:");
		lblSDT.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_1_1.add(lblSDT);

		Component horizontalStrut_2 = Box.createHorizontalStrut(18);
		horizontalBox_1_1.add(horizontalStrut_2);

		MaskFormatter txtSDTFormatter = null;
		try {
			txtSDTFormatter = new MaskFormatter("0#########");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtSDT = new JFormattedTextField(txtSDTFormatter);
		txtSDT.setFont(new Font("Dialog", Font.PLAIN, 24));
		horizontalBox_1_1.add(txtSDT);
		txtSDT.setColumns(10);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_4);

		Component horizontalStrut_3_3 = Box.createHorizontalStrut(50);
		horizontalBox_1_1.add(horizontalStrut_3_3);

		Component verticalStrut_1_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1_2);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_3_1);

		JLabel lblHoTen = new JLabel("Tên khách hàng:");
		lblHoTen.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox.add(lblHoTen);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(18);
		horizontalBox.add(horizontalStrut_2_1);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtHoTen.setColumns(10);
		horizontalBox.add(txtHoTen);

		Component horizontalStrut_3_3_1 = Box.createHorizontalStrut(50);
		horizontalBox.add(horizontalStrut_3_3_1);

		Component verticalStrut_1_2_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1_2_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		Component horizontalStrut_3_2 = Box.createHorizontalStrut(50);
		horizontalBox_1.add(horizontalStrut_3_2);

		JLabel lblCCCD = new JLabel("CCCD khách hàng:");
		lblCCCD.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_1.add(lblCCCD);

		MaskFormatter txtCCCDFormatter = null;
		try {
			txtCCCDFormatter = new MaskFormatter("############");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCCCD = new JFormattedTextField(txtCCCDFormatter);
		txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 24));
		txtCCCD.setColumns(10);
		horizontalBox_1.add(txtCCCD);

		Component horizontalStrut_3_3_1_1 = Box.createHorizontalStrut(50);
		horizontalBox_1.add(horizontalStrut_3_3_1_1);

		Component verticalStrut_1_1 = Box.createVerticalStrut(100);
		verticalBox.add(verticalStrut_1_1);
	}

}
