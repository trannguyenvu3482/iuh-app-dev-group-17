package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import net.miginfocom.swing.MigLayout;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 08-Nov-2023 2:54:49 PM
 */
public class ThongKeTheoNhanVienPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JComboBox<String> boxFilterNgay = new JComboBox<String>();
	private final JPanel panelFilters = new JPanel();
	private JTextField txtSearch;
	private JTable tblThongKe;
	private DefaultTableModel modelThongKe;
	private final JDateChooser fromDateChooser = new JDateChooser();
	private final JDateChooser toDateChooser = new JDateChooser();
	private final JMonthChooser monthChooser = new JMonthChooser();
	private final JYearChooser yearChooser = new JYearChooser();

	/**
	 * 
	 */
	public ThongKeTheoNhanVienPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelCenterTheoNgay = new JPanel();
		add(panelCenterTheoNgay, BorderLayout.NORTH);
		panelCenterTheoNgay.setLayout(new BorderLayout(0, 0));

		JPanel panelFilterTheoNgay = new JPanel();
		panelFilterTheoNgay.setBorder(new EmptyBorder(10, 20, 10, 20));
		panelCenterTheoNgay.add(panelFilterTheoNgay, BorderLayout.NORTH);
		panelFilterTheoNgay.setLayout(new BoxLayout(panelFilterTheoNgay, BoxLayout.X_AXIS));

		boxFilterNgay.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFilterNgay
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Theo ngày", "Theo tháng", "Theo năm" }));
		panelFilterTheoNgay.add(boxFilterNgay);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelFilterTheoNgay.add(horizontalStrut);

		panelFilterTheoNgay.add(panelFilters);
		panelFilters.setLayout(new CardLayout(0, 0));

		JPanel filtersNgay = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filtersNgay.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		filtersNgay.setName("filtersNgay");
		panelFilters.add(filtersNgay, "filtersNgay");

		filtersNgay.add(fromDateChooser);
		fromDateChooser.setMaxSelectableDate(new Date());
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser.getDateEditor().setEnabled(false);
		fromDateChooser.setLocale(new Locale("vi", "VN"));
		fromDateChooser.setPreferredSize(new Dimension(150, 48));
		fromDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		fromDateChooser.setDateFormatString("d/M/y");

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		filtersNgay.add(horizontalStrut_1);

		filtersNgay.add(toDateChooser);

		toDateChooser.setMaxSelectableDate(new Date());
		toDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		toDateChooser.setLocale(new Locale("vi", "VN"));
		toDateChooser.getDateEditor().setEnabled(false);
		toDateChooser.setPreferredSize(new Dimension(150, 48));
		toDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		toDateChooser.setDateFormatString("d/M/y");

		JPanel filtersThang = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) filtersThang.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelFilters.add(filtersThang, "filtersThang");
		filtersThang.setName("filtersThang");

		monthChooser.setLocale(new Locale("vi", "VN"));
		monthChooser.setPreferredSize(new Dimension(200, 46));
		monthChooser.setFont(new Font("Dialog", Font.PLAIN, 18));

		filtersThang.add(monthChooser);

		JPanel filtersNam = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) filtersNam.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelFilters.add(filtersNam, "filtersNam");
		filtersNam.setName("filtersNam");

		yearChooser.setPreferredSize(new Dimension(100, 46));
		yearChooser.setLocale(new Locale("vi", "VN"));
		yearChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		filtersNam.add(yearChooser);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(120);
		panelFilterTheoNgay.add(horizontalStrut_1_1);

		txtSearch = new JTextField();
		txtSearch.putClientProperty("TextField.placeholderText", "Nhập họ tên nhân viên để tìm kiếm");
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelFilterTheoNgay.add(txtSearch);
		txtSearch.setColumns(10);

		JPanel panelContentTheoNgay = new JPanel();
		panelCenterTheoNgay.add(panelContentTheoNgay, BorderLayout.CENTER);

		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO NGÀY", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);
		panelContentTheoNgay.setLayout(new MigLayout("", "[200,grow][400,grow]", "[280,grow][600,grow]"));

		JPanel leftPane = new JPanel();
		leftPane.setBorder(new TitledBorder(null, "Th\u1ED1ng k\u00EA chi ti\u1EBFt", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		leftPane.setBackground(Color.WHITE);
		panelContentTheoNgay.add(leftPane, "cell 0 0,push ,grow");
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));

		Box horizontalBox_3_1 = Box.createHorizontalBox();
		horizontalBox_3_1.setAlignmentY(0.5f);
		horizontalBox_3_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_3_1);

		Component horizontalStrut_3_1_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_3_1_1_1_2);

		JLabel lblTongDoanhThu_1 = new JLabel("Tổng doanh thu: 0");
		lblTongDoanhThu_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongDoanhThu_1.setForeground(new Color(50, 102, 133));
		lblTongDoanhThu_1.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3_1.add(lblTongDoanhThu_1);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		horizontalBox_2_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_2_1);

		Component horizontalStrut_3_1_1_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_3_1_1_1_1_2);

		JLabel lblTongHoaDon_1 = new JLabel("Tổng số hóa đơn: 0");
		lblTongHoaDon_1.setForeground(new Color(50, 102, 133));
		lblTongHoaDon_1.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2_1.add(lblTongHoaDon_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		leftPane.add(horizontalBox_2);
		horizontalBox_2.setAlignmentX(0.0f);

		Component horizontalStrut_3_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3_1_1_1_1);

		JLabel lblDoanhThuPhongThuong = new JLabel("Doanh thu phòng thường: 0");
		lblDoanhThuPhongThuong.setForeground(new Color(50, 102, 133));
		lblDoanhThuPhongThuong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2.add(lblDoanhThuPhongThuong);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentY(0.5f);
		horizontalBox_3.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_3);

		Component horizontalStrut_3_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3_1_1_1);

		JLabel lblDoanhThuPhongVIP = new JLabel("Doanh thu phòng VIP: 0");
		lblDoanhThuPhongVIP.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongVIP.setForeground(new Color(50, 102, 133));
		lblDoanhThuPhongVIP.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3.add(lblDoanhThuPhongVIP);

		Box horizontalBox_1 = Box.createHorizontalBox();
		leftPane.add(horizontalBox_1);
		horizontalBox_1.setAlignmentX(0.0f);

		Component horizontalStrut_3_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3_1_1_1_1_1);

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: 0");
		lblTongTienPhong.setForeground(new Color(50, 102, 133));
		lblTongTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1.add(lblTongTienPhong);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_1_1);

		Component horizontalStrut_3_1_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1_1_1_1_1_1);

		JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ: 0");
		lblTongTienDichVu.setForeground(new Color(50, 102, 133));
		lblTongTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1_1.add(lblTongTienDichVu);

		Component verticalStrut = Box.createVerticalStrut(20);
		leftPane.add(verticalStrut);

		Component horizontalGlue = Box.createHorizontalGlue();
		leftPane.add(horizontalGlue);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		bottomPanel.setBackground(Color.WHITE);
		panelContentTheoNgay.add(bottomPanel, "cell 0 1 2 1,push ,grow");
		bottomPanel.setLayout(new BorderLayout(0, 0));

		createTable();
		JScrollPane tblScrollPane = new JScrollPane(tblThongKe);
		bottomPanel.add(tblScrollPane, BorderLayout.CENTER);

		ChartPanel chartPanel = new ChartPanel(barChart);
		panelContentTheoNgay.add(chartPanel, "cell 1 0, push, grow");

		// Action listeners
		boxFilterNgay.addActionListener(this);

		// Jcalendar handlers
