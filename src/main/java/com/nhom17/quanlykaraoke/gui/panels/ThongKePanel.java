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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

import com.nhom17.quanlykaraoke.bus.ChiTietDichVuBUS;
import com.nhom17.quanlykaraoke.bus.ChiTietPhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.dao.PhieuDatPhongDAO;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

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
	private final JLabel lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình: ");
	private final JLabel lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
	private final JLabel lblTongHoaDon = new JLabel("Tổng số hóa đơn: ");
	private final JLabel lblDoanhThuPhongThuong = new JLabel("Doanh thu phòng thường: ");
	private final JLabel lblDoanhThuPhongVIP = new JLabel("Doanh thu phòng VIP: ");
	private final JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng: ");
	private final JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ: ");

	JDateChooser fromDateChooser = new JDateChooser();
	JDateChooser toDateChooser = new JDateChooser();

	// VARIABLES
	private double doanhThuTrungBinh = 0;
	private double tongDoanhThu = 0;
	private int tongHoaDon = 0;
	private double tongTienPhong = 0;
	private double tongTienDichVu = 0;
	private double doanhThuPhongThuong = 0;
	private double doanhThuPhongVIP = 0;


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


		fromDateChooser.setMaxSelectableDate(new Date());
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
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

		JMonthChooser monthChooser = new JMonthChooser();
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

		JButton btnReset = new JButton("Reset");
		panelFilterTheoNgay.add(btnReset);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(500);
		panelFilterTheoNgay.add(horizontalStrut_1_1);

		JPanel panelContentTheoNgay = new JPanel();
		panelCenterTheoNgay.add(panelContentTheoNgay, BorderLayout.CENTER);

		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO THÁNG", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);

		NumberAxis yAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();

		// Format y axis with increments of 1000000
		yAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		yAxis.setTickUnit(new NumberTickUnit(1000000));
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
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		horizontalBox_3.setAlignmentY(Component.CENTER_ALIGNMENT);
		panel_1.add(horizontalBox_3);

		Component horizontalStrut_3_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3_1_1_1);

		lblTongDoanhThu.setHorizontalAlignment(SwingConstants.LEFT);
		horizontalBox_3.add(lblTongDoanhThu);
		lblTongDoanhThu.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox_2);

		Component horizontalStrut_3_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3_1_1_1_1);

		horizontalBox_2.add(lblTongHoaDon);
		lblTongHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox_1);

		Component horizontalStrut_3_1_1_1_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3_1_1_1_1_1);

		horizontalBox_1.add(lblDoanhThuTrungBinh);
		lblDoanhThuTrungBinh.setFont(new Font("Dialog", Font.BOLD, 20));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.add(horizontalBox);

		Component horizontalStrut_2 = Box.createHorizontalStrut(200);
		horizontalBox.add(horizontalStrut_2);

		JLabel lblDonViDoanhThu = new JLabel("(Doanh thu/hóa đơn)");
		lblDonViDoanhThu.setForeground(Color.LIGHT_GRAY);
		horizontalBox.add(lblDonViDoanhThu);
		lblDonViDoanhThu.setFont(new Font("Dialog", Font.ITALIC, 16));

		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		Box horizontalBox_3_1 = Box.createHorizontalBox();
		horizontalBox_3_1.setAlignmentY(0.5f);
		horizontalBox_3_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_3_1);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_3_1.add(horizontalStrut_3);

		lblDoanhThuPhongThuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblDoanhThuPhongThuong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_3_1.add(lblDoanhThuPhongThuong);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		horizontalBox_2_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_2_1);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		horizontalBox_2_1.add(horizontalStrut_3_1);

		lblDoanhThuPhongVIP.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_2_1.add(lblDoanhThuPhongVIP);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_1_1);

		Component horizontalStrut_3_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1_1);

		lblTongTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1_1.add(lblTongTienPhong);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_1);

		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setAlignmentX(0.0f);
		panel_2.add(horizontalBox_4);

		Component horizontalStrut_3_1_1_2 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_3_1_1_2);

		lblTongTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_4.add(lblTongTienDichVu);

		JPanel panelTheoNV = new JPanel();
		tabbedPane.addTab("Theo nhân viên", MyIcon.getIcon(MaterialDesignB.BADGE_ACCOUNT, 24, null), panelTheoNV, null);

		JPanel panelTheoKhachHang = new JPanel();
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
					if (toDateChooser.getDate() != null && fromDateChooser.getDate().before(toDateChooser.getDate())) {
						// Filter
						handleThongKeByDate(fromDateChooser.getDate(), toDateChooser.getDate());
					}
				}
			}
		});
	}

	private void handleThongKeByDate(Date fromDate, Date toDate) {
		List<PhieuDatPhong> listPDP = pdpDAO.getAllPhieuDatPhongFromDate(DateTimeFormatUtil.formatDateToLocalDate(fromDate), DateTimeFormatUtil.formatDateToLocalDate(toDate));

		// Handle data
		listPDP.forEach(pdp -> {
			// Handle count
			tongHoaDon++;

			// Handle doanh thu phòng
			List<ChiTietPhieuDatPhong> listCTPDP = ctpdpBUS.getAllChiTietPhieuDatPhongByMaPhieuDatPhong(pdp.getMaPhieuDatPhong());
			listCTPDP.forEach(ctpdp -> {
				tongTienPhong += ctpdp.getTienPhongAndPhuPhi();

				// Handle doanh thu phòng thường hoặc VIP
				if (ctpdp.getPhong().getLoaiPhong().getTenLoaiPhong().contains("Thường")) {
					doanhThuPhongThuong += ctpdp.getTienPhongAndPhuPhi();
				} else {
					doanhThuPhongVIP += ctpdp.getTienPhongAndPhuPhi();
				}
			});

			// Handle doanh thu dịch vụ
			tongTienDichVu = ctdvBUS.getTongTienDichVuByMaPDP(pdp.getMaPhieuDatPhong());

			// Handle tong tien and doanh thu trung binh
			tongDoanhThu = tongTienPhong + tongTienDichVu;
			doanhThuTrungBinh = tongDoanhThu / tongHoaDon;
		});

		// Handle set labels
		lblTongHoaDon.setText(lblTongHoaDon.getText().concat(String.valueOf(tongHoaDon)));
		lblDoanhThuPhongThuong.setText(lblDoanhThuPhongThuong.getText().concat(MoneyFormatUtil.format(doanhThuPhongThuong)));
		lblDoanhThuPhongVIP.setText(lblDoanhThuPhongVIP.getText().concat(MoneyFormatUtil.format(doanhThuPhongVIP)));
		lblTongTienPhong.setText(lblTongTienPhong.getText().concat(MoneyFormatUtil.format(tongTienPhong)));
		lblTongTienDichVu.setText(lblTongTienDichVu.getText().concat(MoneyFormatUtil.format(tongTienDichVu)));
		lblTongDoanhThu.setText(lblTongDoanhThu.getText().concat(MoneyFormatUtil.format(tongDoanhThu)));
		lblDoanhThuTrungBinh.setText(lblDoanhThuTrungBinh.getText().concat(MoneyFormatUtil.format(doanhThuTrungBinh)));
	}


	private static CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// Load each month data to chart
		for (int i = 1; i <= 12; i++) {
			int doanhThu = 0;
			List<PhieuDatPhong> listPhieuDatPhong = pdpDAO.getAllPhieuDatPhongByMonth(i);
			for (PhieuDatPhong pdp : listPhieuDatPhong) {
				doanhThu += pdp.getTongTien();
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
