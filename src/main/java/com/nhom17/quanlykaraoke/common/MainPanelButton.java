package com.nhom17.quanlykaraoke.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kordamp.ikonli.Ikon;

public class MainPanelButton extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel buttonTitle;
	private JLabel buttonIcon;
	private Component horizontalStrut_1;

	public MainPanelButton(int width, int height, String title, Ikon icon, JPanel activatePanel) {
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);

		this.buttonIcon = new JLabel();
		buttonIcon.setForeground(Color.WHITE);

		buttonIcon.setIcon(MyIcon.getIcon(icon, MyIcon.DEFAULT_SIZE, Color.WHITE));
		add(buttonIcon);

		Component horizontalStrut = Box.createHorizontalStrut(60);
		add(horizontalStrut);

		this.buttonTitle = new JLabel(title);
		buttonTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		buttonTitle.setForeground(Color.WHITE);
		add(buttonTitle);
	}
}