//		fromDateChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("date")) {
//				// Handle date change
//				Date date = fromDateChooser.getDate();
//
//				if (date != null) {
//					// Set min date for toDateChooser
//					toDateChooser.setMinSelectableDate(date);
//
//					// Handle filter
//					// Check if from date and to date are valid
//					if (toDateChooser.getDate() != null && (fromDateChooser.getDate().before(toDateChooser.getDate())
//							|| fromDateChooser.getDate().equals(toDateChooser.getDate()))) {
//						// Filter
//						handleThongKeByDate(fromDateChooser.getDate(), toDateChooser.getDate());
//						Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê từ "
//								+ fromDateChooser.getDate().toString() + " đến " + toDateChooser.getDate().toString());
//					} else {
//						Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Ngày không hợp lệ");
//					}
//				}
//			}
//		});
//
//		toDateChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("date")) {
//				// Handle date change
//				Date date = fromDateChooser.getDate();
//
//				if (date != null) {
//					// Set min date for toDateChooser
//					toDateChooser.setMinSelectableDate(date);
//
//					// Handle filter
//					// Check if from date and to date are valid
//					if (fromDateChooser.getDate() != null && (fromDateChooser.getDate().before(toDateChooser.getDate())
//							|| fromDateChooser.getDate().equals(toDateChooser.getDate()))) {
//						// Filter
//						handleThongKeByDate(fromDateChooser.getDate(), toDateChooser.getDate());
//						Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê từ "
//								+ fromDateChooser.getDate().toString() + " đến " + toDateChooser.getDate().toString());
//					} else {
//						Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Ngày không hợp lệ");
//						resetInputs();
//					}
//				}
//			}
//		});
//
//		monthChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("month")) {
//				// Handle date change
//				int month = monthChooser.getMonth() + 1;
//
//				// Handle filter
//				// Check if from date and to date are valid
//				if (month > 0) {
//					// Filter
//					handleThongKeByMonth(month);
//					Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT,
//							"Thống kê theo tháng " + month);
//				} else {
//					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Tháng không hợp lệ");
//					resetInputs();
//				}
//			}
//		});
//
//		yearChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("year")) {
//				// Handle date change
//				int year = yearChooser.getYear();
//
//				// Handle filter
//				// Check if from date and to date are valid
//				if (year > 0) {
//					// Filter
//					handleThongKeByYear(year);
//					Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê theo năm " + year);
//				} else {
//					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Năm không hợp lệ");
//					resetInputs();
//				}
//			}
//		});

		refreshTable();
	}

	private void resetInputs() {
		fromDateChooser.setDate(null);
		toDateChooser.setDate(null);
		monthChooser.setMonth(0);
	}

	/**
	 * 
	 */
	private void createTable() {
		// TODO Auto-generated method stub
		final String[] colNames = { "Họ và tên", "SĐT", "CCCD", "Tổng tiền" };
		modelThongKe = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblThongKe = new JTable(modelThongKe);
		tblThongKe.setForeground(new Color(50, 102, 133));

		tblThongKe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThongKe.setFont(new Font("Dialog", Font.PLAIN, 18));
		tblThongKe.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tblThongKe.getTableHeader().setReorderingAllowed(false);
		tblThongKe.setAutoCreateRowSorter(true);
		tblThongKe.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblThongKe.setRowHeight(50);
	}

	/**
	 * 
	 */
	private void refreshTable() {
		// TODO Auto-generated method stub
		Object[] rowData = { "Trần Văn Hiệp", "0123123123", "079201341576", "210.000.000đ" };

		modelThongKe.addRow(rowData);
	}

	private static CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1000000, "Doanh thu", "Tháng 1");
		dataset.addValue(1000000, "Doanh thu", "Tháng 2");
		dataset.addValue(1000000, "Doanh thu", "Tháng 3");
		dataset.addValue(800000, "Doanh thu", "Tháng 4");
		dataset.addValue(1000000, "Doanh thu", "Tháng 5");
		dataset.addValue(1000000, "Doanh thu", "Tháng 6");
		dataset.addValue(1200000, "Doanh thu", "Tháng 7");
		dataset.addValue(1400000, "Doanh thu", "Tháng 8");
		dataset.addValue(2000000, "Doanh thu", "Tháng 9");
		dataset.addValue(3000000, "Doanh thu", "Tháng 10");
		dataset.addValue(5500000, "Doanh thu", "Tháng 11");
		dataset.addValue(12000000, "Doanh thu", "Tháng 12");
		return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(boxFilterNgay)) {
			CardLayout cl = (CardLayout) panelFilters.getLayout();

			if (boxFilterNgay.getSelectedIndex() == 0) {
				cl.show(panelFilters, "filtersNgay");
			} else if (boxFilterNgay.getSelectedIndex() == 1) {
				cl.show(panelFilters, "filtersThang");
			} else if (boxFilterNgay.getSelectedIndex() == 2) {
				cl.show(panelFilters, "filtersNam");
			}
		}
	}
}