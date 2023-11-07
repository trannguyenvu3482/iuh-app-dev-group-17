package com.nhom17.quanlykaraoke.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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

import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignI;
import org.kordamp.ikonli.materialdesign2.MaterialDesignS;

import com.nhom17.quanlykaraoke.bus.NhanVienBUS;
import com.nhom17.quanlykaraoke.common.MainPanelButton;
import com.nhom17.quanlykaraoke.common.MyFrame;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.common.NotificationPanel;
import com.nhom17.quanlykaraoke.entities.NhanVien;
import com.nhom17.quanlykaraoke.gui.panels.QuanLyHangHoaPanel;
import com.nhom17.quanlykaraoke.gui.panels.QuanLyPhongPanel;
import com.nhom17.quanlykaraoke.gui.panels.QuanLyThongTinNhanVienPanel;
import com.nhom17.quanlykaraoke.gui.panels.ThongKePanel;
import com.nhom17.quanlykaraoke.gui.panels.XemThongTinCaNhanPanel;
import com.nhom17.quanlykaraoke.utils.ClockUtil;

import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.swing.MigLayout;
import raven.glasspanepopup.DefaultLayoutCallBack;
import raven.glasspanepopup.DefaultOption;
import raven.glasspanepopup.GlassPanePopup;
import raven.toast.Notifications;

/**
 * 
 * Màn hình nhân viên
 * 
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 17-Oct-2023 22:13:00
 */
