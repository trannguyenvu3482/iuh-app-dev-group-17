package com.nhom17.quanlykaraoke.common;

import java.awt.BorderLayout;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created Nov 3, 2023 1:33:55 PM
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
public class NotificationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Notifications");
	private final JButton btnClear = new JButton("Clear all");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_1 = new JPanel();

	public NotificationPanel() {
		setSize(300, 600);
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));

		add(panel_1, BorderLayout.NORTH);
		panel_1.add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);

		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);

		btnClear.setFont(new Font("Dialog", Font.BOLD, 16));
		add(btnClear, BorderLayout.SOUTH);

		add(scrollPane, BorderLayout.CENTER);

		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setOpaque(false);
		sb.setForeground(new Color(33, 140, 206));
		sb.setPreferredSize(new Dimension(8, 8));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setViewportBorder(null);

		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));

		// TODO: DELETE THIS WHEN DONE
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
		panel.add(new NotificationItem("Đã thêm phòng mới", "Phòng 005", "một phút trước"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());

		// Arrow
		int header = 10;
		AffineTransform tran = new AffineTransform();
		tran.translate(getWidth() - 25, 5);
		tran.rotate(Math.toRadians(45));
		Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), tran);
		Area area = new Area(p);

		area.add(new Area(new RoundRectangle2D.Double(0, header, 300, 800 - header, 10, 10)));
		g2.fill(area);
		g2.dispose();
		super.paintComponent(g);
	}
}
