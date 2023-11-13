package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nhom17.quanlykaraoke.entities.Phong;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 10:39:28 AM
 */
public class ThanhToanDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JTable tbl;
	private DefaultTableModel model;

	// VARIABLES
	private Phong p;

	/**
	 * 
	 */
	public ThanhToanDialog(Phong p) {
		setSize(1200, 800);
		setTitle("Thanh toán phiếu đặt phòng");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		this.p = p;

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(10, 30, 0, 30));
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

		Box hBox1 = Box.createHorizontalBox();
		panelTop.add(hBox1);

		JLabel lblTenNhanVien = new JLabel("Tên nhân viên: Trần Nguyên Vũ");
		lblTenNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox1.add(lblTenNhanVien);

		Component horizontalGlue = Box.createHorizontalGlue();
		hBox1.add(horizontalGlue);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng: Trần Ngọc Phát");
		lblTenKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox1.add(lblTenKhachHang);

		Component verticalStrut = Box.createVerticalStrut(10);
		panelTop.add(verticalStrut);

		Box hBox2 = Box.createHorizontalBox();
		panelTop.add(hBox2);

		JLabel lblSDTNhanVien = new JLabel("SDT nhân viên: 0903252508");
		lblSDTNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox2.add(lblSDTNhanVien);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		hBox2.add(horizontalGlue_1);

		JLabel lblSDTKhachHang = new JLabel("SDT khách hàng: 0919231475");
		lblSDTKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox2.add(lblSDTKhachHang);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(0, 30, 20, 30));
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));

		JButton btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(btnQuayLai);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panelBottom.add(horizontalGlue_2);

		JCheckBox chkXuatHoaDon = new JCheckBox("Xuất hóa đơn?");
		chkXuatHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(chkXuatHoaDon);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBottom.add(horizontalStrut);

		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(btnXacNhan);

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new EmptyBorder(0, 30, 0, 30));
		panelCenter.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));

		JScrollPane scrollPaneTable = new JScrollPane();
		panelCenter.add(scrollPaneTable, BorderLayout.CENTER);

		createTable();
		scrollPaneTable.setViewportView(tbl);

		// Load data to table
		refreshTable();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void createTable() {
		final String[] colNames = { "STT", "Tên", "Số lượng/Thời lượng", "Đơn giá", "Đơn vị tính", "Phụ phí",
				"Thành tiền" };
		model = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.setFont(new Font("Dialog", Font.PLAIN, 16));
		tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setAutoCreateRowSorter(true);
		tbl.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbl.setRowHeight(40);

		// Col width
		tbl.getColumnModel().getColumn(0).setPreferredWidth(8);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(75);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(100);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	};

	private void refreshTable() {
		model.setRowCount(0);

		Object[] rowData = { "1", "Bia 333", "5", "10.000 đ", "Lon", "", "50000 đ" };

		model.addRow(rowData);

	}
}
