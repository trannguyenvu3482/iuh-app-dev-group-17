package com.nhom17.quanlykaraoke.gui.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.kordamp.ikonli.materialdesign2.MaterialDesignH;
import org.kordamp.ikonli.materialdesign2.MaterialDesignN;

import com.nhom17.quanlykaraoke.common.MyIcon;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 07-Nov-2023 1:13:37 PM
 */
public class QuanLyPhongPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableLoaiPhong;
	private JTextField textField;
	private JTextField txtTmTheoM;
	private DefaultTableModel modelLoaiPhong = null;

	/**
	 * 
	 */
	public QuanLyPhongPanel() {
		setSize(1439, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(null);

		createTable();
		refreshTable();
		JScrollPane scrollPane = new JScrollPane(tableLoaiPhong);
		scrollPane.setBounds(0, 134, 1439, 666);
		panel_1.add(scrollPane);
		scrollPane.setViewportView(tableLoaiPhong);


		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1439, 110);
		panel_1.add(panel);
		panel.setBackground(Color.RED);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Trạng thái:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(447, 60, 107, 30);
		panel.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Hoạt động", "Không hoạt động" }));
		comboBox.setBounds(564, 60, 177, 30);
		panel.add(comboBox);

		JLabel lblKchThc = new JLabel("Kích thước: ");
		lblKchThc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKchThc.setBounds(447, 20, 141, 30);
		panel.add(lblKchThc);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "5 người", "10 người", "15 người ", "20 người" }));
		comboBox_1.setBounds(564, 20, 177, 30);
		panel.add(comboBox_1);

		JLabel lblPhPh = new JLabel("Phụ Phí:");
		lblPhPh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhPh.setBounds(10, 60, 96, 30);
		panel.add(lblPhPh);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "0 vnd(Phòng thường)", "200,000 VND (Phòng tiệc)",
				"400,000 VND (Phòng VIP)", "1,000,000 VND (Phòng tiệc + VIP)" }));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setBounds(102, 60, 319, 30);
		panel.add(comboBox_2);

		JLabel lblThngTinPhng = new JLabel("Thông tin phòng");
		lblThngTinPhng.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblThngTinPhng.setBounds(10, 10, 268, 40);
		panel.add(lblThngTinPhng);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(260, 18, 160, 30);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(MyIcon.getIcon(MaterialDesignH.HOME_PLUS, 24, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(763, 10, 40, 40);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(MyIcon.getIcon(MaterialDesignH.HOME_EDIT, 24, null));

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(763, 60, 40, 40);
		panel.add(btnNewButton_1);

		txtTmTheoM = new JTextField();
		txtTmTheoM.setEditable(false);
		txtTmTheoM.setColumns(10);
		txtTmTheoM.setBounds(830, 20, 130, 30);
		panel.add(txtTmTheoM);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(
				new DefaultComboBoxModel(new String[] { "Kích thước", "5 người", "10 người", "15 người", "20 người" }));
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3.setBounds(830, 60, 130, 30);
		panel.add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(
				new String[] { "Phòng theo phụ phí", "Phòng thường", "Phòng tiệc", "Phòng VIP", "Phòng tiệc + VIP" }));
		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_4.setBounds(990, 60, 200, 30);
		panel.add(comboBox_4);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] { "Trạng thái", "Hoạt động", "Không hoạt động" }));
		comboBox_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_5.setBounds(990, 20, 200, 30);
		panel.add(comboBox_5);


	}

	private void createTable() {
		final String[] colNames = { "Mã Phòng", "Kích thước", "Phụ phí", "Loại phòng theo phụ phí", "Trạng thái" };
		modelLoaiPhong = new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableLoaiPhong = new JTable(modelLoaiPhong);
		tableLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}

	private void refreshTable() {
		modelLoaiPhong.setRowCount(0);

		Object[] testRow = { "P001", "5 người", "0 VND", "Phòng thường", "Hoạt động" };

		modelLoaiPhong.addRow(testRow);
	}

}