public class QuanLyGUI extends MyFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	// INTERFACE
	public interface LogoutListener {
		void onLogout();
	}

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
	private final JPanel panelContent = new JPanel();
	private final JPanel quanLyThongTinNhanVienPanel = (JPanel) new QuanLyThongTinNhanVienPanel();
	private final JPanel quanLyPhongPanel = (JPanel) new QuanLyPhongPanel();
	private final JPanel QuanLyHangHoaPanel = (JPanel) new QuanLyHangHoaPanel();
	private final JPanel ThongKePanel = (JPanel) new ThongKePanel();
	private final MainPanelButton mainPanelButton = new MainPanelButton(1280, 20, "Quản lý phòng", MaterialDesignH.HOME,
			quanLyPhongPanel, panelContent);
	private final Component verticalStrut_1_1 = Box.createVerticalStrut(20);
	private final Box hBoxPanelBtn3 = Box.createHorizontalBox();

	private final MainPanelButton mainPanelButton_1_1 = new MainPanelButton(1280, 20, "Thống kê",
			MaterialDesignC.CHART_BAR, (JPanel) ThongKePanel, panelContent);
	private final MainPanelButton mainPanelButton_1 = new MainPanelButton(1280, 20, "Quản lý hàng hóa",
			MaterialDesignS.SILVERWARE_FORK_KNIFE, QuanLyHangHoaPanel, panelContent);
	private final Box horizontalBox_1 = Box.createHorizontalBox();
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
	private final JPanel rightPanel = new JPanel();
	private final JPanel panelTop = new JPanel();
	private final JLabel lblName = new JLabel("Date");
	private final JButton btnNotifications = new JButton("");
	private final Component verticalGlue = Box.createVerticalGlue();
	private final JPanel panelAppInfo = new JPanel();
	private final JLabel lblNewLabel = new JLabel("New label");
	private final Component verticalStrut = Box.createVerticalStrut(20);

	// VARIABLES
	private NhanVienBUS nvBUS = new NhanVienBUS();
	private NhanVien currentNhanVien = null;
	private boolean isSidebarMinimized = false;
	private final int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode().getWidth();
	private LogoutListener logoutListener;
	private final Component verticalStrut_1_1_1_1 = Box.createVerticalStrut(20);

	public QuanLyGUI(String maNV) {
		// Init
		this.currentNhanVien = nvBUS.getNhanVien(maNV);
		Notifications.getInstance().setJFrame(this);
		GlassPanePopup.install(this);

		getContentPane().setLayout(new BorderLayout(0, 0));

		final JPanel xemThongTinCaNhanPanel = new XemThongTinCaNhanPanel(currentNhanVien);
		userFullName.setText(currentNhanVien.getHoTen());

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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon("src/main/resources/images/logo.png");
		Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		icon.setImage(img);

		leftPanel.setBackground(Color.BLACK);
		leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.25), this.getHeight()));
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftVBox.setAlignmentY(Component.TOP_ALIGNMENT);
		leftPanel.add(leftVBox);
		horizontalBox.setMaximumSize(new Dimension(1200, 50));

		leftVBox.add(horizontalBox);

		horizontalBox.add(horizontalGlue_2);
		btnBack.setToolTipText("(Phím tắt: Esc)");
		btnBack.setMinimumSize(new Dimension(50, 50));
		btnBack.setMaximumSize(new Dimension(50, 50));
		btnBack.setPreferredSize(new Dimension(50, 50));
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, Color.white));
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
		userAvatar.setIcon(avatar);
		userAvatar.setIcon(avatar);

		userAvatar.setPreferredSize(new Dimension(200, 200));
		userFullName.setForeground(Color.WHITE);
		userFullName.setAlignmentY(Component.TOP_ALIGNMENT);
		userFullName.setFont(new Font("Dialog", Font.BOLD, 30));

		userFullName.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setAlignmentY(Component.TOP_ALIGNMENT);
		userChucVu.setFont(new Font("Dialog", Font.ITALIC, 18));
		userChucVu.setAlignmentX(CENTER_ALIGNMENT);
		userChucVu.setForeground(new Color(255, 255, 255, 128));
		vBoxAvatar.add(userFullName);
		vBoxAvatar.add(userChucVu);
		leftVBox.add(verticalStrut);

		leftVBox.add(hBoxCurrentTime);
		hBoxPanelBtn.setToolTipText("(Phím tắt: F3)");
		hBoxPanelBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

		leftVBox.add(hBoxPanelBtn);

		hBoxPanelBtn.add(horizontalGlue);

		leftVBox.add(verticalStrut_1);
		leftVBox.add(hBoxPanelBtn2);
		mainPanelButton.setToolTipText("(Phím tắt: F4)");

		hBoxPanelBtn2.add(mainPanelButton);

		hBoxPanelBtn2.add(horizontalGlue_1);
		leftVBox.add(verticalStrut_1_1);
		hBoxPanelBtn3.setAlignmentY(Component.CENTER_ALIGNMENT);
		leftVBox.add(hBoxPanelBtn3);
		mainPanelButton_1.setToolTipText("(Phím tắt: F5)");

		hBoxPanelBtn3.add(mainPanelButton_1);

		hBoxPanelBtn3.add(horizontalGlue_1_1);
		leftVBox.add(verticalStrut_1_1_1_1);
		leftVBox.add(horizontalBox_1);
		horizontalBox_1.add(mainPanelButton_1_1);
		mainPanelButton_1_1.setToolTipText("(Phím tắt: F5)");

		leftVBox.add(verticalStrut_1_1_1);
		hBoxPanelBtn4.setToolTipText("");
		hBoxPanelBtn4.setAlignmentY(Component.CENTER_ALIGNMENT);

		MainPanelButton btnDangXuat = new MainPanelButton(this.getWidth(), 20, "Đăng xuất", MaterialDesignE.EXIT_TO_APP,
				new JPanel(), panelContent);
		btnDangXuat.setToolTipText("(Phím tắt: F6)");
		hBoxPanelBtn4.add(btnDangXuat);

		btnDangXuat.add(horizontalGlue_1_1_1);

		leftVBox.add(hBoxPanelBtn4);

		leftVBox.add(verticalGlue);
		hBoxInfo.setAlignmentY(Component.CENTER_ALIGNMENT);

		leftVBox.add(hBoxInfo);
		panelInfo.setMaximumSize(new Dimension(32767, 100));
		panelInfo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelInfo.setBackground(Color.BLACK);

		hBoxInfo.add(panelInfo);
		panelInfo.setLayout(new MigLayout("alignx center, aligny bottom", "[][][]", "[]"));
		btnInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		btnInfo.setOpaque(false);
		panelInfo.add(btnInfo, "push, cell 0 0, width 50px");

		btnInfo.setIcon(MyIcon.getIcon(MaterialDesignI.INFORMATION, 34, Color.WHITE));
		btnInfo.setRolloverIcon(MyIcon.getIcon(MaterialDesignI.INFORMATION, 34, Color.RED));
		btnInfo.setPreferredSize(new Dimension(50, 50));
		btnInfo.setBackground(null);
		btnInfo.setBorder(null);
		lblTime.setForeground(Color.WHITE);
		panelInfo.add(lblTime, "cell 1 0");
		lblTime.setHorizontalTextPosition((int) CENTER_ALIGNMENT);
		lblTime.setVerticalAlignment((int) CENTER_ALIGNMENT);
		lblTime.setFont(new Font("Dialog", Font.BOLD, 50));

		ClockUtil clock = new ClockUtil(lblTime);
		btnHelp.setOpaque(false);
		panelInfo.add(btnHelp, "cell 2 0,push,alignx right");
		btnHelp.setIcon(MyIcon.getIcon(MaterialDesignH.HELP_CIRCLE, 34, Color.WHITE));
		btnHelp.setRolloverIcon(MyIcon.getIcon(MaterialDesignH.HELP_CIRCLE, 34, Color.RED));
		btnHelp.setPreferredSize(new Dimension(50, 50));
		btnHelp.setToolTipText("Hiển thị file pdf hướng dẫn sử dụng phần mềm");
		btnHelp.setBackground(null);
		btnHelp.setBorder(null);
		MainPanelButton mainPanelButton_2 = new MainPanelButton(this.getWidth(), 20, "Quản lý nhân viên",
				MaterialDesignA.ACCOUNT, quanLyThongTinNhanVienPanel, panelContent);
		mainPanelButton_2.setToolTipText("(Phím tắt: F3)");
		hBoxPanelBtn.add(mainPanelButton_2);

		btnInfo.addActionListener(this);
		btnHelp.addActionListener(this);
		btnBack.addActionListener(this);
		btnNotifications.addActionListener(this);

		// Handle logout
		btnDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (logoutListener != null) {
					dispose();
					logoutListener.onLogout();
					Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_RIGHT,
							"Đăng xuất thành công");
				}
			}
		});
		rightPanel.setBackground(Color.YELLOW);

		getContentPane().add(rightPanel, BorderLayout.CENTER);
		rightPanel.setLayout(new BorderLayout(0, 0));
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblName.setText(new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN")).format(new Date()));
		panelTop.setBackground(Color.DARK_GRAY);

		rightPanel.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("alignx center, aligny center", "[][]", "[]"));

		panelTop.add(lblName, "push, cell 0 0, alignx left");

		btnNotifications.setFont(new Font("Dialog", Font.BOLD, 20));
		btnNotifications.setIcon(MyIcon.getIcon(MaterialDesignB.BELL_CIRCLE, 40, Color.WHITE));
		btnNotifications.setBackground(null);
		btnNotifications.setOpaque(false);
		btnNotifications.setBorder(null);
		panelTop.add(btnNotifications, "push, cell 1 0, alignx right");
		panelContent.setBackground(Color.ORANGE);
		rightPanel.add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new CardLayout(0, 0));

		quanLyThongTinNhanVienPanel.setName("quanLyThongTinNhanVienPanel");
		panelContent.add(quanLyThongTinNhanVienPanel, "quanLyThongTinNhanVienPanel");

		xemThongTinCaNhanPanel.setName("xemThongTinCaNhanPanel");
		panelContent.add(xemThongTinCaNhanPanel, "xemThongTinCaNhanPanel");

		quanLyPhongPanel.setName("quanLyPhongPanel");
		panelContent.add(quanLyPhongPanel, "quanLyPhongPanel");

		QuanLyHangHoaPanel.setName("quanLyHangHoaPanel");
		panelContent.add(QuanLyHangHoaPanel, "quanLyHangHoaPanel");

		ThongKePanel.setName("thongKePanel");
		panelContent.add(QuanLyHangHoaPanel, "thongKePanel");

		panelAppInfo.setName("panelAppInfo");
		panelContent.add(panelAppInfo, "panelAppInfo");

		panelAppInfo.add(lblNewLabel);

		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnSend.addActionListener(this);
		clock.startClock();
