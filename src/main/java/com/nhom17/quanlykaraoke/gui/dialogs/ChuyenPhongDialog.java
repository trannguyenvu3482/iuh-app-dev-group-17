package com.nhom17.quanlykaraoke.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignC;

import com.nhom17.quanlykaraoke.bus.PhieuDatPhongBUS;
import com.nhom17.quanlykaraoke.bus.PhongBUS;
import com.nhom17.quanlykaraoke.common.MyIcon;
import com.nhom17.quanlykaraoke.entities.Phong;
import com.nhom17.quanlykaraoke.utils.MoneyFormatUtil;

import raven.toast.Notifications;
import raven.toast.Notifications.Location;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 13-Nov-2023 2:10:32 AM
 */
public class ChuyenPhongDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable tbl;
	private DefaultTableModel model;

	// COMPONENTS
	private final JLabel lblNewRoom = new JLabel("");
	private final JLabel lblCurrentRoom = new JLabel("Phòng hiện tại: ");

	// VARIABLES
	private Phong p;
	private final PhongBUS pBUS = new PhongBUS();
	private final PhieuDatPhongBUS pdpBUS = new PhieuDatPhongBUS();

	/**
	 * 
	 */
	public ChuyenPhongDialog(Phong p) {
		setSize(700, 500);
		setTitle("Chuyển phòng");
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		this.p = p;

		JPanel panelTop = new JPanel();
		panelTop.setBorder(new EmptyBorder(0, 10, 0, 0));
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(0);
		getContentPane().add(panelTop, BorderLayout.NORTH);

		lblCurrentRoom.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCurrentRoom.setText(lblCurrentRoom.getText().concat(p.getMaPhong()));
		panelTop.add(lblCurrentRoom);

		JLabel lblSwitchIcon = new JLabel("");
		lblSwitchIcon.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSwitchIcon.setIcon(MyIcon.getIcon(MaterialDesignC.CHEVRON_DOUBLE_RIGHT, 24, null));
		panelTop.add(lblSwitchIcon);

		lblNewRoom.setFont(new Font("Dialog", Font.BOLD, 20));
		panelTop.add(lblNewRoom);

		JScrollPane scrollPaneTable = new JScrollPane();
		getContentPane().add(scrollPaneTable, BorderLayout.CENTER);

		createTable();
		scrollPaneTable.setViewportView(tbl);

		// Handle table
		refreshTable(p);

		tbl.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				handleTblDoubleClick(mouseEvent);
			}
		});
	}

	/**
	 * 
	 */
	private void handleTblDoubleClick(MouseEvent mouseEvent) {
		// TODO Auto-generated method stub
		if (mouseEvent.getClickCount() == 1 && tbl.getSelectedRow() != -1) {
			lblNewRoom.setText(tbl.getModel().getValueAt(tbl.getSelectedRow(), 0).toString());
		}

		if (mouseEvent.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(null,
					"Bạn có muốn chuyển sang phòng " + tbl.getModel().getValueAt(tbl.getSelectedRow(), 0), "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if (pdpBUS.changeRoomForPhieuDatPhong(p.getMaPhong(),
						tbl.getModel().getValueAt(tbl.getSelectedRow(), 0).toString().trim())) {
					Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
							"Chuyển phòng thành công");
					dispose();
				} else {
					Notifications.getInstance().show(raven.toast.Notifications.Type.SUCCESS, Location.BOTTOM_RIGHT,
							"Chuyển phòng thất bại");
				}
			}
		}
	}

	private void createTable() {
		final String[] colNames = { "Mã phòng", "Loại phòng", "Số người", "Phụ phí" };
		model = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.setFont(new Font("Dialog", Font.PLAIN, 16));
		tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setAutoCreateRowSorter(true);
		tbl.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbl.setRowHeight(40);

	};

	private void refreshTable(Phong excludeRoom) {
		model.setRowCount(0);

		List<Phong> listPhong = pBUS.getAllEmptyPhongs();
		listPhong.remove(excludeRoom);

		listPhong.forEach((p) -> {
			Object[] rowData = { p.getMaPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getKichThuoc(),
					MoneyFormatUtil.format(p.getLoaiPhong().getPhuPhi()) };
			model.addRow(rowData);
		});
	}

}
