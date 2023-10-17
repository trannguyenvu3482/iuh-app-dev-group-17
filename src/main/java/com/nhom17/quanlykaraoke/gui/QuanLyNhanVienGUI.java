package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;

import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.common.MainPanelButton;
import com.nhom17.quanlykaraoke.common.MyFrame;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.dao.ChucVuDAO;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.utils.ClockUtil;

import net.miginfocom.swing.MigLayout;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 17-Oct-2023 22:13:00
 */
public class QuanLyNhanVienGUI extends MyFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JLabel lbl = new JLabel("Time");
	private final JButton btnSend = new JButton("Thêm nhân viên");

	private ChucVuDAO cvDAO = new ChucVuDAO();
	private NhanVienBUS nvBUS = new NhanVienBUS();
	private final Box leftVBox = Box.createVerticalBox();
	private final Box hBoxPanelBtn4 = Box.createHorizontalBox();
	private final Box hBoxCurrentTime = Box.createHorizontalBox();
	private final Box hBoxPanelBtn = Box.createHorizontalBox();
	private final Box hBoxAvatar = Box.createHorizontalBox();
	private final JLabel userAvatar = new JLabel("");
	private final JLabel userFullName = new JLabel("Trần Nguyên Vũ");
	private final JLabel userChucVu = new JLabel("Quản lý");
	private final Box vBoxAvatar = Box.createVerticalBox();
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Box hBoxPanelBtn2 = Box.createHorizontalBox();
	private final MainPanelButton mainPanelButton = new MainPanelButton(1280, 20, "Quản lý dịch vụ",
			MaterialDesignA.AB_TESTING, (JPanel) null);
	private final Component verticalStrut_1_1 = Box.createVerticalStrut(20);
	private final Box hBoxPanelBtn3 = Box.createHorizontalBox();
	private final MainPanelButton mainPanelButton_1 = new MainPanelButton(1280, 20, "Xem thông tin cá nhân",
			MaterialDesignA.AB_TESTING, (JPanel) null);
	private final Component verticalStrut_1_1_1 = Box.createVerticalStrut(20);
	private final Component horizontalGlue = Box.createHorizontalGlue();
	private final Component horizontalGlue_1 = Box.createHorizontalGlue();
	private final Component horizontalGlue_1_1 = Box.createHorizontalGlue();
	private final Component horizontalGlue_1_1_1 = Box.createHorizontalGlue();
	private final Box hBoxInfo = Box.createHorizontalBox();
	private final JButton btnHelp = new JButton("");
	private final JButton btnInfo = new JButton("");

	// Variables
	private NhanVien currentNhanVien = null;
	private final JPanel panelInfo = new JPanel();

	public QuanLyNhanVienGUI(String maNV) {
		// Init
		this.currentNhanVien = nvBUS.getNhanVien(maNV);

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.PINK);
		leftPanel.setPreferredSize(new Dimension((int) (this.getWidth() * 0.4), this.getHeight()));
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(leftVBox);
		leftVBox.add(verticalStrut);
		hBoxAvatar.setAlignmentX(CENTER_ALIGNMENT);
		hBoxAvatar.setAlignmentY(CENTER_ALIGNMENT);
		leftVBox.add(hBoxAvatar);

		vBoxAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
		hBoxAvatar.add(vBoxAvatar);
		vBoxAvatar.add(userAvatar);
		userAvatar.setAlignmentX(CENTER_ALIGNMENT);
		// TODO: Un-comment this when done
		ImageIcon avatar = null;
		try {
			avatar = new ImageIcon(ImageIO.read(new ByteArrayInputStream(currentNhanVien.getAnhDaiDien())));
			avatar.setImage(avatar.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
			userAvatar.setIcon(avatar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userAvatar.setIcon(avatar);

		userAvatar.setPreferredSize(new Dimension(200, 200));
		userFullName.setFont(new Font("Dialog", Font.BOLD, 30));

		userFullName.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setFont(new Font("Dialog", Font.ITALIC, 18));
		userChucVu.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setForeground(new Color(0, 0, 0, 128));
		vBoxAvatar.add(userFullName);
		vBoxAvatar.add(userChucVu);

		leftVBox.add(hBoxCurrentTime);
		hBoxCurrentTime.add(lbl);
		lbl.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		lbl.setVerticalAlignment((int) CENTER_ALIGNMENT);
		lbl.setFont(new Font("Dialog", Font.BOLD, 50));

		ClockUtil clock = new ClockUtil(lbl);
		hBoxPanelBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

		hBoxPanelBtn.add(new MainPanelButton(this.getWidth(), 20, "Quản lý phiếu đặt phòng", MaterialDesignA.AB_TESTING,
				new JPanel()));

		leftVBox.add(hBoxPanelBtn);

		hBoxPanelBtn.add(horizontalGlue);

		leftVBox.add(verticalStrut_1);
		leftVBox.add(hBoxPanelBtn2);

		hBoxPanelBtn2.add(mainPanelButton);

		hBoxPanelBtn2.add(horizontalGlue_1);
		leftVBox.add(verticalStrut_1_1);
		hBoxPanelBtn3.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftVBox.add(hBoxPanelBtn3);

		hBoxPanelBtn3.add(mainPanelButton_1);

		hBoxPanelBtn3.add(horizontalGlue_1_1);

		leftVBox.add(verticalStrut_1_1_1);
		hBoxPanelBtn4.setAlignmentY(Component.CENTER_ALIGNMENT);

		MainPanelButton mainPanelButton_2 = new MainPanelButton(this.getWidth(), 20, "Đăng xuất",
				MaterialDesignA.AB_TESTING, new JPanel());
		hBoxPanelBtn4.add(mainPanelButton_2);

		mainPanelButton_2.add(horizontalGlue_1_1_1);

		leftVBox.add(hBoxPanelBtn4);
		hBoxInfo.setAlignmentY(Component.CENTER_ALIGNMENT);

		leftVBox.add(hBoxInfo);
		panelInfo.setBackground(Color.PINK);

		hBoxInfo.add(panelInfo);
		panelInfo.setLayout(new MigLayout("alignx center", "[][]", "[]"));
		panelInfo.add(btnInfo, "push, cell 0 0, width 50px");

		btnInfo.setIcon(MyIcon.getIcon(MaterialDesignI.INFORMATION, 20, null));
		btnInfo.setPreferredSize(new Dimension(50, 50));
		panelInfo.add(btnHelp, "push, cell 1 0, width 50px, right");
		btnHelp.setIcon(MyIcon.getIcon(MaterialDesignH.HELP_CIRCLE, 20, null));
		btnHelp.setPreferredSize(new Dimension(50, 50));

		btnInfo.addActionListener(this);

		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnSend.addActionListener(this);
		clock.startClock();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnSend)) {
		}
	}

}
