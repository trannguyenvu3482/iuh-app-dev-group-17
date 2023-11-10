package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
<<<<<<< Updated upstream

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.common.MyIcon;
=======
>>>>>>> Stashed changes

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignD;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.common.MyIcon;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:15:21 PM
 */
<<<<<<< Updated upstream
public class QuanLyHangHoaPanel extends JPanel implements ActionListener{
=======
public class QuanLyHangHoaPanel extends JPanel implements ActionListener {
>>>>>>> Stashed changes
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private JTable tblDichVu;
	private DefaultTableModel modelDichVu;
	private final JTextField txtTenSanPham;
	private final JTextField txtLoaiSanPham;
	private final JFormattedTextField txtDonGia;
	private final JTextField txtSoLuongTon;
	private final JComboBox boxTrangThai;
	private final JTextField txtSearch;
	private final JButton btnThem = new JButton("");
	private final JButton btnSua = new JButton("");
	private final JButton btnNhapHang = new JButton("");
	private final JButton btnNgungHoatDong = new JButton("");

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

		txtLoaiSanPham = new JTextField();
		txtLoaiSanPham.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtLoaiSanPham.setColumns(10);
		boxTwo.add(txtLoaiSanPham);

		Component horizontalStrut_1_1_2_1_1 = Box.createHorizontalStrut(800);
		boxTwo.add(horizontalStrut_1_1_2_1_1);

		Component verticalStrut_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1);

		Box boxThree = Box.createHorizontalBox();
		panelTop.add(boxThree);

		JLabel lblTnSnPhm_1_1 = new JLabel("Đơn giá:");
		lblTnSnPhm_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxThree.add(lblTnSnPhm_1_1);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(92);
		boxThree.add(horizontalStrut_1_1);

		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		format.setMaximumFractionDigits(0);

		NumberFormatter formatter = new NumberFormatter(format);
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
		txtSoLuongTon.setFont(new Font("Dialog", Font.PLAIN, 20));
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
		boxTrangThai.setEnabled(false);
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

		Component horizontalStrut_1_2_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1);
		btnNgungHoatDong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnNgungHoatDong.setIcon(MyIcon.getIcon(MaterialDesignF.FOOD_OFF, 32, null));
		btnNgungHoatDong.putClientProperty("JButton.buttonType", "square");
		boxSix.add(btnNgungHoatDong);

		Component horizontalStrut_1_2_1_1_1_1 = Box.createHorizontalStrut(20);
		boxSix.add(horizontalStrut_1_2_1_1_1_1);

		JButton btnClearFields = new JButton("");
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
		boxFilterLoaiHangHoa.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Loại hàng hóa", "Nước giải khát", "Đồ uống có cồn", "Thức ăn nhanh" }));
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
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào mã hàng hóa cần tìm");

		JScrollPane scrollPaneTable = new JScrollPane();
		add(scrollPaneTable, BorderLayout.CENTER);

		tblDichVu = new JTable();
		createTable();
		scrollPaneTable.setViewportView(tblDichVu);

		// Action listeners
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnNhapHang.addActionListener(this);
		btnNgungHoatDong.addActionListener(this);

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
		tblDichVu.setAutoCreateRowSorter(true);
		tblDichVu.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblDichVu.setRowHeight(50);
	}

	/**
	 * 
	 */
	private void refreshTable() {
		modelDichVu.setRowCount(0);

		Object[] testRow = { "HH001", "Nước suối", "Nước giải khát", "15000", 50, "Còn hoạt động" };
		Object[] testRow2 = { "HH002", "Bia 333 chai", "Thức uống có cồn", "20000", 300, "Còn hoạt động" };
		Object[] testRow3 = { "HH003", "Bia Tiger chai", "Thức uống có cồn", "20000", 40, "Còn hoạt động" };
		Object[] testRow4 = { "HH004", "Khô mực", "Nước giải khát", "150000", 10, "Còn hoạt động" };

		modelDichVu.addRow(testRow);
		modelDichVu.addRow(testRow2);
		modelDichVu.addRow(testRow3);
		modelDichVu.addRow(testRow4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThem)) {

		}
	}
}
