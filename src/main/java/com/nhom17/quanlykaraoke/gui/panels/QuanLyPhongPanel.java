package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.LoaiPhongBUS;
import com.nhom17.quanlykaraoke.bus.PhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.LoaiPhong;
import com.nhom17.quanlykaraoke.entities.Phong;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:13:37 PM
 */
public class QuanLyPhongPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JTextField txtPhong;
	private final JComboBox<String> cbKichThuoc = new JComboBox<String>();
	private final JComboBox<String> cbTenLP = new JComboBox<String>();
	private final JComboBox<String> cbTrangThai = new JComboBox<String>();
	private final JFormattedTextField txtPhuPhi;;
	private final JTextField txtSearch;
	private final JButton btnThem = new JButton("");
	private final JButton btnSua = new JButton("");
	private final JButton btnClearFields = new JButton("");
	private final JComboBox<String> boxFilterTrangThai = new JComboBox<String>();
	private final JComboBox<String> boxFilterKichThuoc = new JComboBox<String>();
	private final JComboBox<String> boxFilterTenLoaiPhong = new JComboBox<String>();
	private final LoaiPhongBUS lpBUS = new LoaiPhongBUS();
	private final PhongBUS pBUS = new PhongBUS();
	private NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private NumberFormatter formatter;
	private JTable tblPhong;
	private DefaultTableModel modelPhong;
	private TableRowSorter<TableModel> rowSorter;

	private List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(4);

	public QuanLyPhongPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.LIGHT_GRAY);
		panelTop.setBorder(new EmptyBorder(10, 24, 20, 24));
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
		Box boxOne_1 = Box.createHorizontalBox();
		panelTop.add(boxOne_1);

		JLabel lblTnSnPhm_2 = new JLabel("Thông tin Phòng:");
		lblTnSnPhm_2.setFont(new Font("Dialog", Font.BOLD, 24));
		boxOne_1.add(lblTnSnPhm_2);

		Component horizontalGlue = Box.createHorizontalGlue();
		boxOne_1.add(horizontalGlue);

		Component verticalStrut = Box.createVerticalStrut(20);
		panelTop.add(verticalStrut);

		Box boxOne = Box.createHorizontalBox();
		panelTop.add(boxOne);

		JLabel lbTenPhong = new JLabel("Tên Phòng:");
		lbTenPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		boxOne.add(lbTenPhong);

		Component horizontalStrut = Box.createHorizontalStrut(56);
		boxOne.add(horizontalStrut);

		txtPhong = new JTextField();
		txtPhong.setEditable(false);
		txtPhong.setEnabled(false);
		boxOne.add(txtPhong);
		txtPhong.setFont(new Font("Dialog", Font.PLAIN, 20));

		txtPhong.setColumns(10);

		Component horizontalStrut_1_1_2_1 = Box.createHorizontalStrut(600);
		boxOne.add(horizontalStrut_1_1_2_1);

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1);

		Box boxTwo = Box.createHorizontalBox();
		panelTop.add(boxTwo);

		JLabel lbKichThuoc = new JLabel("Kích thước:");
		lbKichThuoc.setFont(new Font("Dialog", Font.BOLD, 20));
		boxTwo.add(lbKichThuoc);

		Component horizontalStrut_1 = Box.createHorizontalStrut(52);
		boxTwo.add(horizontalStrut_1);
		cbKichThuoc.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20" }));

		cbKichThuoc.setFont(new Font("Dialog", Font.PLAIN, 20));

		boxTwo.add(cbKichThuoc);

		Component horizontalStrut_2 = Box.createHorizontalStrut(600);
		boxTwo.add(horizontalStrut_2);

		Component verticalStrut_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1);

		Box boxThree = Box.createHorizontalBox();
		panelTop.add(boxThree);

		JLabel lbTenLP = new JLabel("Tên loại phòng:");
		lbTenLP.setFont(new Font("Dialog", Font.BOLD, 20));
		boxThree.add(lbTenLP);

		Component horizontalStrut_1_1_2_1_1_12 = Box.createHorizontalStrut(18);
		boxThree.add(horizontalStrut_1_1_2_1_1_12);
		cbTenLP.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Phòng thường", "Phòng tiệc", "Phòng VIP", "Phòng tiệc VIP" }));

		cbTenLP.setFont(new Font("Dialog", Font.PLAIN, 20));

		boxThree.add(cbTenLP);

		Component horizontalStrut_1_1_2_1_1_11 = Box.createHorizontalStrut(600);
		boxThree.add(horizontalStrut_1_1_2_1_1_11);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1);

		Box boxFour = Box.createHorizontalBox();
		panelTop.add(boxFour);

		JLabel lbPhuPhi = new JLabel("Phụ phí:");
		lbPhuPhi.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFour.add(lbPhuPhi);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(88);
		boxFour.add(horizontalStrut_1_1);

		format.setMaximumFractionDigits(0);
		formatter = new NumberFormatter(format);

		formatter.setMinimum(0);
		formatter.setMaximum(100000000);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		txtPhuPhi = new JFormattedTextField(formatter);
		txtPhuPhi.setEnabled(false);
		txtPhuPhi.setEditable(false);
		txtPhuPhi.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtPhuPhi.setColumns(10);
		txtPhuPhi.setValue(0);

		boxFour.add(txtPhuPhi);
		Component horizontalStrut_1_1_2_1_1_1_1 = Box.createHorizontalStrut(600);
		boxFour.add(horizontalStrut_1_1_2_1_1_1_1);

		Component verticalStrut_1_1_1_1 = Box.createVerticalStrut(15);
		panelTop.add(verticalStrut_1_1_1_1);

		Box boxFive = Box.createHorizontalBox();
		panelTop.add(boxFive);

		JLabel lblTnSnPhm_1_1_1_1 = new JLabel("Trạng thái:");
		lblTnSnPhm_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFive.add(lblTnSnPhm_1_1_1_1);

		Component horizontalStrut_1_1_1 = Box.createHorizontalStrut(63);
		boxFive.add(horizontalStrut_1_1_1);

		cbTrangThai.setEditable(true);
		cbTrangThai.setModel(new DefaultComboBoxModel<String>(new String[] { "Còn hoạt động", "Ngưng hoạt động" }));
		cbTrangThai.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxFive.add(cbTrangThai);

		Component horizontalStrut_1_1_2_1_1_1_1_1 = Box.createHorizontalStrut(800);
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

		boxFilterKichThuoc.setFont(new Font("Dialog", Font.BOLD, 20));
		String[] dataLHH = { "Kích thước", "5", "10", "15", "20" };

		boxFilterKichThuoc.setModel(new DefaultComboBoxModel<String>(dataLHH));
		boxSix_1.add(boxFilterKichThuoc);

		Component horizontalStrut_1_2 = Box.createHorizontalStrut(40);
		boxSix_1.add(horizontalStrut_1_2);

		boxFilterTenLoaiPhong.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Tên loại phòng", "Phòng thường", "Phòng tiệc", "Phòng VIP", "Phòng tiệc VIP" }));
		boxFilterTenLoaiPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		boxSix_1.add(boxFilterTenLoaiPhong);

		Component horizontalStrut_1_2_2 = Box.createHorizontalStrut(40);
		boxSix_1.add(horizontalStrut_1_2_2);
		boxFilterTrangThai.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFilterTrangThai.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Trạng thái", "Còn hoạt động", "Ngưng hoạt động" }));
		boxSix_1.add(boxFilterTrangThai);

		Component horizontalStrut_1_1_2 = Box.createHorizontalStrut(200);
		boxSix_1.add(horizontalStrut_1_1_2);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxSix_1.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào mã phòng cần tìm");

		JScrollPane scrollPaneTable = new JScrollPane();
		add(scrollPaneTable, BorderLayout.CENTER);

		tblPhong = new JTable();
		createTable();
		refreshTable();
		scrollPaneTable.setViewportView(tblPhong);

		// Action listeners
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnClearFields.addActionListener(this);

		cbKichThuoc.addActionListener(this);
		cbTenLP.addActionListener(this);

		boxFilterKichThuoc.addActionListener(this);
		boxFilterTenLoaiPhong.addActionListener(this);
		boxFilterTrangThai.addActionListener(this);

		// Handle search
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Handle search with filters
				String text = txtSearch.getText();
				if (text.equals("")) {
					filters.set(3, RowFilter.regexFilter(".*", 0));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));
				} else {
					filters.set(3, RowFilter.regexFilter("(?i)" + text, 0));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));
				}
			}
		});
	}

	public void createTable() {
		final String[] colNames = { "Mã phòng", "Kích thước", "Tên Loại Phòng", "Phụ phí", "Trạng thái" };
		modelPhong = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tblPhong = new JTable(modelPhong);
		tblPhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPhong.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblPhong.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblPhong.getTableHeader().setReorderingAllowed(false);
		tblPhong.setAutoCreateRowSorter(false);
		tblPhong.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblPhong.setRowHeight(50);

		// Handle filter
		rowSorter = new TableRowSorter<TableModel>(modelPhong);
		tblPhong.setRowSorter(rowSorter);

		// Add filters
		filters.add(RowFilter.regexFilter(".*", 1));
		filters.add(RowFilter.regexFilter(".*", 2));
		filters.add(RowFilter.regexFilter(".*", 4));
		filters.add(RowFilter.regexFilter(".*", 0));

		tblPhong.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblPhong.getSelectedRow() != -1) {
					txtPhong.setText(modelPhong.getValueAt(tblPhong.getSelectedRow(), 0).toString());
					cbKichThuoc.setSelectedItem(modelPhong.getValueAt(tblPhong.getSelectedRow(), 1).toString());
					cbTenLP.setSelectedItem(modelPhong.getValueAt(tblPhong.getSelectedRow(), 2).toString());
					try {
						txtPhuPhi.setValue(nf.parse(modelPhong.getValueAt(tblPhong.getSelectedRow(), 3).toString()));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cbTrangThai.setSelectedItem(modelPhong.getValueAt(tblPhong.getSelectedRow(), 4).toString());
				}
			}

		});
	}

	Locale lc = new Locale("vi", "VN");
	NumberFormat nf = NumberFormat.getCurrencyInstance(lc);

	private void refreshTable() {
		modelPhong.setRowCount(0);
		for (Phong p : pBUS.getAllPhongs()) {
			Object[] data = { p.getMaPhong(), p.getLoaiPhong().getKichThuoc(), p.getLoaiPhong().getTenLoaiPhong(),
					nf.format(p.getLoaiPhong().getPhuPhi()), p.isTrangThai() ? "Còn hoạt động" : "Ngưng hoạt động" };
			modelPhong.addRow(data);
		}

	}

	private void clearFields() {
		txtPhong.setText("");
		cbKichThuoc.setSelectedItem("5");
		cbTenLP.setSelectedItem("Phòng thường");
		txtPhuPhi.setText("");
		cbTrangThai.setSelectedItem("Ngưng hoạt động");
		txtPhong.grabFocus();
		tblPhong.clearSelection();
	}

	private boolean trangThai(String tt) {
		if (tt.equals("Còn hoạt động"))
			return true;
		else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			LoaiPhong lp = lpBUS.getLoaiPhong(cbTenLP.getSelectedItem().toString(),
					Integer.parseInt(cbKichThuoc.getSelectedItem().toString()));
			System.out.println(lp);
			Phong p = new Phong("", lp, trangThai(cbTrangThai.getSelectedItem().toString()));
			System.out.println(p);
			pBUS.addPhong(p);
			refreshTable();
			clearFields();
		} else if (o.equals(btnClearFields)) {
			clearFields();
		} else if (o.equals(btnSua)) {
			if (tblPhong.getSelectedRow() != -1) {
				LoaiPhong lp = lpBUS.getLoaiPhong(cbTenLP.getSelectedItem().toString(),
						Integer.parseInt(cbKichThuoc.getSelectedItem().toString()));
				System.out.println(lp);
				Phong p = new Phong(txtPhong.getText(), lp, trangThai(cbTrangThai.getSelectedItem().toString()));
				System.out.println(p);
				pBUS.updatePhong(p);
				refreshTable();
				clearFields();

			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng muốn cập nhật");
			}
		} else if (o.equals(cbKichThuoc)) {
			LoaiPhong lp = lpBUS.getLoaiPhong(cbTenLP.getSelectedItem().toString(),
					Integer.parseInt(cbKichThuoc.getSelectedItem().toString()));
			txtPhuPhi.setValue(lp.getPhuPhi());

		} else if (o.equals(cbTenLP)) {
			LoaiPhong lp = lpBUS.getLoaiPhong(cbTenLP.getSelectedItem().toString(),
					Integer.parseInt(cbKichThuoc.getSelectedItem().toString()));
			txtPhuPhi.setValue(lp.getPhuPhi());
		} else if (o.equals(boxFilterKichThuoc)) {
			System.out.println("SELECTED: " + boxFilterKichThuoc.getSelectedIndex());
			if (boxFilterKichThuoc.getSelectedIndex() != 0) {
				// Handle table filter
				filters.set(0, RowFilter.regexFilter("^" + boxFilterKichThuoc.getSelectedItem() + "$", 1));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			} else {
				filters.set(0, RowFilter.regexFilter(".*", 1));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}
		} else if (o.equals(boxFilterTenLoaiPhong)) {
			if (boxFilterTenLoaiPhong.getSelectedIndex() != 0) {
				// Handle table filter
				filters.set(1, RowFilter.regexFilter("^" + boxFilterTenLoaiPhong.getSelectedItem() + "$", 2));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			} else {
				filters.set(1, RowFilter.regexFilter(".*", 2));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}
		} else if (o.equals(boxFilterTrangThai)) {
			if (boxFilterTrangThai.getSelectedIndex() != 0) {
				// Handle table filter
				filters.set(2, RowFilter.regexFilter("^" + boxFilterTrangThai.getSelectedItem() + "$", 4));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			} else {
				filters.set(2, RowFilter.regexFilter(".*", 4));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}
		}
	}
}
