package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nhom17.quanlykaraoke.bus.PhieuDatPhongBUS;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 14-Nov-2023 12:30:32 AM
 */
public class LichSuPhieuDatPhongDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JTable tbl;
	private DefaultTableModel model;
	private JTextField txtSearch;

	// VARIABLES
	private PhieuDatPhongBUS pdpBUS = new PhieuDatPhongBUS();

	/**
	 * 
	 */
	public LichSuPhieuDatPhongDialog() {
		setSize(1000, 1000);
		setTitle("Lịch sử phiếu đặt phòng");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));

		JLabel lblLchSPhiu = new JLabel("Lịch sử phiếu đặt phòng");
		lblLchSPhiu.setFont(new Font("Dialog", Font.BOLD, 20));
		panelTop.add(lblLchSPhiu);

		Component horizontalStrut = Box.createHorizontalStrut(500);
		panelTop.add(horizontalStrut);

		txtSearch = new JTextField();
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào mã phiếu đặt phòng cần tìm");
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelTop.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		createTable();
		scrollPane.setViewportView(tbl);

		refreshTable();
	}

	private void createTable() {
		final String[] colNames = { "Mã phiếu đặt phòng", "Nhân viên phụ trách", "Họ tên khách hàng",
				"SĐT khách hàng" };
		model = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);
		tbl.setRowSelectionAllowed(false);
		tbl.setColumnSelectionAllowed(false);
		tbl.setCellSelectionEnabled(false);
		tbl.setFont(new Font("Dialog", Font.PLAIN, 18));
		tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setAutoCreateRowSorter(true);
		tbl.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbl.setRowHeight(40);

		// Col width
		tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

	};

	private void refreshTable() {
		model.setRowCount(0);

		pdpBUS.getAllPhieuDatPhongs().forEach((pdp) -> {
			Object[] rowData = { pdp.getMaPhieuDatPhong(), pdp.getNhanVien().getHoTen(), pdp.getKhachHang().getHoTen(),
					pdp.getKhachHang().getSoDienThoai() };

			model.addRow(rowData);
		});
	}
}
