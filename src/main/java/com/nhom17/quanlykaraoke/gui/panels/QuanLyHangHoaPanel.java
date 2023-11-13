package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.HangHoaBUS;
import com.nhom17.quanlykaraoke.bus.LoaiHangHoaBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;

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
	private final JComboBox cbTenHH = new JComboBox();
	private final JComboBox boxTrangThai;
	private final JTextField txtSearch;
	private final JButton btnThem = new JButton("");
	private final JButton btnSua = new JButton("");
	private final JButton btnNhapHang = new JButton("");
	private final JButton btnGiamHang = new JButton("");
	private final JButton btnClearFields = new JButton("");
	private HangHoaBUS hangHoaBUS = new HangHoaBUS();
	private LoaiHangHoaBUS loaiHangHoaBUS = new LoaiHangHoaBUS();
	private NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	private NumberFormatter formatter;

	/**
	 * 
	 */
	public QuanLyHangHoaPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.PINK);
		panelTop.setBorder(new EmptyBorder(10, 24, 20, 24));
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

		Box boxOne_1 = Box.createHorizontalBox();
		panelTop.add(boxOne_1);

		JLabel lblTnSnPhm_2 = new JLabel("Thông tin hàng hóa:");
		lblTnSnPhm_2.setFont(new Font("Dialog", Font.BOLD, 24));
		boxOne_1.add(lblTnSnPhm_2);

		Component horizontalGlue = Box.createHorizontalGlue();
		boxOne_1.add(horizontalGlue);

		Component verticalStrut = Box.createVerticalStrut(20);
		panelTop.add(verticalStrut);

		Box boxOne = Box.createHorizontalBox();
		panelTop.add(boxOne);

		JLabel lblTnSnPhm = new JLabel("Tên hàng hóa:");
		lblTnSnPhm.setFont(new Font("Dialog", Font.BOLD, 20));
		boxOne.add(lblTnSnPhm);

		Component horizontalStrut = Box.createHorizontalStrut(36);
		boxOne.add(horizontalStrut);

		txtTenSanPham = new JTextField();
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
		lblTnSnPhm_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxTwo.add(lblTnSnPhm_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(32);
		boxTwo.add(horizontalStrut_1);

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
		lblTnSnPhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFive.add(lblTnSnPhm_1_1_1_1);

		Component horizontalStrut_1_1_1 = Box.createHorizontalStrut(65);
		boxFive.add(horizontalStrut_1_1_1);

		boxTrangThai = new JComboBox();
		boxTrangThai.setEditable(true);
		boxTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Còn hoạt động", "Ngưng hoạt động" }));
		boxTrangThai.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxFive.add(boxTrangThai);

		Component horizontalStrut_1_1_2_1_1_1_1_1 = Box.createHorizontalStrut(900);
		boxFive.add(horizontalStrut_1_1_2_1_1_1_1_1);

		Component verticalStrut_1_1_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1_1_1);

		Box boxSix = Box.createHorizontalBox();
		panelTop.add(boxSix);
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnThem.setIcon(MyIcon.getIcon(MaterialDesignP.PLUS_THICK, 32, null));
		btnThem.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnThem);

		Component horizontalStrut_1_2_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1);
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnSua.setIcon(MyIcon.getIcon(MaterialDesignP.PENCIL, 32, null));
		btnSua.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnSua);

		Component horizontalStrut_1_2_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1);
		btnNhapHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnNhapHang.setIcon(MyIcon.getIcon(MaterialDesignD.DOLLY, 32, null));
		btnNhapHang.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnNhapHang);

		Component horizontalStrut_1_2_1_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1_1);

		btnGiamHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGiamHang.setIcon(MyIcon.getIcon(MaterialDesignB.BEAKER_MINUS, 32, null));
		btnGiamHang.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnGiamHang);

		Component horizontalStrut_1_2_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1);

		btnClearFields.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearFields.setIcon(MyIcon.getIcon(MaterialDesignB.BACKSPACE, 32, null));
		btnClearFields.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnClearFields);

		Component verticalStrut_1_1_1_1_1_1 = Box.createVerticalStrut(40);
		panelTop.add(verticalStrut_1_1_1_1_1_1);

		Box boxSix_1 = Box.createHorizontalBox();
		panelTop.add(boxSix_1);

		JLabel lblBLc = new JLabel("Bộ lọc: ");
		lblBLc.setFont(new Font("Dialog", Font.BOLD, 20));
		boxSix_1.add(lblBLc);

		JComboBox<String> boxFilterLoaiHangHoa = new JComboBox<String>();
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

		JComboBox<String> boxFilterTrangThai = new JComboBox<String>();
		boxFilterTrangThai.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFilterTrangThai.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Còn hoạt động", "Ngưng hoạt động" }));
		boxSix_1.add(boxFilterTrangThai);

		Component horizontalStrut_1_1_2 = Box.createHorizontalStrut(300);
		boxSix_1.add(horizontalStrut_1_1_2);

		txtSearch = new JTextField();
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
		// Load data to table
		refreshTable();

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
		tblDichVu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDichVu.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblDichVu.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblDichVu.getTableHeader().setReorderingAllowed(false);
		tblDichVu.setAutoCreateRowSorter(false);
		tblDichVu.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblDichVu.setRowHeight(50);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			if(txtTenSanPham.getText().trim().length()!=0) {
				double b = 0;
				if(txtDonGia.getValue() instanceof Long) {
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
			}else {
				JOptionPane.showMessageDialog(this, "Tên sản phầm không được để trống!");
			}
		} else if (o.equals(btnClearFields)) {
			clearFields();
		} else if (o.equals(btnSua)) {
			if (tblDichVu.getSelectedRow() != -1) {
			
				if(txtTenSanPham.getText().trim().length()!=0) {
					double b = 0;
					if(txtDonGia.getValue() instanceof Long) {
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
				}else {
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
					JOptionPane.showMessageDialog(this,
							"Sai định dạng. Vui lòng nhập lại!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hoá muốn nhập hàng");
			}
		}else if(o.equals(btnGiamHang)) {
			if (tblDichVu.getSelectedRow() != -1) {
				String sl = JOptionPane.showInputDialog(this, "Nhập số lượng muốn giảm hàng: ");
				String rg = "^[0-9]{1,}$";
				if (sl.matches(rg)) {
					if (Integer.parseInt(modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 4).toString())>=Integer.parseInt(sl)) {
						long a = (long) txtDonGia.getValue();
						double b = (double) a;
						LoaiHangHoa lhh = loaiHangHoaBUS.getLoaiHangHoaByname(cbTenHH.getSelectedItem().toString());
						HangHoa hh = new HangHoa((String) modelDichVu.getValueAt(tblDichVu.getSelectedRow(), 0),
								txtTenSanPham.getText(), lhh, Integer.parseInt(txtSoLuongTon.getText()), b,
								trangThai(boxTrangThai.getSelectedItem().toString()));
						hangHoaBUS.updateSoLuongTon(hh, Integer.parseInt(sl)*(-1));
						refreshTable();
						clearFields();
					} else {
						JOptionPane.showMessageDialog(this, "Số lượng phải nhỏ hơn số lượng tồn");
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"Sai định dạng. Vui lòng nhập lại!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hoá muốn nhập hàng");
			}
		}
	}
}
