package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
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
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.kordamp.ikonli.materialdesign2.MaterialDesignE;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.NhanVien;

import net.miginfocom.swing.MigLayout;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 06-Nov-2023 4:16:05 PM
 */
public class XemThongTinCaNhanPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPasswordField txtCurrentPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtConfirmNewPassword;

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
		userAvatar.setHorizontalAlignment(SwingConstants.CENTER);
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

		JPanel panelCurrentPassword = new JPanel();
		panelCurrentPassword.setMaximumSize(new Dimension(32767, 40));
		panelCurrentPassword.setBackground(Color.white);
		panelCurrentPassword.setBounds(new Rectangle(0, 0, 0, 50));
		horizontalBox_1_1_1.add(panelCurrentPassword);
		panelCurrentPassword.setLayout(new MigLayout("", "[546px][34px]", "[:40px:40px,center]"));

		txtCurrentPassword = new JPasswordField();
		txtCurrentPassword.setMaximumSize(new Dimension(2147483647, 40));
		txtCurrentPassword.setEchoChar('*');
		txtCurrentPassword.setBorder(null);
		panelCurrentPassword.add(txtCurrentPassword, "cell 0 0,push ,grow");
		txtCurrentPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtCurrentPassword.setColumns(10);

		JButton btnShowCurrentPassword = new JButton("");
		btnShowCurrentPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowCurrentPassword.setBackground(null);
		btnShowCurrentPassword.setOpaque(false);
		btnShowCurrentPassword.setBorder(null);
		btnShowCurrentPassword.setPreferredSize(new Dimension(34, 16));
		panelCurrentPassword.add(btnShowCurrentPassword, "cell 1 0,alignx left,aligny center");
		btnShowCurrentPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowCurrentPassword.setBackground(null);
		btnShowCurrentPassword.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, null));
		btnShowCurrentPassword.setRolloverIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, Color.red));

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1_1_1.add(horizontalStrut_3);

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

		JPanel panelNewPassword = new JPanel();
		panelNewPassword.setMaximumSize(new Dimension(32767, 40));
		panelNewPassword.setBounds(new Rectangle(0, 0, 0, 50));
		panelNewPassword.setBackground(Color.WHITE);
		horizontalBox_1_1.add(panelNewPassword);
		panelNewPassword.setLayout(new MigLayout("", "[546px][34px]", "[40px]"));

		txtNewPassword = new JPasswordField();
		txtNewPassword.setEchoChar('*');
		txtNewPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtNewPassword.setColumns(10);
		txtNewPassword.setBorder(null);
		panelNewPassword.add(txtNewPassword, "cell 0 0, aligny center, push, grow");

		JButton btnShowNewPassword = new JButton("");
		btnShowNewPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowNewPassword.setPreferredSize(new Dimension(34, 16));
		btnShowNewPassword.setOpaque(false);
		btnShowNewPassword.setBorder(null);
		btnShowNewPassword.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, null));
		btnShowNewPassword.setRolloverIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, Color.red));
		btnShowNewPassword.setBackground((Color) null);
		panelNewPassword.add(btnShowNewPassword, "cell 1 0,alignx left,aligny center");

		Component horizontalStrut_3_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_1.add(horizontalStrut_3_1);

		Box horizontalBox = Box.createHorizontalBox();
		panelRight.add(horizontalBox);

		JLabel lblNhpMtKhu = new JLabel("Nhập lại mật khẩu mới");
		lblNhpMtKhu.setForeground(Color.WHITE);
		lblNhpMtKhu.setFont(new Font("Dialog", Font.BOLD, 24));
		horizontalBox.add(lblNhpMtKhu);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);

		Box horizontalBox_1_2 = Box.createHorizontalBox();
		horizontalBox_1_2.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelRight.add(horizontalBox_1_2);

		JPanel panelConfirmNewPassword = new JPanel();
		panelConfirmNewPassword.setMaximumSize(new Dimension(32767, 40));
		horizontalBox_1_2.add(panelConfirmNewPassword);
		panelConfirmNewPassword.setBounds(new Rectangle(0, 0, 0, 50));
		panelConfirmNewPassword.setBackground(Color.WHITE);
		panelConfirmNewPassword.setLayout(new MigLayout("", "[546px][34px]", "[40px]"));

		txtConfirmNewPassword = new JPasswordField();
		txtConfirmNewPassword.setEchoChar('*');
		txtConfirmNewPassword.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtConfirmNewPassword.setColumns(10);
		txtConfirmNewPassword.setBorder(null);
		panelConfirmNewPassword.add(txtConfirmNewPassword, "cell 0 0, aligny center, push, grow");

		JButton btnShowConfirmNewPassword = new JButton("");
		btnShowConfirmNewPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowConfirmNewPassword.setPreferredSize(new Dimension(34, 16));
		btnShowConfirmNewPassword.setOpaque(false);
		btnShowConfirmNewPassword.setBorder(null);
		btnShowConfirmNewPassword.setBackground((Color) null);
		btnShowConfirmNewPassword.setIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, null));
		btnShowConfirmNewPassword.setRolloverIcon(MyIcon.getIcon(MaterialDesignE.EYE_OFF, 28, Color.red));
		panelConfirmNewPassword.add(btnShowConfirmNewPassword, "cell 1 0, alignx left,aligny center");

		Component horizontalStrut_3_1_1 = Box.createHorizontalStrut(20);
		horizontalBox_1_2.add(horizontalStrut_3_1_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		panelRight.add(horizontalBox_1);

		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 28));
		horizontalBox_1.add(btnNewButton);

		Component verticalStrut_2_1_1 = Box.createVerticalStrut(100);
		panelRight.add(verticalStrut_2_1_1);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EmptyBorder(40, 0, 40, 0));
		panelBottom.setBackground(Color.DARK_GRAY);
		add(panelBottom, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
