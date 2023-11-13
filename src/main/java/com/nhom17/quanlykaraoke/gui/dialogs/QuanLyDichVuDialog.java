package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignN;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.common.MyIcon;

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

	// VARIABLES

	/**
	 * 
	 */
	public QuanLyDichVuDialog() {
		setSize(1200, 800);
		setTitle("Quản lý dịch vụ");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(Color.DARK_GRAY);
		getContentPane().add(panelBottom, BorderLayout.SOUTH);

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		panelTop.setBackground(Color.DARK_GRAY);
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
		panelLeft.setBackground(Color.GRAY);
		panelCenter.add(panelLeft);
		panelLeft.setLayout(new BorderLayout(0, 0));

		JLabel lblLeftTitle = new JLabel("Danh sách hàng hóa");
		lblLeftTitle.setForeground(Color.WHITE);
		lblLeftTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		lblLeftTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelLeft.add(lblLeftTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panelLeft.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_4.setBorder(new EmptyBorder(10, 5, 16, 5));
		panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_4.add(txtSearch);
		txtSearch.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(100);
		panel_4.add(horizontalStrut);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Loại hàng hóa", "Đồ uống có cồn", "Thức ăn" }));
		panel_4.add(comboBox);

		JScrollPane scrollPaneLeft = new JScrollPane();
		panel.add(scrollPaneLeft);

		tblLeft = new JTable();
		scrollPaneLeft.setViewportView(tblLeft);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel.add(panel_1, BorderLayout.SOUTH);

		JButton btnThem = new JButton("");
		btnThem.setIcon(MyIcon.getIcon(MaterialDesignP.PLUS_THICK, 32, null));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnThem);
		btnThem.setFont(new Font("Dialog", Font.BOLD, 20));

		JPanel panelLeftBottom = new JPanel();
		panelLeftBottom.setBorder(new CompoundBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(255, 255, 255)),
				new EmptyBorder(5, 10, 5, 10)));
		panelLeftBottom.setBackground(Color.GRAY);
		panelLeft.add(panelLeftBottom, BorderLayout.SOUTH);
		panelLeftBottom.setLayout(new BoxLayout(panelLeftBottom, BoxLayout.X_AXIS));

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Dialog", Font.BOLD, 20));
		panelLeftBottom.add(btnHuy);

		JPanel panelRight = new JPanel();
		panelRight.setBorder(new LineBorder(Color.DARK_GRAY, 5));
		panelRight.setBackground(Color.GRAY);
		panelCenter.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));

		JLabel lblRightTitle = new JLabel("Dịch vụ đã chọn");
		lblRightTitle.setForeground(Color.WHITE);
		lblRightTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		lblRightTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelRight.add(lblRightTitle, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panelRight.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GRAY);
		panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JLabel lblTenPhong = new JLabel("Tên phòng: Phòng P001");
		lblTenPhong.setForeground(Color.WHITE);
		lblTenPhong.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_5.add(lblTenPhong);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng: Trần Ngọc Phát");
		lblTenKhachHang.setForeground(Color.WHITE);
		lblTenKhachHang.setFont(new Font("Dialog", Font.BOLD, 18));
		panel_5.add(lblTenKhachHang);

		JScrollPane scrollPaneRight = new JScrollPane();
		panel_2.add(scrollPaneRight);

		tblRight = new JTable();
		scrollPaneRight.setViewportView(tblRight);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(10);
		panel_2.add(panel_3, BorderLayout.SOUTH);

		JButton btnXoa = new JButton("");
		btnXoa.setIcon(MyIcon.getIcon(MaterialDesignC.CLOSE_THICK, 32, null));
		btnXoa.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_3.add(btnXoa);

		JButton btnCapNhatSoLuong = new JButton("");
		btnCapNhatSoLuong.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCapNhatSoLuong.setIcon(MyIcon.getIcon(MaterialDesignN.NUMERIC, 32, null));
		panel_3.add(btnCapNhatSoLuong);

		JPanel panelRightBottom = new JPanel();
		panelRightBottom.setBackground(Color.GRAY);
		panelRightBottom.setBorder(new CompoundBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(255, 255, 255)),
				new EmptyBorder(5, 10, 5, 10)));
		panelRight.add(panelRightBottom, BorderLayout.SOUTH);
		panelRightBottom.setLayout(new BoxLayout(panelRightBottom, BoxLayout.X_AXIS));

		Component horizontalGlue = Box.createHorizontalGlue();
		panelRightBottom.add(horizontalGlue);

		JButton btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBackground(Color.RED);
		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
		panelRightBottom.add(btnXacNhan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private void createLeftTable() {
		final String[] colNames = { "Mã HH", "Tên", "Loại", "Đơn giá", "Số lượng tồn kho", "Đơn vị" };
		modelLeft = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tblLeft = new JTable(modelLeft);
		tblLeft.setRowSelectionAllowed(false);
		tblLeft.setColumnSelectionAllowed(false);
		tblLeft.setCellSelectionEnabled(false);
		tblLeft.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblLeft.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
		tblLeft.getTableHeader().setReorderingAllowed(false);
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

	}
}
