package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;
import org.kordamp.ikonli.materialdesign2.MaterialDesignR;

import com.nhom17.quanlykaraoke.bus.ChiTietDichVuBUS;
import com.nhom17.quanlykaraoke.bus.ChiTietPhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.bus.PhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import raven.toast.Notifications.Type;

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
	private final JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu: 0");
	private final JLabel lblTongHoaDon = new JLabel("Tổng số hóa đơn: 0");
	private final JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: 0");
	private final JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ: 0");
	private final JLabel lblDoanhThuPhongThuong = new JLabel("Doanh thu phòng thường: 0");
	private final JLabel lblDoanhThuPhongVIP = new JLabel("Doanh thu phòng VIP: 0");
	private final JButton btnSearch = new JButton("");
	private final JButton btnReset = new JButton("");
	private TableRowSorter<TableModel> rowSorter;
	private JFreeChart barChart;

	// VARIABLES
	private final NhanVienBUS nvBUS = new NhanVienBUS();
	private final PhieuDatPhongBUS pdpBUS = new PhieuDatPhongBUS();
	private final ChiTietPhieuDatPhongBUS ctpdpBUS = new ChiTietPhieuDatPhongBUS();
	private final ChiTietDichVuBUS ctdvBUS = new ChiTietDichVuBUS();

	private double tongDoanhThu = 0;
	private int tongHoaDon = 0;
	private double tongTienPhong = 0;
	private double tongTienDichVu = 0;
	private double doanhThuPhongThuong = 0;
	private double doanhThuPhongVIP = 0;

	private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	private boolean isInputValid = false;

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
		boxFilterNgay.setMaximumSize(new Dimension(34, 32767));

		boxFilterNgay.setFont(new Font("Dialog", Font.BOLD, 20));
		boxFilterNgay
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Theo ngày", "Theo tháng", "Theo năm" }));
		panelFilterTheoNgay.add(boxFilterNgay);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelFilterTheoNgay.add(horizontalStrut);
		panelFilters.setMaximumSize(new Dimension(800, 32767));
		panelFilters.setMinimumSize(new Dimension(10, 21));

		panelFilterTheoNgay.add(panelFilters);
		panelFilters.setLayout(new CardLayout(0, 0));

		JPanel filtersNgay = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filtersNgay.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
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
		yearChooser.setEndYear(2023);
		filtersNam.add(yearChooser);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelFilterTheoNgay.add(horizontalStrut_2);

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(100, 32767));
		panelFilterTheoNgay.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 0));

		btnSearch.putClientProperty("JButton.buttonType", "square");
		btnSearch.setIcon(MyIcon.getIcon(MaterialDesignM.MAGNIFY, 32, null));
		panel.add(btnSearch);

		btnReset.setIcon(MyIcon.getIcon(MaterialDesignR.RESTORE, 32, null));
		btnReset.putClientProperty("JButton.buttonType", "square");
		panel.add(btnReset);

		txtSearch = new JTextField();
		txtSearch.setMaximumSize(new Dimension(280, 2147483647));
		txtSearch.putClientProperty("JTextField.placeholderText", "Nhập họ tên nhân viên để tìm kiếm");

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panelFilterTheoNgay.add(horizontalGlue_1);
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelFilterTheoNgay.add(txtSearch);
		txtSearch.setColumns(20);

		JPanel panelContentTheoNgay = new JPanel();
		panelCenterTheoNgay.add(panelContentTheoNgay, BorderLayout.CENTER);

		barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO THÁNG", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);

		NumberAxis yAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();

		// Format y axis with increments of 1000000
		yAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		yAxis.setAutoTickUnitSelection(true);
		panelContentTheoNgay.setLayout(new BorderLayout(0, 0));

		panelContentTheoNgay.setLayout(new MigLayout("", "[200,grow][700,grow]", "[300,grow][540,grow]"));

		JPanel leftPane = new JPanel();
		leftPane.setBorder(
				new TitledBorder(null, "Thông tin chi tiết", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftPane.setBackground(Color.WHITE);
		panelContentTheoNgay.add(leftPane, "cell 0 0,push ,grow");
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));

		Box horizontalBox_3_1 = Box.createHorizontalBox();
		horizontalBox_3_1.setAlignmentY(0.5f);
		horizontalBox_3_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_3_1);

		Component horizontalStrut_3_1_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_3_1_1_1_2);

		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongDoanhThu.setForeground(new Color(50, 102, 133));
		lblTongDoanhThu.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3_1.add(lblTongDoanhThu);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		horizontalBox_2_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_2_1);

		Component horizontalStrut_3_1_1_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_3_1_1_1_1_2);

		lblTongHoaDon.setForeground(new Color(50, 102, 133));
		lblTongHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2_1.add(lblTongHoaDon);

		Box horizontalBox_2 = Box.createHorizontalBox();
		leftPane.add(horizontalBox_2);
		horizontalBox_2.setAlignmentX(0.0f);

		Component horizontalStrut_3_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3_1_1_1_1);

		lblDoanhThuPhongThuong.setForeground(new Color(50, 102, 133));
		lblDoanhThuPhongThuong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2.add(lblDoanhThuPhongThuong);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentY(0.5f);
		horizontalBox_3.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_3);

		Component horizontalStrut_3_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3_1_1_1);

		lblDoanhThuPhongVIP.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongVIP.setForeground(new Color(50, 102, 133));
		lblDoanhThuPhongVIP.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3.add(lblDoanhThuPhongVIP);

		Box horizontalBox_1 = Box.createHorizontalBox();
		leftPane.add(horizontalBox_1);
		horizontalBox_1.setAlignmentX(0.0f);

		Component horizontalStrut_3_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3_1_1_1_1_1);

		lblTongTienPhong.setForeground(new Color(50, 102, 133));
		lblTongTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1.add(lblTongTienPhong);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1.setAlignmentX(0.0f);
		leftPane.add(horizontalBox_1_1);

		Component horizontalStrut_3_1_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1_1_1_1_1_1);

		lblTongTienDichVu.setForeground(new Color(50, 102, 133));
		lblTongTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1_1.add(lblTongTienDichVu);

		Component verticalStrut = Box.createVerticalStrut(20);
		leftPane.add(verticalStrut);

		Component horizontalGlue = Box.createHorizontalGlue();
		leftPane.add(horizontalGlue);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(
				new TitledBorder(null, "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		fromDateChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("date")) {
				// Handle date change
				Date date = fromDateChooser.getDate();

				if (date != null) {
					// Set min date for toDateChooser
					toDateChooser.setMinSelectableDate(date);

					if (toDateChooser.getDate() != null) {
						isInputValid = true;
					} else {
						isInputValid = false;
					}
				} else {
					isInputValid = false;
				}
			}
		});

		toDateChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("date")) {
				// Handle date change
				Date date = toDateChooser.getDate();

				if (date != null) {
					// Set min date for toDateChooser
					fromDateChooser.setMaxSelectableDate(date);

					if (toDateChooser.getDate() != null) {
						isInputValid = true;
					} else {
						isInputValid = false;
					}
				} else {
					isInputValid = false;
				}
			}
		});

