package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;

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

	// COMPONENTS
	private final JLabel lblTime = new JLabel("Time");
	private final JButton btnSend = new JButton("Thêm nhân viên");
	private final Box leftVBox = Box.createVerticalBox();
	private final Box hBoxPanelBtn4 = Box.createHorizontalBox();
	private final Box hBoxCurrentTime = Box.createHorizontalBox();
	private final Box hBoxPanelBtn = Box.createHorizontalBox();
	private final Box hBoxAvatar = Box.createHorizontalBox();
	private final JLabel userAvatar = new JLabel("");
	private final JLabel userFullName = new JLabel("Trần Nguyên Vũ");
	private final JLabel userChucVu = new JLabel("Quản lý");
	private final Box vBoxAvatar = Box.createVerticalBox();
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Box hBoxPanelBtn2 = Box.createHorizontalBox();
	private final MainPanelButton mainPanelButton = new MainPanelButton(1280, 20, "Quản lý dịch vụ",
			MaterialDesignS.SILVERWARE_FORK_KNIFE, (JPanel) null);
	private final Component verticalStrut_1_1 = Box.createVerticalStrut(20);
	private final Box hBoxPanelBtn3 = Box.createHorizontalBox();
	private final MainPanelButton mainPanelButton_1 = new MainPanelButton(1280, 20, "Xem thông tin cá nhân",
			MaterialDesignA.ACCOUNT, (JPanel) null);
	private final Component verticalStrut_1_1_1 = Box.createVerticalStrut(20);
	private final Component horizontalGlue = Box.createHorizontalGlue();
	private final Component horizontalGlue_1 = Box.createHorizontalGlue();
	private final Component horizontalGlue_1_1 = Box.createHorizontalGlue();
	private final Component horizontalGlue_1_1_1 = Box.createHorizontalGlue();
	private final Box hBoxInfo = Box.createHorizontalBox();
	private final JButton btnHelp = new JButton("");
	private final JButton btnInfo = new JButton("");
	private final JPanel panelInfo = new JPanel();
	private final JButton btnBack = new JButton("");
	private final Box horizontalBox = Box.createHorizontalBox();
	private final Component horizontalGlue_2 = Box.createHorizontalGlue();
	private final JPanel leftPanel = new JPanel();
	private final JLabel lblLogo = new JLabel("");
	private final JPanel rightPanel = new JPanel();
	private final JPanel panelTop = new JPanel();
	private final JLabel lblName = new JLabel("Date");
	private final JLabel lblWorkTime = new JLabel("00:00:00");

	// VARIABLES
	private ChucVuDAO cvDAO = new ChucVuDAO();
	private NhanVienBUS nvBUS = new NhanVienBUS();
	private NhanVien currentNhanVien = null;
	private boolean isSidebarMinimized = false;
	private final int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	private int worktimeSeconds = 0;

	public QuanLyNhanVienGUI(String maNV) {
		// Init
		this.currentNhanVien = nvBUS.getNhanVien(maNV);

		getContentPane().setLayout(new BorderLayout(0, 0));

		leftPanel.setBackground(Color.CYAN);
		leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.25), this.getHeight()));
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftVBox.setAlignmentY(Component.TOP_ALIGNMENT);
		leftPanel.add(leftVBox);
		horizontalBox.setMaximumSize(new Dimension(1200, 50));

		leftVBox.add(horizontalBox);

		horizontalBox.add(horizontalGlue_2);
		btnBack.setMinimumSize(new Dimension(50, 50));
		btnBack.setMaximumSize(new Dimension(50, 50));
		btnBack.setPreferredSize(new Dimension(50, 50));
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, null));
		btnBack.setRolloverIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, Color.RED));
		btnBack.setOpaque(false);
		btnBack.setBackground(null);
		btnBack.setBorder(null);

		horizontalBox.add(btnBack);
		hBoxAvatar.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftVBox.add(hBoxAvatar);
		vBoxAvatar.setAlignmentY(Component.TOP_ALIGNMENT);
		vBoxAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
		hBoxAvatar.add(vBoxAvatar);
		userAvatar.setAlignmentY(Component.TOP_ALIGNMENT);
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
		userFullName.setAlignmentY(Component.TOP_ALIGNMENT);
		userFullName.setFont(new Font("Dialog", Font.BOLD, 30));

		userFullName.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setAlignmentY(Component.TOP_ALIGNMENT);
		userChucVu.setFont(new Font("Dialog", Font.ITALIC, 18));
		userChucVu.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setForeground(new Color(0, 0, 0, 128));
		vBoxAvatar.add(userFullName);
		vBoxAvatar.add(userChucVu);

		leftVBox.add(hBoxCurrentTime);
		hBoxCurrentTime.add(lblTime);
		lblTime.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		lblTime.setVerticalAlignment((int) CENTER_ALIGNMENT);
		lblTime.setFont(new Font("Dialog", Font.BOLD, 50));
		lblName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblName.setText(new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN")).format(new Date()));

		ClockUtil clock = new ClockUtil(lblTime);
		hBoxPanelBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

		hBoxPanelBtn.add(new MainPanelButton(this.getWidth(), 20, "Quản lý phiếu đặt phòng", MaterialDesignH.HOME,
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
				MaterialDesignE.EXIT_TO_APP, new JPanel());
		hBoxPanelBtn4.add(mainPanelButton_2);

		mainPanelButton_2.add(horizontalGlue_1_1_1);

		leftVBox.add(hBoxPanelBtn4);
		hBoxInfo.setAlignmentY(Component.CENTER_ALIGNMENT);

		leftVBox.add(hBoxInfo);
		panelInfo.setMaximumSize(new Dimension(3276, 150));
		panelInfo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelInfo.setBackground(Color.CYAN);

		hBoxInfo.add(panelInfo);
		panelInfo.setLayout(new MigLayout("alignx center, aligny bottom", "[][][]", "[]"));
		panelInfo.add(btnInfo, "push, cell 0 0, width 50px");

		btnInfo.setIcon(MyIcon.getIcon(MaterialDesignI.INFORMATION, 20, null));
		btnInfo.setPreferredSize(new Dimension(50, 50));

		ImageIcon icon = new ImageIcon("src/main/resources/images/logo.png");
		Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		icon.setImage(img);

		lblLogo.setIcon(icon);
		lblLogo.setPreferredSize(new Dimension(200, 50));

		panelInfo.add(lblLogo, "cell 1 0, push, alignx center");
		panelInfo.add(btnHelp, "cell 2 0,push,alignx right");
		btnHelp.setIcon(MyIcon.getIcon(MaterialDesignH.HELP_CIRCLE, 20, null));
		btnHelp.setPreferredSize(new Dimension(50, 50));
		btnHelp.setToolTipText("Hiển thị file pdf hướng dẫn sử dụng phần mềm");
		rightPanel.setBackground(Color.YELLOW);

		getContentPane().add(rightPanel, BorderLayout.CENTER);
		rightPanel.setLayout(new BorderLayout(0, 0));
		panelTop.setBackground(Color.PINK);

		rightPanel.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("alignx center, aligny center", "[][]", "[]"));

		panelTop.add(lblName, "push, cell 0 0, alignx left");
		lblWorkTime.setFont(new Font("Dialog", Font.BOLD, 20));
		panelTop.add(lblWorkTime, "push, cell 1 0, alignx right");

		btnInfo.addActionListener(this);
		btnHelp.addActionListener(this);

		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnSend.addActionListener(this);
		btnBack.addActionListener(this);
		clock.startClock();
		startWorkTimer();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("PRESSED");
				if (e.getKeyCode() == 112) {
					btnHelp.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("PRESSED");
				if (e.getKeyCode() == 112) {
					btnHelp.doClick();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnBack)) {
			if (isSidebarMinimized) {
				lblTime.setVisible(true);
				hBoxAvatar.setVisible(true);
				btnInfo.setVisible(true);
				btnHelp.setVisible(true);
				lblLogo.setVisible(true);
				leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.25), this.getHeight()));
				btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, null));
				btnBack.setRolloverIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, Color.RED));

				isSidebarMinimized = false;
			} else {
				lblTime.setVisible(false);
				hBoxAvatar.setVisible(false);
				btnInfo.setVisible(false);
				btnHelp.setVisible(false);
				lblLogo.setVisible(false);
				leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.04), this.getHeight()));
				btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_RIGHT_CIRCLE, 40, null));
				btnBack.setRolloverIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_RIGHT_CIRCLE, 40, Color.RED));

				isSidebarMinimized = true;

			}
		} else if (o.equals(btnHelp)) {
			try {
				File helpPDF = new File("src/main/resources/pdf/HUONG_DAN_SU_DUNG.pdf");
				Desktop.getDesktop().open(helpPDF);
			} catch (IOException ex) {
				// no application registered for PDFs
			}
		} else if (o.equals(lblWorkTime)) {
		}
	}

	public void startWorkTimer() {
		Timer timer = new Timer(1000, e -> {

			worktimeSeconds++;

			String time = String.format("%02d:%02d:%02d", worktimeSeconds / 3600, (worktimeSeconds % 3600) / 60,
					worktimeSeconds % 60);

			lblWorkTime.setText(time);

		});

		timer.start();
	}
}
