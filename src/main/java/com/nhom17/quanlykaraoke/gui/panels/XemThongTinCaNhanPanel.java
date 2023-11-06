package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.kordamp.ikonli.materialdesign2.MaterialDesignE;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.NhanVien;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:16:05 PM
 */
public class XemThongTinCaNhanPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * @param currentNhanVien
	 * 
	 */
	public XemThongTinCaNhanPanel(NhanVien currentNhanVien) {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.DARK_GRAY);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));

		JLabel lblThngTinNgi = new JLabel("Thông tin người dùng");
		lblThngTinNgi.setBorder(new EmptyBorder(5, 10, 5, 0));
		lblThngTinNgi.setFont(new Font("Dialog", Font.BOLD, 30));
		lblThngTinNgi.setForeground(Color.WHITE);
		panelTop.add(lblThngTinNgi);

		JPanel panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.GRAY);
		panelCenter.add(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		Box boxAvatar = Box.createHorizontalBox();
		boxAvatar.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelLeft.add(boxAvatar);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		boxAvatar.add(horizontalStrut_2);

		// Set avatar
		ImageIcon avatar = null;
		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(currentNhanVien.getAnhDaiDien()));

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
			avatar = new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
//					avatar.setImage(avatar.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel userAvatar = new JLabel("");
		userAvatar.setPreferredSize(new Dimension(200, 200));
		userAvatar.setAlignmentX(0.5f);
		userAvatar.setIcon(avatar);
		boxAvatar.add(userAvatar);

		Component horizontalGlue_1_1 = Box.createHorizontalGlue();
		boxAvatar.add(horizontalGlue_1_1);

		Box boxFullName = Box.createHorizontalBox();
		panelLeft.add(boxFullName);

		Component horizontalStrut_2_1 = Box.createHorizontalStrut(20);
		boxFullName.add(horizontalStrut_2_1);

		JLabel lblName = new JLabel("Tên: Trần Nguyên Vũ");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Dialog", Font.BOLD, 36));
		boxFullName.add(lblName);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		boxFullName.add(horizontalGlue_1);

		Component verticalGlue = Box.createVerticalGlue();
		panelLeft.add(verticalGlue);

		Box boxGioiTinh = Box.createHorizontalBox();
		panelLeft.add(boxGioiTinh);

		Component horizontalStrut_2_1_1 = Box.createHorizontalStrut(20);
		boxGioiTinh.add(horizontalStrut_2_1_1);

		JLabel lblGioiTinh = new JLabel("Giới tính: Nam");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setForeground(Color.WHITE);
		lblGioiTinh.setFont(new Font("Dialog", Font.BOLD, 36));
		boxGioiTinh.add(lblGioiTinh);

		Component horizontalGlue_1_1_1_1 = Box.createHorizontalGlue();
		boxGioiTinh.add(horizontalGlue_1_1_1_1);

		Box boxMaNV = Box.createHorizontalBox();
		panelLeft.add(boxMaNV);

		Component horizontalStrut_2_1_1_1 = Box.createHorizontalStrut(20);
		boxMaNV.add(horizontalStrut_2_1_1_1);

		JLabel lblMaNV = new JLabel("Mã nhân viên: NV001");
		lblMaNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNV.setForeground(Color.WHITE);
		lblMaNV.setFont(new Font("Dialog", Font.BOLD, 36));
		boxMaNV.add(lblMaNV);

		Component horizontalGlue_1_1_1 = Box.createHorizontalGlue();
		boxMaNV.add(horizontalGlue_1_1_1);

		Box boxChucVu = Box.createHorizontalBox();
		panelLeft.add(boxChucVu);

		Component horizontalStrut_2_1_1_1_1 = Box.createHorizontalStrut(20);
		boxChucVu.add(horizontalStrut_2_1_1_1_1);

		JLabel lblChucVu = new JLabel("Chức vụ: Nhân viên");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setForeground(Color.WHITE);
		lblChucVu.setFont(new Font("Dialog", Font.BOLD, 36));
		boxChucVu.add(lblChucVu);

		Component horizontalGlue_1_1_1_1_1 = Box.createHorizontalGlue();
		boxChucVu.add(horizontalGlue_1_1_1_1_1);

		Box boxSDT = Box.createHorizontalBox();
		panelLeft.add(boxSDT);

		Component horizontalStrut_2_1_1_1_1_1 = Box.createHorizontalStrut(20);
		boxSDT.add(horizontalStrut_2_1_1_1_1_1);

		JLabel lblSDT = new JLabel("Số điện thoại: 0903252508");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setForeground(Color.WHITE);
		lblSDT.setFont(new Font("Dialog", Font.BOLD, 36));
		boxSDT.add(lblSDT);

		Component horizontalGlue_1_1_1_1_1_1 = Box.createHorizontalGlue();
		boxSDT.add(horizontalGlue_1_1_1_1_1_1);

		Box boxNgaySinh = Box.createHorizontalBox();
		panelLeft.add(boxNgaySinh);

		Component horizontalStrut_2_1_1_1_1_1_1 = Box.createHorizontalStrut(20);
		boxNgaySinh.add(horizontalStrut_2_1_1_1_1_1_1);

		JLabel lblNgaySinh = new JLabel("Ngày sinh: 23/11/2003");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgaySinh.setForeground(Color.WHITE);
		lblNgaySinh.setFont(new Font("Dialog", Font.BOLD, 36));
		boxNgaySinh.add(lblNgaySinh);

		Component horizontalGlue_1_1_1_1_1_1_1 = Box.createHorizontalGlue();
		boxNgaySinh.add(horizontalGlue_1_1_1_1_1_1_1);

		JPanel panelRight = new JPanel();
		panelRight.setBackground(Color.GRAY);
		panelCenter.add(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(40);
		panelRight.add(verticalStrut_1);

		Box horizontalBox_2_1 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_2_1);

		JLabel lblNhpMtKhu_1_1 = new JLabel("Nhập mật khẩu hiện tại");
		lblNhpMtKhu_1_1.setForeground(Color.WHITE);
		lblNhpMtKhu_1_1.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_2_1.add(lblNhpMtKhu_1_1);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_2_1.add(horizontalGlue_2);

		Box horizontalBox_1_1_1 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_1_1_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_1.setColumns(10);
		horizontalBox_1_1_1.add(textField_1);

		JButton btnShowCurrentPassword = new JButton("");
		btnShowCurrentPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowCurrentPassword.setBackground(Color.white);
		btnShowCurrentPassword.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, null));
		horizontalBox_1_1_1.add(btnShowCurrentPassword);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1_1_1.add(horizontalStrut_3);

		Component verticalStrut = Box.createVerticalStrut(50);
		panelRight.add(verticalStrut);

		Box horizontalBox_2 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_2);

		JLabel lblNhpMtKhu_1 = new JLabel("Nhập mật khẩu mới");
		lblNhpMtKhu_1.setForeground(Color.WHITE);
		lblNhpMtKhu_1.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox_2.add(lblNhpMtKhu_1);

		Component horizontalGlue_2_1 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_2_1);

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_1_1);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField.setColumns(10);
		horizontalBox_1_1.add(textField);

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1);

		Component verticalStrut_2 = Box.createVerticalStrut(50);
		panelRight.add(verticalStrut_2);

		Box horizontalBox = Box.createHorizontalBox();
		panelRight.add(horizontalBox);

		JLabel lblNhpMtKhu = new JLabel("Nhập lại mật khẩu mới");
		lblNhpMtKhu.setForeground(Color.WHITE);
		lblNhpMtKhu.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox.add(lblNhpMtKhu);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);

		Box horizontalBox_1_2 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_1_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField_2.setColumns(10);
		horizontalBox_1_2.add(textField_2);

		Component horizontalStrut_3_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_2.add(horizontalStrut_3_1_1);

		Component verticalStrut_2_1 = Box.createVerticalStrut(40);
		panelRight.add(verticalStrut_2_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_1);

		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 28));
		horizontalBox_1.add(btnNewButton);

		Component verticalStrut_2_1_1 = Box.createVerticalStrut(200);
		panelRight.add(verticalStrut_2_1_1);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(40, 0, 40, 0));
		panelBottom.setBackground(Color.DARK_GRAY);
		add(panelBottom, BorderLayout.SOUTH);
	}
}
