package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

import com.nhom17.quanlykaraoke.dao.ChiTietPhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;

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
	private final ChiTietPhieuDatPhongDAO ctpdpDAO = new ChiTietPhieuDatPhongDAO();

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
		panelTop.setBorder(new EmptyBorder(10, 30, 10, 30));
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
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setBackground(Color.DARK_GRAY);
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
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setBackground(Color.RED);
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(btnXacNhan);

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new EmptyBorder(10, 30, 40, 30));
		panelCenter.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

		Box hBox3 = Box.createHorizontalBox();
		panelInfo.add(hBox3);

		JLabel lblTienDichVu = new JLabel("Tiền dịch vụ: 1.000.000VND");
		lblTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox3.add(lblTienDichVu);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		hBox3.add(horizontalGlue_3);

		JLabel lblGioNhanPhong = new JLabel("Giờ nhận phòng: 21:30:00 - 16/10/2023");
		lblGioNhanPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox3.add(lblGioNhanPhong);

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1);

		Box hBox4 = Box.createHorizontalBox();
		panelInfo.add(hBox4);

		JLabel lblTienPhong = new JLabel("Tiền phòng: 90.000VND");
		lblTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox4.add(lblTienPhong);

		Component horizontalGlue_3_1 = Box.createHorizontalGlue();
		hBox4.add(horizontalGlue_3_1);

		JLabel lblGioTraPhong = new JLabel("Giờ trả phòng: 22:30:00 - 16/10/2023");
		lblGioTraPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox4.add(lblGioTraPhong);

		Component verticalStrut_1_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1_1);

		Box hBox5 = Box.createHorizontalBox();
		panelInfo.add(hBox5);

		JLabel lblThueVAT = new JLabel("Thuế VAT: 10%");
		lblThueVAT.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox5.add(lblThueVAT);

		Component horizontalGlue_3_1_1 = Box.createHorizontalGlue();
		hBox5.add(horizontalGlue_3_1_1);

		JLabel lblTongThoiLuong = new JLabel("Tổng thời lượng: 60 phút");
		lblTongThoiLuong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox5.add(lblTongThoiLuong);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1_1_1);

		Box hBox6 = Box.createHorizontalBox();
		panelInfo.add(hBox6);

		JLabel lblTongTien = new JLabel("Tổng: 1.199.000VND");
		lblTongTien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox6.add(lblTongTien);

		Component horizontalGlue_3_1_1_1 = Box.createHorizontalGlue();
		hBox6.add(horizontalGlue_3_1_1_1);

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

		// Load booked rooms
		String maPDP = ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong()).getPhieuDatPhong()
				.getMaPhieuDatPhong();

		List<ChiTietPhieuDatPhong> listCTPDP = ctpdpDAO.getAllChiTietPhieuDatPhongByMaPhieuDatPhong(maPDP);

		int stt = 1;

		for (ChiTietPhieuDatPhong ctpdp : listCTPDP) {
			String thanhTien = MoneyFormatUtil
					.format(ChronoUnit.HOURS.between(ctpdp.getThoiGianBatDau(), LocalDateTime.now())
							* ConstantUtil.getStandardHourPrice(LocalDateTime.now()));
			Object[] rowData = { stt, "Phòng " + ctpdp.getPhong().getMaPhong(),
					DateTimeFormatUtil.formatTimeBetween(ctpdp.getThoiGianBatDau(), LocalDateTime.now()), "", "", "",
					thanhTien };

			model.addRow(rowData);
			stt++;
		}

		// Load food

	}
}
