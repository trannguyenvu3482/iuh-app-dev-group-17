package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.bus.PhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.dao.NhanVienDAO;
import com.nhom17.quanlykaraoke.entities.LoaiPhong;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:48:14 AM
 */
public class QuanLyThongTinNhanVienPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final NhanVienBUS nvBUS = new NhanVienBUS();
	private final JTextField txtSearch;
	private final JComboBox<String> boxFilterKichThuoc = new JComboBox<String>();
	private DefaultTableModel modelNhanVien;
	private JTable tblNhanVien;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * 
	 */
	public QuanLyThongTinNhanVienPanel() {
		setSize(1200, 800);
		setLayout(null);

		JPanel panelTop = new JPanel();
		panelTop.setBounds(0, 0, 1200, 252);
		panelTop.setBorder(new EmptyBorder(240, 0, 0, 0));
		panelTop.setBackground(new Color(192, 192, 192));
		add(panelTop);
		panelTop.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(171, 29, 386, 40);
		panelTop.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(34, 29, 212, 40);
		panelTop.add(lblNewLabel);

		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCccd.setBounds(34, 80, 69, 40);
		panelTop.add(lblCccd);

		JLabel lblCccd_1 = new JLabel("SDT");
		lblCccd_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCccd_1.setBounds(310, 80, 44, 40);
		panelTop.add(lblCccd_1);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiiTnh.setBounds(34, 131, 81, 40);
		panelTop.add(lblGiiTnh);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgySinh.setBounds(297, 131, 90, 40);
		panelTop.add(lblNgySinh);

		JDateChooser fromDateChooser = new JDateChooser();
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser.setPreferredSize(new Dimension(150, 48));
		fromDateChooser.setLocale(new Locale("vi", "VN"));
		fromDateChooser.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser.setDateFormatString("d/M/y");
		fromDateChooser.setBounds(397, 131, 158, 40);
		panelTop.add(fromDateChooser);

		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTrngThi.setBounds(34, 182, 103, 40);
		panelTop.add(lblTrngThi);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Đang làm", "Đã nghỉ" }));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(143, 180, 144, 40);
		panelTop.add(comboBox_1);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_1.setBounds(143, 132, 144, 40);
		panelTop.add(comboBox_1_1);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(92, 80, 208, 40);
		panelTop.add(textField_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(364, 80, 193, 40);
		panelTop.add(textField_1);

		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChcV.setBounds(297, 182, 103, 40);
		panelTop.add(lblChcV);

		JComboBox comboBox_1_2 = new JComboBox();
		comboBox_1_2.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý" }));
		comboBox_1_2.setToolTipText("");
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2.setBounds(397, 180, 158, 40);
		panelTop.add(comboBox_1_2);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_PLUS, 32, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(775, 29, 60, 60);
		panelTop.add(btnNewButton);
		

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(MyIcon.getIcon(MaterialDesignA.ACCOUNT_EDIT, 32, null));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(775, 100, 60, 60);
		panelTop.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(MyIcon.getIcon(MaterialDesignB.BROOM, 32, null));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(775, 169, 60, 60);
		panelTop.add(btnNewButton_2);

		JLabel lblNgySinhT = new JLabel("Ngày sinh từ");
		lblNgySinhT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgySinhT.setBounds(872, 131, 129, 40);
		panelTop.add(lblNgySinhT);

		JDateChooser fromDateChooser_1 = new JDateChooser();
		fromDateChooser_1.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser_1.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser_1.setPreferredSize(new Dimension(150, 48));
		fromDateChooser_1.setLocale(new Locale("vi", "VN"));
		fromDateChooser_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser_1.setDateFormatString("d/M/y");
		fromDateChooser_1.setBounds(1004, 131, 145, 40);
		panelTop.add(fromDateChooser_1);

		JLabel lblnNgySinh = new JLabel("Đến ngày sinh");
		lblnNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblnNgySinh.setBounds(872, 182, 129, 40);
		panelTop.add(lblnNgySinh);

		JDateChooser fromDateChooser_1_1 = new JDateChooser();
		fromDateChooser_1_1.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser_1_1.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser_1_1.setPreferredSize(new Dimension(150, 48));
		fromDateChooser_1_1.setLocale(new Locale("vi", "VN"));
		fromDateChooser_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		fromDateChooser_1_1.setDateFormatString("d/M/y");
		fromDateChooser_1_1.setBounds(1004, 182, 145, 40);
		panelTop.add(fromDateChooser_1_1);

		JComboBox comboBox_1_2_1 = new JComboBox();
		comboBox_1_2_1.setModel(new DefaultComboBoxModel(new String[] { "Giới tính", "Nam", "Nữ" }));
		comboBox_1_2_1.setToolTipText("");
		comboBox_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2_1.setBounds(872, 80, 129, 40);
		panelTop.add(comboBox_1_2_1);

		JComboBox comboBox_1_2_1_1 = new JComboBox();
		comboBox_1_2_1_1.setModel(new DefaultComboBoxModel(new String[] { "Chức vụ", "Nhân viên", "Quản lý" }));
		comboBox_1_2_1_1.setToolTipText("");
		comboBox_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_2_1_1.setBounds(1020, 80, 129, 40);
		panelTop.add(comboBox_1_2_1_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(872, 29, 277, 40);
		panelTop.add(textField_2);
		
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
	

		Component horizontalStrut_1_1_2 = Box.createHorizontalStrut(200);
		boxSix_1.add(horizontalStrut_1_1_2);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		boxSix_1.add(txtSearch);
		txtSearch.setColumns(10);
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập vào tên nhân viên cần tìm");
		
		boxFilterKichThuoc.addActionListener(this);
		
		// Set avatar
		
		


		// Table setup
		createTable();
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
		tblNhanVien.setRowHeight(50);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneNhanVien = new JScrollPane(tblNhanVien);
		scrollPaneNhanVien.setBounds(0, 251, 1200, 624);
		add(scrollPaneNhanVien);

		refreshTable();
	}

	/**
	 * 
	 */
	private void createTable() {
		final String[] colNames = { "Mã NV", "Họ và tên", "Giới tính", "Ngày sinh", "Chức vụ", "CCCD", "Trạng thái" };
		modelNhanVien = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblNhanVien = new JTable(modelNhanVien);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNhanVien.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblNhanVien.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblNhanVien.getTableHeader().setReorderingAllowed(false);
		tblNhanVien.setAutoCreateRowSorter(true);
		tblNhanVien.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblNhanVien.setRowHeight(50);
		
		
		tblNhanVien.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblNhanVien.getSelectedRow() != -1) {
					
				}
			}

		});
	}


	/**
	 * 
	 */
	public String gioiTinh(int gt) {
		if (gt==1) {
			return "Nữ";
		} else {
			return "Nam";
		}
	}
	private void refreshTable() {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : nvBUS.getAllNhanViens()) {
			Object[] data = { nv.getMaNhanVien(), nv.getHoTen(), gioiTinh(nv.getGioiTinh()),  
					nv.getNgaySinh(), nv.getChucVu().getTenChucVu(), nv.getCCCD(),
					 nv.isTrangThai() ? "Đang làm" : "Đã nghỉ" };
			modelNhanVien.addRow(data);
		}
 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	private boolean trangThai(String tt) {
		if (tt.equals("Còn hoạt động"))
			return true;
		else
			return false;
	}


}
