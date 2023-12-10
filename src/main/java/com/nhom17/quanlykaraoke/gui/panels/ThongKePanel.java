package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
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
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignR;

import com.nhom17.quanlykaraoke.bus.ChiTietDichVuBUS;
import com.nhom17.quanlykaraoke.bus.ChiTietPhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.dao.PhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import raven.toast.Notifications.Type;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:18:03 PM
 */
public class ThongKePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JComboBox<String> boxFilterNgay = new JComboBox<String>();
	private final JPanel panelFilters = new JPanel();
	private static final PhieuDatPhongDAO pdpDAO = new PhieuDatPhongDAO();
	private final ChiTietPhieuDatPhongBUS ctpdpBUS = new ChiTietPhieuDatPhongBUS();

	private final ChiTietDichVuBUS ctdvBUS = new ChiTietDichVuBUS();
	private final JLabel lblDoanhThuTrungBinh = new JLabel("");
	private final JLabel lblTongDoanhThu = new JLabel("");
	private final JLabel lblTongHoaDon = new JLabel("");
	private final JLabel lblDoanhThuPhongThuong = new JLabel("");
	private final JLabel lblDoanhThuPhongVIP = new JLabel("");
	private final JLabel lblTongTienPhong = new JLabel("");
	private final JLabel lblTongTienDichVu = new JLabel("");

	private final JDateChooser fromDateChooser = new JDateChooser();
	private final JDateChooser toDateChooser = new JDateChooser();

	private final JMonthChooser monthChooser = new JMonthChooser();

	// VARIABLES
	private double doanhThuTrungBinh = 0;
	private double tongDoanhThu = 0;
	private int tongHoaDon = 0;
	private double tongTienPhong = 0;
	private double tongTienDichVu = 0;
	private double doanhThuPhongThuong = 0;
	private double doanhThuPhongVIP = 0;
	private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	/**
	 *
	 */
	public ThongKePanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 24));
		add(tabbedPane, BorderLayout.CENTER);

		JPanel panelTheoNgay = new JPanel();
		tabbedPane.addTab("Theo thời gian", MyIcon.getIcon(MaterialDesignC.CALENDAR_CLOCK, 24, null), panelTheoNgay,
				null);
		panelTheoNgay.setLayout(new BorderLayout(0, 0));

		JPanel panelCenterTheoNgay = new JPanel();
		panelTheoNgay.add(panelCenterTheoNgay, BorderLayout.CENTER);
		panelCenterTheoNgay.setLayout(new BorderLayout(0, 0));

		JPanel panelFilterTheoNgay = new JPanel();
		panelFilterTheoNgay.setBackground(new Color(240,240,240));
		panelFilterTheoNgay.setBorder(new EmptyBorder(10, 20, 10, 20));
		panelCenterTheoNgay.add(panelFilterTheoNgay, BorderLayout.NORTH);
		panelFilterTheoNgay.setLayout(new BoxLayout(panelFilterTheoNgay, BoxLayout.X_AXIS));
		boxFilterNgay.setForeground(new Color(50, 102, 133));

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

		fromDateChooser.setMaxSelectableDate(new Date());
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		fromDateChooser.getDateEditor().setEnabled(false);
		filtersNgay.add(fromDateChooser);
		fromDateChooser.setLocale(new Locale("vi", "VN"));
		fromDateChooser.setPreferredSize(new Dimension(150, 48));
		fromDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		fromDateChooser.setDateFormatString("d/M/y");

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		filtersNgay.add(horizontalStrut_1);

		toDateChooser.setMaxSelectableDate(new Date());
		toDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		toDateChooser.setLocale(new Locale("vi", "VN"));
		toDateChooser.getDateEditor().setEnabled(false);
		filtersNgay.add(toDateChooser);
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

		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setPreferredSize(new Dimension(100, 46));
		yearChooser.setLocale(new Locale("vi", "VN"));
		yearChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		filtersNam.add(yearChooser);

		JButton btnReset = new JButton("");
		btnReset.putClientProperty("JButton.buttonType", "square");
		btnReset.setIcon(MyIcon.getIcon(MaterialDesignR.REFRESH, 32, null));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAllStatistics();
			}
		});
		panelFilterTheoNgay.add(btnReset);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(800);
		panelFilterTheoNgay.add(horizontalStrut_1_1);

		JPanel panelContentTheoNgay = new JPanel();
		panelCenterTheoNgay.add(panelContentTheoNgay, BorderLayout.CENTER);

		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO THÁNG", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);

		NumberAxis yAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();

		// Format y axis with increments of auto
		yAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		yAxis.setAutoTickUnitSelection(true);
		panelContentTheoNgay.setLayout(new BorderLayout(0, 0));

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

		panelContentTheoNgay.add(chartPanel);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 20, 10, 20));
		panelContentTheoNgay.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(240, 240, 240));
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox_3.setAlignmentY(Component.CENTER_ALIGNMENT);
		panel_1.add(horizontalBox_3);

		Component horizontalStrut_3_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3_1_1_1);
		lblTongDoanhThu.setForeground(new Color(50, 102, 133));

		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.LEFT);
		horizontalBox_3.add(lblTongDoanhThu);
		lblTongDoanhThu.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox_2);

		Component horizontalStrut_3_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3_1_1_1_1);
		lblTongHoaDon.setForeground(new Color(50, 102, 133));

		horizontalBox_2.add(lblTongHoaDon);
		lblTongHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox_1);

		Component horizontalStrut_3_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3_1_1_1_1_1);
		lblDoanhThuTrungBinh.setForeground(new Color(50, 102, 133));

		horizontalBox_1.add(lblDoanhThuTrungBinh);
		lblDoanhThuTrungBinh.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox);

		Component horizontalStrut_2 = Box.createHorizontalStrut(200);
		horizontalBox.add(horizontalStrut_2);

		JLabel lblDonViDoanhThu = new JLabel("(Doanh thu/hóa đơn)");
		lblDonViDoanhThu.setForeground(new Color(50, 102, 133));
		horizontalBox.add(lblDonViDoanhThu);
		lblDonViDoanhThu.setFont(new Font("Dialog", Font.ITALIC, 16));

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(240, 240, 240));
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		Box horizontalBox_3_1 = Box.createHorizontalBox();
		horizontalBox_3_1.setAlignmentY(0.5f);
		horizontalBox_3_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_3_1);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_3);
		lblDoanhThuPhongThuong.setForeground(new Color(50, 102, 133));

		lblDoanhThuPhongThuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongThuong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3_1.add(lblDoanhThuPhongThuong);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		horizontalBox_2_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_2_1);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_3_1);
		lblDoanhThuPhongVIP.setForeground(new Color(50, 102, 133));

		lblDoanhThuPhongVIP.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2_1.add(lblDoanhThuPhongVIP);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_1_1);

		Component horizontalStrut_3_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1_1);
		lblTongTienPhong.setForeground(new Color(50, 102, 133));

		lblTongTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1_1.add(lblTongTienPhong);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_1);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_4);

		Component horizontalStrut_3_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_3_1_1_2);
		lblTongTienDichVu.setForeground(new Color(50, 102, 133));

		lblTongTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_4.add(lblTongTienDichVu);

		JPanel panelTheoNV = new ThongKeTheoNhanVienPanel();
		tabbedPane.addTab("Theo nhân viên", MyIcon.getIcon(MaterialDesignB.BADGE_ACCOUNT, 24, null), panelTheoNV, null);

		JPanel panelTheoKhachHang = new ThongKeTheoKhachHangPanel();
		tabbedPane.addTab("Theo khách hàng", MyIcon.getIcon(MaterialDesignA.ACCOUNT, 24, null), panelTheoKhachHang,
				null);

		// Action listeners
		boxFilterNgay.addActionListener(this);

		// Load default data (Load from 1/1/2021 to current date)
		Date fromDate = DateTimeFormatUtil.formatLocalDateToDate(LocalDate.of(2021, 1, 1));
		Date toDate = DateTimeFormatUtil.formatLocalDateToDate(LocalDate.now());
		handleThongKeByDate(fromDate, toDate);

		// Handle JCalendar components events
		fromDateChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("date")) {
				// Handle date change
				Date date = fromDateChooser.getDate();

				if (date != null) {
					// Set min date for toDateChooser
					toDateChooser.setMinSelectableDate(date);

					// Handle filter
					// Check if from date and to date are valid
					if (toDateChooser.getDate() != null && (fromDateChooser.getDate().before(toDateChooser.getDate())
							|| fromDateChooser.getDate().equals(toDateChooser.getDate()))) {
						// Filter
						handleThongKeByDate(fromDateChooser.getDate(), toDateChooser.getDate());
						Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê từ "
								+ fromDateChooser.getDate().toString() + " đến " + toDateChooser.getDate().toString());
					} else {
						Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Ngày không hợp lệ");
					}
				}
			}
		});

		toDateChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("date")) {
				// Handle date change
				Date date = fromDateChooser.getDate();

				if (date != null) {
					// Set min date for toDateChooser
					toDateChooser.setMinSelectableDate(date);

					// Handle filter
					// Check if from date and to date are valid
					if (fromDateChooser.getDate() != null && (fromDateChooser.getDate().before(toDateChooser.getDate())
							|| fromDateChooser.getDate().equals(toDateChooser.getDate()))) {
						// Filter
						handleThongKeByDate(fromDateChooser.getDate(), toDateChooser.getDate());
						Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê từ "
								+ fromDateChooser.getDate().toString() + " đến " + toDateChooser.getDate().toString());
					} else {
						Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Ngày không hợp lệ");
						resetInputs();
					}
				}
			}
		});

		monthChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("month")) {
				// Handle date change
				int month = monthChooser.getMonth() + 1;

				// Handle filter
				// Check if from date and to date are valid
				if (month > 0) {
					// Filter
					handleThongKeByMonth(month);
					Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT,
							"Thống kê theo tháng " + month);
				} else {
					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Tháng không hợp lệ");
					resetInputs();
				}
			}
		});

		yearChooser.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName().equals("year")) {
				// Handle date change
				int year = yearChooser.getYear();

				// Handle filter
				// Check if from date and to date are valid
				if (year > 0) {
					// Filter
					handleThongKeByYear(year);
					Notifications.getInstance().show(Type.SUCCESS, Location.BOTTOM_RIGHT, "Thống kê theo năm " + year);
				} else {
					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT, "Năm không hợp lệ");
					resetInputs();
				}
			}
		});
	}

	/**
	 * 
	 */
	private void resetAllStatistics() {
		doanhThuTrungBinh = 0;
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
		lblDoanhThuTrungBinh.setText("Doanh thu trung bình: 0");

	}

	/**
	 * 
	 */
	private void resetInputs() {
		fromDateChooser.setDate(null);
		toDateChooser.setDate(null);
		monthChooser.setMonth(0);
	}

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
		doanhThuTrungBinh = tongDoanhThu / tongHoaDon;
	}

	private void handleSetLabel() {
		lblTongHoaDon.setText("Tổng số hóa đơn: " + (String.valueOf(tongHoaDon)));
		lblDoanhThuPhongThuong.setText("Doanh thu phòng thường: " + (MoneyFormatUtil.format(doanhThuPhongThuong)));
		lblDoanhThuPhongVIP.setText("Doanh thu phòng VIP: " + (MoneyFormatUtil.format(doanhThuPhongVIP)));
		lblTongTienPhong.setText("Tổng tiền phòng: " + (MoneyFormatUtil.format(tongTienPhong)));
		lblTongTienDichVu.setText("Tổng tiền dịch vụ: " + (MoneyFormatUtil.format(tongTienDichVu)));
		lblTongDoanhThu.setText("Tổng doanh thu: " + (MoneyFormatUtil.format(tongDoanhThu)));
		lblDoanhThuTrungBinh.setText("Doanh thu trung bình: " + (MoneyFormatUtil.format(doanhThuTrungBinh)));
	}

	private void handleThongKeByDate(Date fromDate, Date toDate) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpDAO.getAllPhieuDatPhongFromDate(
				DateTimeFormatUtil.formatDateToLocalDate(fromDate).atStartOfDay(),
				DateTimeFormatUtil.formatDateToLocalDate(toDate).atStartOfDay());

		System.out.println("Số PĐP" + ": " + listPDP.size());

		// Handle data
		handleCalculateData(listPDP);

		// Handle set labels
		handleSetLabel();
	}

	private void handleThongKeByMonth(int month) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpDAO.getAllPhieuDatPhongByMonth(month);

		// Calculate data
		handleCalculateData(listPDP);

		// Set label
		handleSetLabel();
	}

	private void handleThongKeByYear(int year) {
		// Reset all fields
		resetAllStatistics();

		List<PhieuDatPhong> listPDP = pdpDAO.getAllPhieuDatPhongByYear(year);

		// Calculate data
		handleCalculateData(listPDP);

		// Set label
		handleSetLabel();
	}

	private static CategoryDataset createDataset() {
		// Load each month data to chart
		for (int i = 1; i <= 12; i++) {
			int doanhThu = 0;
			List<PhieuDatPhong> listPhieuDatPhong = pdpDAO.getAllPhieuDatPhongByMonth(i);
			for (PhieuDatPhong pdp : listPhieuDatPhong) {
				double tongTien = pdp.getTienDichVu() + pdp.getTienPhong();
				doanhThu += tongTien;
			}
			dataset.addValue(doanhThu, "Doanh thu", "Tháng " + i);
		}

		return dataset;
	}

//	private static CategoryDataset createDataset() {
//
//		dataset.addValue(1000000, "Doanh thu", "Tháng 1");
//		dataset.addValue(1000000, "Doanh thu", "Tháng 2");
//		dataset.addValue(1000000, "Doanh thu", "Tháng 3");
//		dataset.addValue(800000, "Doanh thu", "Tháng 4");
//		dataset.addValue(1000000, "Doanh thu", "Tháng 5");
//		dataset.addValue(1000000, "Doanh thu", "Tháng 6");
//		dataset.addValue(1200000, "Doanh thu", "Tháng 7");
//		dataset.addValue(1400000, "Doanh thu", "Tháng 8");
//		dataset.addValue(2000000, "Doanh thu", "Tháng 9");
//		dataset.addValue(3000000, "Doanh thu", "Tháng 10");
//		dataset.addValue(5500000, "Doanh thu", "Tháng 11");
//		dataset.addValue(12000000, "Doanh thu", "Tháng 12");
//		return dataset;
//	}

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
