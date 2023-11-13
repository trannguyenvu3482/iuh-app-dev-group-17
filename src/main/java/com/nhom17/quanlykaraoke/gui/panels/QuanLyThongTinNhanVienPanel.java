package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.toedter.calendar.JDateChooser;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:48:14 AM
 */
public class QuanLyThongTinNhanVienPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * 
	 */
	public QuanLyThongTinNhanVienPanel() {
		setSize(1200, 800);
		setLayout(null);

		JPanel panelTop = new JPanel();
		panelTop.setBounds(0, 0, 1200, 252);
		panelTop.setBorder(new EmptyBorder(240, 0, 0, 0));
		panelTop.setBackground(new Color(192, 192, 192));
		add(panelTop);
		panelTop.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(171, 29, 384, 40);
		panelTop.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(34, 29, 212, 40);
		panelTop.add(lblNewLabel);

		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCccd.setBounds(34, 80, 69, 40);
		panelTop.add(lblCccd);

		JLabel lblCccd_1 = new JLabel("SDT");
		lblCccd_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCccd_1.setBounds(310, 80, 44, 40);
		panelTop.add(lblCccd_1);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiiTnh.setBounds(34, 131, 81, 40);
		panelTop.add(lblGiiTnh);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgySinh.setBounds(297, 131, 90, 40);
		panelTop.add(lblNgySinh);

		JDateChooser fromDateChooser = new JDateChooser();
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser.setPreferredSize(new Dimension(150, 48));
		fromDateChooser.setLocale(new Locale("vi", "VN"));
		fromDateChooser.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser.setDateFormatString("d/M/y");
		fromDateChooser.setBounds(397, 131, 158, 40);
		panelTop.add(fromDateChooser);

		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrngThi.setBounds(34, 182, 103, 40);
		panelTop.add(lblTrngThi);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Đang làm", "Đã nghỉ" }));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(143, 180, 117, 40);
		panelTop.add(comboBox_1);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_1.setBounds(143, 132, 117, 40);
		panelTop.add(comboBox_1_1);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(92, 80, 208, 40);
		panelTop.add(textField_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(364, 80, 193, 40);
		panelTop.add(textField_1);

		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChcV.setBounds(297, 182, 103, 40);
		panelTop.add(lblChcV);

		JComboBox comboBox_1_2 = new JComboBox();
		comboBox_1_2.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý" }));
		comboBox_1_2.setToolTipText("");
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2.setBounds(397, 180, 158, 40);
		panelTop.add(comboBox_1_2);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_PLUS, 32, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(775, 29, 60, 60);
		panelTop.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_EDIT, 32, null));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(775, 100, 60, 60);
		panelTop.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(MyIcon.getIcon(MaterialDesignB.BROOM, 32, null));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(775, 169, 60, 60);
		panelTop.add(btnNewButton_2);

		JLabel lblNgySinhT = new JLabel("Ngày sinh từ");
		lblNgySinhT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgySinhT.setBounds(872, 131, 129, 40);
		panelTop.add(lblNgySinhT);

		JDateChooser fromDateChooser_1 = new JDateChooser();
		fromDateChooser_1.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser_1.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser_1.setPreferredSize(new Dimension(150, 48));
		fromDateChooser_1.setLocale(new Locale("vi", "VN"));
		fromDateChooser_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser_1.setDateFormatString("d/M/y");
		fromDateChooser_1.setBounds(1004, 131, 145, 40);
		panelTop.add(fromDateChooser_1);

		JLabel lblnNgySinh = new JLabel("Đến ngày sinh");
		lblnNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblnNgySinh.setBounds(872, 182, 129, 40);
		panelTop.add(lblnNgySinh);

		JDateChooser fromDateChooser_1_1 = new JDateChooser();
		fromDateChooser_1_1.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser_1_1.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser_1_1.setPreferredSize(new Dimension(150, 48));
		fromDateChooser_1_1.setLocale(new Locale("vi", "VN"));
		fromDateChooser_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser_1_1.setDateFormatString("d/M/y");
		fromDateChooser_1_1.setBounds(1004, 182, 145, 40);
		panelTop.add(fromDateChooser_1_1);

		JComboBox comboBox_1_2_1 = new JComboBox();
		comboBox_1_2_1.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		comboBox_1_2_1.setToolTipText("");
		comboBox_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2_1.setBounds(872, 80, 129, 40);
		panelTop.add(comboBox_1_2_1);

		JComboBox comboBox_1_2_1_1 = new JComboBox();
		comboBox_1_2_1_1.setModel(new DefaultComboBoxModel(new String[] { "Chức vụ", "Nhân viên", "Quản lý" }));
		comboBox_1_2_1_1.setToolTipText("");
		comboBox_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2_1_1.setBounds(1020, 80, 129, 40);
		panelTop.add(comboBox_1_2_1_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(872, 29, 277, 40);
		panelTop.add(textField_2);

		Box boxAvatar = Box.createHorizontalBox();
		boxAvatar.setLocation(565, 29);
		boxAvatar.setSize(200, 200);
		boxAvatar.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelTop.add(boxAvatar);

		// Set avatar

		Component horizontalGlue_1_1 = Box.createHorizontalGlue();
		boxAvatar.add(horizontalGlue_1_1);

		// Table setup
		createTable();
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
		tblNhanVien.setRowHeight(50);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNhanVien = new JScrollPane(tblNhanVien);
		scrollPaneNhanVien.setBounds(0, 251, 1200, 624);
		add(scrollPaneNhanVien);

		refreshTable();
	}

	/**
	 * 
	 */
	private void createTable() {
		final String[] colNames = { "Mã NV", "Họ và tên", "Chức vụ", "Giới tính", "SĐT", "Hoạt động" };
		modelNhanVien = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblNhanVien = new JTable(modelNhanVien);
	}

	/**
	 * 
	 */
	private void refreshTable() {
		modelNhanVien.setRowCount(0);

		Object[] testRow = { "NV001", "Võ Hoàng Phúc", "Quản lý", "Giới tính", "0903252508", "Hoạt động" };
		Object[] testRow2 = { "NV002", "Võ Hoàng Phúc", "Quản lý", "Giới tính", "0903252508", "Hoạt động" };
		Object[] testRow3 = { "NV003", "Võ Hoàng Phúc", "Quản lý", "Giới tính", "0903252508", "Hoạt động" };
		Object[] testRow4 = { "NV004", "Võ Hoàng Phúc", "Quản lý", "Giới tính", "0903252508", "Hoạt động" };

		modelNhanVien.addRow(testRow);
		modelNhanVien.addRow(testRow2);
		modelNhanVien.addRow(testRow3);
		modelNhanVien.addRow(testRow4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
