package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.kordamp.ikonli.materialdesign2.MaterialDesignM;

import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 25-Oct-2023 16:39:00
 */
public class RoomPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JLabel lblLogo = new JLabel("");
	private final JLabel lblRoom = new JLabel("");
	private final JPanel infoPanel = new JPanel();
	private final JPanel mainPanel = new JPanel();
	private final JLabel lblNumber = new JLabel("Số lượng: 5 người");
	private final JPanel panel = new JPanel();
	private final JLabel lblType = new JLabel("Loại: Thường");
	private final JLabel lblFee = new JLabel("Phụ phí: 0 VND");
	private final JLabel lblStatus = new JLabel("Trạng thái: Trống");

	// VARIABLES
	private String roomName = "Phòng ";
	private boolean isSelected = false;
	private Phong p = null;

	public RoomPanel(Phong p) {
		this.p = p;
		this.roomName = roomName.concat(p.getMaPhong());
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		if (p.isTrangThai()) {
			lblLogo.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT, 180, null));
		} else {
			lblLogo.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT_OFF, 180, null));
		}

		addMouseListener(this);
		setLayout(new BorderLayout(0, 0));

		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		mainPanel.setName("mainPanel");
		panel.add(mainPanel, "mainPanel");
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(lblRoom, BorderLayout.SOUTH);

		lblRoom.setText(roomName);

		lblRoom.setVerticalAlignment(SwingConstants.TOP);
		lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoom.setFont(new Font("Dialog", Font.BOLD, 40));
		mainPanel.add(lblLogo, BorderLayout.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.setBorder(new TitledBorder(null, roomName, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setName("infoPanel");
		panel.add(infoPanel, "infoPanel");
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		lblType.setFont(new Font("Dialog", Font.BOLD, 24));
		infoPanel.add(lblType);
		lblNumber.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblNumber);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblStatus);
		lblFee.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblFee);

		lblNumber.setText("Số lượng: " + String.format("%d", p.getLoaiPhong().getKichThuoc()) + " người");
		lblType.setText("Loại: " + p.getLoaiPhong().getTenLoaiPhong());
		lblFee.setText("Phụ phí: " + MoneyFormatUtil.format(p.getLoaiPhong().getPhuPhi()));
		lblStatus.setText("Trạng thái: " + (p.isTrangThai() == true ? "Đã đặt" : "Trống"));
	}

	public void createPDP() {
		System.out.println("Tạo phiếu đặt phòng cho phòng: " + p.getMaPhong());
	}

	public void removePDP() {
		System.out.println("Hủy phiếu đặt phòng cho phòng: " + p.getMaPhong());
	}

	public void select() {
		mainPanel.setBackground(Color.cyan);
		infoPanel.setBackground(Color.cyan);

		this.isSelected = true;
	}

	public void deselect() {
		mainPanel.setBackground(Color.white);
		infoPanel.setBackground(Color.white);

		this.isSelected = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.show(panel, "infoPanel");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.show(panel, "mainPanel");
	}

}
