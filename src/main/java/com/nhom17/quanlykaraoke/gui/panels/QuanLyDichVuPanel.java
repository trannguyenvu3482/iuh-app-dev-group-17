/**
` * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created Nov 3, 2023 2:51:22 PM
 */
package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignM;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;

import com.nhom17.quanlykaraoke.bus.PhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.common.PhieuDatPhongPage;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.ConstantUtil;

import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

/**
 * Màn hình quản lý phiếu đặt phòng
 *
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 25-Oct-2023 16:39:00
 */
public class QuanLyDichVuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// COMPONENTS
	private final JTextField txtSearchMaPhong = new JTextField();
	private final JButton btnLeft = new JButton("");
	private final JButton btnRight = new JButton("");
	private final JButton btnAdd = new JButton("Thêm dịch vụ");
	private final JPanel roomsPanel = new JPanel();
	private final JPanel panel1 = new JPanel();
	private final JPanel panel2 = new JPanel();
	private final JPanel panel3 = new JPanel();
	private final JPanel panel4 = new JPanel();
	private final JPanel panel5 = new JPanel();
	private final JPanel panel6 = new JPanel();
	private final JPanel panel7 = new JPanel();
	private final JPanel panel8 = new JPanel();

	// VARIABLES
	private final PhongBUS pBUS = new PhongBUS();
	private int currentPage = 1;
	private int maxPageSize = -1;
	private List<Phong> listRooms = new ArrayList<Phong>();
	private List<Phong> listEmptyRooms = new ArrayList<Phong>();
	private List<Phong> listBookedRooms = new ArrayList<Phong>();

	private List<Phong> listFilteredRooms = null;
	private RoomPanel currentSelectedRoomPanel = null;
	private final List<JPanel> roomUIPanels = List.of(panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8);
	private String searchString = "";
	private Timer timer;

	/**
	 * Instantiates a new quan ly phieu dat phong panel.
	 */
	public QuanLyDichVuPanel() {
		// Create the UI
		initUI();

		// Initiate the rooms
		refreshRoomList();

		// Load first page
		this.currentPage = 1;
		this.maxPageSize = (int) Math.ceil((double) listBookedRooms.size() / ConstantUtil.MAXIMUM_PAGE_SIZE);
		loadPageRoom(listBookedRooms, currentPage);

	}

	/**
	 * Handle show pagination buttons.
	 * 
	 * @author trannguyenvu3482
	 * @param page The page that is paginated to
	 */
	private void handleShowPaginationButtons(int page) {
		if (maxPageSize == 1) {
			btnLeft.setVisible(false);
			btnRight.setVisible(false);
		} else if (page == 1) {
			btnLeft.setVisible(false);
			btnRight.setVisible(true);
		} else if (page == maxPageSize) {
			btnLeft.setVisible(true);
			btnRight.setVisible(false);
		} else {
			btnLeft.setVisible(true);
			btnRight.setVisible(true);
		}
	}

	/**
	 * Load the list of rooms onto the panels.
	 *
	 * @author trannguyenvu3482
	 * @param currentPageRooms the current list of rooms
	 */
	private void loadRoomPanels(List<Phong> currentPageRooms) {
		// TODO Auto-generated method stub

		// Disable all panels
		roomUIPanels.forEach(panel -> {
			panel.setVisible(false);
		});

		// Handle empty room panels
		if (currentPageRooms == null) {
			Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_RIGHT,
					"Không tìm thấy phòng cần tìm!");
			return;
		}

		// Add RoomPanel inside each panel
		currentPageRooms.forEach((room) -> {

			int index = currentPageRooms.indexOf(room);

			JPanel panel = roomUIPanels.get(index);

			panel.removeAll();

			RoomPanel roomPanel = new RoomPanel(room);

			roomPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					handleRoomClick(roomPanel);
				}
			});

			panel.add(roomPanel);
			panel.setVisible(true);

		});
	}

	/**
	 * Handle pagination and loading of rooms.
	 *
	 * @author trannguyenvu3482
	 * @param listRooms The current list of rooms
	 * @param page      The page that the user wants to go to
	 */
	private void loadPageRoom(List<Phong> listRooms, int page) {
		System.out.println("LOAD PAGE ROOM");

		// Invlid page check
		if (page < 1 || page > maxPageSize) {
			return;
		}

		// Calculate page size
		this.maxPageSize = (int) Math.ceil((double) listRooms.size() / ConstantUtil.MAXIMUM_PAGE_SIZE);

		// Get pages
		PhieuDatPhongPage<Phong> currentPage = new PhieuDatPhongPage<Phong>(page, listRooms);

		// Handle left, right btns
		handleShowPaginationButtons(currentPage.getPageNumber());

		// Load all room panels
		loadRoomPanels(currentPage.getItems());
	}

	/**
	 * Handle room click.
	 * 
	 * @author trannguyenvu3482
	 * @param clickedPanel the clicked panel
	 */
	private void handleRoomClick(RoomPanel clickedPanel) {
		// Deselect all
		roomUIPanels.forEach(panel -> {
			if (panel.getComponentCount() == 1) {
				((RoomPanel) panel.getComponent(0)).deselect();
			}
		});

		// Select clicked
		clickedPanel.select();
		currentSelectedRoomPanel = clickedPanel;
	}

	/**
	 * Handle filter rooms.
	 * 
	 * @author trannguyenvu3482
	 * @param newRoomList the new room list
	 */
	private void handleFilterRooms(List<Phong> newRoomList) {
		this.listFilteredRooms = newRoomList;
		this.maxPageSize = (int) Math.ceil((double) listFilteredRooms.size() / ConstantUtil.MAXIMUM_PAGE_SIZE);
		this.currentPage = 1;
		loadPageRoom(newRoomList, currentPage);
	}

	/**
	 * Action performed.
	 * 
	 * @author trannguyenvu3482
	 * @param e the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnLeft)) {
			currentPage--;
			loadPageRoom(listEmptyRooms, currentPage);
		} else if (o.equals(btnRight)) {
			currentPage++;
			loadPageRoom(listEmptyRooms, currentPage);
		} else if (o.equals(btnAdd)) {
			currentSelectedRoomPanel.addDichVu(() -> {
				currentPage++;
				loadPageRoom(listEmptyRooms, currentPage);
			});
		}
	}

	/**
	 * 
	 */

	/**
	 * Handle search function
	 * 
	 * @author trannguyenvu3482
	 */
	private void handleSearch() {
		listFilteredRooms = listFilteredRooms.stream().filter(room -> {
			return room.getMaPhong().contains(searchString);
		}).collect(Collectors.toList());

		handleFilterRooms(listFilteredRooms);

		if (listFilteredRooms.size() > 0) {
			this.currentPage = 1;
			loadPageRoom(listFilteredRooms, currentPage);
		} else {
			this.currentPage = 1;
			loadPageRoom(listBookedRooms, currentPage);
		}
	}

	/**
	 * 
	 */
	private void refreshRoomList() {
		// TODO Auto-generated method stub
		this.listRooms = pBUS.getAllPhongs();
		this.listEmptyRooms = pBUS.getAllEmptyPhongs();

		// Handle empty rooms
		listBookedRooms.clear();
		listRooms.forEach(room -> {
			if (!listEmptyRooms.contains(room)) {
				listBookedRooms.add(room);
			}
		});

		System.out.println("Tổng số phòng: " + listRooms.size());
		System.out.println("Số phòng trống: " + listEmptyRooms.size());
		if (listBookedRooms != null) {
			System.out.println("Số phòng được đặt: " + listBookedRooms.size());
		}
	}

	/**
	 * 
	 */
	private void performSearch() {
		SwingUtilities.invokeLater(() -> {
			// TODO Auto-generated method stub
			searchString = txtSearchMaPhong.getText().trim();
			if (searchString.equals("")) {
				handleFilterRooms(listBookedRooms);
				return;
			} else {
				listFilteredRooms = listBookedRooms;
				handleSearch();
			}
		});
	}

	/**
	 * Initiate the UI
	 * 
	 * @author trannguyenvu3482
	 */
	private void initUI() {
		setSize(1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("center", "[][][]", "[]"));

		JPanel panel = new JPanel();
		panel.setBackground(null);
		panelTop.add(panel, "cell 2 0,push ,alignx right,aligny center");

		panel.add(txtSearchMaPhong);
		txtSearchMaPhong.setFont(new Font("Dialog", Font.PLAIN, 20));
		txtSearchMaPhong.setForeground(Color.LIGHT_GRAY);
		txtSearchMaPhong.setText("Nhập vào mã phòng cần tìm...");
		txtSearchMaPhong.setColumns(20);
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.GRAY);
		add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] { 60, 250, 60 };
		gbl_panelCenter.rowHeights = new int[] { 200, 100, 30 };
		gbl_panelCenter.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gbl_panelCenter.rowWeights = new double[] { 1.0 };
		panelCenter.setLayout(gbl_panelCenter);
		btnLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Add left button
		btnLeft.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_LEFT_CIRCLE, 46, null));
		btnLeft.setBackground(null);
		btnLeft.setOpaque(false);
		btnLeft.setBorder(null);
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.fill = GridBagConstraints.BOTH;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 0;
		gbc_btnLeft.gridy = 0;
		panelCenter.add(btnLeft, gbc_btnLeft);

		roomsPanel.setBackground(Color.GRAY);
		GridBagConstraints gbc_roomsPanel = new GridBagConstraints();
		gbc_roomsPanel.fill = GridBagConstraints.BOTH;
		gbc_roomsPanel.insets = new Insets(50, 0, 20, 0);
		gbc_roomsPanel.gridx = 1;
		gbc_roomsPanel.gridy = 0;
		panelCenter.add(roomsPanel, gbc_roomsPanel);
		GridBagLayout gbl_roomsPanel = new GridBagLayout();
		gbl_roomsPanel.columnWidths = new int[] { 250, 250, 250, 250, 0 };
		gbl_roomsPanel.rowHeights = new int[] { 200, 200, 0 };
		gbl_roomsPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_roomsPanel.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		roomsPanel.setLayout(gbl_roomsPanel);

		panel1.setBackground(null);
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.insets = new Insets(0, 50, 50, 50);
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 0;
		roomsPanel.add(panel1, gbc_panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		panel2.setBackground(null);
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.insets = new Insets(0, 0, 50, 50);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 1;
		gbc_panel2.gridy = 0;
		roomsPanel.add(panel2, gbc_panel2);

		panel3.setBackground(null);
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.insets = new Insets(0, 0, 50, 50);
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 2;
		gbc_panel3.gridy = 0;
		roomsPanel.add(panel3, gbc_panel3);

		panel4.setBackground(null);
		GridBagConstraints gbc_panel4 = new GridBagConstraints();
		gbc_panel4.insets = new Insets(0, 0, 50, 50);
		gbc_panel4.fill = GridBagConstraints.BOTH;
		gbc_panel4.gridx = 3;
		gbc_panel4.gridy = 0;
		roomsPanel.add(panel4, gbc_panel4);

		panel5.setBackground(null);
		GridBagConstraints gbc_panel5 = new GridBagConstraints();
		gbc_panel5.insets = new Insets(0, 50, 0, 50);
		gbc_panel5.fill = GridBagConstraints.BOTH;
		gbc_panel5.gridx = 0;
		gbc_panel5.gridy = 1;
		roomsPanel.add(panel5, gbc_panel5);

		panel6.setBackground(null);
		GridBagConstraints gbc_panel6 = new GridBagConstraints();
		gbc_panel6.insets = new Insets(0, 0, 0, 50);
		gbc_panel6.fill = GridBagConstraints.BOTH;
		gbc_panel6.gridx = 1;
		gbc_panel6.gridy = 1;
		roomsPanel.add(panel6, gbc_panel6);

		panel7.setBackground(null);
		GridBagConstraints gbc_panel7 = new GridBagConstraints();
		gbc_panel7.insets = new Insets(0, 0, 0, 50);
		gbc_panel7.fill = GridBagConstraints.BOTH;
		gbc_panel7.gridx = 2;
		gbc_panel7.gridy = 1;
		roomsPanel.add(panel7, gbc_panel7);

		panel8.setBackground(null);
		GridBagConstraints gbc_panel8 = new GridBagConstraints();
		gbc_panel8.insets = new Insets(0, 0, 0, 50);
		gbc_panel8.fill = GridBagConstraints.BOTH;
		gbc_panel8.gridx = 3;
		gbc_panel8.gridy = 1;
		roomsPanel.add(panel8, gbc_panel8);
		btnRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRight.addActionListener(this);

		// TODO: DELETE THIS WHEN DONE
		panel2.setLayout(new BorderLayout(0, 0));
		panel3.setLayout(new BorderLayout(0, 0));
		panel4.setLayout(new BorderLayout(0, 0));
		panel5.setLayout(new BorderLayout(0, 0));
		panel6.setLayout(new BorderLayout(0, 0));
		panel7.setLayout(new BorderLayout(0, 0));
		panel8.setLayout(new BorderLayout(0, 0));

		// Add right button
		btnRight.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_RIGHT_CIRCLE, 46, null));
		btnRight.setBackground(null);
		btnRight.setOpaque(false);
		btnRight.setBorder(null);
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.fill = GridBagConstraints.BOTH;
		gbc_btnRight.insets = new Insets(0, 0, 5, 0);
		gbc_btnRight.gridx = 2;
		gbc_btnRight.gridy = 0;
		panelCenter.add(btnRight, gbc_btnRight);

		JPanel panelControls = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelControls.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(25);
		panelControls.setBackground(null);
		GridBagConstraints gbc_panelControls = new GridBagConstraints();
		gbc_panelControls.insets = new Insets(0, 0, 5, 5);
		gbc_panelControls.fill = GridBagConstraints.BOTH;
		gbc_panelControls.gridy = 1;
		gbc_panelControls.gridx = 1;
		panelCenter.add(panelControls, gbc_panelControls);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.RED);
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 20));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnAdd.setIcon(MyIcon.getIcon(MaterialDesignP.PLUS_THICK, 40, null));
		panelControls.add(btnAdd);

		JPanel panelBottom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBottom.getLayout();
		flowLayout.setHgap(60);
		add(panelBottom, BorderLayout.SOUTH);

		JLabel lblChuaDat = new JLabel("Phòng chưa có người đặt: 15");
		lblChuaDat.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT, 28, null));
		lblChuaDat.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblChuaDat);

		JLabel lblDaDat = new JLabel("Phòng đã có người đặt: 8");
		lblDaDat.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT_OFF, 28, null));
		lblDaDat.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblDaDat);

		JLabel lblPhongKhongHD = new JLabel("Phòng không hoạt động: 0");
		lblPhongKhongHD.setIcon(MyIcon.getIcon(MaterialDesignC.CANCEL, 28, null));
		lblPhongKhongHD.setFont(new Font("Dialog", Font.BOLD, 24));
		panelBottom.add(lblPhongKhongHD);

		// Button listeners
		btnAdd.addActionListener(this);
		btnLeft.addActionListener(this);

		// txtSearch handler
		txtSearchMaPhong.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtSearchMaPhong.getText().equals("Nhập vào mã phòng cần tìm...")) {
					txtSearchMaPhong.setText("");
					txtSearchMaPhong.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtSearchMaPhong.getText().isEmpty()) {
					txtSearchMaPhong.setForeground(Color.GRAY);
					txtSearchMaPhong.setText("Nhập vào mã phòng cần tìm...");
				}

			}
		});

		txtSearchMaPhong.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (timer != null) {
					timer.stop();
				}

				timer = new Timer(1000, event -> {
					performSearch();
				});

				timer.setRepeats(false);
				timer.start();
			}

		});

	}
}
