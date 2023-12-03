package com.nhom17.quanlykaraoke.common;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.kordamp.ikonli.Ikon;

public class MainPanelButton extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private JLabel buttonTitle;
	private JLabel buttonIcon;
	private Component horizontalStrut_1;
	private JPanel activatePanel;
	private JPanel panelContent;

	public MainPanelButton(int width, int height, String title, Ikon icon, JPanel activatePanel, JPanel panelContent) {
		this.activatePanel = activatePanel;
		this.panelContent = panelContent;
		setMinimumSize(new Dimension(10, 100));
		setMaximumSize(new Dimension(32767, 100));
		setSize(width, height);
		setPreferredSize(new Dimension(width, 100));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);

		this.buttonIcon = new JLabel();
		buttonIcon.setForeground(Color.BLACK);

		buttonIcon.setIcon(MyIcon.getIcon(icon, 50, null));
		add(buttonIcon);

		Component horizontalStrut = Box.createHorizontalStrut(60);
		add(horizontalStrut);

		this.buttonTitle = new JLabel(title);
		buttonTitle.setFont(new Font("Dialog", Font.BOLD, 26));
		buttonTitle.setForeground(Color.BLACK);
		add(buttonTitle);

		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		CardLayout layout = (CardLayout) panelContent.getLayout();
		layout.show(panelContent, activatePanel.getName());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(new Color(49, 98, 240));
		buttonTitle.setForeground(Color.WHITE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(Color.WHITE);
		buttonTitle.setForeground(Color.BLACK);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
