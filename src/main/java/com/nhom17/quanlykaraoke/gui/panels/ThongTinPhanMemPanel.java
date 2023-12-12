package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nhom17.quanlykaraoke.utils.ConstantUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 01-Dec-2023 2:34:29 AM
 */
public class ThongTinPhanMemPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ThongTinPhanMemPanel() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		// Tên tiêu đề
		JPanel panelTop = new JPanel();
		panelTop.setBackground(ConstantUtil.MAIN_BLUE);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblThngTinPhn = new JLabel("THÔNG TIN PHẦN MỀM");
		lblThngTinPhn.setForeground(Color.WHITE);
		lblThngTinPhn.setBackground(Color.BLACK);
		lblThngTinPhn.setFont(new Font("Dialog", Font.BOLD, 35));
		panelTop.add(lblThngTinPhn);

		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCenter.setForeground(Color.BLACK);
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panelCenter.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

		// Set avatar
		ImageIcon icon = null;
		try {
			BufferedImage img = ImageIO.read(new File("src/main/resources/images/logo-icon.png"));

			int width = img.getWidth(null);
			int height = img.getHeight(null);

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = bi.createGraphics();

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int circleDiameter = Math.min(width, height);
			Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
			g2.setClip(circle);
			g2.drawImage(img, 0, 0, null);

			// Display circular image
			icon = new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblLogo.setIcon(icon);

		panel_1.add(lblLogo);

		JPanel panel = new JPanel();
		panelCenter.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông tin doanh nghiệp của Quản Lý Karaoke NNice         ");
		lblNewLabel_1.setBounds(0, 0, 551, 26);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));

		JLabel lblNewLabel_2 = new JLabel("Phiên bản: 1.0.0");
		lblNewLabel_2.setBounds(0, 28, 106, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblNewLabel = new JLabel("Ngày cập nhật: 07/12/2023");
		lblNewLabel.setBounds(0, 44, 146, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel(
				"Phần mềm quản lý quán karaoke được thiết kế để tối ưu hóa quy trình vận hành và cung cấp một trải nghiệm quản lý hiệu quả cho các quán karaoke. Với giao diện đơn giản ");
		lblNewLabel_3.setBounds(0, 119, 837, 42);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(
				"và tính năng đa dạng, chúng tôi cam kết giúp doanh nghiệp của bạn hoạt động mượt mà và hiệu quả.");
		lblNewLabel_4.setBounds(0, 151, 777, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Nhóm 17 - PTUD 17B 2023 , Trường Đại học Công Nghiệp TPHCM.");
		lblNewLabel_5.setBounds(0, 61, 335, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("12 Nguyễn Văn Bảo, P.4, Q. Gò Vấp, TP.HCM");
		lblNewLabel_6.setBounds(0, 79, 238, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Cấu hình phần cừng - phần mềm được cập nhật");
		lblNewLabel_7.setBounds(0, 191, 316, 32);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Intel Core i5, 2.3 GHz");
		lblNewLabel_8.setBounds(0, 218, 137, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Ram 8GB trở lên");
		lblNewLabel_9.setBounds(0, 234, 93, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("HDD 256GB");
		lblNewLabel_10.setBounds(0, 248, 78, 14);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Độ phân giải màn hình 1920x1020 trở lên");
		lblNewLabel_11.setBounds(0, 262, 335, 14);
		panel.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel(
				"Sử dụng Microsoft SQL Server 2014, Microsoft Windows 10 và Eclipse IDE for Java Developers  23-06  ");
		lblNewLabel_12.setBounds(0, 282, 715, 14);
		panel.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel(
				"Phần mềm quản lý ứng dụng Karaoke yêu cầu quyền truy cập Internet/Dữ liệu truy cập để sử dụng.");
		lblNewLabel_13.setBounds(0, 332, 575, 14);
		panel.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Hỗ trợ và Liên lạc");
		lblNewLabel_14.setBounds(0, 382, 137, 14);
		panel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel(
				"Chúng tôi cam kết cung cấp sự hỗ trợ nhanh chóng và hiệu quả cho mọi vấn đề phát sinh.");
		lblNewLabel_15.setBounds(0, 396, 681, 14);
		panel.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel(
				"Để biết thêm chi tiết hoặc bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi qua địa chỉ email: support@karaoke17biuh2023.com");
		lblNewLabel_16.setBounds(0, 416, 668, 14);
		panel.add(lblNewLabel_16);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(ConstantUtil.MAIN_BLUE);
		add(panelBottom, BorderLayout.SOUTH);

		JLabel lblNewLabel_17 = new JLabel("                                                                      ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 44));
		panelBottom.add(lblNewLabel_17);
	}
}