//		startWorkTimer();

		// Handle key presser
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEYCODE: " + e.getKeyCode());
				if (e.getKeyCode() == 112) {
					btnHelp.doClick();
				}

				if (e.getKeyCode() == 27) {
					btnBack.doClick();
				}
			}
		});

		// Handle menu switch

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
				btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, Color.white));
				btnBack.setRolloverIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 40, Color.RED));

				leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.25), leftPanel.getHeight()));

				isSidebarMinimized = false;
			} else {
				lblTime.setVisible(false);
				hBoxAvatar.setVisible(false);
				btnInfo.setVisible(false);
				btnHelp.setVisible(false);
				btnBack.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_RIGHT_CIRCLE, 40, Color.white));
				btnBack.setRolloverIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_RIGHT_CIRCLE, 40, Color.RED));

				leftPanel.setPreferredSize(new Dimension((int) (screenWidth * 0.04), leftPanel.getHeight()));

				isSidebarMinimized = true;

			}
		} else if (o.equals(btnHelp)) {
			try {
				File helpPDF = new File("src/main/resources/pdf/HUONG_DAN_SU_DUNG.pdf");
				Desktop.getDesktop().open(helpPDF);
			} catch (IOException ex) {
				// no application registered for PDFs
			}
		} else if (o.equals(btnInfo)) {
			CardLayout layout = (CardLayout) panelContent.getLayout();
			layout.show(panelContent, panelAppInfo.getName());
		} else if (o.equals(btnNotifications)) {
			GlassPanePopup.showPopup(new NotificationPanel(), new DefaultOption() {
				@Override
				public float opacity() {
					// TODO Auto-generated method stub
					return (float) 0.2;
				}

				@Override
				public LayoutCallback getLayoutCallBack(Component parent) {
					return new DefaultLayoutCallBack(parent) {
						@Override
						public void correctBounds(ComponentWrapper comp) {
							if (parent.isVisible()) {
								Point pl = parent.getLocationOnScreen();
								Point bl = btnNotifications.getLocationOnScreen();
								int x = bl.x - pl.x;
								int y = bl.y - pl.y;
								y += (1f - getAnimate()) * 10f;
								comp.setBounds(x - comp.getWidth() + btnNotifications.getWidth(),
										y + btnNotifications.getHeight(), comp.getWidth(), comp.getHeight());
							} else {
								super.correctBounds(comp);
							}
						}
					};
				}

				@Override
				public String getLayout(Component parent, float animate) {
					// TODO Auto-generated method stub
					return null;
				}
			});
		}
	}

	public void setLogoutListener(LogoutListener listener) {
		this.logoutListener = listener;
	}
}
