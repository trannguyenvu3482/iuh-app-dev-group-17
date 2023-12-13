package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.HangHoaBUS;
import com.nhom17.quanlykaraoke.bus.LoaiHangHoaBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.ExcelUtil;

import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import raven.toast.Notifications.Type;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:15:21 PM
 */
public class QuanLyHangHoaPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JTable tblDichVu;
	private DefaultTableModel modelDichVu;
	private final JTextField txtTenSanPham;
	private final JFormattedTextField txtDonGia;
	private final JTextField txtSoLuongTon;
	private final JComboBox<String> cbTenHH = new JComboBox<String>();
	private final JComboBox<String> boxTrangThai;
	private final JTextField txtSearch;
	private final JButton btnThem = new JButton("");
	private final JButton btnSua = new JButton("");
	private final JButton btnNhapHang = new JButton("");
	private final JButton btnGiamHang = new JButton("");
	private final JButton btnClearFields = new JButton("");
	private final JButton btnExportToExcel = new JButton("");
	private final JComboBox<String> boxFilterTrangThai = new JComboBox<String>();
	private final JComboBox<String> boxFilterLoaiHangHoa = new JComboBox<String>();
	private HangHoaBUS hangHoaBUS = new HangHoaBUS();
	private LoaiHangHoaBUS loaiHangHoaBUS = new LoaiHangHoaBUS();
	private NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private NumberFormatter formatter;
	private TableRowSorter<TableModel> rowsorter;
	private List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(4);

	/**
	 * 
	 */
	public QuanLyHangHoaPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setForeground(new Color(50, 102, 133));
		panelTop.setBackground(ConstantUtil.MAIN_LIGHTEST_BLUE);
		panelTop.setBorder(new EmptyBorder(10, 24, 20, 24));
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

		Box boxOne_1 = Box.createHorizontalBox();
		panelTop.add(boxOne_1);

		JLabel lblTnSnPhm_2 = new JLabel("Thông tin hàng hóa:");
		lblTnSnPhm_2.setForeground(new Color(50, 102, 133));
		lblTnSnPhm_2.setFont(new Font("Dialog", Font.BOLD, 24));
		boxOne_1.add(lblTnSnPhm_2);

		Component horizontalGlue = Box.createHorizontalGlue();
		boxOne_1.add(horizontalGlue);

		Component verticalStrut = Box.createVerticalStrut(20);
		panelTop.add(verticalStrut);

		Box boxOne = Box.createHorizontalBox();
		panelTop.add(boxOne);

		JLabel lblTnSnPhm = new JLabel("Tên hàng hóa:");
		lblTnSnPhm.setForeground(new Color(50, 102, 133));
		lblTnSnPhm.setFont(new Font("Dialog", Font.BOLD, 20));
		boxOne.add(lblTnSnPhm);

		Component horizontalStrut = Box.createHorizontalStrut(36);
		boxOne.add(horizontalStrut);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setForeground(new Color(50, 102, 133));
		boxOne.add(txtTenSanPham);
		txtTenSanPham.setFont(new Font("Dialog", Font.PLAIN, 20));

		txtTenSanPham.setColumns(10);

		Component horizontalStrut_1_1_2_1 = Box.createHorizontalStrut(800);
		boxOne.add(horizontalStrut_1_1_2_1);

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1);

		Box boxTwo = Box.createHorizontalBox();
		panelTop.add(boxTwo);

		JLabel lblTnSnPhm_1 = new JLabel("Loại hàng hóa:");
		lblTnSnPhm_1.setForeground(new Color(50, 102, 133));
		lblTnSnPhm_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxTwo.add(lblTnSnPhm_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(32);
		boxTwo.add(horizontalStrut_1);
		cbTenHH.setForeground(new Color(50, 102, 133));

		cbTenHH.setFont(new Font("Dialog", Font.PLAIN, 20));
		for (LoaiHangHoa lhh : loaiHangHoaBUS.getAllLoaiHangHoas()) {
			cbTenHH.addItem(lhh.getTenLoaiHangHoa());
		}

		boxTwo.add(cbTenHH);

		Component horizontalStrut_2 = Box.createHorizontalStrut(800);
		boxTwo.add(horizontalStrut_2);

		Component verticalStrut_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1);

		Box boxThree = Box.createHorizontalBox();
		panelTop.add(boxThree);

		JLabel lblTnSnPhm_1_1 = new JLabel("Đơn giá:");
		lblTnSnPhm_1_1.setForeground(new Color(50, 102, 133));
		lblTnSnPhm_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxThree.add(lblTnSnPhm_1_1);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(92);
		boxThree.add(horizontalStrut_1_1);

		format.setMaximumFractionDigits(0);
		formatter = new NumberFormatter(format);

		formatter.setMinimum(0);
		formatter.setMaximum(100000000);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		txtDonGia = new JFormattedTextField(formatter);
		txtDonGia.setForeground(new Color(50, 102, 133));
		txtDonGia.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtDonGia.setColumns(10);
		txtDonGia.setValue(0);

		boxThree.add(txtDonGia);

		Component horizontalStrut_1_1_2_1_1_1 = Box.createHorizontalStrut(940);
		boxThree.add(horizontalStrut_1_1_2_1_1_1);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1);

		Box boxFour = Box.createHorizontalBox();
		panelTop.add(boxFour);

		JLabel lblTnSnPhm_1_1_1 = new JLabel("Số lượng tồn kho:");
		lblTnSnPhm_1_1_1.setForeground(new Color(50, 102, 133));
		lblTnSnPhm_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFour.add(lblTnSnPhm_1_1_1);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setEditable(false);
		txtSoLuongTon.setEnabled(false);
		txtSoLuongTon.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSoLuongTon.setText("0");
		boxFour.add(txtSoLuongTon);

		Component horizontalStrut_1_1_2_1_1_1_1 = Box.createHorizontalStrut(1000);
		boxFour.add(horizontalStrut_1_1_2_1_1_1_1);

		Component verticalStrut_1_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1_1);

		Box boxFive = Box.createHorizontalBox();
		panelTop.add(boxFive);

		JLabel lblTnSnPhm_1_1_1_1 = new JLabel("Trạng thái:");
		lblTnSnPhm_1_1_1_1.setForeground(new Color(50, 102, 133));
		lblTnSnPhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFive.add(lblTnSnPhm_1_1_1_1);

		Component horizontalStrut_1_1_1 = Box.createHorizontalStrut(65);
		boxFive.add(horizontalStrut_1_1_1);

		boxTrangThai = new JComboBox<String>();
		boxTrangThai.setForeground(new Color(50, 102, 133));
		boxTrangThai.setEditable(true);
		boxTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Còn hoạt động", "Ngưng hoạt động" }));
		boxTrangThai.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxFive.add(boxTrangThai);

		Component horizontalStrut_1_1_2_1_1_1_1_1 = Box.createHorizontalStrut(900);
		boxFive.add(horizontalStrut_1_1_2_1_1_1_1_1);

		Component verticalStrut_1_1_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1_1_1);

		Box boxSix = Box.createHorizontalBox();
		panelTop.add(boxSix);
		btnThem.setForeground(new Color(50, 102, 133));
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnThem.setIcon(MyIcon.getIcon(MaterialDesignP.PLUS_THICK, 32, null));
		btnThem.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnThem);

		Component horizontalStrut_1_2_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1);
		btnSua.setForeground(new Color(50, 102, 133));
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSua.setIcon(MyIcon.getIcon(MaterialDesignP.PENCIL, 32, null));
		btnSua.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnSua);

		Component horizontalStrut_1_2_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1);
		btnNhapHang.setForeground(new Color(50, 102, 133));
		btnNhapHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnNhapHang.setIcon(MyIcon.getIcon(MaterialDesignA.ARROW_UP_BOLD, 32, null));
		btnNhapHang.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnNhapHang);

		Component horizontalStrut_1_2_1_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1_1);
		btnGiamHang.setForeground(new Color(50, 102, 133));

		btnGiamHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGiamHang.setIcon(MyIcon.getIcon(MaterialDesignA.ARROW_DOWN_BOLD, 32, null));
		btnGiamHang.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnGiamHang);

		Component horizontalStrut_1_2_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1);
		btnClearFields.setForeground(new Color(50, 102, 133));

		btnClearFields.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearFields.setIcon(MyIcon.getIcon(MaterialDesignB.BACKSPACE, 32, null));
		btnClearFields.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnClearFields);

		Component horizontalStrut_1_2_1_1_1_2 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1_2);

		btnExportToExcel.setIcon(MyIcon.getIcon(MaterialDesignM.MICROSOFT_EXCEL, 32, null));
		btnExportToExcel.setForeground(new Color(50, 102, 133));
		boxSix.add(btnExportToExcel);

		Component verticalStrut_1_1_1_1_1_1 = Box.createVerticalStrut(40);
		panelTop.add(verticalStrut_1_1_1_1_1_1);

		Box boxSix_1 = Box.createHorizontalBox();
		panelTop.add(boxSix_1);

		JLabel lblBLc = new JLabel("Bộ lọc: ");
		lblBLc.setForeground(new Color(50, 102, 133));
		lblBLc.setFont(new Font("Dialog", Font.BOLD, 20));
		boxSix_1.add(lblBLc);
		boxFilterLoaiHangHoa.setForeground(new Color(50, 102, 133));

		boxFilterLoaiHangHoa.setFont(new Font("Dialog", Font.BOLD, 20));
		String[] dataLHH = { "Loại hàng hoá" };

		for (LoaiHangHoa lhh : loaiHangHoaBUS.getAllLoaiHangHoas()) {
			dataLHH = Arrays.copyOf(dataLHH, dataLHH.length + 1);
			dataLHH[dataLHH.length - 1] = lhh.getTenLoaiHangHoa();
		}
		boxFilterLoaiHangHoa.setModel(new DefaultComboBoxModel<String>(dataLHH));
		boxSix_1.add(boxFilterLoaiHangHoa);

		Component horizontalStrut_1_2 = Box.createHorizontalStrut(40);
		boxSix_1.add(horizontalStrut_1_2);
		boxFilterTrangThai.setForeground(new Color(50, 102, 133));

		boxFilterTrangThai.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFilterTrangThai.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Còn hoạt động", "Ngưng hoạt động" }));
		boxSix_1.add(boxFilterTrangThai);

		Component horizontalStrut_1_1_2 = Box.createHorizontalStrut(300);
		boxSix_1.add(horizontalStrut_1_1_2);

		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(50, 102, 133));
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxSix_1.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào tên hàng hóa cần tìm");

		JScrollPane scrollPaneTable = new JScrollPane();
		add(scrollPaneTable, BorderLayout.CENTER);

		tblDichVu = new JTable();
		createTable();
		scrollPaneTable.setViewportView(tblDichVu);

		// Action listeners
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnNhapHang.addActionListener(this);
		btnClearFields.addActionListener(this);
		btnGiamHang.addActionListener(this);
		btnExportToExcel.addActionListener(this);

		boxFilterLoaiHangHoa.addActionListener(this);
		boxFilterTrangThai.addActionListener(this);
		// Load data to table
		refreshTable();
		txtSearch.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String txtTim = txtSearch.getText();
				if (txtTim.equals("")) {
					filters.set(0, RowFilter.regexFilter(".*", 1));
					rowsorter.setRowFilter(RowFilter.andFilter(filters));
				} else {
					filters.set(0, RowFilter.regexFilter("(?i)" + txtTim, 1));
					rowsorter.setRowFilter(RowFilter.andFilter(filters));
				}
			}

		});
	}

	private void createTable() {
		final String[] colNames = { "Mã hàng hóa", "Tên sản phẩm", "Loại", "Đơn giá", "Số lượng tồn kho",
				"Trạng thái" };
		modelDichVu = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tblDichVu = new JTable(modelDichVu);
		tblDichVu.setBorder(new TitledBorder(null, "Danh s\u00E1ch h\u00E0ng h\u00F3a", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		tblDichVu.setForeground(new Color(50, 102, 133));
		tblDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDichVu.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblDichVu.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblDichVu.getTableHeader().setReorderingAllowed(false);
		tblDichVu.setAutoCreateRowSorter(false);
		tblDichVu.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblDichVu.setRowHeight(50);

		rowsorter = new TableRowSorter<TableModel>(modelDichVu);
		tblDichVu.setRowSorter(rowsorter);

		// Add filters
		filters.add(RowFilter.regexFilter(".*", 1));
		filters.add(RowFilter.regexFilter(".*", 2));
		filters.add(RowFilter.regexFilter(".*", 5));
		filters.add(RowFilter.regexFilter(".*", 0));

		tblDichVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblDichVu.getSelectedRow() != -1) {
					txtTenSanPham.setText((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 1));
					cbTenHH.setSelectedItem((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 2));
					try {
						txtDonGia.setValue(nf.parse((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 3)));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtSoLuongTon.setText((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 4));
					boxTrangThai.setSelectedItem((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 5));
				}
			}
		});
	}

	/**
	 * 
	 */
	Locale lc = new Locale("vi", "VN");
	NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

	private void refreshTable() {
		modelDichVu.setRowCount(0);
		for (HangHoa hh : hangHoaBUS.getAllHangHoas()) {
			String[] row = { hh.getMaHangHoa(), hh.getTenHangHoa(), hh.getLoaiHangHoa().getTenLoaiHangHoa(),
					nf.format(hh.getDonGia()), String.valueOf(hh.getSoLuongTon()),
					hh.isTrangThai() ? "Còn hoạt động" : "Ngưng hoạt động" };
			modelDichVu.addRow(row);
		}

	}

	private void clearFields() {
		txtTenSanPham.setText("");
		txtDonGia.setValue(0);
		txtSoLuongTon.setText("0");
		boxTrangThai.setSelectedItem("Ngưng hoạt động");
		txtTenSanPham.grabFocus();
		tblDichVu.clearSelection();
	}

	private boolean trangThai(String tt) {
		if (tt.equals("Còn hoạt động"))
			return true;
		else
			return false;
	}

	@SuppressWarnings("resource")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			if (txtTenSanPham.getText().trim().length() != 0) {
				double b = 0;
				if (txtDonGia.getValue() instanceof Long) {
					Long a = (Long) txtDonGia.getValue();
					b = (double) a;
				} else {
					int a = (int) txtDonGia.getValue();
					b = (double) a;
				}
				LoaiHangHoa lhh = loaiHangHoaBUS.getLoaiHangHoaByname(cbTenHH.getSelectedItem().toString());
				HangHoa hh = new HangHoa("", txtTenSanPham.getText(), lhh, Integer.parseInt(txtSoLuongTon.getText()), b,
						trangThai(boxTrangThai.getSelectedItem().toString()));
				hangHoaBUS.addHangHoa(hh);
				refreshTable();
				clearFields();
			} else {
				JOptionPane.showMessageDialog(this, "Tên sản phầm không được để trống!");
			}
		} else if (o.equals(btnClearFields)) {
			clearFields();
		} else if (o.equals(btnSua)) {
			if (tblDichVu.getSelectedRow() != -1) {

				if (txtTenSanPham.getText().trim().length() != 0) {
					if (txtTenSanPham.getText().trim().length() != 0) {
						double b = 0;
						if (txtDonGia.getValue() instanceof Long) {
							Long a = (Long) txtDonGia.getValue();
							b = (double) a;
						} else {
							int a = (int) txtDonGia.getValue();
							b = (double) a;
						}
						LoaiHangHoa lhh = loaiHangHoaBUS.getLoaiHangHoaByname(cbTenHH.getSelectedItem().toString());
						HangHoa hh = new HangHoa((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 0),
								txtTenSanPham.getText(), lhh, Integer.parseInt(txtSoLuongTon.getText()), b,
								trangThai(boxTrangThai.getSelectedItem().toString()));
						hangHoaBUS.updateHangHoa(hh);
						refreshTable();
						clearFields();
					} else {
						JOptionPane.showMessageDialog(this, "Tên sản phầm không được để trống!");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Tên sản phầm không được để trống!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hoá muốn cập nhật");
			}
		} else if (o.equals(btnNhapHang)) {
			if (tblDichVu.getSelectedRow() != -1) {
				String sl = JOptionPane.showInputDialog(this, "Nhập số lượng muốn nhập hàng: ");
				String rg = "^[0-9]{1,}$";
				if (sl.matches(rg)) {
					long a = (long) txtDonGia.getValue();
					double b = (double) a;
					LoaiHangHoa lhh = loaiHangHoaBUS.getLoaiHangHoaByname(cbTenHH.getSelectedItem().toString());
					HangHoa hh = new HangHoa((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 0),
							txtTenSanPham.getText(), lhh, Integer.parseInt(txtSoLuongTon.getText()), b,
							trangThai(boxTrangThai.getSelectedItem().toString()));
					hangHoaBUS.updateSoLuongTon(hh, Integer.parseInt(sl));
					refreshTable();
					clearFields();
				} else {
					JOptionPane.showMessageDialog(this, "Sai định dạng. Vui lòng nhập lại!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hoá muốn nhập hàng");
			}
		} else if (o.equals(btnGiamHang)) {
			if (tblDichVu.getSelectedRow() != -1) {
				String sl = JOptionPane.showInputDialog(this, "Nhập số lượng muốn giảm hàng: ");
				String rg = "^[0-9]{1,}$";
				if (sl.matches(rg)) {
					if (Integer.parseInt(modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 4).toString()) >= Integer
							.parseInt(sl)) {
						long a = (long) txtDonGia.getValue();
						double b = (double) a;
						LoaiHangHoa lhh = loaiHangHoaBUS.getLoaiHangHoaByname(cbTenHH.getSelectedItem().toString());
						HangHoa hh = new HangHoa((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 0),
								txtTenSanPham.getText(), lhh, Integer.parseInt(txtSoLuongTon.getText()), b,
								trangThai(boxTrangThai.getSelectedItem().toString()));
						hangHoaBUS.updateSoLuongTon(hh, Integer.parseInt(sl) * (-1));
						refreshTable();
						clearFields();
					} else {
						JOptionPane.showMessageDialog(this, "Số lượng phải nhỏ hơn số lượng tồn");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Sai định dạng. Vui lòng nhập lại!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hoá muốn nhập hàng");
			}
		} else if (o.equals(boxFilterLoaiHangHoa)) {
			if (boxFilterLoaiHangHoa.getSelectedIndex() != 0) {
				filters.set(0, RowFilter.regexFilter("^" + boxFilterLoaiHangHoa.getSelectedItem() + "$", 2));
				rowsorter.setRowFilter(RowFilter.andFilter(filters));
			} else {
				filters.set(0, RowFilter.regexFilter(".*", 2));
				rowsorter.setRowFilter(RowFilter.andFilter(filters));
			}
		} else if (o.equals(boxFilterTrangThai)) {
			if (boxFilterTrangThai.getSelectedIndex() != 0) {
				filters.set(1, RowFilter.regexFilter("^" + boxFilterTrangThai.getSelectedItem() + "$", 5));
				rowsorter.setRowFilter(RowFilter.andFilter(filters));
			} else {
				filters.set(1, RowFilter.regexFilter(".*", 5));
				rowsorter.setRowFilter(RowFilter.andFilter(filters));
			}
		} else if (o.equals(btnExportToExcel)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Lưu file Excel");
			chooser.setFileFilter(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".xlsx") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return "Excel file (*.xlsx)";
				}
			});
			int result = chooser.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String excelFilePath = chooser.getSelectedFile().toString();
				try {
					List<String> listTitle = new ArrayList<String>();
					listTitle.add("Mã hàng hóa");
					listTitle.add("Tên hàng hóa");
					listTitle.add("Loại hàng hóa");
					listTitle.add("Số lượng tồn");
					listTitle.add("Đơn giá");
					listTitle.add("Trạng thái");
					ExcelUtil.writeExcel(listTitle, hangHoaBUS.getAllHangHoas(), excelFilePath);

					int status = JOptionPane.showConfirmDialog(null, "Bạn có muốn mở file excel vừa xuất không?",
							"Thông báo", JOptionPane.YES_NO_OPTION);

					if (status == JOptionPane.YES_OPTION) {
						Desktop.getDesktop().open(chooser.getSelectedFile());
					}

					Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Xuất file Excel thành công");

				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, "Lưu file thất bại!");
					ex.printStackTrace();
				}
			}
		}
	}
}
