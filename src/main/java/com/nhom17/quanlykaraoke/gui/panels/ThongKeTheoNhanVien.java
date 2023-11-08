package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 08-Nov-2023 2:54:49 PM
 */
public class ThongKeTheoNhanVien extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JComboBox<String> boxFilterNgay = new JComboBox<String>();
	private final JPanel panelFilters = new JPanel();

	/**
	 * 
	 */
	public ThongKeTheoNhanVien() {
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

		JDateChooser fromDateChooser = new JDateChooser();
		fromDateChooser.getCalendarButton().setPreferredSize(new Dimension(26, 19));
		fromDateChooser.getCalendarButton().setFont(new Font("Dialog", Font.BOLD, 20));
		filtersNgay.add(fromDateChooser);
		fromDateChooser.setLocale(new Locale("vi", "VN"));
		fromDateChooser.setPreferredSize(new Dimension(150, 48));
		fromDateChooser.setFont(new Font("Dialog", Font.PLAIN, 18));
		fromDateChooser.setDateFormatString("d/M/y");

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		filtersNgay.add(horizontalStrut_1);

		JDateChooser toDateChooser = new JDateChooser();
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

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(600);
		panelFilterTheoNgay.add(horizontalStrut_1_1);

		JPanel panelContentTheoNgay = new JPanel();
		panelCenterTheoNgay.add(panelContentTheoNgay, BorderLayout.CENTER);
		panelContentTheoNgay.setLayout(new BorderLayout(0, 0));

		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO NGÀY", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);

		ChartPanel chartPanel = new ChartPanel(barChart);

		panelContentTheoNgay.add(chartPanel);

		// Action listeners
		boxFilterNgay.addActionListener(this);

		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ THỐNG KÊ DOANH SỐ THEO NGÀY", "Tháng", "Doanh thu",
				createDataset(), PlotOrientation.VERTICAL, false, false, false);

		NumberAxis yAxis = (NumberAxis) barChart.getCategoryPlot().getRangeAxis();

		// Format y axis with increments of 1000000
		yAxis.setNumberFormatOverride(NumberFormat.getNumberInstance());
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		yAxis.setTickUnit(new NumberTickUnit(1000000));
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
