package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * Màn hình chờ khi load ứng dụng
 * 
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 25-Oct-2023 14:51:00
 */
public class SplashScreenDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JProgressBar loadingBar = new JProgressBar();
	private final JLabel lblPercent = new JLabel("0%");
	private final JLabel lblStatus = new JLabel("");

	public SplashScreenDialog() {
		initUI();
		startLoadingBar();
	}

	/**
	 * Bắt đầu chạy thanh loading
	 */
	private void startLoadingBar() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i <= 100; i++) {
						// Speed of the progress bar
						Thread.sleep(60);
						lblPercent.setText(i + "%");

						if (i == 10) {
							lblStatus.setText("Đang bật hệ thống...");
						}

						if (i == 20) {
							lblStatus.setText("Đang bắt đầu hệ thống...");
						}

						if (i == 50) {
							lblStatus.setText("Đang kết nối đến cơ sở dữ liệu...");
						}

						if (i == 70) {
							lblStatus.setText("Đang kiểm tra các lỗi...");
						}

						if (i == 80) {
							lblStatus.setText("Đang hoàn tất...");
						}

						loadingBar.setValue(i);
					}

					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * Khởi tạo UI
	 */
	private void initUI() {
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setTitle("Hệ thống karaoke Nnice");
		setUndecorated(true);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon icon = null;
		try {
			Image image = ImageIO
					.read(SplashScreenDialog.class.getClassLoader().getResourceAsStream("images/logo.png"));
			Image scaledImage = image.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
			icon = new ImageIcon(scaledImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lblLogo.setIcon(icon);
		lblLogo.setFont(new Font("Dialog", Font.BOLD, 27));
		lblLogo.setBounds(310, 100, 600, 400);
		mainPanel.add(lblLogo);

		loadingBar.setBounds(0, 706, 1290, 14);
		mainPanel.add(loadingBar);

		lblPercent.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPercent.setForeground(Color.WHITE);
		lblPercent.setBounds(12, 675, 60, 26);
		mainPanel.add(lblPercent);

		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBackground(Color.WHITE);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 20));
		lblStatus.setBounds(920, 675, 340, 26);
		mainPanel.add(lblStatus);

		JLabel lblTtttt = new JLabel("Phần mềm quản lý karaoke Nnice v1.00");
		lblTtttt.setForeground(Color.WHITE);
		lblTtttt.setFont(new Font("Dialog", Font.BOLD, 36));
		lblTtttt.setBounds(290, 520, 706, 50);
		mainPanel.add(lblTtttt);

		JLabel lblNhm = new JLabel("© Nhóm 17 - Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy");
		lblNhm.setForeground(Color.LIGHT_GRAY);
		lblNhm.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNhm.setBounds(374, 570, 528, 20);
		mainPanel.add(lblNhm);

	}
}
