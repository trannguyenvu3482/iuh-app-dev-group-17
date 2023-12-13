package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignG;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 01-Dec-2023 2:34:29 AM
 */
public class ThongTinPhanMemPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JButton btnGithub = new JButton("");
	private final JButton btnFacebook = new JButton("");

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

		JPanel panelLogo = new JPanel();
		panelLogo.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		panelLogo.setBackground(null);
		panelCenter.add(panelLogo);
		panelLogo.setLayout(new GridLayout(0, 1, 0, 0));

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
			icon = new ImageIcon(bi.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblLogo.setIcon(icon);

		panelLogo.add(lblLogo);

		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(null);
		panelCenter.add(panelInfo);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

		Component verticalStrut_1_1 = Box.createVerticalStrut(250);
		panelInfo.add(verticalStrut_1_1);

		Box horizontalBox = Box.createHorizontalBox();
		panelInfo.add(horizontalBox);

		JLabel lblNewLabel_1 = new JLabel("Phần mềm quản lý karaoke Nnice");
		horizontalBox.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 32));

		Box horizontalBox_1 = Box.createHorizontalBox();
		panelInfo.add(horizontalBox_1);

		JLabel lblNewLabel_2 = new JLabel("Phiên bản: v1.0.0");
		horizontalBox_1.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Dialog", Font.ITALIC, 18));

		Box horizontalBox_1_1 = Box.createHorizontalBox();
		panelInfo.add(horizontalBox_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("© Nhóm 17 - IUH - Đồ án Phát triển ứng dụng");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 20));
		horizontalBox_1_1.add(lblNewLabel_2_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelInfo.add(verticalStrut_2);

		Box horizontalBox_1_1_1 = Box.createHorizontalBox();
		horizontalBox_1_1_1.setAlignmentY(Component.CENTER_ALIGNMENT);
		panelInfo.add(horizontalBox_1_1_1);
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnGithub.setIcon(MyIcon.getIcon(MaterialDesignG.GITHUB, 40, null));
		btnGithub.setRolloverIcon(MyIcon.getIcon(MaterialDesignG.GITHUB, 40, Color.blue));
		btnGithub.setBackground(null);
		btnGithub.setOpaque(false);
		btnGithub.setContentAreaFilled(false);
		btnGithub.setBorderPainted(false);
		btnGithub.setBorder(null);
		horizontalBox_1_1_1.add(btnGithub);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1_1_1.add(horizontalStrut);
		btnFacebook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnFacebook.setIcon(MyIcon.getIcon(MaterialDesignF.FACEBOOK, 40, null));
		btnFacebook.setRolloverIcon(MyIcon.getIcon(MaterialDesignF.FACEBOOK, 40, Color.blue));
		btnFacebook.setBackground(null);
		btnFacebook.setOpaque(false);
		btnFacebook.setContentAreaFilled(false);
		btnFacebook.setBorderPainted(false);
		btnFacebook.setBorder(null);
		horizontalBox_1_1_1.add(btnFacebook);

		Component verticalStrut_1 = Box.createVerticalStrut(450);
		panelInfo.add(verticalStrut_1);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(ConstantUtil.MAIN_BLUE);
		add(panelBottom, BorderLayout.SOUTH);

		JLabel lblNewLabel_17 = new JLabel("                                                                      ");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 44));
		panelBottom.add(lblNewLabel_17);

		btnGithub.addActionListener(this);
		btnFacebook.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnGithub)) {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/trannguyenvu3482/iuh-app-dev-group-17"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnFacebook)) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.facebook.com/DuzFromSOL"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
