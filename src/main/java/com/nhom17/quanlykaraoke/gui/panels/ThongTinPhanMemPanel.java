package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JTextField;

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
		
		//Tên tiêu đề
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.BLACK);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
		
		JLabel lblThngTinPhn = new JLabel("                THÔNG TIN PHẦN MỀM");
		lblThngTinPhn.setForeground(Color.RED);
		lblThngTinPhn.setBackground(Color.BLACK);
		lblThngTinPhn.setFont(new Font("Dialog", Font.BOLD, 35));
		panelTop.add(lblThngTinPhn);
		
		
		
		JPanel panelLeft = new JPanel();
		panelLeft.setForeground(Color.BLACK);
		panelLeft.setBackground(Color.WHITE);
		add(panelLeft, BorderLayout.CENTER);
		panelLeft.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin doanh nghiệp của Quản Lý Karaoke NNice         ");
		lblNewLabel_1.setBounds(157, 11, 551, 26);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		panelLeft.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phiên bản: 1.0.0");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(157, 39, 106, 14);
		panelLeft.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Ngày cập nhật: 07/12/2023");
		lblNewLabel.setBounds(157, 55, 146, 14);
		panelLeft.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Phần mềm quản lý quán karaoke được thiết kế để tối ưu hóa quy trình vận hành và cung cấp một trải nghiệm quản lý hiệu quả cho các quán karaoke. Với giao diện đơn giản ");
		lblNewLabel_3.setBounds(157, 130, 837, 42);
		panelLeft.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("và tính năng đa dạng, chúng tôi cam kết giúp doanh nghiệp của bạn hoạt động mượt mà và hiệu quả.");
		lblNewLabel_4.setBounds(157, 162, 777, 14);
		panelLeft.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nhóm 17 - PTUD 17B 2023 , Trường Đại học Công Nghiệp TPHCM.");
		lblNewLabel_5.setBounds(157, 72, 335, 14);
		panelLeft.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("12 Nguyễn Văn Bảo, P.4, Q. Gò Vấp, TP.HCM");
		lblNewLabel_6.setBounds(157, 90, 238, 14);
		panelLeft.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Cấu hình phần cừng - phần mềm được cập nhật");
		lblNewLabel_7.setBounds(157, 202, 316, 32);
		panelLeft.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Intel Core i5, 2.3 GHz");
		lblNewLabel_8.setBounds(157, 229, 137, 14);
		panelLeft.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Ram 8GB trở lên");
		lblNewLabel_9.setBounds(157, 245, 93, 14);
		panelLeft.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("HDD 256GB");
		lblNewLabel_10.setBounds(157, 259, 78, 14);
		panelLeft.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Độ phân giải màn hình 1920x1020 trở lên");
		lblNewLabel_11.setBounds(157, 273, 335, 14);
		panelLeft.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Sử dụng Microsoft SQL Server 2014, Microsoft Windows 10 và Eclipse IDE for Java Developers  23-06  ");
		lblNewLabel_12.setBounds(157, 293, 715, 14);
		panelLeft.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Phần mềm quản lý ứng dụng Karaoke yêu cầu quyền truy cập Internet/Dữ liệu truy cập để sử dụng.");
		lblNewLabel_13.setBounds(157, 343, 575, 14);
		panelLeft.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Hỗ trợ và Liên lạc");
		lblNewLabel_14.setBounds(157, 393, 137, 14);
		panelLeft.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Chúng tôi cam kết cung cấp sự hỗ trợ nhanh chóng và hiệu quả cho mọi vấn đề phát sinh.");
		lblNewLabel_15.setBounds(157, 407, 681, 14);
		panelLeft.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Để biết thêm chi tiết hoặc bất kỳ câu hỏi nào, vui lòng liên hệ với chúng tôi qua địa chỉ email: support@karaoke17biuh2023.com");
		lblNewLabel_16.setBounds(157, 427, 668, 14);
		panelLeft.add(lblNewLabel_16);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(Color.BLACK);
		add(panelBottom, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_17 = new JLabel("                                                                      ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 44));
		panelBottom.add(lblNewLabel_17);
	}
}
