package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.nhom17.quanlykaraoke.bus.ChiTietDichVuBUS;
import com.nhom17.quanlykaraoke.bus.ChiTietPhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.bus.PhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.entities.ChiTietDichVu;
import com.nhom17.quanlykaraoke.entities.ChiTietPhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.KhachHang;
import com.nhom17.quanlykaraoke.entities.PhieuDatPhong;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;
import com.nhom17.quanlykaraoke.utils.DateTimeFormatUtil;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;

import raven.toast.Notifications;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 10:39:28 AM
 */
public class ThanhToanDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JLabel lblTenNhanVien = new JLabel("Tên nhân viên: ");
	private final JLabel lblTenKhachHang = new JLabel("Tên khách hàng: ");
	private final JLabel lblSDTNhanVien = new JLabel("SDT nhân viên: ");
	private final JLabel lblSDTKhachHang = new JLabel("SDT khách hàng: ");
	private final JLabel lblTienPhong = new JLabel("Tiền phòng: ");
	private final JLabel lblTongTien = new JLabel("Tổng: ");
	private final JLabel lblGioNhanPhong = new JLabel("Giờ nhận phòng: ");
	private final JLabel lblGioTraPhong = new JLabel("Giờ trả phòng: ");
	private final JLabel lblThueVAT = new JLabel("Thuế VAT: 10%");
	JLabel lblTienDichVu = new JLabel("Tiền dịch vụ: ");
	private JTable tbl;
	private DefaultTableModel model;
	private JButton btnXacNhan = new JButton("Xác nhận");
	private JCheckBox chkXuatHoaDon = new JCheckBox("Xuất hóa đơn?");

	// VARIABLES
	private Phong p;
	private KhachHang kh;
	private PhieuDatPhong pdp;
	private final ChiTietPhieuDatPhongBUS ctpdpBUS = new ChiTietPhieuDatPhongBUS();
	private final ChiTietDichVuBUS ctdvBUS = new ChiTietDichVuBUS();
	private final PhieuDatPhongBUS pdpBUS = new PhieuDatPhongBUS();
	private double tienPhong = 0;
	private double tienDichVu = 0;
	private double tongTien = 0;
	private LocalDateTime thoiGianBatDau = LocalDateTime.now();

	/**
	 * 
	 */
	public ThanhToanDialog(Phong p) {
		setSize(1200, 800);
		setTitle("Thanh toán phiếu đặt phòng");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		this.p = p;

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(10, 30, 10, 30));
		getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

		Box hBox1 = Box.createHorizontalBox();
		panelTop.add(hBox1);

		lblTenNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox1.add(lblTenNhanVien);

		Component horizontalGlue = Box.createHorizontalGlue();
		hBox1.add(horizontalGlue);

		lblTenKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox1.add(lblTenKhachHang);

		Component verticalStrut = Box.createVerticalStrut(10);
		panelTop.add(verticalStrut);

		Box hBox2 = Box.createHorizontalBox();
		panelTop.add(hBox2);

		lblSDTNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox2.add(lblSDTNhanVien);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		hBox2.add(horizontalGlue_1);

		lblSDTKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox2.add(lblSDTKhachHang);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(0, 30, 20, 30));
		getContentPane().add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));

		JButton btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setBackground(Color.DARK_GRAY);
		btnQuayLai.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(btnQuayLai);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panelBottom.add(horizontalGlue_2);

		chkXuatHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(chkXuatHoaDon);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBottom.add(horizontalStrut);

		btnXacNhan.setForeground(Color.WHITE);
		btnXacNhan.setBackground(Color.RED);
		btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
		panelBottom.add(btnXacNhan);

		JPanel panelCenter = new JPanel();
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new EmptyBorder(10, 30, 40, 30));
		panelCenter.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

		Box hBox3 = Box.createHorizontalBox();
		panelInfo.add(hBox3);

		lblTienDichVu.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox3.add(lblTienDichVu);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		hBox3.add(horizontalGlue_3);

		lblGioNhanPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox3.add(lblGioNhanPhong);

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1);

		Box hBox4 = Box.createHorizontalBox();
		panelInfo.add(hBox4);

		lblTienPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox4.add(lblTienPhong);

		Component horizontalGlue_3_1 = Box.createHorizontalGlue();
		hBox4.add(horizontalGlue_3_1);

		lblGioTraPhong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox4.add(lblGioTraPhong);

		Component verticalStrut_1_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1_1);

		Box hBox5 = Box.createHorizontalBox();
		panelInfo.add(hBox5);

		lblThueVAT.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox5.add(lblThueVAT);

		Component horizontalGlue_3_1_1 = Box.createHorizontalGlue();
		hBox5.add(horizontalGlue_3_1_1);

		JLabel lblTongThoiLuong = new JLabel("Tổng thời lượng: 60 phút");
		lblTongThoiLuong.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox5.add(lblTongThoiLuong);

		Component verticalStrut_1_1_1 = Box.createVerticalStrut(5);
		panelInfo.add(verticalStrut_1_1_1);

		Box hBox6 = Box.createHorizontalBox();
		panelInfo.add(hBox6);

		lblTongTien.setFont(new Font("Dialog", Font.BOLD, 20));
		hBox6.add(lblTongTien);

		Component horizontalGlue_3_1_1_1 = Box.createHorizontalGlue();
		hBox6.add(horizontalGlue_3_1_1_1);

		JScrollPane scrollPaneTable = new JScrollPane();
		panelCenter.add(scrollPaneTable, BorderLayout.CENTER);

		createTable();
		scrollPaneTable.setViewportView(tbl);

		// Load data to table
		refreshTable();

		// Load info data
		lblTenNhanVien.setText(lblTenNhanVien.getText().concat(ConstantUtil.currentNhanVien.getHoTen()));
		lblSDTNhanVien.setText(lblSDTNhanVien.getText().concat(ConstantUtil.currentNhanVien.getSoDienThoai()));
		lblTenKhachHang.setText(lblTenKhachHang.getText().concat(kh.getHoTen()));
		lblSDTKhachHang.setText(lblSDTKhachHang.getText().concat(kh.getSoDienThoai()));

		lblTienPhong.setText(lblTienPhong.getText().concat(MoneyFormatUtil.format(tienPhong)));
		lblTongTien.setText(lblTongTien.getText().concat(MoneyFormatUtil.format(tongTien * 1.1)));
		lblGioNhanPhong.setText(lblGioNhanPhong.getText().concat(DateTimeFormatUtil.formatFullDate(thoiGianBatDau)));
		lblGioTraPhong.setText(lblGioTraPhong.getText().concat(DateTimeFormatUtil.formatFullDate(LocalDateTime.now())));
		lblTienDichVu.setText(lblTienDichVu.getText().concat(MoneyFormatUtil.format(tienDichVu)));
		// Action listeners
		btnXacNhan.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnXacNhan)) {
			// TODO: REMOVE THIS WHEN DONE
//			pdpBUS.finishPhieuDatPhong(p.getMaPhong());

			if (chkXuatHoaDon.isSelected()) {
				try {
					exportPDF();

					Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT,
							"Xuất hóa đơn thành công");
				} catch (IOException | DocumentException ex) {
					throw new RuntimeException(ex);
				}
			}

			dispose();
		}

	}

	public void exportPDF() throws IOException, DocumentException {
		Container content = this.getContentPane();
		int height = content.getHeight();
		int width = content.getHeight();
		BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		content.printAll(g2d);
		g2d.dispose();

		String path = "src/main/resources/pdf/HoaDon" + pdp.getMaPhieuDatPhong() + ".pdf";
		Document d = new Document();
		PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(path));
		d.open();

		PdfContentByte contentByte = writer.getDirectContent();
		Image image = Image.getInstance(contentByte, ConstantUtil.scaleImage(400, height, img), 1);

		PdfTemplate template = contentByte.createTemplate(width, height);
		image.setAbsolutePosition(0, 0);
		template.addImage(image);
		contentByte.addTemplate(template, 0, 100);
		d.close();

		Desktop.getDesktop().open(new File(path));
	}

	private void createTable() {
		final String[] colNames = { "STT", "Tên", "Số lượng/Thời lượng", "Đơn giá", "Đơn vị tính", "Phụ phí",
				"Thành tiền" };
		model = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl = new JTable(model);
		tbl.setRowSelectionAllowed(false);
		tbl.setColumnSelectionAllowed(false);
		tbl.setCellSelectionEnabled(false);
		tbl.setFont(new Font("Dialog", Font.PLAIN, 18));
		tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setAutoCreateRowSorter(true);
		tbl.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbl.setRowHeight(40);

		// Col width
		tbl.getColumnModel().getColumn(0).setPreferredWidth(8);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(125);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(4).setPreferredWidth(75);
		tbl.getColumnModel().getColumn(5).setPreferredWidth(100);
		tbl.getColumnModel().getColumn(6).setPreferredWidth(100);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

	};

	private void refreshTable() {
		model.setRowCount(0);

		// Load booked rooms
		pdp = ctpdpBUS.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong()).getPhieuDatPhong();

		kh = ctpdpBUS.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong()).getPhieuDatPhong().getKhachHang();

		List<ChiTietPhieuDatPhong> listCTPDP = ctpdpBUS
				.getAllChiTietPhieuDatPhongByMaPhieuDatPhong(pdp.getMaPhieuDatPhong());

		int stt = 1;

		for (ChiTietPhieuDatPhong ctpdp : listCTPDP) {
			double phuPhi = ctpdp.getPhong().getLoaiPhong().getPhuPhi();
			double thanhTien = ChronoUnit.HOURS.between(ctpdp.getThoiGianBatDau(), LocalDateTime.now())
					* ConstantUtil.getStandardHourPrice(LocalDateTime.now()) + phuPhi;

			Object[] rowData = { stt, "Phòng " + ctpdp.getPhong().getMaPhong(),
					DateTimeFormatUtil.formatTimeBetween(ctpdp.getThoiGianBatDau(), LocalDateTime.now()), "", "",
					MoneyFormatUtil.format(phuPhi), MoneyFormatUtil.format(thanhTien) };

			model.addRow(rowData);
			stt++;

			// Handle sum values
			tienPhong += thanhTien;
			tongTien += thanhTien;

			if (ctpdp.getThoiGianBatDau().isBefore(thoiGianBatDau)) {
				thoiGianBatDau = ctpdp.getThoiGianBatDau();
			}
		}

		// Load food
		for (ChiTietPhieuDatPhong ctpdp : listCTPDP) {
			List<ChiTietDichVu> listCTDV = ctdvBUS.getChiTietDichVuByMaPDPAndMaPhong(
					ctpdp.getPhieuDatPhong().getMaPhieuDatPhong(), ctpdp.getPhong().getMaPhong());

			for (ChiTietDichVu c : listCTDV) {
				double thanhTien = c.getHangHoa().getDonGia() * c.getSoLuong();

				Object[] rowData = { stt, c.getHangHoa().getTenHangHoa(), c.getSoLuong(),
						MoneyFormatUtil.format(c.getHangHoa().getDonGia()),
						c.getHangHoa().getLoaiHangHoa().getDonViTinh(), "", "", MoneyFormatUtil.format(thanhTien) };

				model.addRow(rowData);
				stt++;

				// Handle sum values
				tienDichVu += thanhTien;
				tongTien += thanhTien;
			}
		}
	}
}
