package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:48:14 AM
 */
public class QuanLyThongTinNhanVienPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien = null;

	/**
	 * 
	 */
	public QuanLyThongTinNhanVienPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(240, 0, 0, 0));
		panelTop.setBackground(Color.BLUE);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		// Table setup
		createTable();
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
		tblNhanVien.setRowHeight(50);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNhanVien = new JScrollPane(tblNhanVien);
		panelCenter.add(scrollPaneNhanVien, BorderLayout.CENTER);

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
