package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignN;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.ChiTietDichVuBUS;
import com.nhom17.quanlykaraoke.bus.ChiTietPhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.bus.HangHoaBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;

import raven.toast.Notifications;
import raven.toast.Notifications.Location;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 4:30:19 PM
 */
public class QuanLyDichVuDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JTable tblLeft;
	private JTable tblRight;
	private DefaultTableModel modelLeft;
	private DefaultTableModel modelRight;
	private JTextField txtSearch;
	private final JButton btnThem = new JButton("");
	private final JButton btnXoa = new JButton("");
	private final JButton btnXacNhan = new JButton("Xác nhận");
	private final JButton btnHuy = new JButton("Hủy");
	private final JButton btnCapNhatSoLuong = new JButton("");
	private final JLabel lblTenNhanVien = new JLabel("");
	private final JLabel lblTenPhong = new JLabel("");
	private final JComboBox<String> boxFilter = new JComboBox<String>();

	// VARIABLES
	private Phong p;
	private PhieuDatPhong pdp;
	private HangHoaBUS hhBUS = new HangHoaBUS();
	private ChiTietDichVuBUS ctdvBUS = new ChiTietDichVuBUS();

	private ChiTietPhieuDatPhongBUS ctpdpBUS = new ChiTietPhieuDatPhongBUS();
	private int stt = 1;
	private List<ChiTietDichVu> listCTDVCurrent = new ArrayList<ChiTietDichVu>();
	private List<ChiTietDichVu> listCTDVPending = new ArrayList<ChiTietDichVu>();
	private List<ChiTietDichVu> listCTDVDelete = new ArrayList<ChiTietDichVu>();
	private TableRowSorter<TableModel> rowSorter;
	private List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);

	/**
	 * 
	 */
	public QuanLyDichVuDialog(Phong p) {
		this.p = p;
		setSize(1200, 800);
		setTitle("Danh sách dịch vụ");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelBottom, BorderLayout.SOUTH);

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panelTop.setBackground(ConstantUtil.MAIN_BLUE);
		getContentPane().add(panelTop, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Quản lý dịch vụ");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 28));
		lblTitle.setForeground(Color.WHITE);
		panelTop.add(lblTitle);

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));

		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new LineBorder(new Color(64, 64, 64), 5));
		panelLeft.setBackground(new Color(238, 238, 238));
		panelCenter.add(panelLeft);
		panelLeft.setLayout(new BorderLayout(0, 0));

		JLabel lblLeftTitle = new JLabel("Danh sách hàng hóa");
		lblLeftTitle.setBackground(new Color(238, 238, 238));
		lblLeftTitle.setForeground(Color.BLACK);
		lblLeftTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		lblLeftTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelLeft.add(lblLeftTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panelLeft.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(238, 238, 238));
		panel_4.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 5, 16, 5)));
		panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(50, 102, 133));
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào tên hàng hóa cần tìm");
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_4.add(txtSearch);
		txtSearch.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(100);
		panel_4.add(horizontalStrut);

		boxFilter.setForeground(new Color(50, 102, 133));
		boxFilter.putClientProperty("JTextField.placeholderText", "Chọn loại hàng hóa");
		boxFilter.setFont(new Font("Dialog", Font.BOLD, 18));
		boxFilter.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Loại hàng hóa", "Đồ uống có cồn", "Thức ăn" }));
		panel_4.add(boxFilter);

		JScrollPane scrollPaneLeft = new JScrollPane();
		panel.add(scrollPaneLeft);

		createLeftTable();
		scrollPaneLeft.setViewportView(tblLeft);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(238, 238, 238));
		panel.add(panel_1, BorderLayout.SOUTH);

		btnThem.setIcon(MyIcon.getIcon(MaterialDesignP.PLUS_THICK, 32, null));

		panel_1.add(btnThem);
		btnThem.setFont(new Font("Dialog", Font.BOLD, 20));

		JPanel panelLeftBottom = new JPanel();
		panelLeftBottom.setBorder(new CompoundBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(255, 255, 255)),
				new EmptyBorder(5, 10, 5, 10)));
		panelLeftBottom.setBackground(new Color(238, 238, 238));
		panelLeft.add(panelLeftBottom, BorderLayout.SOUTH);
		panelLeftBottom.setLayout(new BoxLayout(panelLeftBottom, BoxLayout.X_AXIS));
		btnHuy.setForeground(new Color(50, 102, 133));

		btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuy.setBackground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("Dialog", Font.BOLD, 20));
		panelLeftBottom.add(btnHuy);

		JPanel panelRight = new JPanel();
		panelRight.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		panelRight.setBackground(new Color(238, 238, 238));
		panelCenter.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));

		JLabel lblRightTitle = new JLabel("Dịch vụ đã chọn");
		lblRightTitle.setForeground(Color.BLACK);
		lblRightTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		lblRightTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelRight.add(lblRightTitle, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panelRight.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(238, 238, 238));
		panel_5.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 10, 5, 10)));
		panel_2.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		lblTenPhong.setForeground(Color.BLACK);
		lblTenPhong.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_5.add(lblTenPhong);

		lblTenNhanVien.setForeground(Color.BLACK);
		lblTenNhanVien.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_5.add(lblTenNhanVien);

		JScrollPane scrollPaneRight = new JScrollPane();
		panel_2.add(scrollPaneRight);

		createRightTable();
		scrollPaneRight.setViewportView(tblRight);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(238, 238, 238));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(10);
		panel_2.add(panel_3, BorderLayout.SOUTH);

		btnXoa.setIcon(MyIcon.getIcon(MaterialDesignC.CLOSE_THICK, 32, null));
		btnXoa.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_3.add(btnXoa);

		btnCapNhatSoLuong.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCapNhatSoLuong.setIcon(MyIcon.getIcon(MaterialDesignN.NUMERIC, 32, null));
		panel_3.add(btnCapNhatSoLuong);

		JPanel panelRightBottom = new JPanel();
		panelRightBottom.setBackground(new Color(238, 238, 238));
		panelRightBottom.setBorder(new CompoundBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(255, 255, 255)),
				new EmptyBorder(5, 10, 5, 10)));
		panelRight.add(panelRightBottom, BorderLayout.SOUTH);
		panelRightBottom.setLayout(new BoxLayout(panelRightBottom, BoxLayout.X_AXIS));

		Component horizontalGlue = Box.createHorizontalGlue();
		panelRightBottom.add(horizontalGlue);

		btnXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXacNhan.setBackground(new Color(255, 255, 255));
		btnXacNhan.setForeground(new Color(50, 102, 133));
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
		panelRightBottom.add(btnXacNhan);

		// Refresh tables

		// Search and Filter
		txtSearch.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtSearch.getText();
				if (text.equals("")) {
					filters.set(0, RowFilter.regexFilter(".*", 0));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));
				} else {
					filters.set(0, RowFilter.regexFilter("(?i)" + text, 0));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));
				}
			}

		});

		// Action listeners
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhatSoLuong.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXacNhan.addActionListener(this);
		boxFilter.addActionListener(this);

		// Set labels
		lblTenNhanVien.setText("Tên nhân viên:" + ConstantUtil.currentNhanVien.getHoTen());
		lblTenPhong.setText("Tên phòng: Phòng " + p.getMaPhong());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			if (tblLeft.getSelectedRow() != -1) {
				String result = JOptionPane.showInputDialog(null, "Nhập vào số lượng cần thêm", "Thông báo",
						JOptionPane.QUESTION_MESSAGE);

				int row = tblLeft.getSelectedRow();
				if (Integer.valueOf(result) > Integer.valueOf(modelLeft.getValueAt(row, 4).toString())) {
					Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
							"Số lượng không hợp lệ");
				} else {
					ChiTietDichVu c = new ChiTietDichVu(pdp, hhBUS.getHangHoa(modelLeft.getValueAt(row, 0).toString()),
							p, Integer.valueOf(result));

					listCTDVPending.add(c);

					Object[] rowData = { stt, c.getHangHoa().getTenHangHoa(), c.getHangHoa().getDonGia(),
							c.getSoLuong(), MoneyFormatUtil.format(c.getHangHoa().getDonGia() * c.getSoLuong()) };

					modelLeft.setValueAt(
							Integer.valueOf(modelLeft.getValueAt(row, 4).toString()) - Integer.valueOf(result),
							tblLeft.getSelectedRow(), 4);

					modelRight.addRow(rowData);

					this.stt++;

				}

			} else {
				Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
						"Bạn phải chọn một loại hàng hóa cần thêm");
			}
		} else if (o.equals(btnXoa)) {
			if (tblRight.getSelectedRow() != -1) {
				int row = tblRight.getSelectedRow();
				int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dịch vụ này", "Thông báo",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					ChiTietDichVu c;

					c = listCTDVCurrent.stream().filter(
							ctdv -> modelRight.getValueAt(row, 1).toString().equals(ctdv.getHangHoa().getTenHangHoa()))
							.findAny().orElse(null);

					if (c == null) {
						c = listCTDVPending.stream().filter(ctdv -> modelRight.getValueAt(row, 1).toString()
								.equals(ctdv.getHangHoa().getTenHangHoa())).findAny().orElse(null);

						listCTDVPending.remove(c);
					} else {
						listCTDVCurrent.remove(c);
						listCTDVDelete.add(c);
					}

					modelRight.removeRow(row);
				}

			} else {
				Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
						"Bạn phải chọn một dịch vụ cần hủy");
			}
		} else if (o.equals(btnCapNhatSoLuong)) {
			if (tblRight.getSelectedRow() != -1) {
				int row = tblRight.getSelectedRow();
				String result = JOptionPane.showInputDialog(null, "Nhập vào số lượng mới", "Thông báo",
						JOptionPane.QUESTION_MESSAGE);

				// Check if result is int > 0
				if (!ConstantUtil.isStringInteger(result) || Integer.valueOf(result) < 0) {
					Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
							"Số lượng không hợp lệ");
					return;
				}

				if (Integer.valueOf(result) > Integer.valueOf(modelLeft.getValueAt(row, 4).toString())) {
					Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
							"Số lượng không hợp lệ");
				} else {
					ChiTietDichVu c;

					c = listCTDVCurrent.stream().filter(
							ctdv -> modelRight.getValueAt(row, 1).toString().equals(ctdv.getHangHoa().getTenHangHoa()))
							.findAny().orElse(null);

					if (c == null) {
						c = listCTDVPending.stream().filter(ctdv -> modelRight.getValueAt(row, 1).toString()
								.equals(ctdv.getHangHoa().getTenHangHoa())).findAny().orElse(null);

						int index = 0;
						for (ChiTietDichVu ctdv : listCTDVPending) {
							if (ctdv.equals(c)) {
								ctdv.setSoLuong(Integer.valueOf(result));

								listCTDVPending.set(index, ctdv);
							}

							index++;
						}
					} else {
						int index = 0;
						for (ChiTietDichVu ctdv : listCTDVCurrent) {

							if (ctdv.equals(c)) {
								ctdv.setSoLuong(Integer.valueOf(result));

								listCTDVCurrent.set(index, ctdv);
							}

							index++;
						}
					}

					modelRight.setValueAt(Integer.valueOf(result), row, 3);

				}

			} else {
				Notifications.getInstance().show(raven.toast.Notifications.Type.ERROR, Location.BOTTOM_RIGHT,
						"Bạn phải chọn một dịch vụ cần hủy");
			}
		} else if (o.equals(btnHuy)) {
			dispose();
		} else if (o.equals(btnXacNhan)) {
			listCTDVPending.forEach((ctdv) -> {
				ctdvBUS.addChiTietDichVu(ctdv);
			});

			listCTDVCurrent.forEach((ctdv) -> {
				ctdvBUS.updateChiTietDichVu(ctdv);
			});

			listCTDVDelete.forEach((ctdv) -> {
				ctdvBUS.deleteChiTietDichVu(ctdv);
			});

			Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
					"Thêm dịch vụ thành công");

			dispose();
		} else if (o.equals(boxFilter)) {
			handleBoxFilter();
		}
	}

	/**
	 * 
	 */
	private void handleBoxFilter() {
		// TODO Auto-generated method stub
		if (boxFilter.getSelectedIndex() == 0) {
			filters.set(1, RowFilter.regexFilter(".*", 2));
			rowSorter.setRowFilter(RowFilter.andFilter(filters));
		} else {
			filters.set(1, RowFilter.regexFilter("(?i)" + boxFilter.getSelectedItem().toString(), 2));
			rowSorter.setRowFilter(RowFilter.andFilter(filters));

		}
	}

	/**
	 * 
	 */
	private void createLeftTable() {
		final String[] colNames = { "Mã HH", "Tên", "Loại", "Đơn giá", "Số lượng tồn", "Đơn vị" };
		modelLeft = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblLeft = new JTable(modelLeft);
		tblLeft.setBackground(new Color(243, 241, 228));
		tblLeft.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		tblLeft.setColumnSelectionAllowed(false);
		tblLeft.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblLeft.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));
		tblLeft.getTableHeader().setReorderingAllowed(false);
		tblLeft.getTableHeader().setEnabled(false);
		tblLeft.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblLeft.setRowHeight(40);

		// Col width
		tblLeft.getColumnModel().getColumn(0).setPreferredWidth(8);
		tblLeft.getColumnModel().getColumn(1).setPreferredWidth(20);
		tblLeft.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblLeft.getColumnModel().getColumn(3).setPreferredWidth(30);
		tblLeft.getColumnModel().getColumn(4).setPreferredWidth(35);
		tblLeft.getColumnModel().getColumn(5).setPreferredWidth(40);
		tblLeft.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		rowSorter = new TableRowSorter<TableModel>(modelLeft);
		tblLeft.setRowSorter(rowSorter);

		// Add filters
		filters.add(RowFilter.regexFilter(".*", 0));
		filters.add(RowFilter.regexFilter(".*", 1));

		// Load data
		refreshLeftTable();
	}

	/**
	 * 
	 */
	private void refreshLeftTable() {
		// TODO Auto-generated method stub
		modelLeft.setRowCount(0);

		hhBUS.getAllHangHoas().forEach((hh) -> {

			Object[] rowData = { hh.getMaHangHoa(), hh.getTenHangHoa(), hh.getLoaiHangHoa().getTenLoaiHangHoa(),
					MoneyFormatUtil.format(hh.getDonGia()), hh.getSoLuongTon(), hh.getLoaiHangHoa().getDonViTinh() };
			modelLeft.addRow(rowData);
		});

	}

	private void createRightTable() {
		final String[] colNames = { "STT", "Tên", "Đơn giá", "Số lượng", "Thành tiền" };
		modelRight = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblRight = new JTable(modelRight);
		tblRight.setBackground(new Color(243, 241, 228));
		tblRight.setFont(new Font("Dialog", Font.PLAIN, 14));
		tblRight.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));
		tblRight.getTableHeader().setReorderingAllowed(false);
		tblRight.getTableHeader().setEnabled(false);
		tblRight.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblRight.setRowHeight(40);

		// Col width
		tblRight.getColumnModel().getColumn(0).setPreferredWidth(8);
		tblRight.getColumnModel().getColumn(1).setPreferredWidth(20);
		tblRight.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblRight.getColumnModel().getColumn(3).setPreferredWidth(30);
		tblRight.getColumnModel().getColumn(4).setPreferredWidth(35);
		tblRight.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		refreshRightTable();
	}

	private void refreshRightTable() {
		// TODO Auto-generated method stub
		modelRight.setRowCount(0);

		// Logic
		pdp = ctpdpBUS.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong()).getPhieuDatPhong();

		listCTDVCurrent = ctdvBUS.getChiTietDichVuByMaPDPAndMaPhong(pdp.getMaPhieuDatPhong(), p.getMaPhong());

		for (ChiTietDichVu c : listCTDVCurrent) {
			Object[] rowData = { stt, c.getHangHoa().getTenHangHoa(), c.getHangHoa().getDonGia(), c.getSoLuong(),
					MoneyFormatUtil.format(c.getHangHoa().getDonGia() * c.getSoLuong()) };

			modelRight.addRow(rowData);

			this.stt++;
		}

	}
}