//		monthChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("month")) {
//				isInputValid = true;
//			} else {
//				isInputValid = false;
//			}
//		});
//
//		yearChooser.addPropertyChangeListener(evt -> {
//			if (evt.getPropertyName().equals("year")) {
//				isInputValid = true;
//			} else {
//				isInputValid = false;
//			}
//		});

		// Handle search
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// Handle search with filters
				String text = txtSearch.getText();
				if (text.equals("")) {
					rowSorter.setRowFilter(RowFilter.regexFilter(".*", 1));
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
				}
			}
		});

		// Action listeners
		btnReset.addActionListener(this);
		btnSearch.addActionListener(this);

		refreshTable();
	}

	private void resetInputs() {
		fromDateChooser.setDate(null);
		toDateChooser.setDate(null);
		monthChooser.setMonth(0);
	}

	private void resetAllStatistics() {
		tongDoanhThu = 0;
		tongHoaDon = 0;
		tongTienPhong = 0;
		tongTienDichVu = 0;
		doanhThuPhongThuong = 0;
		doanhThuPhongVIP = 0;

		lblTongHoaDon.setText("Tổng số hóa đơn: 0");
		lblDoanhThuPhongThuong.setText("Doanh thu phòng thường: 0");
		lblDoanhThuPhongVIP.setText("Doanh thu phòng VIP: 0");
		lblTongTienPhong.setText("Tổng tiền phòng: 0");
		lblTongTienDichVu.setText("Tổng tiền dịch vụ: 0");
		lblTongDoanhThu.setText("Tổng doanh thu: 0");
	}

	/**
	 * 
	 */
	private void createTable() {
		// TODO Auto-generated method stub
		final String[] colNames = { "Mã nhân viên", "Họ và tên", "SĐT", "CCCD" };
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

		// Handle filter
		rowSorter = new TableRowSorter<TableModel>(modelThongKe);
		tblThongKe.setRowSorter(rowSorter);
	}

	/**
	 * 
	 */
	private void refreshTable() {
		// TODO Auto-generated method stub
		modelThongKe.setRowCount(0);

		List<NhanVien> listNV = nvBUS.getAllNhanViens();

		for (NhanVien nv : listNV) {
			Object[] rowData = { nv.getMaNhanVien(), nv.getHoTen(), nv.getSoDienThoai(), nv.getCCCD() };
			modelThongKe.addRow(rowData);
		}
	}

	/**
	 * 
	 */
	private void handleThongKeByDate(String maNV, Date fromDate, Date toDate) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpBUS.getAllPhieuDatPhongFromDateByNhanVien(maNV,
				DateTimeFormatUtil.formatDateToLocalDate(fromDate).atStartOfDay(),
				DateTimeFormatUtil.formatDateToLocalDate(toDate).atStartOfDay());

		if (listPDP == null || listPDP.size() == 0) {
			Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
					"Không tìm thấy hóa đơn nào phù hợp theo yêu cầu tìm kiếm");
			return;
		}

		System.out.println("Mã nhân viên: " + maNV);

		handleCalculateData(listPDP);

		// Handle set labels
		handleSetLabel();
	}

	/**
	 * 
	 */
	private void handleThongKeByMonth(String maNV, int month) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpBUS.getAllPhieuDatPhongByMonthByNhanVien(maNV, month);

		if (listPDP == null || listPDP.size() == 0) {
			Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
					"Không tìm thấy hóa đơn nào phù hợp theo yêu cầu tìm kiếm");
			return;
		}

		handleCalculateData(listPDP);

		// Handle set labels
		handleSetLabel();
	}

	private void handleThongKeByYear(String maNV, int year) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpBUS.getAllPhieuDatPhongByYearByNhanVien(maNV, year);

		if (listPDP == null || listPDP.size() == 0) {
			Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
					"Không tìm thấy hóa đơn nào phù hợp theo yêu cầu tìm kiếm");
			return;
		}

		handleCalculateData(listPDP);

		// Handle set labels
		handleSetLabel();
	}

	/**
	 * 
	 */
	private void handleCalculateData(List<PhieuDatPhong> listPDP) {
		listPDP.forEach(pdp -> {
			// Handle count
			tongHoaDon++;

			// Handle doanh thu phòng
			List<ChiTietPhieuDatPhong> listCTPDP = ctpdpBUS
					.getAllChiTietPhieuDatPhongByMaPhieuDatPhong(pdp.getMaPhieuDatPhong());

			System.out.println("Số chi tiết PĐP cho PĐP " + pdp.getMaPhieuDatPhong() + ":" + listCTPDP.size());

			double tienPhong = 0;
			for (ChiTietPhieuDatPhong ctpdp : listCTPDP) {
				tienPhong = ctpdp.getTienPhongAndPhuPhi();

				tongTienPhong += tienPhong;

				// Handle doanh thu phòng thường hoặc VIP
				if (ctpdp.getPhong().getLoaiPhong().getTenLoaiPhong().contains("Thường")) {
					doanhThuPhongThuong += ctpdp.getTienPhongAndPhuPhi();
				} else {
					doanhThuPhongVIP += ctpdp.getTienPhongAndPhuPhi();
				}
			}

			// Handle doanh thu dịch vụ
			double tienDichVu = ctdvBUS.getTongTienDichVuByMaPDP(pdp.getMaPhieuDatPhong());
			tongTienDichVu += tienDichVu;

			System.out.println("Tổng tiền hóa đơn " + pdp.getMaPhieuDatPhong() + ": " + (tienPhong + tienDichVu));
		});

		// Handle tong tien and doanh thu trung binh
		tongDoanhThu = tongTienPhong + tongTienDichVu;
	}

	private void handleSetLabel() {
		lblTongHoaDon.setText("Tổng số hóa đơn: " + (String.valueOf(tongHoaDon)));
		lblDoanhThuPhongThuong.setText("Doanh thu phòng thường: " + (MoneyFormatUtil.format(doanhThuPhongThuong)));
		lblDoanhThuPhongVIP.setText("Doanh thu phòng VIP: " + (MoneyFormatUtil.format(doanhThuPhongVIP)));
		lblTongTienPhong.setText("Tổng tiền phòng: " + (MoneyFormatUtil.format(tongTienPhong)));
		lblTongTienDichVu.setText("Tổng tiền dịch vụ: " + (MoneyFormatUtil.format(tongTienDichVu)));
		lblTongDoanhThu.setText("Tổng doanh thu: " + (MoneyFormatUtil.format(tongDoanhThu)));
	}

	private static CategoryDataset createDataset() {
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

	private void updateChart(String maNV) {
		dataset.clear();

		// Load each month data to chart
		for (int i = 1; i <= 12; i++) {
			int doanhThu = 0;
			List<PhieuDatPhong> listPhieuDatPhong = pdpBUS.getAllPhieuDatPhongByMonthByNhanVien(maNV, i);
			for (PhieuDatPhong pdp : listPhieuDatPhong) {
				doanhThu += pdp.getTongTien();
			}
			dataset.addValue(doanhThu, "Doanh thu", "Tháng " + i);
		}

		barChart.fireChartChanged();
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
				isInputValid = true;
			} else if (boxFilterNgay.getSelectedIndex() == 2) {
				cl.show(panelFilters, "filtersNam");
				isInputValid = true;
			}
		} else if (o.equals(btnSearch)) {
			if (tblThongKe.getSelectedRow() == -1) {
				Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Hãy chọn nhân viên");
				return;
			}

			if (isInputValid) {
				if (boxFilterNgay.getSelectedIndex() == 0) {
					String maNV = modelThongKe.getValueAt(tblThongKe.getSelectedRow(), 0).toString();
					Notifications.getInstance().show(Type.INFO, Location.BOTTOM_RIGHT,
							"Thống kê từ ngày " + fromDateChooser.getDate().toString() + " đến ngày "
									+ toDateChooser.getDate().toString() + " cho nhân viên " + maNV);
					handleThongKeByDate(maNV, fromDateChooser.getDate(), toDateChooser.getDate());
					updateChart(maNV);
				} else if (boxFilterNgay.getSelectedIndex() == 1) {
					String maNV = modelThongKe.getValueAt(tblThongKe.getSelectedRow(), 0).toString();
					Notifications.getInstance().show(Type.INFO, Location.BOTTOM_RIGHT,
							"Thống kê tháng " + monthChooser.getMonth() + " cho nhân viên " + maNV);
					handleThongKeByMonth(maNV, monthChooser.getMonth());
					updateChart(maNV);
					refreshTable();
				} else if (boxFilterNgay.getSelectedIndex() == 2) {
					String maNV = modelThongKe.getValueAt(tblThongKe.getSelectedRow(), 0).toString();
					Notifications.getInstance().show(Type.INFO, Location.BOTTOM_RIGHT,
							"Thống kê năm " + yearChooser.getYear() + " cho nhân viên " + maNV);
					handleThongKeByYear(maNV, yearChooser.getYear());
					updateChart(maNV);
					refreshTable();
				}
			} else {
				Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
						"Hãy chọn ngày bắt đầu và ngày kết thúc");
			}
		} else if (o.equals(btnReset)) {
			resetInputs();
			resetAllStatistics();
		}
	}
}
