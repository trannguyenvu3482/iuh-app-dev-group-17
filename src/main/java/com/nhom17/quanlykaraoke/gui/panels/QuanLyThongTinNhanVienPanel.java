package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;

import com.nhom17.quanlykaraoke.bus.ChucVuBUS;
import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.entities.HangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiHangHoa;
import com.nhom17.quanlykaraoke.entities.LoaiPhong;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.PasswordUtil;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.Dimension;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:48:14 AM
 */
public class QuanLyThongTinNhanVienPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final NhanVienBUS nvBUS = new NhanVienBUS();
	private final JComboBox<String> boxFilterKichThuoc = new JComboBox<String>();
	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private JTextField txtTenNV = new JTextField();;
	private JTextField txtCCCD = new JTextField();;
	private JTextField txtSDT = new JTextField();;
	private JDateChooser txtNgaySinh = new JDateChooser();
	private JComboBox cbChucVu = new JComboBox();
	private JComboBox cbTrangThai = new JComboBox();
	private JComboBox cbGioiTinh = new JComboBox();
	private JTextField txtSearchNV = new JTextField();
	private JLabel avt = new JLabel("");
	private JDateChooser txtSearchFrom = new JDateChooser();
	private JButton btnLocNgaySinh = new JButton("");
	private JButton btnSua = new JButton("");
	private JButton btnThem = new JButton("");
	private JButton btnClearfields = new JButton("");
	private JComboBox cbFilterChucVu = new JComboBox();
	private JComboBox cbFilterGioiTinh = new JComboBox();
	private JDateChooser txtSearchTo = new JDateChooser();
	private ChucVuBUS cvBUS = new ChucVuBUS();
	private TableRowSorter<TableModel> rowSorter;
	private List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object,Object>>(4);;

	/**
	 * 
	 */
	public QuanLyThongTinNhanVienPanel() {
		setSize(1500, 1000);
		setLayout(null);

		JPanel panelTop = new JPanel();
		panelTop.setForeground(new Color(0, 0, 0));
		panelTop.setBounds(0, 0, 1440, 252);
		panelTop.setBorder(new EmptyBorder(240, 0, 0, 0));
		panelTop.setBackground(new Color(216, 209, 165));
		add(panelTop);
		panelTop.setLayout(null);
		txtTenNV.setForeground(new Color(50, 102, 133));


		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenNV.setBounds(109, 29, 448, 40);
		panelTop.add(txtTenNV);
		txtTenNV.setColumns(10);

		JLabel lblTenNV = new JLabel("Tên NV:");
		lblTenNV.setForeground(new Color(50, 102, 133));
		lblTenNV.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lblTenNV.setBounds(10, 28, 153, 40);
		panelTop.add(lblTenNV);

		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setForeground(new Color(50, 102, 133));
		lblCccd.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lblCccd.setBounds(297, 80, 69, 40);
		panelTop.add(lblCccd);

		JLabel lbSDT = new JLabel("SDT:");
		lbSDT.setForeground(new Color(50, 102, 133));
		lbSDT.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbSDT.setBounds(10, 80, 44, 40);
		panelTop.add(lbSDT);

		JLabel lbGioiTinh = new JLabel("Giới tính:");
		lbGioiTinh.setForeground(new Color(50, 102, 133));
		lbGioiTinh.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbGioiTinh.setBounds(10, 131, 90, 40);
		panelTop.add(lbGioiTinh);

		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setForeground(new Color(50, 102, 133));
		lblNgySinh.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lblNgySinh.setBounds(297, 131, 103, 40);
		panelTop.add(lblNgySinh);

		txtNgaySinh.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		txtNgaySinh.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		txtNgaySinh.getCalendarButton().setForeground(new Color(50, 102, 133));
		txtNgaySinh.setForeground(new Color(50, 102, 133));
		txtNgaySinh.setPreferredSize(new Dimension(150, 48));
		txtNgaySinh.setLocale(new Locale("vi", "VN"));
		txtNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNgaySinh.setDateFormatString("d/M/y");
		txtNgaySinh.setBounds(397, 131, 160, 40);
		panelTop.add(txtNgaySinh);

		JLabel lbTrangThai = new JLabel("Trạng thái:");
		lbTrangThai.setForeground(new Color(50, 102, 133));
		lbTrangThai.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbTrangThai.setBounds(10, 182, 103, 40);
		panelTop.add(lbTrangThai);
		cbTrangThai.setForeground(new Color(50, 102, 133));


		cbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đã nghỉ", "Đang làm" }));
		cbTrangThai.setToolTipText("");
		cbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbTrangThai.setBounds(109, 180, 178, 40);
		panelTop.add(cbTrangThai);
		cbGioiTinh.setForeground(new Color(50, 102, 133));

		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbGioiTinh.setToolTipText("");
		cbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbGioiTinh.setBounds(109, 132, 178, 40);
		panelTop.add(cbGioiTinh);
		txtCCCD.setForeground(new Color(50, 102, 133));

		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(397, 80, 160, 40);
		panelTop.add(txtCCCD);
		txtSDT.setForeground(new Color(50, 102, 133));

		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBounds(109, 81, 178, 40);
		panelTop.add(txtSDT);

		JLabel lbChucVu = new JLabel("Chức vụ:");
		lbChucVu.setForeground(new Color(50, 102, 133));
		lbChucVu.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbChucVu.setBounds(297, 182, 103, 40);
		panelTop.add(lbChucVu);
		cbChucVu.setForeground(new Color(50, 102, 133));

		cbChucVu.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý" }));
		cbChucVu.setToolTipText("");
		cbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbChucVu.setBounds(397, 180, 160, 40);
		panelTop.add(cbChucVu);
		btnThem.setForeground(new Color(50, 102, 133));

		btnThem.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_PLUS, 32, null));
		

		btnThem.setBounds(775, 29, 60, 60);
		panelTop.add(btnThem);
		btnSua.setForeground(new Color(50, 102, 133));


		btnSua.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_EDIT, 32, null));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSua.setBounds(775, 100, 60, 60);
		panelTop.add(btnSua);
		btnClearfields.setForeground(new Color(50, 102, 133));

		btnClearfields.setIcon(MyIcon.getIcon(MaterialDesignB.BROOM, 32, null));
		btnClearfields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClearfields.setBounds(775, 169, 60, 60);
		panelTop.add(btnClearfields);

		JLabel lbSearchForm = new JLabel("Từ:");
		lbSearchForm.setForeground(new Color(50, 102, 133));
		lbSearchForm.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbSearchForm.setBounds(845, 131, 44, 40);
		panelTop.add(lbSearchForm);

		txtSearchFrom.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		txtSearchFrom.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		txtSearchFrom.getCalendarButton().setForeground(new Color(50, 102, 133));
		txtSearchFrom.setForeground(new Color(50, 102, 133));
		txtSearchFrom.setPreferredSize(new Dimension(150, 48));
		txtSearchFrom.setLocale(new Locale("vi", "VN"));
		txtSearchFrom.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSearchFrom.setDateFormatString("d/M/y");
		txtSearchFrom.setBounds(897, 131, 165, 40);
		panelTop.add(txtSearchFrom);

		JLabel lbSearchTo = new JLabel("Đến:");
		lbSearchTo.setForeground(new Color(50, 102, 133));
		lbSearchTo.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lbSearchTo.setBounds(845, 182, 54, 40);
		panelTop.add(lbSearchTo);

		txtSearchTo.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		txtSearchTo.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		txtSearchTo.getCalendarButton().setForeground(new Color(50, 102, 133));
		txtSearchTo.setForeground(new Color(50, 102, 133));
		txtSearchTo.setPreferredSize(new Dimension(150, 48));
		txtSearchTo.setLocale(new Locale("vi", "VN"));
		txtSearchTo.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSearchTo.setDateFormatString("d/M/y");
		txtSearchTo.setBounds(897, 182, 165, 40);
		panelTop.add(txtSearchTo);
		cbFilterGioiTinh.setForeground(new Color(50, 102, 133));

		
		cbFilterGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		cbFilterGioiTinh.setToolTipText("");
		cbFilterGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbFilterGioiTinh.setBounds(845, 80, 154, 40);
		panelTop.add(cbFilterGioiTinh);
		cbFilterChucVu.setForeground(new Color(50, 102, 133));

		
		cbFilterChucVu.setModel(new DefaultComboBoxModel(new String[] { "Chức vụ", "Nhân viên", "Quản lý" }));
		cbFilterChucVu.setToolTipText("");
		cbFilterChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbFilterChucVu.setBounds(1009, 80, 153, 40);
		panelTop.add(cbFilterChucVu);
		txtSearchNV.setForeground(new Color(50, 102, 133));

		txtSearchNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSearchNV.setColumns(10);
		txtSearchNV.setBounds(937, 29, 225, 40);
		panelTop.add(txtSearchNV);
		
		Box boxAvatar = Box.createHorizontalBox();
		boxAvatar.setLocation(565, 29);
		boxAvatar.setSize(200, 200);
		boxAvatar.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelTop.add(boxAvatar);

		Component horizontalGlue_1_1 = Box.createHorizontalGlue();
		boxAvatar.add(horizontalGlue_1_1);
		

		boxAvatar.add(avt);
		btnLocNgaySinh.setForeground(new Color(50, 102, 133));
		
		
		btnLocNgaySinh.setBounds(1072, 131, 90, 90);
		panelTop.add(btnLocNgaySinh);
		btnLocNgaySinh.setIcon(MyIcon.getIcon(MaterialDesignF.FILTER, 32, null));
		
		JLabel lblBLc = new JLabel("Tìm theo:");
		lblBLc.setForeground(new Color(50, 102, 133));
		lblBLc.setFont(new Font("Leelawadee UI", Font.BOLD, 19));
		lblBLc.setBounds(845, 28, 90, 40);
		panelTop.add(lblBLc);

		// Table setup
		createTable();
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
		tblNhanVien.setRowHeight(50);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNhanVien = new JScrollPane(tblNhanVien);
		scrollPaneNhanVien.setBounds(0, 252, 1440, 705);
		add(scrollPaneNhanVien);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnClearfields.addActionListener(this);
		cbFilterChucVu.addActionListener(this);
		cbFilterGioiTinh.addActionListener(this);
		btnLocNgaySinh.addActionListener(this);
		
		avt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblNhanVien.getSelectedRow()!=-1) {
					JFileChooser fileChoose = new JFileChooser();
					fileChoose.setFileFilter(new FileFilter() {

						   public String getDescription() {
						       return "JPG Images (*.jpg)";
						   }

						   public boolean accept(File f) {
						       if (f.isDirectory()) {
						           return true;
						       } else {
						           String filename = f.getName().toLowerCase();
						           return filename.endsWith(".jpg") || filename.endsWith(".png") ;
						       }
						   }
						});
					int fileInterVal = fileChoose.showOpenDialog(boxAvatar);
					File newAvt = fileChoose.getSelectedFile();
					
					if(fileInterVal==JFileChooser.APPROVE_OPTION) {
						byte[] avtToByte;
						try {
							avtToByte = Files.readAllBytes(newAvt.toPath());
							NhanVien nv = nvBUS.getNhanVien(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0).toString());
							nv.setAnhDaiDien(avtToByte);
							nvBUS.updateNV(nv);
							refreshTable();
							clearFields();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		refreshTable();
		
		txtSearchNV.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String text = txtSearchNV.getText();
				if(text.equals("")) {
					filters.set(0, RowFilter.regexFilter(".*", 1));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));
				}else {
					filters.set(0, RowFilter.regexFilter("(?i)"+text, 1));
					rowSorter.setRowFilter(RowFilter.andFilter(filters));					
				}
			}
			
		});
	}

	/**
	 * 
	 */
	private void createTable() {
		final String[] colNames = { "Mã NV", "Họ và tên", "Giới tính", "Ngày sinh", "Chức vụ", "Hoạt động" };
		modelNhanVien = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblNhanVien = new JTable(modelNhanVien);
		tblNhanVien.setForeground(new Color(50, 102, 133));

		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblNhanVien.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblNhanVien.getTableHeader().setReorderingAllowed(false);
		tblNhanVien.setAutoCreateRowSorter(true);
		tblNhanVien.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblNhanVien.setRowHeight(50);

		rowSorter = new TableRowSorter<TableModel>(modelNhanVien);
		tblNhanVien.setRowSorter(rowSorter);

		// Add filters
		filters.add(RowFilter.regexFilter(".*", 0));
		filters.add(RowFilter.regexFilter(".*", 1));
		filters.add(RowFilter.regexFilter(".*", 2));
		
		tblNhanVien.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblNhanVien.getSelectedRow() != -1) {
					NhanVien nv = nvBUS
							.getNhanVien(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0).toString());
					txtSDT.setText(nv.getSoDienThoai());
					txtTenNV.setText(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 1).toString());
					cbGioiTinh.setSelectedIndex(
							numGT(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 2).toString()));
					cbChucVu.setSelectedItem(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 4).toString());
					txtCCCD.setText(nv.getCCCD());
					cbTrangThai.setSelectedItem(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 5).toString());
					txtNgaySinh.setDate(DateTimeFormatUtil.formatLocalDateToDate(
							LocalDate.parse(modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 3).toString(),
									DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
					avt.setIcon(ConstantUtil.byteArrayToImageIcon(nv.getAnhDaiDien()));
				}
			}

		});

	}

	/**
	 * 
	 */

	public String gioiTinh(int gt) {
		if (gt == 1) {
			return "Nữ";
		} else {
			return "Nam";
		}
	}

	public int numGT(String gt) {
		if (gt.equals("Nam"))
			return 0;
		else
			return 1;
	}

	private void refreshTable() {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : nvBUS.getAllNhanViens()) {
			Object[] data = { nv.getMaNhanVien(), nv.getHoTen(), gioiTinh(nv.getGioiTinh()),
					DateTimeFormatter.ofPattern("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getChucVu().getTenChucVu(),
					nv.isTrangThai() ? "Đang làm" : "Đã nghỉ" };
			modelNhanVien.addRow(data);
		}

	}
	private void refreshTableByDOB(List<NhanVien> ls) {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : ls) {
			Object[] data = { nv.getMaNhanVien(), nv.getHoTen(), gioiTinh(nv.getGioiTinh()),
					DateTimeFormatter.ofPattern("dd/MM/yyyy").format(nv.getNgaySinh()), nv.getChucVu().getTenChucVu(),
					nv.isTrangThai() ? "Đang làm" : "Đã nghỉ" };
			modelNhanVien.addRow(data);
		}

	}

	public void clearFields() {
		txtTenNV.setText("");
		txtCCCD.setText("");
		txtSDT.setText("");
		cbChucVu.setSelectedIndex(0);
		cbGioiTinh.setSelectedIndex(0);
		cbTrangThai.setSelectedIndex(0);
		avt.setIcon(null);
		txtNgaySinh.setDate(null);
		txtTenNV.grabFocus();
		tblNhanVien.clearSelection();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnClearfields)) {
			clearFields();
		} else if (o.equals(btnThem)) {
			String rgTen = "^\\p{Lu}\\p{Ll}+(\\s+\\p{Lu}\\p{Ll}+)+$";
			LocalDate ns = txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate today = LocalDate.now();
			Period p = Period.between(ns, today);
			int y = p.getYears();
			System.out.println(y);
			if (!txtTenNV.getText().matches(rgTen)) {
				JOptionPane.showMessageDialog(this, "Họ vè tên phải có ít nhất 2 từ\nMỗi từ phái viết hoa chữ cái đầu");
			} else if (!txtCCCD.getText().matches("\\d{12}")) {
				JOptionPane.showMessageDialog(this, "Căn cước công dân gồm 12 số");
			} else if (!txtSDT.getText().matches("0\\d{9}")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và bắt đầu bằng số 0");
			} else if (y < 18 || ns.isAfter(today)) {
				JOptionPane.showMessageDialog(this, "Ngày sinh phải có định dạng dd/MM/yyyy\nPhải đủ 18 tuổi");
			} else {
				NhanVien nv = new NhanVien(txtTenNV.getText(), cbGioiTinh.getSelectedIndex(), PasswordUtil.encrypt("1"),
						txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						cvBUS.getChucVuByName(cbChucVu.getSelectedItem().toString()), txtSDT.getText(),
						txtCCCD.getText(), cbGioiTinh.getSelectedIndex() == 0 ? ConstantUtil.getDefaultMaleAvatar()
								: ConstantUtil.getDefaultFemaleAvatar());
				nvBUS.addNhanVien(nv);
				refreshTable();
				clearFields();
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			}
		} else if (o.equals(btnSua)) {
			if (tblNhanVien.getSelectedRow() != -1) {

				String rgTen = "^\\p{Lu}\\p{Ll}+(\\s+\\p{Lu}\\p{Ll}+)+$";
				LocalDate ns = txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate today = LocalDate.now();
				Period p = Period.between(ns, today);
				int y = p.getYears();
				System.out.println(y);
				if (!txtTenNV.getText().matches(rgTen)) {
					JOptionPane.showMessageDialog(this,
							"Họ vè tên phải có ít nhất 2 từ\nMỗi từ phái viết hoa chữ cái đầu");
				} else if (!txtCCCD.getText().matches("\\d{12}")) {
					JOptionPane.showMessageDialog(this, "Căn cước công dân gồm 12 số");
				} else if (!txtSDT.getText().matches("0\\d{9}")) {
					JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và bắt đầu bằng số 0");
				} else if (y < 18 || ns.isAfter(today)) {
					JOptionPane.showMessageDialog(this, "Ngày sinh phải có định dạng dd/MM/yyyy\nPhải đủ 18 tuổi");
				} else {
					String maNV = modelNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 0).toString();
					NhanVien nv = new NhanVien(maNV,
							txtTenNV.getText(), cbGioiTinh.getSelectedIndex(), PasswordUtil.encrypt("1"),
							txtNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
							cvBUS.getChucVuByName(cbChucVu.getSelectedItem().toString()), txtSDT.getText(),
							txtCCCD.getText(), nvBUS.getNhanVien(maNV).getAnhDaiDien(),
							cbTrangThai.getSelectedIndex() == 1 ? true : false);
					nvBUS.updateNV(nv);
					refreshTable();
					clearFields();
					JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn cập nhật");
			}
		} else if (o.equals(cbFilterChucVu)) {
			if(cbFilterChucVu.getSelectedIndex()!=0) {
				filters.set(1, RowFilter.regexFilter("^" + cbFilterChucVu.getSelectedItem()+"$", 4));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}else {
				filters.set(1, RowFilter.regexFilter(".*", 4));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}
		}else if(o.equals(cbFilterGioiTinh)) {
			if(cbFilterGioiTinh.getSelectedIndex()!=0) {
				filters.set(2, RowFilter.regexFilter("^" + cbFilterGioiTinh.getSelectedItem()+"$", 2));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}else {
				filters.set(2, RowFilter.regexFilter(".*", 2));
				rowSorter.setRowFilter(RowFilter.andFilter(filters));
			}
		}else if(o.equals(btnLocNgaySinh)) {
//			LocalDate dateFrom = txtSearchFrom.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			LocalDate dateTo = txtSearchTo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			List<NhanVien> lsNV = new ArrayList<NhanVien>();
//			if(dateFrom!=null&&dateTo!=null) {
//				for(NhanVien nv : nvBUS.getAllNhanViens()) {
//					if(dateFrom.isBefore(nv.getNgaySinh())&&dateTo.isAfter(nv.getNgaySinh()))
//						lsNV.add(nv);
//					
//				}
//				refreshTableByDOB(lsNV);
//			}else if(dateFrom!=null) {
//				for(NhanVien nv : nvBUS.getAllNhanViens()) {
//					if(dateFrom.isBefore(nv.getNgaySinh()))
//						lsNV.add(nv);
//					
//				}
//				refreshTableByDOB(lsNV);
//			}else if(dateTo!=null) {
//				for(NhanVien nv : nvBUS.getAllNhanViens()) {
//					if(dateTo.isAfter(nv.getNgaySinh()))
//						lsNV.add(nv);
//					
//				}
//				refreshTableByDOB(lsNV);
//			}else {
//				refreshTable();
//			}
		}
	}
}
