package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;

import com.nhom17.quanlykaraoke.common.MyIcon;

import net.miginfocom.swing.MigLayout;

public class QuanLyPhieuDatPhongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JTextField txtSearchMaPhong;
	private final JButton btnLichSuPDP = new JButton("");

	public QuanLyPhieuDatPhongPanel() {
		setSize(1200, 800);
		setBackground(Color.PINK);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.PINK);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("center", "[][][]", "[]"));

		JLabel lblFilter = new JLabel("Type:");
		panelTop.add(lblFilter, "cell 0 0,alignx trailing");

		JComboBox<String> boxFilter = new JComboBox<String>();
		boxFilter.setFont(new Font("Dialog", Font.BOLD, 20));

		boxFilter.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Phòng đang được sử dụng", "Phòng chưa được sử dụng" }));
		boxFilter.setSelectedIndex(0);
		panelTop.add(boxFilter, "cell 1 0,push ,alignx left,aligny center");

		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panelTop.add(panel, "cell 2 0,push ,alignx right,aligny center");

		txtSearchMaPhong = new JTextField();
		panel.add(txtSearchMaPhong);
		txtSearchMaPhong.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSearchMaPhong.setForeground(Color.LIGHT_GRAY);
		txtSearchMaPhong.setText("Nhập vào mã phòng cần tìm...");
		txtSearchMaPhong.setColumns(20);
		btnLichSuPDP.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(btnLichSuPDP);

		btnLichSuPDP.setIcon(MyIcon.getIcon(MaterialDesignH.HISTORY, 24, null));

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new MigLayout("center", "[][][][][][]", "[][]"));

		// Add left button
		JButton leftButton = new JButton("<");
		panelCenter.add(leftButton, "width 50px");

		// Add right button
		JButton rightButton = new JButton(">");
		panelCenter.add(rightButton, "wrap,right");

		JPanel panelBottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setHgap(60);
		add(panelBottom, BorderLayout.SOUTH);

		JLabel lblChuaDat = new JLabel("Phòng chưa có người đặt: 15");
		lblChuaDat.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT, 28, null));
		lblChuaDat.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblChuaDat);

		JLabel lblDaDat = new JLabel("Phòng đã có người đặt: 8");
		lblDaDat.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT_OFF, 28, null));
		lblDaDat.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblDaDat);

		JLabel lblPhongKhongHD = new JLabel("Phòng không hoạt động: 0");
		lblPhongKhongHD.setIcon(MyIcon.getIcon(MaterialDesignC.CANCEL, 28, null));
		lblPhongKhongHD.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblPhongKhongHD);

		// txtSearch handler
		txtSearchMaPhong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearchMaPhong.getText().equals("Nhập vào mã phòng cần tìm...")) {
					txtSearchMaPhong.setText("");
					txtSearchMaPhong.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearchMaPhong.getText().isEmpty()) {
					txtSearchMaPhong.setForeground(Color.GRAY);
					txtSearchMaPhong.setText("Nhập vào mã phòng cần tìm...");
				}
			}
		});
	}
}
